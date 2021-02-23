package org.example.yugong.IO.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qiaobao
 * @since 2021-02-22
 */
public class SocketServer2 {




    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(83);

        try {
            while(true) {
                Socket socket = serverSocket.accept();
                //当然业务处理过程可以交给一个线程（这里可以使用线程池）,并且线程的创建是很耗资源的。
                //最终改变不了.accept()只能一个一个接受socket的情况,并且被阻塞的情况
                SocketServerThread socketServerThread = new SocketServerThread(socket);
                new Thread(socketServerThread).start();
            }
        } catch(Exception e) {
            System.out.println("error"+e.getMessage());
        } finally {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
