import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrankGenerator {

    //selectionner un message au random

    public static void main(String[] args) {
        //TODO: ajouter logique nbre de groupe, test ici 1 groupe

        ConfigReader configs = new ConfigReader();
        SmtpClient smtp = new SmtpClient(configs.getSmtpAddress(),configs.getSmtpPort());
        Group victime = new Group();
        for(Person i : configs.getVictims()){
            victime.addPerson(i);
        }
        Group CCs = new Group(new Person(configs.getCCs()));

        Set<String> key = configs.getMessages().keySet();
        List<String> key2 = new ArrayList<String>(key); //list de toutes les clefs

        String message = configs.getMessages().get(key2.get(0));
        String subject = key2.get(0);

        Mail chosenEmail = new Mail(victime.getPerson().get(0), victime, CCs, subject, message); //from to cc subject body

        Prank currentPrank = new Prank(victime, chosenEmail, CCs); //group message Cc
        currentPrank.runPrank(smtp);
    }
}
