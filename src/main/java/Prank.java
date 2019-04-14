import java.util.*;

public class Prank {
    private Group pranked;
    private HashMap<String, String> message;
    private Group CCs;
    static Random rand;

    static {
        rand = new Random();
    }

    public Prank(Group target, HashMap<String, String> messages, Group CCs){
        this.pranked = target;
        this.message = messages;
        this.CCs = CCs;
    }

    public void runPrank(SmtpClient smtp){
        Person faker = this.pranked.getPerson().get(0);
        Set<String> key = message.keySet();
        // List of the keys
        List<String> key2 = new ArrayList<String>(key);

        int keyMessage = rand.nextInt(message.size());
        String body = message.get(key2.get(keyMessage ));
        String subject = key2.get(keyMessage);

        // First member of group sender so we skip it
        for(int i =1;i < pranked.getPerson().size(); ++i){
                Mail currentPrank = new Mail(faker, new Group(pranked.getPerson().get(i)), CCs, subject, body);
                smtp.sendMail(currentPrank);
        }
    }
}
