public abstract class Vegetal implements LoisDeLaNature{
    protected double tempMax;
    protected double seuilEau;
    protected int seuilBoire;
    protected double tauxReprod;
    protected String nom ;
    protected String habitatNaturel;
    protected int joursSansBoire = 0;
    protected double ConsEau;

    protected boolean estVivant = true;
    
    public boolean getEtat(){
        return estVivant;
    }
    
    public void passerJour(Zone z){
        if(z.getType().equals(habitatNaturel)){
            if(seuilBoire <= joursSansBoire){
                estVivant = false;
            }
        }else{
            if(seuilBoire - 2 <= joursSansBoire){
                estVivant = false;
            }      
        }
        if(z.getTemperature() >= tempMax){
            estVivant = false;
        } 
        joursSansBoire ++;
    }

    public void ConsommerEau(Zone z){
        if(z.removEau(ConsEau))
            joursSansBoire = 0;
    }

    public abstract void seNourrir(Zone z);

    public String getNom(){
        return nom;
    }
    
    public void SeReproduire(Zone z) {          
        double r = Math.random();
        boolean reproduit = false;
        if (z.getType().equals(habitatNaturel))
            if (r <= tauxReprod)
                reproduit = true;
            else if (r <= tauxReprod * 0.8f)
                reproduit = true;

        if (reproduit){
            if(this instanceof Arbre)
                z.ajouterVegetal(new Arbre(nom));
            if(this instanceof Vivace)
                z.ajouterVegetal(new Vivace(nom));
        }
    }
}