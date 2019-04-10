import java.util.ArrayList;

public class Prank {
    private Group pranked;
    private String message;
    private ArrayList<String> CCs;

    public Prank(Group groupe, String message, ArrayList<String> CCs){
        this.pranked = groupe;
        this.message = message;
        this.CCs = CCs;
    }

    public void runPrank(SmtpClient smtp){
        Person faker = this.pranked.getPerson().get(0);
        Person to;
        for(int i =1;i < pranked.getPerson().size(); ++i){
            to = pranked.getPerson().get(i);
            Mail currentPrank = new Mail(faker.getEmail(), to.getEmail(), CCs.get(0), this.message);
            smtp.sendMail(currentPrank);
        }
    }
}
