package me.guid118.strategysimulation.utils;

import me.guid118.strategysimulation.LapTime;

public class Strategy {

    private final double avgFuelTime;
    public Stint[] stints;
    public int currentStint;

    /**
     * Construct a new Strategy
     * @param stints integer with the amount of stops to be made
     */
    public Strategy(Stint[] stints, double avgFuelTime) {
        this.stints = stints;
        this.avgFuelTime = avgFuelTime;
    }


    public double run() {
        double result = 0;
        for (Stint stint : stints) {
            result += LapTime.calculate(stint, avgFuelTime);
        }
        return result;
    }


    /**
     * @return the type of tire in use in the current stint.
     */
    public Tire getCurrentTire() {
        return stints[currentStint].getTire();
    }

    /**
     *
     * @return the amount of laps in the current stint.
     */
   public Stint getCurrentStint() {
        return stints[currentStint];
    }

    /**
     *
     * @return the number of the current stint. if used for index you should use -1
     */
    public int getStintNr() {
        return currentStint;
    }


    /**
     * move to the next stint in the strategy.
     */
    public void addStint() {
        currentStint++;
    }

    /**
     *
     * @return the previous boxlap
     */
    public int getlastBoxlap() {
        return stints[currentStint].getStartLap();
    }

    /**
     * get the tire of the stint given.
     * @return tiretype of the stint given.
     */
    public Tire getTire(int stint) {
        return stints[stint].getTire();
    }

    public Tire[] getTires() {
        Tire[] tires = new Tire[stints.length];
        for (int i = 0; i < stints.length; i++) {
            tires[i] = stints[i].getTire();
        }
        return tires;
    }

    public int[] getBoxlaps() {
        int[] boxlaps = new int[stints.length-1];
        for (int i = 0; i < stints.length - 1; i++) {
            boxlaps[i] = stints[i].getStartLap() + stints[i].getLaps();
        }
        return boxlaps;
    }

    /**
     * get the length of the requested stint.
     * @param stint stint for which the length is to be calculated
     * @return length of the requested stint.
     */
    public int getStintLength(int stint) {
        return stints[currentStint].getLaps();
    }


}
