package me.guid118.strategysimulation.utils;

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



    public String[] toStringArray() {
        Tire[] tires = strat.getTires();
        int[] boxlaps = strat.getBoxlaps();
        String[] result = new String[6];
        for (int i = 0; i < boxlaps.length; i++) {
            result[i + 1] = String.valueOf(boxlaps[i]);
        }
        result[0] = stringTires(tires);
        result[4] = String.valueOf(round(time, 2));
        result[5] = RiskFactor;
        return result;
    }


}
