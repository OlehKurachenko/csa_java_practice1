package rmi1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntegralRemote extends Remote {
    double function(double x) throws RemoteException;
    double simpsonIntegral(double a, double b, double e) throws RemoteException;
}
