package rmi1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IntegralRemote {

    @Override
    public double function(double x) {
        return x * x;
    }

    @Override
    public double simpsonIntegral(double a, double b, double e) throws RemoteException {
        int n = 2;
        double dist = (b - a) * 0.5;
        double g_side = function(a) + function(b);
        double g_even = (4 * function(a + dist));
        double g_odd = 0;
        double res = g_side + g_even;
        double prev_res = 0;

        do {
            prev_res = res;
            g_odd = g_even * 0.5 + g_odd;
            g_even = 0;
            for (int i = 0; i < n; i++) {
                g_even += 4 * function(a + (0.5 + i) * dist);
            }
            n *= 2;
            dist *= 0.5;
            res = dist * (g_side + g_even + g_odd);
        } while (Math.abs(res - prev_res) / 3 > e);
        return (res / 3);
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            IntegralRemote stub = (IntegralRemote)UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("IntegralRemote", stub);

            System.out.println("Server is ready!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
