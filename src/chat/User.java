package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class User {
    private static final String ip_adress = "127.0.0.1";
    private static final int USER_1_PORT = 6601;
    private static final int USER_2_PORT = 6602;

    public static void main(String[] args) {
        try (
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String user = "";
            while (!(user.equals("1") || user.equals("2"))) {
                System.out.print("Number of user (1 or 2)>");
                user = keyboard.readLine();
            }
            int user_port = (user.equals("1")) ? USER_1_PORT : USER_2_PORT;
            try (
                    Socket socket = new Socket(InetAddress.getByName(ip_adress), user_port);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ) {
                if (user.equals("1")) {
                    System.out.println(in.readUTF());
                    System.out.println(in.readUTF());
                } else {
                    System.out.println(in.readUTF());
                    System.out.println("Waiting for User 1 typing...");
                    System.out.println(in.readUTF());
                }
                String line = null;
                //noinspection InfiniteLoopStatement
                while (true) {
                    System.out.print(">");
                    line = keyboard.readLine();
                    out.writeUTF(line);
                    out.flush();
                    System.out.println("Waiting for User " + ((user.equals("1")) ? '2' : '1') + " typing...");
                    System.out.println(in.readUTF());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
