public class Herbivore extends Mammifere {

    public Herbivore(HerbivoreEspece nom,  double tauxReprod,double tauxChasse, double ConsEau,
                    int seuilManger,int seuilBoire, TerrainType habitat,double chanceDeplacer){
        initializeHerbivore(nom, tauxReprod, tauxChasse, ConsEau, seuilManger, seuilBoire, habitat, chanceDeplacer);
    }

    public Herbivore(String nom) {
        switch (nom) {
            case "ECUREUIL":
                initializeHerbivore(HerbivoreEspece.ECUREUIL, 0.45f, 0.3f,0.1f, 5, 3, TerrainType.FORET, 0.3f);
                break;
            case "MOUTON":
                initializeHerbivore(HerbivoreEspece.MOUTON, 0.45f, 0.6f,0.1f, 3, 2, TerrainType.PLAINE, 0.4f);
                break;
            default:
                System.out.println("Espèce non reconnue");
        }
    }

    private void initializeHerbivore(HerbivoreEspece nom, double tauxReprod, double tauxChasse, double ConsEau,
            int seuilManger, int seuilBoire, TerrainType habitat, double chanceDeplacer) {
        this.nom = nom.name();
        this.tauxReprod = tauxReprod;
        this.tauxChasse = tauxChasse;
        this.ConsEau = ConsEau;
        this.seuilManger = seuilManger;
        this.seuilBoire = seuilBoire;
        this.habitatNaturel = habitat.name();
        this.chanceDeplacer = chanceDeplacer;
    }

}

enum HerbivoreEspece{
    MOUTON,
    ECUREUIL
}