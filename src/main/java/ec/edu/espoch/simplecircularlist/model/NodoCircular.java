package ec.edu.espoch.simplecircularlist.model;

public class NodoCircular {
    int dato;
    NodoCircular next;

    NodoCircular(int dato) {
        this.dato = dato;
        this.next = this; // circular por defecto
    }
}
