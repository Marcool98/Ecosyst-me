public class Insecte extends Animal{

    public Insecte(InsecteEspece nom, double tauxReprod, TerrainType habitat, double chanceDeplacer){
        initializeInsecte(nom, tauxReprod, habitat, chanceDeplacer);
    }

    public Insecte(String nom){
        switch(nom){
            case "CHENILLE":
            initializeInsecte(InsecteEspece.CHENILLE, 0.8f, TerrainType.FORET, 0.2f);
                break;
            case "COCCINELLE":
            initializeInsecte(InsecteEspece.COCCINELLE, 0.8f, TerrainType.PLAINE, 0.8f);
                break;
            default:
                System.out.println("Espèce non reconnu");

        }
    }

    public void initializeInsecte(InsecteEspece nom, double tauxReprod, TerrainType habitat, double chanceDeplacer){
        this.nom = nom.name();
        this.tauxReprod = tauxReprod;
        this.habitatNaturel = habitat.name();
        this.chanceDeplacer = chanceDeplacer;
    }

}

enum InsecteEspece{
    CHENILLE,
    COCCINELLE
}