import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Service implements Runnable{
    private Socket clientSocket;

    protected BufferedReader in = null;
    protected boolean moreQuotes = true;

    public Service(Socket c, BufferedReader i) {
        clientSocket = c;
        in = i;

    }


    @Override
    public void run() {
        PrintWriter out  = null;
        try {
            out  = new PrintWriter( clientSocket.getOutputStream(), true);
            // Flujo de entrada (leer datos que envia el cliente)
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( clientSocket.getInputStream() ));

            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(package.Service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

}
