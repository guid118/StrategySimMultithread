package me.guid118.StrategieSimulatie;



import me.guid118.StrategieSimulatie.utils.Strategy;

import static me.guid118.StrategieSimulatie.Main.race;

public class LapTime {
    //this is the location for all the time variable calculations.
    //these should calulate the total laptime for a stint, not per lap.

    /**
     * Calculates the total laptime for a specific stint.
     * @param strategy the strategy for which the calculation should be performed
     * @return the laptime calculated
     */
    public static double calculate(Strategy strategy) {
        int laps = strategy.getCurrentStint();
        return (race.getBaseLapTime(strategy.getCurrentTire()) * laps + race.AvgFuelTime*laps + TireWear(strategy));
    }

    /**
     * Calculates the fuelwear that is caused by fuel in the tank.
     * @param strat strategy for which the calculation should be performed
     * @param tirelap lap for which the calculation should be performed
     * @return fuel wear for a specific lap.
     */
    private static double getFuelwear(Strategy strat, int tirelap) {
        double fuelwear;
        int lap = strat.getlastBoxlap() + tirelap;
        fuelwear = 0.95 + (1 - (double) lap / race.racelaps) / 10;
        return fuelwear;
    }


    /**
     * Calculates the tirewear for a specific stint.
     * @param strat strategy for which the calculation should be performed
     * @return tirewear time for a specific stint. this does not include base laptime.
     */
    private static double TireWear(Strategy strat){
        //TODO use fuelwear again.
        //new newest version: https://www.desmos.com/calculator/zm6ma6utrb
        double tirewear;
        int tirelap = strat.getlastBoxlap();
        int tiretype = strat.getCurrentTire();
        double deg = race.getDegradation(tiretype);
        if (tirelap <= race.getFastDegLap(tiretype)) {
            //(((tirelap + stabledeglap)/7)^-2.5 * deg + tirelap * deg
            tirewear = Math.pow(((double) (tirelap + race.getStableDegLap(tiretype)) / 7), -2.5) * deg + (1+deg);
        } else {
            //(((tirelap - fastdeglap)/7)+deg)^2.5 * 1 + deg
            tirewear = Math.pow(((double) (tirelap - race.getFastDegLap(tiretype)) / 7 + deg), 2.5) * (1+deg);
        }
        //double fuelwear = getFuelwear(strat, tirelap);
        //tirewear = tirewear * getFuelwear(strat, tirelap);
        return tirewear;
    }
}
