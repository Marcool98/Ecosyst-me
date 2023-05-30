public class Carnivore extends Mammifere {

    public Carnivore(CarnivoreEspece nom, double tauxReprod, 
        double tauxChasse,double ConsEau, int seuilManger,int seuilBoire, TerrainType habitat, double chanceDeplacer){
            initializeCarnivore(nom, tauxReprod, tauxChasse, ConsEau , seuilManger, seuilBoire, habitat, chanceDeplacer);
    }

    public Carnivore(String nom) {
        switch (nom) {
            case "LOUP":
            initializeCarnivore(CarnivoreEspece.LOUP, 0.35f, 0.69f , 0.5f, 4, 4, TerrainType.PLAINE, 0.3f);
                break;
            case "LEOPARD":
            initializeCarnivore(CarnivoreEspece.LEOPARD, 0.35f, 0.78f , 0.5f , 4 , 4, TerrainType.FORET, 0.4f);
                break;
            default:
                System.out.println("Espece non reconnue");
        }
    }

    public void initializeCarnivore(CarnivoreEspece nom, double tauxReprod, double tauxChasse, double ConsEau,
        int seuilManger,int seuilBoire, TerrainType habitat, double chanceDeplacer){
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

enum CarnivoreEspece{
    LOUP,
    LEOPARD
}
