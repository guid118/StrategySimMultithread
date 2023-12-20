package me.guid118.StrategieSimulatie.utils;

import static me.guid118.StrategieSimulatie.Main.*;

public record Result(Strategy strat, double time, String RiskFactor) {

    public double getTime() {
        return time;
    }

    private String stringTires(TireType[] tires) {
        StringBuilder Stringtires = new StringBuilder();
        for (int i = 0; i < tires.length; i++) {
            Stringtires.append(Tire.toString(tires[i]));
            if (!(i == tires.length - 1)) {
                Stringtires.append("-");
            }
        }
        return Stringtires.toString();
    }

    public String toString() {
        TireType[] tires = strat.getTires();
        int[] boxlaps = strat.getBoxlaps();
        StringBuilder savedstring = new StringBuilder();
        savedstring.append(stringTires(tires));
        savedstring.append("\t");
        for (int boxlap : boxlaps) {
            savedstring.append(boxlap).append("\t");
        }
        savedstring.append(round(time, 2)).append("\t").append(RiskFactor);
        return savedstring.toString();
    }


}
