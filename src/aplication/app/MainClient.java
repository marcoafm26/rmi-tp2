package aplication.app;

import aplication.model.rmi.RMIClient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.rmi.RemoteException;
import java.util.Scanner;
public class MainClient {

        public static void init() throws InterruptedIOException, RemoteException {
            Scanner ler = new Scanner(System.in);
            System.out.println("Iniciando o RMI: ");
            RMIClient client = new RMIClient();
            client.startConection();

            // Chama metodo responsavel por definir os times
            String choseTeam = client.choseTeam();

            // escolha do numero
            int number = client.readNumber();

            // cadastrando a jogada e em cascata e realizado a verificação para saber
            // se o time inicial e o mesmo do final
            choseTeam = client.makePlay(choseTeam,number);

            // verificacao do resultado final
                client.verifyWin(choseTeam);



        }


        public static void main (String[]args) throws InterruptedIOException {
            try {
                init();
            }catch (InterruptedIOException | RemoteException e){
                e.printStackTrace();
            }

        }
    }

