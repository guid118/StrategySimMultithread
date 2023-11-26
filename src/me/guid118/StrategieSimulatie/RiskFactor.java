package me.guid118.StrategieSimulatie;


import java.util.Arrays;
import me.guid118.StrategieSimulatie.utils.Strategy;

import static me.guid118.StrategieSimulatie.Main.*;

public class RiskFactor {

    public static String Risk(Strategy strat) {
        double[] riskratio = new double[strat.stints + 1];
        for (int i = 0; i <= strat.stints; i++) {
            riskratio[i] = (double) strat.getStintLength(i) / race.getFailLap(strat.getTire(i));
        }
        Arrays.sort(riskratio);
        if (riskratio[riskratio.length - 1] <= 0.7) return "Low";
        else if (riskratio[riskratio.length - 1] <= 0.85) return "Medium";
        else if (riskratio[riskratio.length - 1] <= 0.95) return "High";
        else if (riskratio[riskratio.length - 1] < 1) return "Very High";
        else return "Failure!";
    }


}
