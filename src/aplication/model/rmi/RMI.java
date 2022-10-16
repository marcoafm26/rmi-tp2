package aplication.model.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
    int OddOrEvenSum() throws RemoteException;

    boolean verifyTeam(String time) throws RemoteException;

    void addClient(String time, int number) throws RemoteException;

    boolean verifyPlayers() throws RemoteException;

    void clearClients() throws RemoteException;
}
