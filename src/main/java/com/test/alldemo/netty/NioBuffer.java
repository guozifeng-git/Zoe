package com.test.alldemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioBuffer {
    public void buffer() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        // 创建容量为48字节的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 读进buffer 从该Channel中将字节序列读取到给定buffer
        int bytesRead = inChannel.read(buf);
        // 什么时候会读到-1呢？
        // 对于服务器端，当客户端调用了channel.close()关闭连接时，这时服务器端返回的读取数是-1，表示已到末尾
        // 那么此时需要把对应SelectionKey给cancel掉，表示selector不再监听这个channel上的读事件，并关闭channel
        while (bytesRead != -1) {
            // make buffer ready for read
            buf.flip();
            while (buf.hasRemaining()) {
                // read 1 byte at a time
                // 在从channel往buffer中读入后，使用byteBuffer.get()获取时，不可重复调用，因为get()会移动position
                // 使得多次调用get()获取的内容是不同的
                System.out.print((char) buf.get());
            }
            // make buffer ready for writing
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
