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


    // sujet --> pronom | article adjectif nom

    public void sujet() {
        if(isPronom(tc)){
            pronom();
        }else if(isArticle(tc)){
            article();
            if (isAdjectif(tc)){
                adjectif();
            }
            nom();
        }else{
            throw new RuntimeException("Erreur: attendu un article ou un pronom mais trouvé '" + tc + "'");
        }
    }

    // pronom --> je | tu | il | elle | on | nous | vous | ils | elles

    public void pronom(){
        switch (tc){
            case "je":
            case "tu":
            case "il":
            case "elle":
            case "on":
            case "nous":
            case "vous":
            case "ils":
            case "elles":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un pronom mais trouvé '" + tc + "'");
        }
    }

    // complement --> COD | CCT | CCL | COI | CCC | CCB

    public void complement() {
        if (tc.equals("le") || tc.equals("la") || tc.equals("les") ||
                tc.equals("un") || tc.equals("une") || tc.equals("des")) {
            COD(); CCL();
        } else if (tc.equals("à") || tc.equals("chaque") || tc.equals("toujours")) {
            CCT();
        } else if(tc.equals("dans") || tc.equals("sur") || tc.equals("sous")){
            CCL();
        } else if (tc.equals("à") || tc.equals("pour")) {
            COI();
        }else if (tc.equals("à cause de") || tc.equals("grâce à")){
            CCC();
        }else if(tc.equals("afin de")){
            CCB();
        }else {
            throw new RuntimeException("Erreur: attendu un COD ou un CCT ou CCL ou COI ou CCC ou CCB mais trouvé '" + tc + "'");
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

    // CCL --> préposition article nom
    public void CCL(){ // COMPLEMENT CIRCONSTANCIEL DE LIEU
        preposition();
        article();
        nom();
    }

    // COI --> préposition article nom
    public void COI() { // COMPLIMENT D'OBJET INDERICT
        preposition();
        article();
        nom();
    }

    // CCC --> préposition article nom | préposition verbe
    public void CCC(){ // COMPLEMENT CIRCONSTANCIEL DE CAUSE
        preposition();
        if(isArticle(tc)){
            article();
            nom();
        }else{
            verbe();
        }
    }

    // CCB --> préposition verbe
    public void CCB(){ // COMPLEMENT CIRCONSTANCIEL DE BUT
        preposition();
        verbe();
    }

    // Préposition --> dans | sur | sous | à | pour | à cause de | grâce à
    public void preposition(){
        switch (tc){
            case "dans":
            case "sur":
            case "sous":
            case "à":
            case "pour":
            case "à cause de":
            case "grâce à":
            case "afin de":
                consommer(tc);
                break;
            default:
               // throw new RuntimeException("Erreur: attendu une préposition mais trouvé '" + tc + "'");
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
               // throw new RuntimeException("Erreur: attendu un article mais trouvé '" + tc + "'");
        }

        // verbe --> mange | mangent | charge

    }
    public void verbe() {
        switch (tc) {
            case "mange":
            case "mangent":
            case "charge":
            case "sonne":
            case "est":
            case "réussis":
            case "réussir":
            case "dors":
            case "dormir":
            case "joue":
            case "étudie":
                consommer(tc);
                break;
            default:
                throw new RuntimeException("Erreur: attendu un verbe mais trouvé '" + tc + "'");
        }
    }

    // nom --> souris | fromage | telephone | maison | table | jardin | café | classe

    public void nom() {
        switch (tc) {
            case "souris":
            case "fromage":
            case "fille":
            case "telephone":
            case "maison":
            case "table":
            case "football":
            case "jardin":
            case "café":
            case "classe":
                consommer(tc);
                break;
            default:
               // throw new RuntimeException("Erreur: attendu un nom mais trouvé '" + tc + "'");
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

    private boolean isPronom(String token) {
        return token.equals("je") || token.equals("tu") ||
                token.equals("il") || token.equals("elle") ||
                token.equals("on") || token.equals("nous") ||
                token.equals("vous") || token.equals("ils") ||
                token.equals("elles");
    }

    private boolean isArticle(String token) {
        return token.equals("le") || token.equals("la") ||
                token.equals("les") || token.equals("un") ||
                token.equals("une") || token.equals("des");
    }

    private boolean isAdjectif(String token) {
        return token.equals("grand") || token.equals("joli") ||
                token.equals("petit") || token.equals("beau") ||
                token.equals("rapide");
    }

    public void parse() {
        phrase();
        if (!tc.equals("#")) {
            throw new RuntimeException("Erreur: caractère inattendu '" + tc + "' après l'analyse.");
        }
    }
}

