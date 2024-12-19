package parseur.projet;

import java.util.List;

public class TokenManager {
    private String[] entree;
    private int iCourant;

    public TokenManager(String Str) {
        this.entree =Str.toLowerCase().split("[ ,]+");

    }

    public String suivant() {
        if (iCourant < entree.length) {
            return entree[iCourant++];
        }
        return "#";
    }
}