package aplication.model.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class RMIClient {

    final String nameRMI = "rmi://localhost:6430/OddOrEven";
    RMI stub;
    public void startConection(){
        try{
            stub = (RMI) Naming.lookup(nameRMI);
        }catch (RemoteException | MalformedURLException | NotBoundException e){
            e.printStackTrace();
        }
    }

    public String makePlay(String team, int number){
        String finalTeam="";
        try{
            boolean verifyTeam = stub.verifyTeam(team);

            if ( verifyTeam ){
                stub.addClient(team,number);
                finalTeam = confirmMessages(team,team);
                return finalTeam;
            } else {

                if (team.equals("Par")){
                    finalTeam = confirmMessages(team,"Impar");
                    stub.addClient(finalTeam,number);
                    return finalTeam;
                }else{
                    finalTeam = confirmMessages(team,"Par");
                    stub.addClient(finalTeam,number);
                    return finalTeam;

                }
            }
        }catch (RemoteException e){
            System.out.println(e.getMessage());
        }
        return finalTeam;
    }

    public String confirmMessages(String teamChose, String finalTeam) {
        if (teamChose.equals(finalTeam)) {
            if (finalTeam.equals("Par")) {
                System.out.println("Par escolhido com sucesso.");
            } else {
                System.out.println("Impar escolhido com sucesso.");
            }
        } else {
            teamChose = finalTeam;
            if (teamChose.equals("Par")) {
                System.out.println("Voce e do time Par. O Impar ja tinha sido escolhido.");
            } else {
                System.out.println("Voce e do time Impar. O Par ja tinha sido escolhido.");
            }
        }
        return teamChose;
    }

    public int readNumber() {
        int numLocal;
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite um numero positivo de sua escolha:");

        // leitura e tratamento do número inserido pelo cliente
        // enquanto o número digitado for invalido ele pede a insersao de um proximo
        String number = ler.next();
        while (Integer.parseInt(number) < 0) {
            System.out.println("Insira um numero valido!");
            number = ler.next();
        }
        numLocal = Integer.parseInt(number);
        return numLocal;
    }

    public String choseTeam() {
        Scanner ler = new Scanner(System.in);
        String chose;
        System.out.println("Escolha um time: ");
        System.out.println("0 - Par");
        System.out.println("1 - Impar");
        String number = ler.next();

        int numberInt = Integer.parseInt(number);
        while (numberInt != 0 && numberInt != 1) {
            System.out.println("Escolha um time valido!");
            number = ler.next();
            numberInt = Integer.parseInt(number);
        }
        if (numberInt == 0) {
            System.out.println("Voce escolheu o time Par.");
            chose = "Par";
        } else {
            System.out.println("Voce escolheu o time Impar.");
            chose = "Impar";
        }
        return chose;
    }

    public void verifyWin(String team) throws RemoteException {

        while(!stub.verifyPlayers());
        if(stub.verifyPlayers()){
            int sum = stub.OddOrEvenSum();
            int mod = sum % 2;
            System.out.println("A soma dos valores foi " + sum);
            if ((mod == 0 && team.equals("Par")) || (mod != 0 && team.equals("Impar"))) {
                System.out.println("Parabens voce ganhou!");
            } else {
                System.out.println("Infelizmente voce perdeu!");
            }
            stub.clearClients();
        }
    }
}
