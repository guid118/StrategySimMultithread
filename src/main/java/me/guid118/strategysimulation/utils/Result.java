package me.guid118.strategysimulation.utils;

import static me.guid118.strategysimulation.Main.race;
import static me.guid118.strategysimulation.Main.round;

public record Result(Strategy strat, double time, String RiskFactor) {

    public double getTime() {
        return time;
    }

    private String stringTires(Tire[] tires) {
        StringBuilder stringTires = new StringBuilder();
        for (Tire tire : tires) {
            stringTires.append(tire.toString()).append("-");
        }
        stringTires.delete(stringTires.length() - 1, stringTires.length());
        return stringTires.toString();
    }

    public String toString() {
        Tire[] tires = strat.getTires();
        int[] boxlaps = strat.getBoxlaps();
        StringBuilder savedString = new StringBuilder();
        savedString.append(stringTires(tires));
        savedString.append("\t");
        for (int boxlap : boxlaps) {
            savedString.append(boxlap);
            if (boxlap != boxlaps[boxlaps.length -1]) {
                savedString.append("\t");
            }
        }
        savedString.append("\t".repeat(Math.max(0, race.maxPitstops - boxlaps.length)));
        savedString.append("\t");

        savedString.append(round(time, 2)).append("\t").append(RiskFactor);
        return savedString.toString();
    }


}
