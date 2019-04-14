import java.util.ArrayList;

public class Mail {
    private Person from;
    private Group to;
    private Group witness;
    private String body;
    private String subject;

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

    public Group getTo() {
        return to;
    }

    public Group getCc() {
        return witness;
    }

    public String getMessage() {
        return body;
    }

    public String getSubject(){return subject;}
}
