import java.io.ObjectOutputStream.PutField;

public class Arbre extends Vegetal{

    public Arbre(ArbreEspece nom, double tempMax,double ConsEau, int seuilEau, int seuilBoire, double tauxReprod, TerrainType habitatNaturel) {
        initializeArbre(nom, tempMax,ConsEau, seuilEau, seuilBoire, tauxReprod, habitatNaturel);
    }

    public Arbre(String nom){
        switch (nom) {
            case "CHENE":
                initializeArbre(ArbreEspece.CHENE, 40, 0.3f, 40, 2, 0.15f, TerrainType.FORET);
                break;
            case "SAPIN":
                initializeArbre(ArbreEspece.SAPIN, 40, 0.15f, 20, 6, 0.15f, TerrainType.PLAINE);
                break;
            default:
                System.out.println("Espèce non reconnu");
        }
    }

    private void initializeArbre(ArbreEspece nom, double tempMax, double ConsEau,int seuilEau, int seuilBoire, double tauxReprod,
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

enum ArbreEspece{
    CHENE,
    SAPIN
}