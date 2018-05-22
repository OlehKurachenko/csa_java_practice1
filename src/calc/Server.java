package calc;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private static final int PORT = 6601;

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket socket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ) {
            System.out.println("connected...");
            Calculable calculable = (Calculable)in.readObject();
            out.writeDouble(calculable.calc());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
