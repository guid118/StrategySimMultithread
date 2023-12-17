package me.guid118.StrategieSimulatie.utils;

public class Stint {

    private final int laps;
    private final TireType tire;

    private boolean isCalculated;

    public Stint(int laps, TireType tiretype) {
        this.laps = laps;
        this.tire = tiretype;
    }

    public int getLaps() {
        return laps;
    }

    public TireType getTire() {
        return tire;
    }

    public boolean isCalculated() {
        return isCalculated;
    }
}
