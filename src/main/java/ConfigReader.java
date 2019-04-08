import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    //source : https://www.mkyong.com/java/java-properties-file-examples/
    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("config/config.properties");

            // load a property files
            prop.load(input);

            System.out.println(prop.getProperty("smtpServerAdress"));
            System.out.println(prop.getProperty("smtpServerPort"));
            System.out.println(prop.getProperty("numberofGroups"));
            System.out.println(prop.getProperty("witnessesToCC"));



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null){
                try{
                    input.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
