package nia.chapter1.exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BlockingIoClient {

    private Socket socket;

    public BlockingIoClient(String serverAddress, int serverPort) {
        try {
            this.socket = new Socket(serverAddress, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = "hello";
            out.println(line);
            System.out.println("服务端返回：" + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
 
    public static void main(String[] args) {
        for (int i = 0 ; i < 10; i++) {
            final BlockingIoClient client = new BlockingIoClient("127.0.0.1", 10086);
            client.send();
        }
    }
}
