package parseur.projet;

public class Main {
    public static void main(String[] args) {

        String str="le peit telephone sonne à 6 heures";
        TokenManager tm=new TokenManager(str);
        Parseur p=new Parseur(tm);
        try {
            p.parse();
            System.out.println(str + " est valide");
        } catch (RuntimeException e) {
            System.out.println(str + " n'est pas valide");
            System.out.println(e.getMessage());
        }
    }
    }



