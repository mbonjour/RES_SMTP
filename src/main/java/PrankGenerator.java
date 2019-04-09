import java.util.ArrayList;

public class PrankGenerator {
    public Prank prank;
    public Group target;

    public static void main(String[] args) {
        //TODO: ajouter logique nbre de groupe, test ici 1 groupe

        ConfigReader configs = new ConfigReader();
        SmtpClient smtp = new SmtpClient(configs.getSmtpAddress(),configs.getSmtpPort());
        Group test = new Group();
        for(Person i : configs.getVictims()){
            test.addPerson(i);
        }

        ArrayList<String> CCs = new ArrayList<String>();
        CCs.add(configs.getCCs());
        Prank currentPrank = new Prank(test, configs.getMessages().get(0), CCs );
        currentPrank.runPrank(smtp);
    }
}
