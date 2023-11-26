package me.guid118.StrategieSimulatie;




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
    private static final int maxthreads = Runtime.getRuntime().availableProcessors()/4*3;
    //private static final int maxthreads = 0;
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
        createthreads();
        double endTime = System.currentTimeMillis();
        saveResults();
        Write();
        System.out.println("total execution took: " + (round((endTime - startTime)/1000,2) + " seconds"));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static final ThreadedSim[] threads = new ThreadedSim[maxthreads + 1];

    public static void createthreads() {
        double startTime = System.currentTimeMillis();
        race.generateStrategies();
        double endTime = System.currentTimeMillis();
        double generationTime = endTime - startTime;
        System.out.println("generating strategies took: " + generationTime + " milliseconds");
        while (race.hasNextStrategy()) {
            for (int threadnumber = 0; threadnumber <= maxthreads && race.hasNextStrategy(); threadnumber++) {
                if (threads[threadnumber] == null) {
                    threads[threadnumber] = new ThreadedSim(threadnumber, race.getNextStrategy(), race);
                    threads[threadnumber].start();
                }
            }
        }

    }

    public static void remthread(int threadnumber) {
        threads[threadnumber] = null;
    }

    private static String Stringtires(int[] tires) {
        StringBuilder Stringtires = new StringBuilder();
        for (int i = 0; i < tires.length; i++) {
            Stringtires.append(Tire.getString(tires[i]));
            if (!(i == tires.length - 1)) {
                Stringtires.append("-");
            }
        }
        return Stringtires.toString();
    }

    public static List<Result> results = new ArrayList<>();
    public static Result[] sortArray(Result[] results) {
            Merge ob = new Merge();
            ob.sort(results, 0, results.length-1);
            return results;
    }

    private static void saveResults(){
        Result[] resultsA = results.toArray(new Result[] {});
        System.arraycopy(sortArray(resultsA), 0, resultsA, 0, Math.min(race.maxResults, resultsA.length-1));
        for (int i = 0; i < resultsA.length && i < race.maxResults; i++) {
            Result result = resultsA[i];
            Strategy strat = result.getStrategy();
            int[] tires = strat.tires;
            int[] boxlaps = strat.boxlap;
            StringBuilder savedstring = new StringBuilder();
            savedstring.append(Stringtires(tires));
            savedstring.append("\t");
            for (int j = 0; j < race.getMaxpitstops(); j++) {
                if (j < boxlaps.length) {
                    savedstring.append(boxlaps[j]);
                }
                savedstring.append("\t");
            }
            savedstring.append(round(result.getTime(), 2)).append("\t").append(result.getRiskfactor());
            Save(savedstring.toString());

        }
    }
}