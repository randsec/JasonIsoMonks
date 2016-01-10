import java.util.ArrayList;

public class AEstrella {
	private int ancho, alto;
    private int filas;
    private int columnas;
    //private VentanaGUI v;
    private estado status;
    
    private Nodo salida;
    private Nodo meta;
    
    private static ArrayList<Nodo> abiertos;
    private static ArrayList<Nodo> cerrados;
    
    private static ArrayList<Nodo> obstaculos;
    private static ArrayList<Nodo> path;
    
    public enum estado {
        nulo, salida, meta, obstaculo 
    }
    
    public void setStatus(estado status) {
        this.status = status;
    }
    
    public boolean haySalida() {
        return salida.getX() != -1;
    }

    public boolean hayMeta() {
        return meta.getX() != -1;
    }
    
    public void ponerObstaculos(boolean b){
        if (b) {status = estado.obstaculo;}
        else {status = estado.nulo; }
    }
    
    public void ejecutar() {
        Nodo nodoMeta = buscaNodo(abiertos, meta);
        Nodo nodoSalida = buscaNodo(cerrados, salida);
        
        ArrayList<Nodo> auxAbiertos = (ArrayList<Nodo>) abiertos.clone();
        ArrayList<Nodo> auxCerrados = (ArrayList<Nodo>) cerrados.clone();
        ArrayList<Nodo> ida = camino(nodoSalida, nodoMeta);
        abiertos = (ArrayList<Nodo>) auxAbiertos.clone();
        cerrados = (ArrayList<Nodo>) auxCerrados.clone();
        cerrar(nodoMeta);
        abrir(nodoSalida);
        ArrayList<Nodo> vuelta = camino(nodoMeta, nodoSalida);
        abiertos = (ArrayList<Nodo>) auxAbiertos.clone();
        cerrados = (ArrayList<Nodo>) auxCerrados.clone();
        cerrar(nodoSalida);
        abrir(nodoMeta);
        
        double idaUltimoH = ida.get(ida.size()-1).getH();
        double vueltaUltimoH = vuelta.get(vuelta.size()-1).getH();
        boolean idaMenorCoste = idaUltimoH < vueltaUltimoH;
        boolean idaLlegaAMeta = ida.get(ida.size()-1).equals(nodoMeta);
        boolean vueltaLlegaASalida = vuelta.get(vuelta.size()-1).equals(nodoSalida);
        boolean idaMenosMovimientos = ida.size() <= vuelta.size();
        
        if ((idaMenosMovimientos && idaMenorCoste) || !vueltaLlegaASalida) { 
            path = ida; 
        } else { 
            path = vuelta; 
        }

        if (!idaLlegaAMeta) {
            System.out.println("No hay camino posible");
            borrarCamino();
        } else {
            System.out.println("Camino encontrado");
        }
        
    }
    
    private ArrayList<Nodo> camino(Nodo nodoSalida, Nodo nodoMeta) {
        ArrayList<Nodo> c = new ArrayList<Nodo>();
        
        Nodo nodoActual = nodoSalida;
        nodoActual.calcularH();
        nodoActual.calcularG(nodoMeta);
        cerrar(nodoActual);
        c.add(nodoActual);
        
        boolean imposible = false;
        while(nodoActual != nodoMeta && !imposible) {
            Nodo nodoPadre = nodoActual;
            ArrayList<Nodo> adys = calcularAdys(nodoActual);
            nodoActual = buscarNodoSiguiente(adys, nodoMeta);                 
            
            if (nodoActual == null) {
                if (nodoPadre != nodoSalida) {
                    c.remove(nodoPadre);
                    nodoActual = nodoPadre.getPadre();
                } else {
                    imposible = true;
                }
            } else {
                //////////////////////////////////
                // RECTIFICADOR DE ESQUINAS 2x2 //
                //////////////////////////////////
                for (int i = c.size()-2; i >= 0; i--) {   
                    Nodo nodoAbuelo = c.get(i);
                    if (nodoAbuelo != null) {
                        boolean esAdyacenteNodoAbuelo = adyacente(nodoActual, nodoAbuelo);
                        if (esAdyacenteNodoAbuelo) {
                            c.remove(nodoPadre);
                            nodoActual.setPadre(nodoAbuelo);
                            nodoPadre = nodoAbuelo;
                        }
                    }
                }
                
                cerrar(nodoActual);
                c.add(nodoActual);
            }
        }
        return c;
    }
    
    private Nodo buscaNodo(ArrayList<Nodo> a, Nodo n) {
        Nodo res = null;
        int x = 0;
        while (res == null && x < a.size()) {
            if (a.get(x).equals(n)) {
                res = a.get(x);
            }
            x++;
        }
        return res;
    }
    
    private ArrayList<Nodo> calcularAdys(Nodo nodoActual) {
        ArrayList<Nodo> adys = new ArrayList<Nodo>();
        for(int ix = -1; ix < 2; ix++) {
            for (int iy = -1; iy < 2; iy++) {
                Nodo a = buscaNodo(abiertos, new Nodo(nodoActual.getX()+ix, nodoActual.getY()+iy));
                if (a != null) {
                    a.setPadre(nodoActual);
                    a.calcularH();
                    adys.add(a);
                }
            }
        }
        return adys;
    }

    private Nodo buscarNodoSiguiente(ArrayList<Nodo> adys, Nodo nodoSalida) {
        for (Nodo n : adys) {
            n.calcularH();
            n.calcularG(nodoSalida);
        }
        double min = 99999999;
        Nodo nmin = null;
        for (Nodo n : adys) {
            if (n.getF() < min) {
                min = n.getF();
                nmin = n;
            }
        }
        return nmin;
    }

    private void cerrar(Nodo nodoActual) {
        abiertos.remove(nodoActual);
        cerrados.add(nodoActual);
    }
    
    private void abrir(Nodo nodoActual) {
        cerrados.remove(nodoActual);
        abiertos.add(nodoActual);
    }

    private boolean adyacente(Nodo nodoSiguiente, Nodo p) {
        boolean encontrado = false;
        ArrayList<Nodo> adys = calcularAdys(p);
        int i = 0;
        while (!encontrado && i < adys.size()) {
            if (adys.get(i).equals(nodoSiguiente)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }
    
    private void borrarCamino() {
        path = new ArrayList<Nodo>();
    }
}
