package me.guid118.strategysimulation.utils;

public class Stint {

    private final int laps;
    private final Tire tire;
    private final int startLap;


    public Stint(int laps, Tire tiretype, int startLap) {
        this.laps = laps;
        this.tire = tiretype;
        this.startLap = startLap;
    }

    public int getLaps() {
        return laps;
    }

    public Tire getTire() {
        return tire;
    }

    public int getStartLap() {
        return startLap;
    }
}
