package nia.chapter1.exp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingIoServer {

    private ServerSocket serverSocket;

    public BlockingIoServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void serve() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ServerHandler(clientSocket)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 10086;
        BlockingIoServer server = new BlockingIoServer(port);

        try {
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
