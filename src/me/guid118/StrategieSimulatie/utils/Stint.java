package me.guid118.StrategieSimulatie.utils;

public class Stint {

    private final int laps;
    private final TireType tire;
    private final int startLap;


    public Stint(int laps, TireType tiretype, int startLap) {
        this.laps = laps;
        this.tire = tiretype;
        this.startLap = startLap;
    }

    public int getLaps() {
        return laps;
    }

    public TireType getTire() {
        return tire;
    }

    public int getStartLap() {
        return startLap;
    }
}
