package aplication.app;

import aplication.model.rmi.RMIServer;

import java.io.IOException;

public class MainServer {

    public static void init() throws IOException {
        RMIServer server = new RMIServer();
        System.out.println(server.startConection());;
    }

    public static void main(String[] args) throws IOException {
        init();
    }
}
