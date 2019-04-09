import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class can be used as a resourceful of methods to connect
 * and send mails via SMTP or used with Mail class to send directly and all automatized Mail.
 */
public class SmtpClient {
    private String SmtpAddress;
    private int SmtpPort;
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;

    SmtpClient(String smtpAddress, int SmtpPort){
        this.SmtpAddress = smtpAddress;
        this.SmtpPort = SmtpPort;
    }

    public void sendMail(Mail mail){
        //TODO : envoi automatisé d'un mail
        this.connect();
        this.readInput();
        try {
            output.write("EHLO test");
            output.newLine();
            output.flush();
            this.readInput();
            output.write("MAIL FROM: " + mail.getFrom());
            this.readInput();
            for(String dest : mail.getTo()){
                output.write("RCPT TO: " + dest);
                this.readInput();
            }
            output.write("DATA");
            this.readInput();
            output.write("From: " + mail.getFrom() + "\nTo: ");
            for(String dest : mail.getTo()){
                output.write(dest + ";");
            }
            output.write(mail.getMessage());
            output.write("\n.\n");
            this.readInput();
            output.write("QUIT");
        } catch(IOException e){
            e.printStackTrace();
        }
        this.disconnect();
    }

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
            input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
                output.write(message);
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
                while (!line.contains("250 ") && !line.contains("500") && !line.contains("501") && !line.contains("354") && !line.contains("220")) {
                    //TODO: Check les erreurs et renvoyer à l'user ?
                    line = input.readLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
