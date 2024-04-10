module strategysimulation {
    requires javafx.base;
    requires javafx.controls;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.csv;
    requires javafx.fxml;
    requires java.desktop;

    opens me.guid118.strategysimulation.gui to javafx.fxml;
    opens me.guid118.strategysimulation.files to com.fasterxml.jackson.databind;
    opens me.guid118.strategysimulation.utils to com.fasterxml.jackson.databind;
    exports me.guid118.strategysimulation.gui;
    exports me.guid118.strategysimulation.gui.controllers;
    exports me.guid118.strategysimulation.files;
    exports me.guid118.strategysimulation.exceptions;
    exports me.guid118.strategysimulation.utils;
    opens me.guid118.strategysimulation.gui.controllers to javafx.fxml;
    exports me.guid118.strategysimulation;
    opens me.guid118.strategysimulation to javafx.fxml;
}