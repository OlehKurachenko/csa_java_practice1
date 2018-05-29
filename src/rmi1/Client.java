package rmi1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {}
    public static void main(String[] args) {
        int host = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            IntegralRemote stub = (IntegralRemote) registry.lookup("IntegralRemote");
            System.out.println("integral of sqr on 0..1: " +  stub.simpsonIntegral(0, 1, 0.001));
            System.out.println("integral of sqr on 0..2: " +  stub.simpsonIntegral(0, 2, 0.001));
            System.out.println("integral of sqr on -1..23: " +  stub.simpsonIntegral(-1, 23, 0.001));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
