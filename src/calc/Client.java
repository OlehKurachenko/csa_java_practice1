package calc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final int PORT = 6601;

    public static void main(String[] args) {
        PiNumber piNumber = new PiNumber();
        try (
                Socket socket = new Socket("localHost", PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
        ) {
            out.writeObject(piNumber);
            double res = in.readDouble();
            System.out.println("Stored vs. Recieved: " + piNumber.calc() + " vs " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
