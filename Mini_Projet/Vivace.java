public class Vivace extends Vegetal {

    public Vivace(VivaceEspece nom, double tempMax, double ConsEau, int seuilEau, int seuilBoire, double tauxReprod, TerrainType habitatNaturel) {
        initializeVivace(nom, tempMax,ConsEau, seuilEau, seuilBoire, tauxReprod, habitatNaturel);
    }

    public Vivace(String nom){
        switch (nom) {
            case "TULIPE":
            initializeVivace(VivaceEspece.TULIPE, 35, 0.01f,30, 3, 0.01f, TerrainType.FORET);
                break;
            case "ROSE":
            initializeVivace(VivaceEspece.ROSE, 30,0.01f, 30, 2, 0.01f, TerrainType.PLAINE);
                break;
            default:
                System.out.println("Espèce non reconnue");
        }
    }

    private void initializeVivace(VivaceEspece nom, double tempMax, double ConsEau, int seuilEau, int seuilBoire, double tauxReprod,
            TerrainType habitatNaturel) {
        this.nom = nom.name();
        this.tempMax = tempMax;
        this.ConsEau = ConsEau;
        this.seuilEau = seuilEau;
        this.tauxReprod = tauxReprod;
        this.habitatNaturel = habitatNaturel.name();
        this.seuilBoire = seuilBoire;
    }



    @Override
    public void seNourrir(Zone z){}


}

enum VivaceEspece{
    ROSE,
    TULIPE
}