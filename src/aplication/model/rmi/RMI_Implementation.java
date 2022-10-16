package aplication.model.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class RMI_Implementation extends UnicastRemoteObject implements RMI {
    private static final long serialVersionUID = 1L;
    List<Client> clients;

    private class Client{
        String team;
        int number;

        private Client(String team, int number) {
        this.team = team;
        this.number = number;
    }}

    protected RMI_Implementation() throws RemoteException {
        super();
        clients = new LinkedList<>();
    }

    @Override
    public int OddOrEvenSum() throws RemoteException {
        int sum = 0;
        for (Client client: clients) {
            sum += client.number;
        }
        return sum;
    }

    @Override
    public boolean verifyTeam(String time) throws RemoteException{
        for (Client client: clients) {
            if (client.team.equals(time)){
                return false;
            }
        }
        return true;
    }
    @Override
    public void addClient(String time, int number){
        if ( time != null && number >= 0){
            clients.add(new Client(time,number));
            System.out.println("Cliente "+clients.size() + " conectado com sucesso.");
        }
        if (clients.size() == 2){
            System.out.println("Todos os clientes estao conectados.");
        }
    }

    @Override
    public boolean verifyPlayers() throws RemoteException {
        if (clients.size() == 2 ){
            return true;
        }
        return false;
    }

    @Override
    public void clearClients() throws RemoteException {
        clients.clear();
    }
}
