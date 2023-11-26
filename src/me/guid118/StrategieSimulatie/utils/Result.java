package me.guid118.StrategieSimulatie.utils;

public record Result(Strategy strat, double time, String RiskFactor) {
    public Strategy getStrategy() {
        return strat;
    }

    public double getTime() {
        return time;
    }

    public String getRiskfactor() {
        return RiskFactor;
    }


}
