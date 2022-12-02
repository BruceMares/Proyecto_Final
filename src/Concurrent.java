import java.net.*;
import java.io.*;
import java.util.Date;
import java.net.ServerSocket;
import java.net.Socket;
public class Concurrent {
    private ServerSocket serverSocket;


    public Concurrent(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(args[0]);
        Concurrent server = new Concurrent(serverSocket);



        try {
            System.out.println("Esperando a cliente...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Bienvenido");
                Client Client = new Client(socket);
                Thread logs = new Thread(Client);
                logs.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
