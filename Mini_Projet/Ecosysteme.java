import java.sql.Struct;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.Color;

public class Ecosysteme {
    private GrilleNature grille;
    private Zone[][] z;
    private Map<String, String> couplesPP = new HashMap<>();
    private int taille;
    private int tailleMinD = 5;

    private double chanceExpansion = 0.6f;

    public Ecosysteme(int taille) {
        grille = new GrilleNature(taille, taille, 100);
        z = new Zone[taille][taille];
        this.taille = taille;

        initialiserCouplesPP();


        for (int i = 0; i < taille; i++) 
            for (int j = 0; j < taille; j++) {
                if(i<j){
                    z[i][j] = new Zone(TerrainType.PLAINE, 25, 80, couplesPP);
                    for (int k = 0; k < 15; k++) {
                        z[i][j].ajouterVegetal(new Arbre("SAPIN"));
                        z[i][j].ajouterVegetal(new Vivace("ROSE"));
                        z[i][j].ajouterVegetal(new Vivace("TULIPE"));
                    }
                }
                else{
                    z[i][j] = new Zone(TerrainType.FORET, 20, 100, couplesPP);
                    for (int k = 0; k < 20; k++) {
                        z[i][j].ajouterVegetal(new Arbre("CHENE"));
                        z[i][j].ajouterVegetal(new Arbre("SAPIN"));
                    }
                    for (int k = 0; k < 10; k++) {
                        z[i][j].ajouterVegetal(new Vivace("ROSE"));
                        z[i][j].ajouterVegetal(new Vivace("TULIPE"));
                    }
                } 
            }
        
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                for (int k = 0; k < 10; k++)
                    z[i][j].ajouterAnimal(new Herbivore("MOUTON"));

                for (int k = 0; k < 10; k++)
                    z[i][j].ajouterAnimal(new Herbivore("ECUREUIL"));

                for (int k = 0; k < 8; k++)
                    z[i][j].ajouterAnimal(new Carnivore("LOUP"));

                for (int k = 0; k < 8; k++)
                    z[i][j].ajouterAnimal(new Carnivore("LEOPARD"));

                for (int k = 0; k < 10; k++)
                    z[i][j].ajouterAnimal(new Insecte("CHENILLE"));

                for (int k = 0; k < 10; k++)
                    z[i][j].ajouterAnimal(new Insecte("COCCINELLE"));

                for (int k = 0; k < 8; k++)
                    z[i][j].ajouterAnimal(new Oiseau("CORBEAU"));

                for (int k = 0; k < 8; k++)
                    z[i][j].ajouterAnimal(new Oiseau("MOINEAU"));

            }
    }

    public void simulation() {
        int indice = 0;
        while (true) {
            majGrille_visuel();
            System.out.println("Itération " + indice++);

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    z[i][j].precipitation(10);
                    System.out.println("Zone " + z[i][j].getType() + " (" + i + "," + j + ")");

                    z[i][j].vivre();
                    expansionArbres(i, j);
                    deplacementAnimaux(i, j);
                    z[i][j].passerJour();
                    System.out.println();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void majGrille_visuel() {
        grille.retirerDisques();
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {

                switch (z[i][j].getType()) {
                    case "FORET":
                        grille.colorieFond(i, j, new Color(0, 102, 0));
                        break;
                    case "PLAINE":
                        grille.colorieFond(i, j, new Color(0, 255, 0));
                        break;
                    case "DESERT":
                        grille.colorieFond(i, j, new Color(204, 153, 0));
                        break;
                    default:
                        System.out.println("type terrain non reconnu");
                }

                majDisqueCarnivore(i, j);
                majDisqueOiseau(i, j);
                majDisqueHerbivore(i, j);
                majDisqueArbre(i, j);
                majDisqueInsecte(i, j);
                majDisqueVivace(i, j);
            }
        grille.redessine();
    }

    private void majDisqueVivace(int i, int j) {
        int nb;
        for (VivaceEspece a : VivaceEspece.values()) {
            nb = z[i][j].getNombreVegetal(a.name());
            while (nb >= 40) {
                switch (a) {
                    case TULIPE:
                        grille.addDisque(i, j, 20, Color.orange);
                        break;
                    case ROSE:
                        grille.addDisque(i, j, 20, Color.pink);
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }
            }
            switch (a) {
                case TULIPE:
                    grille.addDisque(i, j, (int) (nb / 2), Color.orange);
                    break;
                case ROSE:
                    grille.addDisque(i, j, (int) (nb / 2), Color.pink);
                    break;
                default:
                    System.out.println("Espèce non reconnue");
            }

        }
    }

    private void majDisqueInsecte(int i, int j) {
        int nb;
        for (InsecteEspece a : InsecteEspece.values()) {
            nb = z[i][j].getNombreAnimal(a.name());
            while (nb >= 40) {
                switch (a) {
                    case COCCINELLE:
                        grille.addDisque(i, j, 20, Color.red);
                        break;
                    case CHENILLE:
                        grille.addDisque(i, j, 20, new Color(102, 102, 51));
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }
            }
            switch (a) {
                case COCCINELLE:
                    grille.addDisque(i, j, (int) (nb / 2), Color.red);
                    break;
                case CHENILLE:
                    grille.addDisque(i, j, (int) (nb / 2), new Color(102, 102, 51));
                    break;
                default:
                    System.out.println("Espèce non reconnue");
            }

        }
    }

    private void majDisqueArbre(int i, int j) {
        int nb;
        for (ArbreEspece a : ArbreEspece.values()) {
            nb = z[i][j].getNombreVegetal(a.name());
            while (nb >= 40) {
                switch (a) {
                    case SAPIN:
                        grille.addDisque(i, j, 20, new Color(0, 51, 0));
                        break;
                    case CHENE:
                        grille.addDisque(i, j, 20, Color.cyan);
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }
                nb = nb - 30;
            }
            switch (a) {
                case SAPIN:
                    grille.addDisque(i, j, (int) (nb / 2), new Color(0, 51, 0));
                    break;
                case CHENE:
                    grille.addDisque(i, j, (int) (nb / 2), Color.cyan);
                    break;
                default:
                    System.out.println("Espèce non reconnue");
            }
        }
    }

    private void majDisqueHerbivore(int i, int j) {
        int nb;
        for (HerbivoreEspece a : HerbivoreEspece.values()) {
            nb = z[i][j].getNombreAnimal(a.name());
            while (nb >= 40) {
                switch (a) {
                    case MOUTON:
                        grille.addDisque(i, j, 20, Color.white);
                        break;
                    case ECUREUIL:
                        grille.addDisque(i, j, 20, new Color(102, 51, 0));
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }
                nb = nb - 30;
            }
            switch (a) {
                case MOUTON:
                    grille.addDisque(i, j, (int) (nb / 2), Color.white);
                    break;
                case ECUREUIL:
                    grille.addDisque(i, j, (int) (nb / 2), new Color(102, 51, 0));
                    break;
                default:
                    System.out.println("Espèce non reconnue");
            }
        }
    }

    private void majDisqueOiseau(int i, int j) {
        int nb;
        for (OiseauEspece a : OiseauEspece.values()) {
            nb = z[i][j].getNombreAnimal(a.name());
            while (nb >= 40) {
                switch (a) {
                    case MOINEAU:
                        grille.addDisque(i, j, 20, Color.gray);
                        break;
                    case CORBEAU:
                        grille.addDisque(i, j, 20, Color.black);
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }
                switch (a) {
                    case MOINEAU:
                        grille.addDisque(i, j, (int) (nb / 2), Color.gray);
                        break;
                    case CORBEAU:
                        grille.addDisque(i, j, (int) (nb / 2), Color.black);
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }

            }
        }
    }

    private void majDisqueCarnivore(int i, int j) {
        int nb;
        for (CarnivoreEspece a : CarnivoreEspece.values()) {
            nb = z[i][j].getNombreAnimal(a.name());
            while (nb >= 40) {
                switch (a) {
                    case LOUP:
                        grille.addDisque(i, j, 20, Color.magenta);
                        break;
                    case LEOPARD:
                        grille.addDisque(i, j, 20, Color.yellow);
                        break;
                    default:
                        System.out.println("Espèce non reconnue");
                }
            }
            switch (a) {
                case LOUP:
                    grille.addDisque(i, j, (int) (nb / 2), Color.magenta);
                    break;
                case LEOPARD:
                    grille.addDisque(i, j, (int) (nb / 2), Color.yellow);
                    break;
                default:
                    System.out.println("Espèce non reconnue");
            }
        }
    }

    private void initialiserCouplesPP() {
        couplesPP.put(CarnivoreEspece.LOUP.name(), HerbivoreEspece.MOUTON.name());
        couplesPP.put(CarnivoreEspece.LEOPARD.name(), HerbivoreEspece.ECUREUIL.name());
        couplesPP.put(OiseauEspece.MOINEAU.name(), InsecteEspece.CHENILLE.name());
        couplesPP.put(OiseauEspece.CORBEAU.name(), InsecteEspece.COCCINELLE.name());
        
    }

    public void expansionArbres(int i, int j) {
        if (z[i][j].getType().equals("FORET")) {
            double r, r2, r3;
            ArrayList<Zone> zones = new ArrayList<Zone>();

            if (i - 1 >= 0)
                zones.add(z[i - 1][j]);
            if (i + 1 < taille)
                zones.add(z[i + 1][j]);

            if (j - 1 >= 0)
                zones.add(z[i][j - 1]);
            if (j + 1 < taille)
                zones.add(z[i][j + 1]);

            for (int k = 0; k < zones.size(); k++) {
                r = Math.random();
                if(!zones.get(k).getType().equals("DESERT"))
                if (r <= chanceExpansion){
                    r2 = Math.random();
                    r3 = Math.random();
                    if(r2 <= z[i][j].getNombreVegetal("CHENE")/50)
                        zones.get(k).ajouterVegetal(new Arbre("CHENE"));
                    if(r3 <= z[i][j].getNombreVegetal("SAPIN")/50)
                        zones.get(k).ajouterVegetal(new Arbre("SAPIN"));
                }
            }
        }

    }

    public void deplacementAnimaux(int i, int j) {
        ArrayList<Animal> animaux = z[i][j].getListeAnimaux();
        Random random = new Random();
        int r;
        ArrayList<Zone> zones = new ArrayList<Zone>();

        if (i - 1 >= 0)
            zones.add(z[i - 1][j]);
        if (i + 1 < taille)
            zones.add(z[i + 1][j]);

        if (j - 1 >= 0)
            zones.add(z[i][j - 1]);
        if (j + 1 < taille)
            zones.add(z[i][j + 1]);

        for (int k = 0; k < animaux.size(); k++) {
            if (animaux.get(k).seDeplacer()) {
                r = random.nextInt(zones.size());
                zones.get(r).ajouterAnimal(animaux.get(k));
                animaux.remove(animaux.get(k));
            }
        }
    }
}