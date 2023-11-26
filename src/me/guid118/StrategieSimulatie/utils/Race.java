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


    public Race(String name, double SoftBaseLapTime, int SoftFailLap, double SoftDegradation, int SoftStableDeglap, int SoftFastDegLap,
                double MediumBaseLapTime, int MediumFailLap, double MediumDegradation, int MediumStableDeglap, int MediumFastDegLap,
                double HardBaseLapTime, int HardFailLap, double HardDegradation, int HardStableDegLap, int HardFastDegLap,
                double PitstopTime, int minPitstops, int maxPitstops, double maxRaceTime, int Laps, int maxResults) {
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
    
    public int getFailLap(int tiretype) {
        if (tiretype == 5) {
            return SoftFailLap;
        } else if (tiretype == 4) {
            return MediumFailLap;
        } else if (tiretype == 3) {
            return HardFailLap;
        } else return -1;
    }

    public double getBaseLapTime(int tiretype) {
        if (tiretype == 5) {
            return SoftBaseLapTime;
        } else if (tiretype == 4) {
            return MediumBaseLapTime;
        } else if (tiretype == 3) {
            return HardBaseLapTime;
        } else return -1;
    }

    public double getDegradation(int tiretype) {
        if (tiretype == 5) {
            return SoftDegradation;
        } else if (tiretype == 4) {
            return MediumDegradation;
        } else if (tiretype == 3) {
            return HardDegradation;
        } else return -1;
    }
    
    public double getPitstopTime() {return this.PitstopTime;}
    
    public int getMaxpitstops() { return this.maxPitstops;}
    
    public double getMaxRaceTime() { return this.maxRaceTime;}

    public boolean hasNextStrategy() {
        return currentstrat < strategies.size() - 1;
    }

    public void generateStrategies() {
        List<Integer> tireOptions = new ArrayList<>();
        tireOptions.add(3);
        tireOptions.add(4);
        tireOptions.add(5);
        for (int stops = minPitstops; stops <= maxPitstops; stops++) {
            for (int tire0 : tireOptions) {
                for (int tire1 : tireOptions) {
                    if (stops >= 2) {
                        for (int tire2 : tireOptions) {
                            if (stops == 3) {
                                for (int tire3 : tireOptions) {
                                    if (tire0 == tire1 && tire1 == tire2 && tire2 == tire3) break;
                                    int[] tires = new int[4];
                                    tires[0] = tire0;
                                    tires[1] = tire1;
                                    tires[2] = tire2;
                                    tires[3] = tire3;
                                    for (int[] boxlaps : chooseboxlaps(tires)) {
                                        strategies.add(new Strategy(tires, stops, boxlaps));
                                    }
                                }
                            } else {
                                if (tire0 == tire1 && tire1 == tire2) break;
                                int[] tires = new int[3];
                                tires[0] = tire0;
                                tires[1] = tire1;
                                tires[2] = tire2;
                                for (int[] boxlaps : chooseboxlaps(tires)) {
                                    strategies.add(new Strategy(tires, stops, boxlaps));
                                }
                            }
                        }
                    } else {
                        if (tire0 == tire1) break;
                        int[] tires = new int[2];
                        tires[0] = tire0;
                        tires[1] = tire1;
                        for (int[] boxlaps : chooseboxlaps(tires)) {
                            strategies.add(new Strategy(tires, stops, boxlaps));
                        }
                    }
                }
            }
        }
        System.out.println("Total strategies generated: " + strategies.size());
    }

    private ArrayList<int[]> chooseboxlaps(int[] tires) {
        ArrayList<int[]> result = new ArrayList<>();

        for (int boxlap0 = getStableDegLap(tires[0]);
             boxlap0 <= Math.min(getFailLap(tires[0]), racelaps - getStableDegLap(tires[1]));
             boxlap0++) {

            if (tires.length == 2 && boxlap0 >= racelaps - getFailLap(tires[1])) {
                int[] boxlaps = new int[1];
                boxlaps[0] = boxlap0;
                result.add(boxlaps);
            } else if (tires.length > 2){
                for (int boxlap1 = getStableDegLap(tires[1]) + boxlap0;
                     boxlap1 <= Math.min(getFailLap(tires[1]) + boxlap0, racelaps - getStableDegLap(tires[1]));
                     boxlap1++) {

                    if (tires.length == 3 && boxlap1 >= racelaps - getFailLap(tires[2])) {
                        int[] boxlaps = new int[2];
                        boxlaps[0] = boxlap0;
                        boxlaps[1] = boxlap1;
                        result.add(boxlaps);
                    } else if (tires.length > 3){
                        for (int boxlap2 = getStableDegLap(tires[2]) + boxlap1;
                             boxlap2 <= Math.min(getFailLap(tires[2]) + boxlap1, racelaps - getStableDegLap(tires[2]));
                             boxlap2++) {

                            if (boxlap2 >= racelaps - getFailLap(tires[3])) {
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


    public Strategy getNextStrategy() {
            Strategy strat = strategies.get(currentstrat);
            currentstrat++;
            return strat;
    }

    public int getStableDegLap(int tiretype) {
        if (tiretype == 5) {
            return SoftStableDegLap;
        } else if (tiretype == 4) {
            return MediumStableDegLap;
        } else if (tiretype == 3) {
            return HardStableDegLap;
        } else return -1;
    }

    public int getFastDegLap(int tiretype) {
        if (tiretype == 5) {
            return SoftFastDegLap;
        } else if (tiretype == 4) {
            return MediumFastDegLap;
        } else if (tiretype == 3) {
            return HardFastDegLap;
        } else return -1;
    }

}
