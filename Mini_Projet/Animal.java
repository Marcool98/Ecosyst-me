public abstract class Animal implements LoisDeLaNature{
    protected String habitatNaturel;
    protected double chanceDeplacer;
    protected String nom ; 
    protected double tauxReprod;
    protected double tauxChasse;
    protected int seuilManger;
    protected int seuilBoire;
    protected int joursSansManger = 0;
    protected int joursSansBoire = 0;
    protected double ConsEau = 0.5f;
    protected boolean estVivant = true;
    protected boolean moved = false;

    public boolean getEtat(){
        return estVivant;
    }

    public void passerJour(Zone z){
        if(this instanceof Insecte)
            return;
        if (z.getType().equals(habitatNaturel)) {
            if (seuilManger <= joursSansManger || seuilBoire <= joursSansBoire) {
                estVivant = false;
            }
        } else {
            if (seuilManger - 1 <= joursSansManger || seuilBoire - 1 <= joursSansBoire) {
                estVivant = false;
            }
        }
        moved = false;
        joursSansManger ++;
        joursSansBoire ++;
    }

    public void SeReproduire(Zone z) {
        double r = Math.random();
        boolean reproduit = false;
        if (z.getType().equals(habitatNaturel))
            if (r <= tauxReprod)
                reproduit = true;
            else if (r <= tauxReprod * 0.8f)
                reproduit = true;

        if (reproduit)
        if (z.getCouples().containsKey(nom)) {
            if(z.getNombreAnimal(nom) > 1 && z.getNombreAnimal(z.getCouples().get(nom)) > 0){
                if(this instanceof Carnivore)
                    z.ajouterAnimal(new Carnivore(nom));
                if(this instanceof Oiseau)
                    z.ajouterAnimal(new Oiseau(nom));
                if(this instanceof Herbivore)
                    z.ajouterAnimal(new Herbivore(nom));
                if(this instanceof Insecte)
                    z.ajouterAnimal(new Insecte(nom));

            }
        }else{
            if(z.getNombreAnimal(nom) > 1){
                if (this instanceof Carnivore)
                    z.ajouterAnimal(new Carnivore(nom));
                if (this instanceof Oiseau)
                    z.ajouterAnimal(new Oiseau(nom));
                if (this instanceof Herbivore)
                    z.ajouterAnimal(new Herbivore(nom));
                if (this instanceof Insecte)
                    z.ajouterAnimal(new Insecte(nom));
            }
        }
    }

    public void seNourrir(Zone z){
        double r = Math.random();
        boolean chasse = false;
        if (z.getType().equals(habitatNaturel))
                chasse = true;
        else if (r <= tauxChasse* 0.8f)
            chasse = true;

        if (chasse) {
            if (z.getCouples().containsKey(nom)) {
                if(z.retirerAnimal(z.getCouples().get(nom)))
                    joursSansManger = 0;
            }
        }
    }

    public void ConsommerEau(Zone z){
        if(z.removEau(ConsEau))
            joursSansBoire = 0;
    }

    
    public boolean seDeplacer(){
        double x = Math.random();
        if( x <= chanceDeplacer && !moved){
            moved = true;
            return true;
        }
        return false;
    }

    public double getChanceDeplacer(){
        return chanceDeplacer;
    }

    public String getNom(){
        return nom;
    }
}

