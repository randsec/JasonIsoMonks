public class Nodo {
    private int coordenadaX;
    private int coordenadaY;
    private Nodo padre;
    private double H;
    private double G;
    
    public Nodo() {
        this.coordenadaX = -1;
        this.coordenadaY = -1;
        this.padre = null;
    }

    public Nodo(int x, int y) {
            this.coordenadaX = x;
            this.coordenadaY = y;
            this.padre = null;
    }

    public int getX() {
            return coordenadaX;
    }

    public void setX(int coordenadaX) {
            this.coordenadaX = coordenadaX;
    }

    public int getY() {
            return coordenadaY;
    }

    public void setY(int coordenadaY) {
            this.coordenadaY = coordenadaY;
    }

    public Nodo getPadre() {
            return padre;
    }

    public void setPadre(Nodo padre) {
            this.padre = padre;
    }

    public double getH() {
            return H;
    }

    public void setH(int H) {
            this.H = H;
    }

    public double getG() {
        return G;
    }

    public void setG(double G) {
        this.G = G;
    }

    public double getF() {
        return H+G;
    }

    public void calcularG(Nodo nodoMeta) {
        this.G = Math.sqrt(Math.pow((coordenadaX - nodoMeta.getX()), 2) + Math.pow((coordenadaY - nodoMeta.getY()), 2));
    }

    public void calcularH() {
        if (padre != null) {
            this.H = Math.sqrt(Math.pow((coordenadaX - padre.getX()), 2) + Math.pow((coordenadaY - padre.getY()), 2)) + padre.H;
        } else {
            this.H = 0;
        }
    }
    
    public boolean equals(Nodo a) {
        if (this == null || a == null) {
            return false;
        } else {
            return this.getX() == a.getX() && this.getY() == a.getY();
        }
    }
        
}

