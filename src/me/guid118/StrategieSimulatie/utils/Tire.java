package me.guid118.StrategieSimulatie.utils;

import me.guid118.StrategieSimulatie.files.Config;


public class Tire {

    private final TireType type;
    private final int failLap;
    private final double baseLapTime;
    private final double degradation;
    private final int stableDegLap;
    private final int fastDegLap;

    public Tire(TireType tiretype) {
        this.type = tiretype;
        this.failLap = Config.getFailLap(tiretype);
        this.baseLapTime = Config.getBaseLapTime(tiretype);
        this.degradation = Config.getDegradation(tiretype);
        this.stableDegLap = Config.getStableDegLap(tiretype);
        this.fastDegLap = Config.getFastDegLap(tiretype);



    }

    public TireType getType() {
        return type;
    }

    public int getFailLap() {
        return failLap;
    }

    public double getBaseLapTime() {
        return baseLapTime;
    }

    public double getDegradation() {
        return degradation;
    }

    public int getStableDegLap() {
        return stableDegLap;
    }

    public int getFastDegLap() {
        return fastDegLap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tire tire) {
            return tire.getType() == this.getType();
        }
        return false;
    }


    /**
     * @return the name of the tire
     */
    public String toString() {
        if (type == TireType.SOFT) {
            return "Soft";
        } else if (type == TireType.MEDIUM) {
            return "Medium";
        } else if (type == TireType.HARD) {
            return "Hard";
        } else {
            return String.valueOf(type);
        }
    }
}


