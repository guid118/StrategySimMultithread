package me.guid118.strategysimulation;


import java.io.IOException;
import java.util.*;

import me.guid118.strategysimulation.exceptions.UnknownRaceException;
import me.guid118.strategysimulation.files.CSVOutput;
import me.guid118.strategysimulation.files.JSONConfig;
import me.guid118.strategysimulation.utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;



public class Main {

    private static Race race;
    private static JSONConfig config;
    private static CSVOutput csvOutput;

    static {
        try {
            config = new JSONConfig("config.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("What race would you like to simulate?");
                Scanner sc = new Scanner(System.in);
                race = config.readFromJsonFile(Round.getFromString(sc.nextLine()));
            } else {
                race = config.readFromJsonFile(Round.getFromString(args[0]));
            }
            csvOutput = new CSVOutput(race);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        race.doSimulation();
    }


    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}