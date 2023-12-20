package me.guid118.StrategieSimulatie;


import java.util.Comparator;
import me.guid118.StrategieSimulatie.Files.Config;
import me.guid118.StrategieSimulatie.Files.Output;
import me.guid118.StrategieSimulatie.utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static me.guid118.StrategieSimulatie.Files.Output.*;

public class Main {
    private static final int maxthreads = Runtime.getRuntime().availableProcessors() / 4 * 3;
    //private static final int maxthreads = 100;
    private static final ThreadedSim[] threads = new ThreadedSim[maxthreads + 1];
    public static OrderedProperties prop = new OrderedProperties();
    public static Race race;

    public static void main(String[] args) {
        if (args.length == 0) {
            Config.CreateFile();
            System.out.println("What race would you like to simulate?");
            Scanner sc = new Scanner(System.in);
            Config.getvalues(sc.nextLine());
        } else {
            Config.getvalues(args[0]);
        }
        double startTime = System.currentTimeMillis();
        Output.CreateFile();
        generateStrategies();
        createthreads();
        double endTime = System.currentTimeMillis();
        saveResults();
        Write();
        System.out.println(
                "total execution took: " + (round((endTime - startTime) / 1000, 2) + " seconds"));
    }

    public static double round(double value, int places) {
        if (places < 0) { throw new IllegalArgumentException(); }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



    private static void createthreads() {

        while (race.hasNextStrategy()) {
            for (int threadnumber = 0; threadnumber < maxthreads; threadnumber++) {
                threads[threadnumber] = new ThreadedSim(threadnumber, race);
                threads[threadnumber].start();
            }
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
            Save(result.toString());
        }
    }
}