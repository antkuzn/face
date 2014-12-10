
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by nofuture on 12/4/2014.
 */
 
 class Pair{
    int a;
    int b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        return a == pair.a && b == pair.b;
    }

    public String toString(){
        return a + " " + b;
    }
}

public class ScriptCreator {

    FileGetter getter;

    public ScriptCreator(FileGetter getter) {

        this.getter = getter;
    }

    void createCharrefsScript() throws Exception {
        int c = 0;
        HashSet<Pair> set = new HashSet<Pair>(); //!!!!
        PrintWriter writer = new PrintWriter("D:\\project\\sql\\Charrefs.txt");
        List<Issue> issueList = (List<Issue>) getter.getFromFile("D:\\project\\issues_v2");
        List<Character> characterList = (List<Character>) getter.getFromFile("D:\\project\\characters_v1");
        ListIterator<Character> charIter = characterList.listIterator();

        int count = 1;
        Map<Integer, Integer> identifiers = new HashMap<Integer, Integer>();
        while (charIter.hasNext()){
            Character character = charIter.next();
            identifiers.put(character.getFirstId(), count++);
        }
        for (Issue i: issueList) {
            if (i.getCharacters() != null)
                for (Character ch : i.getCharacters()) {
                    Pair tmp = new Pair(i.getIssueId(), identifiers.get(ch.getFirstId()));
                    set.add(tmp); //!!!!

                }
        }
        
        
        Pair ss = new Pair(854, 23373);
        for(Pair p: set){
            c++;
            //System.out.println(c);
            writer.printf("insert into charrefs (issue_id, char_id) values (%d, %d);%n",
                    p.a, p.b);
            if (p.equals(ss)){
                System.out.println("HALLELUJAH");
            }
        }
        writer.close();
    }

}
