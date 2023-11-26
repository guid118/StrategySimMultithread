package me.guid118.StrategieSimulatie.utils;


public class Tire {


    /**
     * @param tirenumber 3-5
     * @return the name of that tire
     */
    public static String getString(int tirenumber) {
        if (tirenumber == 5) {
            return "Soft";
        } else if (tirenumber == 4) {
            return "Medium";
        } else if (tirenumber == 3) {
            return "Hard";
        } else {
            return String.valueOf(tirenumber);
        }
    }
}


