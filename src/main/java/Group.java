import java.util.ArrayList;
import java.util.List;

public class Group {
    private ArrayList<Person> listPersonne;

    public void Group(){
        this.listPersonne = new ArrayList<Person>();
    }
    public void addPerson(Person person){ //Regarder si déjà dans le groupe ?
        listPersonne.add(person);
    }
    public List<Person> getPerson(){
        return listPersonne;
    }
}
