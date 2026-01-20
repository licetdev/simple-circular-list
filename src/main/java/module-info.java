module ec.edu.espoch.simplecircularlist {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espoch.simplecircularlist.controller to javafx.fxml;
    exports ec.edu.espoch.simplecircularlist;
    exports ec.edu.espoch.simplecircularlist.controller;
    exports ec.edu.espoch.simplecircularlist.model;
    exports ec.edu.espoch.simplecircularlist.view;
}
