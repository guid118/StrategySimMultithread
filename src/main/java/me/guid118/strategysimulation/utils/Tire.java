package me.guid118.strategysimulation.utils;


public class Tire {

    private final TireType type;
    private final int failLap;
    private final double baseLapTime;
    private final double degradation;
    private final int stableDegLap;
    private final int fastDegLap;

    public Tire() {
        this.type = TireType.SOFT;
        this.failLap = 21;
        this.baseLapTime = 104;
        this.degradation = 0.3;
        this.stableDegLap = 2;
        this.fastDegLap = 14;
    }

    public Tire(TireType type, int failLap, double baseLapTime, double degradation, int stableDegLap, int fastDegLap) {
        this.type = type;
        this.failLap = failLap;
        this.baseLapTime = baseLapTime;
        this.degradation = degradation;
        this.stableDegLap = stableDegLap;
        this.fastDegLap = fastDegLap;
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


