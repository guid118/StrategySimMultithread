package me.guid118.StrategieSimulatie.utils;


public class Tire {








    /**
     * @param tirenumber 3-5
     * @return the name of that tire
     */
    public static String toString(TireType tirenumber) {
        if (tirenumber == TireType.SOFT) {
            return "Soft";
        } else if (tirenumber == TireType.MEDIUM) {
            return "Medium";
        } else if (tirenumber == TireType.HARD) {
            return "Hard";
        } else {
            return String.valueOf(tirenumber);
        }
    }
}


