import java.util.ArrayList;

public class Mail {
    private String from;
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private String message;

    public Mail(String from, ArrayList<String> to, ArrayList<String> cc, String message){
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

    public ArrayList<String> getTo() {
        return to;
    }

    public void setTo(ArrayList<String> to) {
        this.to = to;
    }

    public ArrayList<String> getCc() {
        return cc;
    }

    public void setCc(ArrayList<String> cc) {
        this.cc = cc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
