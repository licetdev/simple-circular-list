package ec.edu.espoch.simplecircularlist.model;

import java.util.ArrayList;
import java.util.List;

public class ListaCircularSimple {

    private NodoCircular head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    // ================================
    // INSERTAR AL INICIO
    // ================================
    public void insertarInicio(int dato) {
        NodoCircular nuevo = new NodoCircular(dato);

        if (isEmpty()) {
            head = nuevo;
        } else {
            NodoCircular tail = head;
            while (tail.next != head) {
                tail = tail.next;
            }
            nuevo.next = head;
            tail.next = nuevo;
            head = nuevo;
        }
        size++;
    }

    // ================================
    // INSERTAR AL FINAL
    // ================================
    public void insertarFinal(int dato) {
        NodoCircular nuevo = new NodoCircular(dato);

        if (isEmpty()) {
            head = nuevo;
        } else {
            NodoCircular tail = head;
            while (tail.next != head) {
                tail = tail.next;
            }
            tail.next = nuevo;
            nuevo.next = head;
        }
        size++;
    }

    // ================================
    // INSERTAR DESPUÉS DE UN VALOR
    // ================================
    public boolean insertarDespues(int referencia, int dato) {
        if (isEmpty()) return false;

        NodoCircular aux = head;

        do {
            if (aux.dato == referencia) {
                NodoCircular nuevo = new NodoCircular(dato);
                nuevo.next = aux.next;
                aux.next = nuevo;
                size++;
                return true;
            }
            aux = aux.next;
        } while (aux != head);

        return false;
    }

    // ================================
    // BUSCAR
    // ================================
    public int buscar(int dato) {
        if (isEmpty()) return -1;

        NodoCircular aux = head;
        int index = 0;

        do {
            if (aux.dato == dato) {
                return index;
            }
            aux = aux.next;
            index++;
        } while (aux != head);

        return -1;
    }

    // ================================
    // ELIMINAR
    // ================================
    public boolean eliminar(int dato) {
        if (isEmpty()) return false;

        NodoCircular actual = head;
        NodoCircular anterior = null;

        do {
            if (actual.dato == dato) {

                // solo un nodo
                if (actual.next == head && actual == head) {
                    head = null;
                }
                // eliminar head
                else if (actual == head) {
                    NodoCircular tail = head;
                    while (tail.next != head) {
                        tail = tail.next;
                    }
                    head = head.next;
                    tail.next = head;
                }
                // eliminar intermedio o final
                else {
                    anterior.next = actual.next;
                }

                size--;
                return true;
            }

            anterior = actual;
            actual = actual.next;

        } while (actual != head);

        return false;
    }

    // ================================
    // PARA EL CANVAS
    // ================================
    public List<Integer> toList() {
        List<Integer> list = new ArrayList<>();
        if (isEmpty()) return list;

        NodoCircular aux = head;
        do {
            list.add(aux.dato);
            aux = aux.next;
        } while (aux != head);

        return list;
    }

    public int size() {
        return size;
    }
}
