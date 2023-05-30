import java.util.ArrayList;
import java.sql.Struct;
import java.util.Map;

public class Zone {
    private TerrainType type;
    private double temperature;
    private double nivEau;
    private ArrayList<Animal> animaux = new ArrayList<Animal>();
    private ArrayList<Vegetal> vegetaux = new ArrayList<Vegetal>();
    private Map<String, String> couplesPP;

    public Zone(TerrainType type, double temp, double nivEau, Map<String, String> couplesPP) {
        this.type = type;
        this.temperature = temp;
        this.nivEau = nivEau;
        this.couplesPP = couplesPP;
    }

    private void majEtatZone(){
        int nbArbre = getNombreArbre();
        if(nbArbre >= 40){
            type = TerrainType.FORET;
        }else if(nivEau >= 5){
            type = TerrainType.PLAINE;
        }else{
            type = TerrainType.DESERT;
            temperature = 50;
        }
    }

    public void vivre() {
        for (int i = 0; i < animaux.size(); i++)
            if (!animaux.get(i).getEtat())
                animaux.remove(animaux.get(i));

        for (int i = 0; i < vegetaux.size(); i++)
            if (!vegetaux.get(i).getEtat())
                vegetaux.remove(vegetaux.get(i));

        for (int i = 0; i < animaux.size(); i++) {
            if (animaux.get(i) instanceof Herbivore)
                animaux.get(i).SeReproduire(this);
        }

        for (int i = 0; i < animaux.size(); i++) {
            if (animaux.get(i) instanceof Carnivore)
                animaux.get(i).SeReproduire(this);
        }

        for (int i = 0; i < animaux.size(); i++) {
            animaux.get(i).seNourrir(this);
        }

        for (int i = 0; i < vegetaux.size(); i++) {
            vegetaux.get(i).SeReproduire(this);
        }

        for (int i = 0; i < animaux.size(); i++) {
            animaux.get(i).seNourrir(this);
        }

        for (int i = 0; i < vegetaux.size(); i++) {
            vegetaux.get(i).ConsommerEau(this);
        }


        for (int i = 0; i < animaux.size(); i++) {
            animaux.get(i).ConsommerEau(this);
        }
    }

    public void passerJour() {
        for (int i = 0; i < animaux.size(); i++)
            animaux.get(i).passerJour(this);

        for (int i = 0; i < vegetaux.size(); i++) {
            vegetaux.get(i).passerJour(this);
        }

        majEtatZone();
        System.out.println("nombre loup = " + getNombreAnimal("LOUP"));
        System.out.println("nombre leopard = " + getNombreAnimal("LEOPARD"));
        System.out.println("nombre mouton = " + getNombreAnimal("MOUTON"));
        System.out.println("nombre ecureuil = " + getNombreAnimal("ECUREUIL"));
        System.out.println("nombre chene = " + getNombreVegetal("CHENE"));
        System.out.println("nombre tulipe = " + getNombreVegetal("TULIPE"));
        System.out.println("nombre rose = " + getNombreVegetal("ROSE"));
        System.out.println("nombre chenille = " + getNombreAnimal("CHENILLE"));
        System.out.println("nombre coccinelle = " + getNombreAnimal("COCCINELLE"));
        System.out.println("nombre moineau = " + getNombreAnimal("MOINEAU"));
        System.out.println("nombre corbeau = " + getNombreAnimal("CORBEAU"));


    }

    public Map<String, String> getCouples() {
        return couplesPP;
    }

    public boolean ajouterAnimal(Animal a) {
        return animaux.add(a);
    }

    public boolean ajouterVegetal(Vegetal v) {
        return vegetaux.add(v);
    }

    public boolean retirerAnimal(String nom) {
        for (int i = 0; i < animaux.size(); i++) {
            if (animaux.get(i).getNom().equals(nom)) {
                return animaux.remove(animaux.get(i));
            }
        }
        return false;
    }

    public boolean retirerVegetal(String nom) {
        for (int i = 0; i < vegetaux.size(); i++) {
            if (vegetaux.get(i).getNom().equals(nom)) {
                return vegetaux.remove(vegetaux.get(i));
            }
        }
        return false;
    }

    public int getNombreAnimal(String nom) {
        int counter = 0;
        for (Animal a : animaux) {
            if (a.getNom().equals(nom)) {
                counter++;
            }
        }
        return counter;
    }

    public int getNombreVegetal(String nom) {
        int counter = 0;
        for (Vegetal v : vegetaux) {
            if (v.getNom().equals(nom)) {
                counter++;
            }
        }
        return counter;
    }

    
    public int getNombreArbre() {
        int counter = 0;
        for (Vegetal v : vegetaux) {
            if (v instanceof Arbre) {
                counter++;
            }
        }
        return counter;
    }

    public String getType() {
        return type.name();
    }

    public double getTemperature() {
        return this.temperature;
    }

    public double getnivEau() {
        return this.nivEau;
    }

    public void precipitation(double eau) {
        if(!getType().equals("DESERT"))
            this.nivEau += eau;
    }

    public boolean removEau(double eau) {
        if (eau > nivEau)
            return false;
        nivEau = nivEau - eau;
        return true;
    }


    public ArrayList<Animal> getListeAnimaux() {
        return this.animaux;
    }
}

enum TerrainType {
    FORET,
    PLAINE,
    DESERT
}