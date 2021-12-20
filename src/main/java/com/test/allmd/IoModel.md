#I/O多路复用
select 基于轮训机制

epoll基于操作系统支持的I/O通知机制 epoll支持水平触发和边沿触发两种模式。


##select
int select (int n, fd_set *readfds, fd_set *writefds, fd_set *exceptfds, struct timeval *timeout);

在Linux中，内核利用文件描述符（File Descriptor）即文件句柄，来访问文件。文件描述符是非负整数。

fd_set可以理解为一个集合，这个集合中存放的是文件描述符(file descriptor)，即文件句柄，它用'一位'来表示一个fd

select本质上是通过设置或检查存放fd标志位的数据结构进行下一步处理。

单个进程可监视的fd数量被限制，即能监听端口的数量有限.32位机默认1024个，64位默认2048。

对socket是线性扫描，即轮询，效率较低： 仅知道有I/O事件发生，却不知是哪几个流，只会无差异轮询所有流，找出能读数据或写数据的流进行操作。同时处理的流越多，无差别轮询时间越长 - O(n)。

当socket较多时，每次select都要通过遍历FD_SETSIZE个socket，不管是否活跃，这会浪费很多CPU时间。如果能给 socket 注册某个回调函数，当他们活跃时，自动完成相关操作，即可避免轮询，这就是epoll与kqueue。

##poll
int poll(struct pollfd *fds,nfds_t nfds, int timeout);

和select类似，只是描述fd集合的方式不同，poll使用pollfd结构而非select的fd_set结构。 管理多个描述符也是进行轮询，根据描述符的状态进行处理，但poll没有最大文件描述符数量的限制。

Poll机制会判断fds中的文件是否可读，如果可读则会立即返回，返回的值就是可读fd的数量，如果不可读，那么就进程就会休眠timeout这么长的时间，然后再来判断是否有文件可读，如果有，返回fd的数量，如果没有，则返回0.

##epoll
int sys_epoll_ctl(int epfd, int op, int fd, struct epoll_event *event)

epoll模型修改主动轮询为被动通知，当有事件发生时，被动接收通知。所以epoll模型注册套接字后，主程序可做其他事情，当事件发生时，接收到通知后再去处理。

可理解为event poll，epoll会把哪个流发生哪种I/O事件通知我们。所以epoll是事件驱动（每个事件关联fd），此时我们对这些流的操作都是有意义的。复杂度也降到O(1)。

###epoll触发模式

EPOLLLT和EPOLLET两种：

LT，默认的模式（水平触发） 只要该fd还有数据可读，每次 epoll_wait 都会返回它的事件，提醒用户程序去操作，

ET是“高速”模式（边缘触发）  只会提示一次，直到下次再有数据流入之前都不会再提示，无论fd中是否还有数据可读。所以在ET模式下，read一个fd的时候一定要把它的buffer读完，即读到read返回值小于请求值或遇到EAGAIN错误

没有最大并发连接的限制，能打开的FD的上限远大于1024（1G的内存上能监听约10万个端口）

效率提升，不是轮询，不会随着FD数目的增加效率下降。只有活跃可用的FD才会调用callback函数 即Epoll最大的优点就在于它只关心“活跃”的连接，而跟连接总数无关，因此在实际的网络环境中，Epoll的效率就会远远高于select和poll

内存拷贝，利用mmap()文件映射内存加速与内核空间的消息传递；即epoll使用mmap减少复制开销。

epoll通过内核和用户空间共享一块内存来实现的


https://blog.csdn.net/y277an/article/details/97622206