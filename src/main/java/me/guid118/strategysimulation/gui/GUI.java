package me.guid118.strategysimulation.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import me.guid118.strategysimulation.files.CSVOutput;
import me.guid118.strategysimulation.files.JSONConfig;
import me.guid118.strategysimulation.gui.controllers.MainController;
import me.guid118.strategysimulation.utils.Race;

import java.io.IOException;
import java.util.Arrays;

public class GUI extends Application {

    public static final String DEFAULTGRIDPANESTYLE = " -fx-background-radius: 0px; -fx-background-color: #212C38FF; ";
    public static final String DEFAULTLABELSTYLE = " -fx-background-radius: 0px; -fx-font-size: 15px; -fx-text-fill: #ffffff; ";
    public static final String DEFAULTTEXTFIELDSYTLE = " -fx-background-radius: 0px; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-color: #414A5EFF; ";
    public static final String DEFAULTBUTTONSTYLE = " -fx-background-radius: 0px; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-color: #6376A1FF; ";


    public static final Color DEFAULTCOLOR = Color.valueOf("#212C38FF");
    public static final Background DEFAULTBACKGROUND = new Background(new BackgroundFill(DEFAULTCOLOR, null, null));

    public static CSVOutput csvOutput = null;
    public static Race race = null;
    public JSONConfig config;


    @Override
        public void start(Stage stage) {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        try {
            config = new JSONConfig("config.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            stage.setTitle("F1 Strategy Simulator");
            MainController mainController = new MainController(this);
            stage.setScene(mainController.createScene());
            stage.getScene().getWindow().setHeight(400);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }


    /**
     * creates a button with the given text and action.
     *
     * @param text   the text to be displayed in the button
     * @param action the action to be executed when the button is pressed
     * @return the created button
     */
    //@ requires text != null;
    //@ requires action != null;
    public static Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setStyle(DEFAULTBUTTONSTYLE);
        button.setOnAction(event -> action.run());
        button.setAlignment(Pos.CENTER);
        button.setBackground(
                new Background(new BackgroundFill(Paint.valueOf("#6376A1FF"), null, null)));
        return button;
    }

    /**
     * creates a label with the given text, font size and color.
     *
     * @param text     the text to be displayed in the label
     * @param fontSize the font size of the label
     * @param textColor    the color of the label
     * @return the created label
     */
    public static Label createLabel(String text, int fontSize, Color textColor) {
        Label label = new Label(text);
        label.setStyle(DEFAULTLABELSTYLE.replace("#ffffff", textColor.toString())
                .substring(2) + ";" + " -fx-font-size: " + fontSize + "px;");
        label.setAlignment(Pos.CENTER);
        return label;
    }

    /**
     * creates a label with the given text and font size.
     *
     * @param text     the text to be displayed in the label
     * @param fontSize the font size of the label
     * @return the created label
     */
    public static Label createLabel(String text, int fontSize) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    /**
     * creates a text field with the given text. (default colors and font size).
     *
     * @param text the text to be displayed in the text field
     * @return the created text field
     */
    //@ requires text != null;
    public static TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setStyle(DEFAULTTEXTFIELDSYTLE);
        textField.setBackground(
                new Background(new BackgroundFill(Paint.valueOf("#414A5EFF"), null, null)));
        return textField;
    }

    /**
     * creates a toggle button with the given text and action.
     *
     * @param text   the text to be displayed in the toggle button
     * @param action the action to be executed when the toggle button is pressed
     * @return the created toggle button
     */
    //@ requires text != null;
    //@ requires action != null;
    public static ToggleButton createToggleButton(String text, Runnable action) {
        ToggleButton toggleButton = new ToggleButton(text);
        toggleButton.setOnAction(event -> action.run());
        return toggleButton;
    }

    public static ComboBox<String> createComboBox(String[] options) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setBackground(DEFAULTBACKGROUND);
        comboBox.setStyle(DEFAULTLABELSTYLE);
        Arrays.stream(options).forEach(option -> comboBox.getItems().add(option));
        return comboBox;
    }

}
