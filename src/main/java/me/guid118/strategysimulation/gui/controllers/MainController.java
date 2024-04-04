package me.guid118.strategysimulation.gui.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import me.guid118.strategysimulation.gui.GUI;

import java.awt.*;
import java.io.File;

import static me.guid118.strategysimulation.gui.GUI.createButton;

public class MainController {
    public void openConfig() {
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(new File("config.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene createScene() {
        GridPane root = new GridPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        javafx.scene.control.Button configButton = createButton("open config", this::openConfig);
        root.add(configButton, 0, 0);
        root.setBackground(GUI.DEFAULTBACKGROUND);
        return new Scene(root, 640, 480);
    }
}
