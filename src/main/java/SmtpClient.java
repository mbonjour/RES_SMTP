import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SmtpClient {
    private String SmtpAddress;
    private int SmtpPort;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private String getSmtpAddress() {
        return SmtpAddress;
    }

    public void setSmtpAddress(String smtpAddress) {
        SmtpAddress = smtpAddress;
    }

    private int getSmtpPort() {
        return SmtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        SmtpPort = smtpPort;
    }

    public void connect(){
        try{
            socket = new Socket(SmtpAddress, SmtpPort);
            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connected");
    }

    public void disconnect(){
        try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("disconnected");
    }

    public void sendMessage(String message){
        if(socket.isConnected()){
            try {
                output.writeUTF(message);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void readInput(){
        String line = "";
        if(socket.isConnected()) {
            try { //condition de fin correspond au ligne qu'envoie le protocole SMTP
                while (!line.contains("250") && !line.contains("500") && !line.contains("501") && !line.contains("354")) {
                    line = input.readUTF();
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
