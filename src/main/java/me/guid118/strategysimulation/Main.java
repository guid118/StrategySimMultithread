package me.guid118.strategysimulation;


import java.io.IOException;
import java.util.*;

import me.guid118.strategysimulation.files.CSVOutput;
import me.guid118.strategysimulation.files.JSONConfig;
import me.guid118.strategysimulation.utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;



public class Main {
    private static final int maxthreads = Runtime.getRuntime().availableProcessors() / 4 * 3;
    //private static final int maxthreads = 100;
    private static final ThreadedSim[] threads = new ThreadedSim[maxthreads + 1];
    public static Race race;
    public static JSONConfig config;
    public static CSVOutput csvOutput;

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
            csvOutput = new CSVOutput();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        double startTime = System.currentTimeMillis();
        generateStrategies();
        createthreads();
        double endTime = System.currentTimeMillis();
        for (ThreadedSim thread : threads) {
            try {
                if (thread != null) {
                    thread.getThread().join();
                }
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
        saveResults();
        System.out.println(
                "total execution took: " + (round((endTime - startTime) / 1000, 2) + " seconds"));
    }


    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    private static void createthreads() {
        for (int threadnumber = 0; threadnumber < maxthreads; threadnumber++) {
            threads[threadnumber] = new ThreadedSim(threadnumber, race);
            threads[threadnumber].start();
        }

    }

    private static void generateStrategies() {
        double startTime = System.currentTimeMillis();
        race.generateStrategies();
        double endTime = System.currentTimeMillis();
        double generationTime = endTime - startTime;
        System.out.println("generating strategies took: " + generationTime + " milliseconds");
    }

    public static void remthread(int threadnumber) {
        threads[threadnumber] = null;
    }

    public static List<Result> results = new ArrayList<>();

    public static synchronized void addResult(Result result) {
        results.add(result);
    }

    private static void saveResults() {
        results.sort(Comparator.comparingDouble(Result::getTime));
        for (int i = 0; i < results.size() && i < race.maxResults; i++) {
            Result result = results.get(i);
            csvOutput.Save((Object[]) result.toStringArray());
        }
    }
}