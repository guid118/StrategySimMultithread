package me.guid118.strategysimulation.gui.controllers;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import me.guid118.strategysimulation.Main;
import me.guid118.strategysimulation.exceptions.UnknownRaceException;
import me.guid118.strategysimulation.files.CSVOutput;
import me.guid118.strategysimulation.gui.GUI;
import me.guid118.strategysimulation.utils.Race;
import me.guid118.strategysimulation.utils.Round;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static me.guid118.strategysimulation.gui.GUI.*;

public class MainController {

    private final ComboBox<String> comboBox = createComboBox(new String[]{"England", "Azerbaijan", "Netherlands"});
    private final GUI gui;

    public MainController(GUI gui) {
        this.gui = gui;
    }

    public Scene createScene() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        GridPane raceSelection = new GridPane();
        raceSelection.add(comboBox, 0, 0);
        raceSelection.add(createButton("open config", this::openConfig), 1, 0);
        root.add(raceSelection, 0, 0);
        root.add(createButton("Start Simulation", this::onStartSimulation), 0, 1);
        root.setBackground(GUI.DEFAULTBACKGROUND);
        return new Scene(root, 640, 480);
    }

    public void openConfig() {
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(new File("config.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onStartSimulation() {
        try {
            Round round = Round.getFromString(comboBox.getSelectionModel().getSelectedItem());
            System.out.println(round.toString());
            GUI.race = gui.config.readFromJsonFile(round);
            GUI.csvOutput = new CSVOutput(race);
        } catch (UnknownRaceException | IOException e) {
            throw new RuntimeException(e);
        }
        GUI.race.doSimulation();
    }
}
