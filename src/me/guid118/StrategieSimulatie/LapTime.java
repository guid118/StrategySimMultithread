package me.guid118.StrategieSimulatie;



import me.guid118.StrategieSimulatie.utils.Stint;
import me.guid118.StrategieSimulatie.utils.Strategy;
import me.guid118.StrategieSimulatie.utils.Tire;
import me.guid118.StrategieSimulatie.utils.TireType;

import static me.guid118.StrategieSimulatie.Main.race;

public class LapTime {
    //this is the location for all the time variable calculations.
    //these should calulate the total laptime for a stint, not per lap.

    /**
     * Calculates the total laptime for a specific stint.
     * @param stint the stint for which the calculation should be performed
     * @return the laptime calculated
     */
    public static double calculate(Stint stint) {
        int laps = stint.getLaps();
        return (stint.getTire().getBaseLapTime() * laps + race.AvgFuelTime*laps + TireWear(stint));
    }


    /**
     * Calculates the tirewear for a specific stint.
     * @param stint stint for which the calculation should be performed
     * @return tirewear time for a specific stint. this does not include base laptime.
     */
    private static double TireWear(Stint stint){
        //new newest version: https://www.desmos.com/calculator/zm6ma6utrb
        double tirewear;
        int tirelap = stint.getStartLap();
        Tire tiretype = stint.getTire();
        double deg = tiretype.getDegradation();
        if (tirelap <= tiretype.getFastDegLap()) {
            //(((tirelap + stabledeglap)/7)^-2.5 * deg + tirelap * deg
            tirewear = Math.pow(((double) (tirelap + tiretype.getStableDegLap()) / 7), -2.5) * deg + (1+deg);
        } else {
            //(((tirelap - fastdeglap)/7)+deg)^2.5 * 1 + deg
            tirewear = Math.pow(((double) (tirelap - tiretype.getFastDegLap()) / 7 + deg), 2.5) * (1+deg);
        }
        return tirewear;
    }
}
