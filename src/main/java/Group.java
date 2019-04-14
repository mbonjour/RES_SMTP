import java.util.ArrayList;
import java.util.List;

public class Group {
    private ArrayList<Person> listPersonne;

    public Group(){
        this.listPersonne = new ArrayList<Person>();
    }
    public Group(Person person){
        this.listPersonne = new ArrayList<Person>();
        listPersonne.add(person);
    }
    public void addPerson(Person person){ //Regarder si déjà dans le groupe ?
        listPersonne.add(person);
    }
    public List<Person> getPerson(){
        return listPersonne;
    }
    public int getSize(){
        return listPersonne.size();
    }
    public Person get(int i){
        return this.listPersonne.get(i);
    }
}
