#I/O多路复用
select 基于轮训机制

epoll基于操作系统支持的I/O通知机制 epoll支持水平触发和边沿触发两种模式。


##select
int select (int n, fd_set *readfds, fd_set *writefds, fd_set *exceptfds, struct timeval *timeout);

在Linux中，内核利用文件描述符（File Descriptor）即文件句柄，来访问文件。文件描述符是非负整数。

fd_set可以理解为一个集合，这个集合中存放的是文件描述符(file descriptor)，即文件句柄，它用'一位'来表示一个fd

select本质上是通过设置或检查存放fd标志位的数据结构进行下一步处理。

单个进程可监视的fd数量被限制，即能监听端口的数量有限.32位机默认1024个，64位默认2048。
