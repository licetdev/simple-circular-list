package ec.edu.espoch.simplecircularlist.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Collections;
import java.util.List;

public class ListaCircularCanvas extends Canvas {

    private List<Integer> values = Collections.emptyList();
    private int highlightIndex = -1;

    private final double nodeW = 90;
    private final double nodeH = 45;
    private final double spacing = 50;
    private final double y = 180;
    private final double margin = 40;

    public ListaCircularCanvas() {
        setWidth(1200);
        setHeight(380);
    }

    public void setValues(List<Integer> values) {
        this.values = (values == null) ? Collections.emptyList() : values;
    }

    public void setHighlightIndex(int idx) {
        this.highlightIndex = idx;
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
    }

    public void render() {
        GraphicsContext g = getGraphicsContext2D();

        // Fondo
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Título
        g.setFill(Color.BLACK);
        g.setFont(Font.font(16));
        g.fillText("Lista Circular Simple", margin, 28);

        if (values.isEmpty()) {
            g.setFont(Font.font(14));
            g.setFill(Color.GRAY);
            g.fillText("La lista está vacía", margin, y);
            return;
        }

        double x = margin;

        // Guardamos referencias del primer nodo
        double firstNodeCenterX = margin + nodeW / 2;
        double firstNodeTopY = y;

        // Dibujar nodos y flechas normales
        for (int i = 0; i < values.size(); i++) {
            boolean hl = (i == highlightIndex);
            drawNode(g, x, y, values.get(i), hl);

            if (i < values.size() - 1) {
                drawArrow(
                        g,
                        x + nodeW,
                        y + nodeH / 2,
                        x + nodeW + spacing,
                        y + nodeH / 2
                );
            }

            x += nodeW + spacing;
        }

        // Coordenadas del último nodo
        double lastNodeCenterX = x - spacing + nodeW / 2;
        double lastNodeTopY = y;

        // Flecha circular (último → primero)
        drawCircularArrow(
                g,
                lastNodeCenterX,
                lastNodeTopY,
                firstNodeCenterX,
                firstNodeTopY
        );
    }

    // ================================
    // DIBUJO DE NODO
    // ================================
    private void drawNode(GraphicsContext g, double x, double y, int dato, boolean highlighted) {
        g.setStroke(highlighted ? Color.DODGERBLUE : Color.BLACK);
        g.setLineWidth(highlighted ? 3 : 2);
        g.strokeRoundRect(x, y, nodeW, nodeH, 14, 14);

        // Separador dato | next
        g.strokeLine(x + nodeW * 0.6, y, x + nodeW * 0.6, y + nodeH);

        // Dato
        g.setFont(Font.font(14));
        g.setFill(Color.BLACK);
        g.fillText(String.valueOf(dato), x + 20, y + 28);

        // Next
        g.setFont(Font.font(10));
        g.setFill(Color.DODGERBLUE);
        g.fillText("next", x + nodeW * 0.65, y + 18);

        if (highlighted) {
            g.setFont(Font.font(10));
            g.fillText("FOUND", x + 6, y - 6);
        }
    }

    // ================================
    // FLECHA NORMAL
    // ================================
    private void drawArrow(GraphicsContext g,
                           double x1, double y1,
                           double x2, double y2) {
        g.setStroke(Color.DODGERBLUE);
        g.setLineWidth(2);
        g.strokeLine(x1, y1, x2, y2);
        arrowHead(g, x1, y1, x2, y2);
    }

    // ================================
    // FLECHA CIRCULAR (ÚLTIMO → PRIMERO)
    // ================================
    private void drawCircularArrow(GraphicsContext g,
                                   double startX, double startY,
                                   double endX, double endY) {

        g.setStroke(Color.FORESTGREEN);
        g.setLineWidth(2);

        double controlY = startY - 90;

        g.beginPath();
        g.moveTo(startX, startY);
        g.quadraticCurveTo(
                (startX + endX) / 2,
                controlY,
                endX,
                endY
        );
        g.stroke();

        arrowHead(g,
                (startX + endX) / 2,
                controlY,
                endX,
                endY
        );
    }

    // ================================
    // PUNTA DE FLECHA
    // ================================
    private void arrowHead(GraphicsContext g,
                           double x1, double y1,
                           double x2, double y2) {

        double angle = Math.atan2(y2 - y1, x2 - x1);
        double len = 10;

        g.strokeLine(
                x2, y2,
                x2 - len * Math.cos(angle - Math.PI / 8),
                y2 - len * Math.sin(angle - Math.PI / 8)
        );

        g.strokeLine(
                x2, y2,
                x2 - len * Math.cos(angle + Math.PI / 8),
                y2 - len * Math.sin(angle + Math.PI / 8)
        );
    }
}
