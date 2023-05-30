public interface LoisDeLaNature {
    public void seNourrir(Zone z);
    public void ConsommerEau(Zone z);
    public abstract void SeReproduire(Zone z);
    public void passerJour(Zone z);
    public boolean getEtat();
}
