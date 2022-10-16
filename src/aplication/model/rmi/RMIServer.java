package aplication.model.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer{

    final int serverPort = 6430;
    final String nameRMI = "rmi://localhost:6430/OddOrEven";

    public String startConection(){
        try{
            Registry registry = LocateRegistry.createRegistry(serverPort);
            Naming.rebind(nameRMI, new RMI_Implementation());
        }catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
        return "Servidor iniciado com sucesso!";
    }

}
