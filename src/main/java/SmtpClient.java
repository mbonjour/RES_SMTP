import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;

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
    private boolean withAuth;
    private String username = "";
    private String password = "";

    SmtpClient(String smtpAddress, int SmtpPort){
        this(smtpAddress,SmtpPort,"","");
    }

    SmtpClient(String smtpAddress, int SmtpPort, String username, String password){
        this.SmtpAddress = smtpAddress;
        this.SmtpPort = SmtpPort;
        this.withAuth = false;
        this.username = username;
        this.password = password;
    }

    public void sendMail(Mail mail){
        this.connect();
        this.readInput();

        try {
            output.write("EHLO test\r\n");
            output.flush();
            this.readInput();
            if(this.withAuth){
                output.write("AUTH LOGIN\r\n");
                output.flush();
                this.readInput();
                output.write(Base64.getEncoder().encodeToString(username.getBytes()) + "\r\n");
                output.flush();
                this.readInput();
                output.write(Base64.getEncoder().encodeToString(password.getBytes()) + "\r\n");
                output.flush();
                this.readInput();
            }
            output.write("MAIL FROM: <" + mail.getFrom().getEmail() + ">\r\n");
            output.flush();
            this.readInput();

            for(Person toPerson : mail.getTo().getPerson()) {
                System.out.println("RCPT TO: <" + toPerson.getEmail() + ">\r\n");
                output.write("RCPT TO: <" + toPerson.getEmail() + ">\r\n");
                output.flush();
                this.readInput();
            }

            output.write("DATA\r\n");
            output.flush();
            this.readInput();
            output.write("From: " + mail.getFrom().getEmail() + "\r\n" );

            for(Person toPerson : mail.getTo().getPerson()){
                output.write( "To: " + toPerson.getEmail() + "\r\n");
            }

            for(Person ccPerson : mail.getCc().getPerson()){
                output.write( "Cc: " + ccPerson.getEmail() + "\r\n");
            }
            output.write("Content-type: text/plain; charset=utf-8\r\n");
            output.write("Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(mail.getSubject().getBytes()) + "?=\r\n\r\n");
            output.write(mail.getMessage());
            output.write("\r\n.\r\n");
            output.flush();
            this.readInput();
            output.write("QUIT\r\n");
            output.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
        this.disconnect();
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
                while (!line.contains("250 ") && !line.contains("500") && !line.contains("501") && !line.contains("354") && !line.contains("220") && !line.contains("334") && !line.contains("235")) {
                    if(line.contains("AUTH")){
                        this.withAuth = true;
                    }
                    line = input.readLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
