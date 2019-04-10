import java.util.ArrayList;

public class Prank {
    private Group pranked;
    private Mail message;
    private Group CCs;

    public Prank(Group target, Mail message, Group CCs){
        this.pranked = target;
        this.message = message;
        this.CCs = CCs;
    }

    public void runPrank(SmtpClient smtp){
        Person faker = this.pranked.getPerson().get(0);

        for(int i =1;i < pranked.getPerson().size(); ++i){

                Mail currentPrank = new Mail(faker, new Group(pranked.getPerson().get(i)), CCs, this.message.getSubject(), this.message.getMessage());
                smtp.sendMail(currentPrank);
        }
    }
}
