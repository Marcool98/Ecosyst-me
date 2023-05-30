public class Oiseau extends Animal {

    public Oiseau(OiseauEspece nom, double tauxReprod,double tauxChasse, double ConsEau, 
    int seuilManger,int seuilBoire,TerrainType habitat, double chanceDeplacer){
        initializeOiseau(nom, tauxReprod, tauxChasse, ConsEau, seuilManger, seuilBoire,habitat, chanceDeplacer);
    }


    public Oiseau(String nom){
        switch(nom){
            case "MOINEAU":
                initializeOiseau(OiseauEspece.MOINEAU, 0.4f, 0.45f,0.1f, 5, 10,TerrainType.FORET, 0.8f);
                break;
            case "CORBEAU":
                initializeOiseau(OiseauEspece.CORBEAU, 0.3f, 0.45f,0.1f, 4, 8,TerrainType.PLAINE, 0.6f);
                break;
            default:
            System.out.println("Espèce non reconnu");
        }
    }

    public void initializeOiseau(OiseauEspece nom, double tauxReprod,double tauxChasse,double ConsEau,
    int seuilManger,int seuilBoire, TerrainType habitat, double chanceDeplacer){
        this.nom = nom.name();
        this.tauxReprod = tauxReprod;
        this.tauxChasse = tauxChasse;
        this.ConsEau =ConsEau;
        this.seuilManger = seuilManger;
        this.seuilBoire = seuilBoire;
        this.habitatNaturel = habitat.name();
        this.chanceDeplacer = chanceDeplacer;
    }

    @Override
    public void seNourrir(Zone z) {
        double r = Math.random();
        if (r <= tauxChasse) {
            if (getNom() == "MOINEAU") {
                if (z.retirerAnimal("CHENILLE"))
                    joursSansManger = 0;
            } else if (getNom() == "CORBEAU") {
                if (z.retirerAnimal("COCCINELLE"))
                    joursSansManger = 0;
            }
        }
    }
}

enum OiseauEspece{
   MOINEAU,
   CORBEAU
}