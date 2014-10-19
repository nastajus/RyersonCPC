import java.util.*;

public class Genes {
    static final String dom = "dominant", rec = "recessive", non = "non-existent";
    static class Person{
        public String name;
        public Person p1;
        public Person p2;
        public String gene;
        public Person(String name){this.name = name;}
        public Person(String name, String gene){this.name = name; this.gene = gene;}
        public void addParent(Person p){
            if (p1 == null){
                p1=p;
                return;
            }
            if (p2 != null) throw new RuntimeException();
            p2=p;

            if (p2.gene == null || p1.gene == null) throw new RuntimeException();

            switch(p1.gene){
                case dom:
                    if (p2.gene.equals(non)) gene = rec;
                    else gene = dom;
                    break;
                case rec:
                    switch(p2.gene){
                        case dom:
                            gene = dom;
                            break;
                        case rec:
                            gene = rec;
                            break;
                        case non:
                            gene = rec;
                            break;
                    }
                    break;
                case non:
                    if (p2.gene.equals(dom)) gene = rec;
                    else gene = non;
                    break;
            }
        }
    }


    public static void main(String [] args){
        Scanner s = new Scanner(System.in);
        TreeMap<String, Person> tree = new TreeMap<>();
        if (!s.hasNextInt()) return;
        int totalRecords = s.nextInt();
        for (int i = 0; i < totalRecords; i++) {
            String word1= s.next();
            String word2= s.next();
            Person p = tree.get(word1);
            if(p == null){
                tree.put(word1, new Person(word1,word2));
            }else {
                Person child = tree.get(word2);
                if (child == null) child= new Person(word2);
                child.addParent(tree.get(word1));
                tree.put(word2, child);
            }
        }
        for (String key : tree.keySet()){
            System.out.println(tree.get(key).name + " " +tree.get(key).gene);
        }
    }
}
