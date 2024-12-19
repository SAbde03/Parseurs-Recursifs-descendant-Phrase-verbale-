package parseur.projet;

public class Parseur {
    private TokenManager tm;
    private String tc;


    private void avancer() {
        tc = tm.suivant();
    }

    private void consommer(String attendu) {
        if (tc.equals(attendu)) {
            avancer();
        } else {
            throw new RuntimeException("Erreur: attendu '" + attendu + "' mais trouvé '" + tc + "'");
        }
    }


    public Parseur(TokenManager tm) {
        this.tm = tm;
        avancer();
    }

    // phrase --> sujet verbe complément

    public void phrase() {
        sujet();
        verbe();
        complement();
    }


    // sujet --> article nom

    public void sujet() {
        article();
        adjectif();
        nom();
    }
    // complement --> COD | CCT

    public void complement() {
        if (tc.equals("le") || tc.equals("la") || tc.equals("les") ||
                tc.equals("un") || tc.equals("une") || tc.equals("des")) {
            COD();
        } else if (tc.equals("à") || tc.equals("chaque") || tc.equals("toujours")) {
            CCT();
        } else {
            throw new RuntimeException("Erreur: attendu un COD ou un CCT mais trouvé '" + tc + "'");
        }
    }
    // COD--> article nom
    public void COD() {
        article();
        nom();
    }
    //CTT --> adverbe temps

    public void CCT() {     //COMPLEMENT CIRCONSTANCIEL DU TEMPS
        adverbe();
        nombre();
        if(tc.equals("heures")) {
            consommer("heures");
        }
    }

    // article --> la | le | les | un | une | des

    public void article() {
        switch (tc) {
            case "la":
            case "le":
            case "les":
            case "un":
            case "une":
            case "des":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un article mais trouvé '" + tc + "'");
        }

        // verbe --> mange | mangent | charge

    }
    public void verbe() {
        switch (tc) {
            case "mange":
            case "mangent":
            case "charge":
            case "sonne":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un verbe mais trouvé '" + tc + "'");
        }
    }

    // nom --> souris | fromage | telephone

    public void nom() {
        switch (tc) {
            case "souris":
            case "fromage":
            case "fille":
            case "telephone":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un nom mais trouvé '" + tc + "'");
        }
    }

    public void adverbe() {
        switch (tc) {
            case "à":
            case  "chaque":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un adverbe mais trouvé '" + tc + "'");
        }
    }
    public void nombre() {
        switch (tc) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un nombre mais trouvé '" + tc + "'");
        }
    }
    public void adjectif() {
        switch (tc) {
            case "grand":
            case "petit":
            case "joli":
            case "beau":
            case "rapide":
                consommer(tc);
                break;
            default:

                break;
        }
    }

    public void parse() {
        phrase();
        if (!tc.equals("#")) {
            throw new RuntimeException("Erreur: caractère inattendu '" + tc + "' après l'analyse.");
        }
    }
}

