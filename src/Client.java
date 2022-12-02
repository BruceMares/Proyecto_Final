import java.util.ArrayList;
import java.util.Scanner;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
public class Client implements Runnable{

        private Socket socket;
        private BufferedReader in;
        private BufferedWriter out;

        public Client (Socket socket) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }

        public static void main(String[] args) throws IOException {
            Socket socket = new Socket("localhost", 1717);
            Client Client = new Client(socket);
        }


        @Override
        public void run() {
            try {

                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

                Scanner sc = new Scanner(System.in);
                String userInput;
                String usuario,mensaje, destinatario;

                while (true) {
                    userInput = teclado.readLine();
                    ArrayList<String> users = new ArrayList<>();

                    if (userInput.trim().contains("CONNECT ")) {

                        ArrayList<Character> namelist = new ArrayList<>();
                        for(int i = 8; i < userInput.length(); i++){
                            namelist.add(userInput.charAt(i));
                        }
                        usuario = namelist.toString();
                        users.add(usuario);

                    } else if (userInput.trim().contains("SEND #") && userInput.trim().contains("@")) {

                        ArrayList<Character> messagelist = new ArrayList<>();
                        ArrayList<Character> destlist = new ArrayList<>();
                        int contador = 6;

                        while (contador < userInput.length() && userInput.charAt(contador) != '@') {
                            messagelist.add(userInput.charAt(contador));
                            contador++;
                        }
                        mensaje = messagelist.toString();
                        if (userInput.length() > contador) {
                            while (contador < userInput.length()) {
                                destlist.add(userInput.charAt(contador));
                                contador++;
                            }
                        }
                        destinatario = destlist.toString();

                        System.out.println("Para " + destinatario + ": " + mensaje);


                    } else if (userInput.trim().equals("LIST")) {
                        System.out.println("Usuarios activos en este momento:");
                        for(String item:users){
                            System.out.println(item);
                        }
                    } else  if (userInput.trim().equals("DISCONNECT")) {
                        break;
                    } else {
                        System.out.print("Comando inválido");
                    }
                }
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to localhost");
                System.exit(1);
            }
        }
}
