package me.guid118.StrategieSimulatie.utils;

import static me.guid118.StrategieSimulatie.Main.race;

public class Strategy {

    public int[] tires;
    public int stints;
    public int[] boxlap;
    private int CurrentStint;

    /**
     * Construct a new Strategy
     * @param tires int array containing the type of tires to be used
     * @param stints integer with the amount of stops to be made
     * @param boxlap int array containing specific laps on which to switch to new tires.
     */
    public Strategy(int[] tires, int stints, int[] boxlap) {
        this.tires = tires;
        this.stints = stints;
        this.boxlap = boxlap;
    }

    /**
     *
     * @return the type of tire in use in the current stint.
     */
    public int getCurrentTire() {
        if (tires.length > CurrentStint)
            return tires[CurrentStint];
        return tires[tires.length - 1];
    }

    /**
     *
     * @return the amount of laps in the current stint.
     */
   public int getCurrentStint() {
        if (boxlap.length > CurrentStint) {
            return boxlap[CurrentStint];
        } else {
            return race.racelaps - boxlap[boxlap.length - 1];
        }
    }

    /**
     *
     * @return the number of the current stint. if used for index you should use -1
     */
    public int getStintNr() {
        return CurrentStint;
    }


    /**
     * move to the next stint in the strategy.
     */
    public void addStint() {
        CurrentStint++;
    }

    /**
     *
     * @return the previous boxlap
     */
    public int getlastBoxlap() {
        if (boxlap.length > CurrentStint) {
            return boxlap[CurrentStint];
        } else {
            return boxlap[boxlap.length - 1];
        }
    }

    /**
     * get the tire of the stint given
     * @param stint for which the tire is to be retrieved
     * @return tiretype of the stint given.
     */
    public int getTire(int stint) {
        return tires[stint];
    }

    /**
     * get the length of the requested stint.
     * @param stint stint for which the length is to be calculated
     * @return length of the requested stint.
     */
    public int getStintLength(int stint) {
        if (stint <= 1) {
            return boxlap[0];
        } else if (stint < stints){
            return boxlap[stint-1] - boxlap[stint-2];
        } else {
            return race.racelaps - boxlap[stint-1];
        }
    }


}
