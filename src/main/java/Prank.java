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
        ArrayList<String> TOs = new ArrayList<String>();
        for(int i =1;i < pranked.getPerson().size(); ++i){
            TOs.add(pranked.getPerson().get(i).getEmail());
        }

        Mail currentPrank = new Mail(faker.getEmail(), TOs, CCs, this.message);
        smtp.sendMail(currentPrank);
    }
}
