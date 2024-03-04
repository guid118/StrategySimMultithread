package me.guid118.strategysimulation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.guid118.strategysimulation.utils.Stint;
import me.guid118.strategysimulation.utils.Strategy;

public class RiskFactor {

    public static String Risk(Strategy strat) {
        List<Double> riskratio = new ArrayList<>();
        for (Stint stint : strat.stints) {
            riskratio.add((double) stint.getLaps() / stint.getTire().getFailLap());
        }
        Collections.sort(riskratio);
        if (riskratio.get(riskratio.size() - 1) <= 0.7) return "Low";
        else if (riskratio.get(riskratio.size() - 1) <= 0.85) return "Medium";
        else if (riskratio.get(riskratio.size() - 1) <= 0.95) return "High";
        else if (riskratio.get(riskratio.size() - 1) < 1) return "Very High";
        else return "Failure!";
    }


}
