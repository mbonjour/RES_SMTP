import java.util.ArrayList;

public class PrankGenerator {

    public static void main(String[] args) {
        // TODO: ajouter logique nbre de groupe, test ici 1 groupe

        ConfigReader configs = new ConfigReader();

        SmtpClient smtp = new SmtpClient(configs.getSmtpAddress(),configs.getSmtpPort(), configs.getUsername(),configs.getPassword());
        ArrayList<Group> victime = new ArrayList<Group>();
        int ratio = configs.getVictims().size() / configs.getNumberOfGroups();
        if(ratio < 3){
            System.out.println("Please provide a number of groups that can contain 3 users for the users specified");
            return;
        }

        // Creation of the groupes
        int counter = 0;
        for(int i = 0; i < configs.getNumberOfGroups(); ++i) {
            Group current = new Group();
            for(int j = 0; j < ratio; ++j) {
                current.addPerson(configs.getVictims().get(counter++));
            }
            victime.add(current);
        }

        for(;counter < configs.getVictims().size(); ++counter){
            victime.get(configs.getNumberOfGroups() - 1).addPerson(configs.getVictims().get(counter));
        }

        // Recuperation of the common CC for the prank
        Group CCs = new Group(new Person(configs.getCCs()));
        for(Group victims : victime) {
            Prank currentPrank = new Prank(victims, configs.getMessages(), CCs); //group message Cc
            currentPrank.runPrank(smtp);
        }
    }
}
