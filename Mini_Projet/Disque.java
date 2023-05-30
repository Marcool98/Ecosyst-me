import java.awt.Color;

class Disque {

    private int rayon;
    private Color c;
    private int x, y;
    private int indice;

    Disque(int rayon, Color c, int indice) {
        this.rayon = rayon;
        this.c = c;    
        this.indice = indice;
        x = (int)(Math.random()*100);
		y = (int)(Math.random()*100);
        while(x > 100 - rayon || x < rayon){
            x = (int)(Math.random()*100);
        }
        while(y > 100 - rayon  || y < rayon){
		    y = (int)(Math.random()*100);
        }
        
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public Color getCouleur() {
        return c;
    }

    public void setCouleur(Color c) {
        this.c = c;
    }

    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }

    public void setx(int x){
        this.x = x;
    }

    public void sety(int y){
        this.y = y;
    }

}
