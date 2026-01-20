
package ec.edu.espoch.simplecircularlist.controller;

import ec.edu.espoch.simplecircularlist.model.ListaCircularSimple;
import ec.edu.espoch.simplecircularlist.view.ListaCircularCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class CircularListController  {
    @FXML
    private TextField txtValue;

    @FXML
    private TextField txtReferencia;

    @FXML
    private StackPane canvasHolder;

    private ListaCircularCanvas canvas;
    private final ListaCircularSimple lista = new ListaCircularSimple();

    @FXML
    private void initialize() {
        canvas = new ListaCircularCanvas();
        canvasHolder.getChildren().add(canvas);
        refreshView();
    }

    @FXML
    private void insertarInicio() {
        Integer v = readInt(txtValue);
        if (v == null) return;

        lista.insertarInicio(v);
        refreshView();
    }

    @FXML
    private void insertarFinal() {
        Integer v = readInt(txtValue);
        if (v == null) return;

        lista.insertarFinal(v);
        refreshView();
    }

    @FXML
    private void insertarDespues() {
        Integer ref = readInt(txtReferencia);
        Integer v = readInt(txtValue);
        if (ref == null || v == null) return;

        lista.insertarDespues(ref, v);
        refreshView();
    }

    @FXML
    private void buscar() {
        Integer v = readInt(txtValue);
        if (v == null) return;

        int idx = lista.buscar(v);
        canvas.setHighlightIndex(idx);
        refreshView();
    }

    @FXML
    private void eliminar() {
        Integer v = readInt(txtValue);
        if (v == null) return;

        lista.eliminar(v);
        canvas.clearHighlight();
        refreshView();
    }

    private Integer readInt(TextField txt) {
        try {
            String s = txt.getText();
            if (s == null || s.trim().isEmpty()) return null;
            return Integer.valueOf(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void refreshView() {
        canvas.setValues(lista.toList());
        canvas.render();
    }
}
