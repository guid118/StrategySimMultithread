package me.guid118.strategysimulation.utils;

import java.util.*;

public class Race {

    public final String name;
    public final int racelaps;
    public final me.guid118.strategysimulation.utils.Round round;
    private Tire[] tires;
    public final double PitstopTime;
    public final int minPitstops;
    public final int maxPitstops;
    public final double maxRaceTime;
    public final double AvgFuelTime = 1.5;
    public int currentstrat = 0;
    public final List<Strategy> strategies = new ArrayList<>();

    public final int maxResults;


    public Race(Round round, Tire soft, Tire medium, Tire hard, int racelaps, int minPitstops, int maxPitstops, int maxRaceTime, int maxResults, int pitstopTime) {
        this.name = round.toString();
        this.racelaps = racelaps;
        this.round = round;
        this.tires = new Tire[]{soft, medium, hard};
        PitstopTime = pitstopTime;
        this.minPitstops = minPitstops;
        this.maxPitstops = maxPitstops;
        this.maxRaceTime = maxRaceTime;
        this.maxResults = maxResults;
    }

    public synchronized boolean hasNextStrategy() {
        return currentstrat < strategies.size() - 1;
    }

    public void generateStrategies() {
        for (int stops = minPitstops; stops <= maxPitstops; stops++) {
            for (Tire tire0 : tires) {
                for (Tire tire1 : tires) {
                    if (stops >= 2) {
                        for (Tire tire2 : tires) {
                            if (stops == 3) {
                                for (Tire tire3 : tires) {
                                    if (!tire0.equals(tire1) && !tire1.equals(tire2) && !tire2.equals(tire3)) {
                                        Tire[] tires = {tire0, tire1, tire2, tire3};
                                        for (int[] boxlaps : chooseboxlaps(tires)) {
                                            Stint[] stints = new Stint[4];
                                            stints[0] = new Stint(boxlaps[0], tire0, 0);
                                            stints[1] = new Stint(boxlaps[1] - boxlaps[0], tire1,
                                                    boxlaps[0]);
                                            stints[2] = new Stint(boxlaps[2] - boxlaps[1], tire2,
                                                    boxlaps[1]);
                                            stints[3] = new Stint(racelaps - boxlaps[2], tire3,
                                                    boxlaps[2]);
                                            strategies.add(new Strategy(stints));
                                        }
                                    }
                                }
                            } else {
                                if (!tire0.equals(tire1) && !tire1.equals(tire2)) {
                                    Tire[] tires = {tire0, tire1, tire2};
                                    for (int[] boxlaps : chooseboxlaps(tires)) {
                                        Stint[] stints = new Stint[3];
                                        stints[0] = new Stint(boxlaps[0], tire0, 0);
                                        stints[1] = new Stint(boxlaps[1] - boxlaps[0], tire1,
                                                boxlaps[0]);
                                        stints[2] = new Stint(racelaps - boxlaps[1], tire2, boxlaps[1]);
                                        strategies.add(new Strategy(stints));
                                    }
                                }
                            }
                        }
                    } else {
                        if (!tire0.equals(tire1)) {
                            Tire[] tires = {tire0, tire1};
                            for (int[] boxlaps : chooseboxlaps(tires)) {
                                Stint[] stints = new Stint[2];
                                stints[0] = new Stint(boxlaps[0], tire0, 0);
                                stints[1] = new Stint(racelaps - boxlaps[0], tire1, boxlaps[0]);
                                strategies.add(new Strategy(stints));
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Total strategies generated: " + strategies.size());
    }

    private ArrayList<int[]> chooseboxlaps(Tire[] tires) {
        ArrayList<int[]> result = new ArrayList<>();

        for (int boxlap0 = tires[0].getStableDegLap(); boxlap0 < Math.min(tires[0].getFailLap(),
                racelaps -
                        tires[1].getStableDegLap()); boxlap0++) {

            if (tires.length == 2 && boxlap0 > racelaps - tires[1].getFailLap()) {
                int[] boxlaps = new int[1];
                boxlaps[0] = boxlap0;
                result.add(boxlaps);
            } else if (tires.length > 2) {
                for (int boxlap1 = tires[1].getStableDegLap() + boxlap0; boxlap1 < Math.min(
                        tires[1].getFailLap() + boxlap0,
                        racelaps - tires[1].getStableDegLap()); boxlap1++) {

                    if (tires.length == 3 && boxlap1 > racelaps - tires[2].getFailLap()) {
                        int[] boxlaps = new int[2];
                        boxlaps[0] = boxlap0;
                        boxlaps[1] = boxlap1;
                        result.add(boxlaps);
                    } else if (tires.length > 3) {
                        for (int boxlap2 = tires[2].getStableDegLap() + boxlap1; boxlap2 < Math.min(
                                tires[2].getFailLap() + boxlap1,
                                racelaps - tires[2].getStableDegLap()); boxlap2++) {

                            if (boxlap2 > racelaps - tires[3].getFailLap()) {
                                int[] boxlaps = new int[3];
                                boxlaps[0] = boxlap0;
                                boxlaps[1] = boxlap1;
                                boxlaps[2] = boxlap2;
                                result.add(boxlaps);
                            }
                        }
                    }
                }
            }

        }
        return result;
    }


    public synchronized Strategy getNextStrategy() {
        if (this.hasNextStrategy()) {
            Strategy strat = strategies.get(currentstrat);
            currentstrat++;
            return strat;
        } else {
            return null;
        }
    }

}
