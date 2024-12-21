package parseur.projet;

import java.util.ArrayList;
import java.util.List;

public class TokenManager {
    private List<String> entree;
    private int iCourant;

    public TokenManager(String Str) {
        String[] multiMotsPreposition = {"à cause de", "grâce à", "afin de"};
        for(String preposition : multiMotsPreposition){
            Str = Str.replace(preposition, preposition.replace(" ", "_"));
        }

        this.entree = new ArrayList<>(List.of(Str.toLowerCase().split("[ ,]+")));

        for(String preposition : multiMotsPreposition){
            this.entree.replaceAll((token) -> token.equals(preposition.replace(" ", "_")) ? preposition : token);
        }
    }

    public String suivant() {
        if (iCourant < entree.size()) {
            return entree.get(iCourant++);
        }
        return "#";
    }
}


