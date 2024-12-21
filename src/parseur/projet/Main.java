package parseur.projet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;
        String phrase;
        do{
            System.out.println("********** Menu **********");
            System.out.println("0 : Pour quitter le programme.");
            System.out.println("1 : Pour tester le programme.");
            System.out.println("****************************");
            System.out.println();
            System.out.print("Entrer votre choix: ");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix){
                case 0: break;
                case 1:
                    System.out.print("Entrer une phrase: ");
                    phrase = sc.nextLine();
                    TokenManager tm=new TokenManager(phrase.trim());
                    Parseur p=new Parseur(tm);
                    try {
                        p.parse();
                        System.out.println(phrase + " est valide");
                    } catch (RuntimeException e) {
                        System.out.println(phrase + " n'est pas valide");
                        System.out.println(e.getMessage());
                    }
            }
        }while(choix != 0);
    }
}





