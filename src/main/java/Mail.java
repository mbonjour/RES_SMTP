import java.util.ArrayList;

public class Mail {
    private Person from;
    private Group to;
    private Group witness;
    private String body;
    private String subject;

    //ajouter subject et body
    //TODO: Améliorer pour avoir plusieurs CCs et autres
    public Mail(Person from, Group  to, Group cc, String subject, String body){
        this.from = from;
        this.to = to;
        this.witness = cc;
        this.body = body;
        this.subject = subject;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Group getTo() {
        return to;
    }

    public void setTo(Group to) {
        this.to = to;
    }

    public Group getCc() {
        return witness;
    }

    public void setCc(Group cc) {
        this.witness = cc;
    }

    public String getMessage() {
        return body;
    }

    public void setMessage(String message) {
        this.body = message;
    }

    public void setSubject(String subject){ this.subject = subject; }

    public String getSubject(){return subject;}
}
