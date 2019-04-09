
import jdk.internal.net.http.common.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;
    private ArrayList<String> victim;
    private ArrayList<Pair<String, String>> message;

    //source : https://www.mkyong.com/java/java-properties-file-examples/

    public ConfigReader() {
        this.prop = getProp();
        this.message = getMessage();
        this.victim = getVictim();
    }

    private Properties getProp() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config/config.properties");

            // load a property files
            prop.load(input);

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

    private ArrayList<Pair<String, String>> getMessage(){
        String data="";
        String subject="";
        ArrayList<Pair<String, String>> message = new ArrayList<Pair<String, String>>();
        try {
            FileReader reader = new FileReader("messages.utf8");
            BufferedReader br = new BufferedReader(reader);

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {

                if(line.contains("Subject :")){
                    subject = line;
                }
                else if(line.contains("---")){
                    message.add(new Pair(subject, data));
                    data = subject = "";

                }else{
                    data += line + '\n';
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return message;
    }

    private ArrayList<String> getVictim(){
        ArrayList<String> victim = new ArrayList<String>();

        try {
            FileReader reader = new FileReader("messages.utf8");
            BufferedReader br = new BufferedReader(reader);

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                victim.add(line);
            }

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        return victim;
    }



}
