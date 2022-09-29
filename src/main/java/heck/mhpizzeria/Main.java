package heck.mhpizzeria;

import heck.mhpizzeria.gui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <h1>Pizzeria</h1>
 * Dieses Programm ermöglicht das Anlegen von Rechnungen für vor-Ort Besteller in einer Pizzeria.
 * Kunden, definiert durch Name und Tischnummer, können im Programm neu angelegt werden,
 * obwohl auch bestehende Kunden ausgewählt werden können.
 * Pizzen müssen in einer Datenbank vordefiniert sein, damit sie zusammen mit einer Anzahl auf einer
 * Rechnung als Position vorkommen können.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setStage(stage);
        SceneManager.getInstance().stageMasterScene();
    }

    public static void main(String[] args) {
        launch();
    }
}