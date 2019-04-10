import java.util.ArrayList;

public class Mail {
    private String from;
    private String to;
    private String cc;
    private String message;
    //TODO: Améliorer pour avoir plusieurs CCs et autres
    public Mail(String from, String  to, String cc, String message){
        this.from = from;
        this.to = to;
        this.cc = cc;

        //TODO: Split message into body and Subject
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
