package chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int USER_1_PORT = 6601;
    private static final int USER_2_PORT = 6602;

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket1 = new ServerSocket(Server.USER_1_PORT);
                Socket socket1 = serverSocket1.accept();
                DataInputStream in1 = new DataInputStream(socket1.getInputStream());
                DataOutputStream out1 = new DataOutputStream(socket1.getOutputStream());
        ) {
            System.out.println("log: User 1 connected.");
            out1.writeUTF("Hello, User 1. Waiting for User 2...");
            out1.flush();
            try (
                    ServerSocket serverSocket2 = new ServerSocket(Server.USER_2_PORT);
                    Socket socket2 = serverSocket2.accept();
                    DataInputStream in2 = new DataInputStream(socket2.getInputStream());
                    DataOutputStream out2 = new DataOutputStream(socket2.getOutputStream());
            ) {
                System.out.println("log: User 2 connected.");
                out1.writeUTF("User 2 online.");
                out1.flush();
                out2.writeUTF("Hello, User 2. User 1 online.");
                out2.flush();
                String line = null;
                //noinspection InfiniteLoopStatement
                while (true) {
                    line = in1.readUTF();
                    System.out.println("log: User 1: " + line);
                    out2.writeUTF(line);
                    out2.flush();
                    line = in2.readUTF();
                    System.out.println("log: User 2: " + line);
                    out1.writeUTF(line);
                    out1.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
