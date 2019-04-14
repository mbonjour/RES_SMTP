import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;
    private String smtpAddress;
    private int smtpPort;
    private int numberOfGroups;
    private String CCs;
    private Group victim;
    private HashMap<String,String> message;
    private String username;
    private String password;


    public ConfigReader() {
        this.prop = getProp();
        this.message = getMessage();
        this.victim = getVictim();
    }

    private Properties getProp() {
        //source : https://www.mkyong.com/java/java-properties-file-examples/
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config/config.properties");

            // load a property files
            prop.load(input);
            this.smtpAddress = prop.getProperty("smtpServerAdress");
            this.smtpPort = Integer.valueOf(prop.getProperty("smtpServerPort"));
            this.CCs = prop.getProperty("witnessesToCC");
            this.numberOfGroups = Integer.valueOf(prop.getProperty("numberofGroups"));
            this.username = prop.getProperty("username") == null ? "" : prop.getProperty("username");
            this.password = prop.getProperty("password") == null ? "" : prop.getProperty("password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    private HashMap<String, String> getMessage(){
        HashMap<String, String> map = new HashMap<String, String>();
        String currentMessage="";
        String currentSubject="";
        try {
            FileReader reader = new FileReader("config/messages.utf8");
            BufferedReader br = new BufferedReader(reader);

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("---")){
                    map.put(currentSubject, currentMessage);
                    currentSubject = "";
                    currentMessage = "";
                }else if (line.contains("Subject:")){
                    currentSubject = line.substring(8);
                } else{
                    currentMessage += line + '\n';
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);

        }
        return map;
    }

    private Group getVictim(){
        Group victim = new Group();

        try {
            FileReader reader = new FileReader("config/mailTarget.utf8");
            BufferedReader br = new BufferedReader(reader);

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                victim.addPerson(new Person(line));
            }

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        return victim;
    }


    public String getSmtpAddress() {
        return this.smtpAddress;
    }

    public int getSmtpPort() {
        return this.smtpPort;
    }

    public int getNumberOfGroups() {
        return this.numberOfGroups;
    }

    public String getCCs() {
        return this.CCs;
    }

    public Group getVictims(){ return this.victim;
    }

    public HashMap<String, String> getMessages(){
        return this.message;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
