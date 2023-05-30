
import java.awt.Color;
import java.util.LinkedList;



class CaseGrille{

    private Color c;
    public LinkedList<Disque> lDisques;
    private final int maxDisque = 1000;
    private int nbDisque;

    CaseGrille(){
        lDisques = new LinkedList<Disque>();
        nbDisque = 0;
    }

    public int getnbDisque(){
        return nbDisque;
    }

    public void setCouleur(Color c){this.c=c;}
    public Color getCouleur(){return c;}

    
    public void addDisque(int rayon, Color c){
        if(nbDisque == maxDisque) return;
	    lDisques.add(new Disque(rayon,c, nbDisque));
        nbDisque ++;
    }

    public void retirerDisques(){
        lDisques.clear();
        nbDisque = 0;
    }

}
