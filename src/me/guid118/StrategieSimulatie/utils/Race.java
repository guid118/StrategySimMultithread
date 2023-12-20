package me.guid118.StrategieSimulatie.utils;

import java.util.*;

public class Race {

    public final String name;
    public final int racelaps;


    public int maxpitstops;
    public final int SoftFailLap;
    public final int MediumFailLap;
    public final int HardFailLap;
    public final double SoftBaseLapTime;
    public final double MediumBaseLapTime;
    public final double HardBaseLapTime;
    public final double SoftDegradation;
    public final double MediumDegradation;
    public final double HardDegradation;
    public final double PitstopTime;
    public final int minPitstops;
    public final int maxPitstops;
    public final double maxRaceTime;
    public final double AvgFuelTime = 1.5;
    public int currentstrat = 0;
    public final List<Strategy> strategies = new ArrayList<>();
    private final int SoftStableDegLap;
    private final int MediumStableDegLap;
    private final int HardStableDegLap;
    private final int SoftFastDegLap;
    private final int MediumFastDegLap;
    private final int HardFastDegLap;

    public final int maxResults;


    public Race(String name, double SoftBaseLapTime, int SoftFailLap, double SoftDegradation, int SoftStableDeglap, int SoftFastDegLap, double MediumBaseLapTime, int MediumFailLap, double MediumDegradation, int MediumStableDeglap, int MediumFastDegLap, double HardBaseLapTime, int HardFailLap, double HardDegradation, int HardStableDegLap, int HardFastDegLap, double PitstopTime, int minPitstops, int maxPitstops, double maxRaceTime, int Laps, int maxResults) {
        this.name = name;
        this.SoftBaseLapTime = SoftBaseLapTime;
        this.SoftFailLap = SoftFailLap;
        this.SoftDegradation = SoftDegradation;
        this.SoftStableDegLap = SoftStableDeglap;
        this.SoftFastDegLap = SoftFastDegLap;

        this.MediumBaseLapTime = MediumBaseLapTime;
        this.MediumFailLap = MediumFailLap;
        this.MediumDegradation = MediumDegradation;
        this.MediumStableDegLap = MediumStableDeglap;
        this.MediumFastDegLap = MediumFastDegLap;

        this.HardBaseLapTime = HardBaseLapTime;
        this.HardFailLap = HardFailLap;
        this.HardDegradation = HardDegradation;
        this.HardStableDegLap = HardStableDegLap;
        this.HardFastDegLap = HardFastDegLap;

        this.PitstopTime = PitstopTime;
        this.minPitstops = minPitstops;
        this.maxPitstops = maxPitstops;
        this.maxRaceTime = maxRaceTime;
        this.racelaps = Laps;
        this.maxResults = maxResults;

    }

    public int getFailLap(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return SoftFailLap;
        } else if (tiretype == TireType.MEDIUM) {
            return MediumFailLap;
        } else if (tiretype == TireType.HARD) {
            return HardFailLap;
        } else {
            return -1;
        }
    }

    public double getBaseLapTime(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return SoftBaseLapTime;
        } else if (tiretype == TireType.MEDIUM) {
            return MediumBaseLapTime;
        } else if (tiretype == TireType.HARD) {
            return HardBaseLapTime;
        } else {
            return -1;
        }
    }

    public double getDegradation(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return SoftDegradation;
        } else if (tiretype == TireType.MEDIUM) {
            return MediumDegradation;
        } else if (tiretype == TireType.HARD) {
            return HardDegradation;
        } else {
            return -1;
        }
    }

    public double getPitstopTime() {
        return this.PitstopTime;
    }

    public int getMaxpitstops() {
        return this.maxPitstops;
    }

    public double getMaxRaceTime() {
        return this.maxRaceTime;
    }

    public synchronized boolean hasNextStrategy() {
        return currentstrat < strategies.size() - 1;
    }

    public void generateStrategies() {
        for (int stops = minPitstops; stops <= maxPitstops; stops++) {
            for (TireType tire0 : TireType.values()) {
                for (TireType tire1 : TireType.values()) {
                    if (stops >= 2) {
                        for (TireType tire2 : TireType.values()) {
                            if (stops == 3) {
                                for (TireType tire3 : TireType.values()) {
                                    if (tire0 != tire1 && tire1 != tire2 && tire2 != tire3) {
                                        TireType[] tires = {tire0, tire1, tire2, tire3};
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
                                if (tire0 != tire1 && tire1 != tire2) {
                                    TireType[] tires = {tire0, tire1, tire2};
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
                        if (tire0 != tire1) {
                            TireType[] tires = {tire0, tire1};
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

    private ArrayList<int[]> chooseboxlaps(TireType[] tires) {
        ArrayList<int[]> result = new ArrayList<>();

        for (int boxlap0 = getStableDegLap(tires[0]); boxlap0 < Math.min(getFailLap(tires[0]),
                racelaps - getStableDegLap(
                        tires[1])); boxlap0++) {

            if (tires.length == 2 && boxlap0 > racelaps - getFailLap(tires[1])) {
                int[] boxlaps = new int[1];
                boxlaps[0] = boxlap0;
                result.add(boxlaps);
            } else if (tires.length > 2) {
                for (int boxlap1 = getStableDegLap(tires[1]) + boxlap0; boxlap1 < Math.min(
                        getFailLap(tires[1]) + boxlap0,
                        racelaps - getStableDegLap(tires[1])); boxlap1++) {

                    if (tires.length == 3 && boxlap1 > racelaps - getFailLap(tires[2])) {
                        int[] boxlaps = new int[2];
                        boxlaps[0] = boxlap0;
                        boxlaps[1] = boxlap1;
                        result.add(boxlaps);
                    } else if (tires.length > 3) {
                        for (int boxlap2 = getStableDegLap(tires[2]) + boxlap1; boxlap2 < Math.min(
                                getFailLap(tires[2]) + boxlap1,
                                racelaps - getStableDegLap(tires[2])); boxlap2++) {

                            if (boxlap2 > racelaps - getFailLap(tires[3])) {
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

    public int getStableDegLap(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return SoftStableDegLap;
        } else if (tiretype == TireType.MEDIUM) {
            return MediumStableDegLap;
        } else if (tiretype == TireType.HARD) {
            return HardStableDegLap;
        } else {
            return -1;
        }
    }

    public int getFastDegLap(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return SoftFastDegLap;
        } else if (tiretype == TireType.MEDIUM) {
            return MediumFastDegLap;
        } else if (tiretype == TireType.HARD) {
            return HardFastDegLap;
        } else {
            return -1;
        }
    }

}
