module strategysimulation {
    requires javafx.base;
    requires javafx.controls;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.csv;
    requires javafx.fxml;
    requires java.desktop;

    opens me.guid118.strategysimulation.gui to javafx.fxml;
    exports me.guid118.strategysimulation.gui;
    exports me.guid118.strategysimulation.gui.controllers;
    opens me.guid118.strategysimulation.gui.controllers to javafx.fxml;
}