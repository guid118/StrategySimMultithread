package me.guid118.StrategieSimulatie.files;



import me.guid118.StrategieSimulatie.Main;
import me.guid118.StrategieSimulatie.utils.OrderedProperties;
import me.guid118.StrategieSimulatie.utils.Race;
import me.guid118.StrategieSimulatie.utils.Tire;
import me.guid118.StrategieSimulatie.utils.TireType;

import java.io.*;

import static me.guid118.StrategieSimulatie.Main.*;
public class Config {

    static File file;
    static String configFilePath = "config.properties";

    public static void CreateFile() {
        try {
            file = new File(configFilePath);
            if (!file.exists()) {
                file.createNewFile();
                Config.setdefaults();
            }
            FileInputStream propsInput = new FileInputStream(configFilePath);
            prop.load(propsInput);
            setdefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getBaseLapTime(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return Double.parseDouble(OrderedProperties.get(race.name + ".tires.Soft.LapTime"));
        } else if (tiretype == TireType.MEDIUM) {
            return Double.parseDouble(OrderedProperties.get(race.name + ".tires.Medium.LapTime"));
        } else if (tiretype == TireType.HARD) {
            return Double.parseDouble(OrderedProperties.get(race.name + ".tires.Hard.LapTime"));
        } else {
            return 0;
        }
    }

    public static int getFailLap(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Soft.FailLap"));
        } else if (tiretype == TireType.MEDIUM) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Medium.FailLap"));
        } else if (tiretype == TireType.HARD) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Hard.FailLap"));
        } else {
            return 0;
        }
    }

    public static double getDegradation(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return Double.parseDouble(OrderedProperties.get(race.name + ".tires.Soft.Degradation"));
        } else if (tiretype == TireType.MEDIUM) {
            return Double.parseDouble(OrderedProperties.get(race.name + ".tires.Medium.Degradation"));
        } else if (tiretype == TireType.HARD) {
            return Double.parseDouble(OrderedProperties.get(race.name + ".tires.Hard.Degradation"));
        } else {
            return 0;
        }
    }

    public static int getStableDegLap(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Soft.StableDegLap"));
        } else if (tiretype == TireType.MEDIUM) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Medium.StableDegLap"));
        } else if (tiretype == TireType.HARD) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Hard.StableDegLap"));
        } else {
            return 0;
        }
    }

    public static int getFastDegLap(TireType tiretype) {
        if (tiretype == TireType.SOFT) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Soft.FastDegLap"));
        } else if (tiretype == TireType.MEDIUM) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Medium.FastDegLap"));
        } else if (tiretype == TireType.HARD) {
            return Integer.parseInt(OrderedProperties.get(race.name + ".tires.Hard.FastDegLap"));
        } else {
            return 0;
        }
    }
    



    public static void setdefaults() {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            OrderedProperties.putIfAbsent("Azerbaijan", "zie hieronder instellingen");

            OrderedProperties.putIfAbsent("AzerbaijanSoft", "");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Soft.LapTime", "107.4");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Soft.FailLap", "25");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Soft.Degradation", "0.16");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Soft.StableDegLap", "3");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Soft.FastDegLap", "19");
            
            OrderedProperties.putIfAbsent("AzerbaijanMedium", "");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Medium.LapTime", "107.6");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Medium.FailLap", "33");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Medium.Degradation", "0.1");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Medium.StableDegLap", "5");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Medium.FastDegLap", "28");

            OrderedProperties.putIfAbsent("AzerbaijanHard", "");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Hard.LapTime", "108.2");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Hard.FailLap", "45");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Hard.Degradation", "0.05");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Hard.StableDegLap", "10");
            OrderedProperties.putIfAbsent("Azerbaijan.tires.Hard.FastDegLap", "34");
            
            OrderedProperties.putIfAbsent("Azerbaijanother", "");
            OrderedProperties.putIfAbsent("Azerbaijan.other.pitstops.Time", "23");
            OrderedProperties.putIfAbsent("Azerbaijan.other.pitstops.minimum", "1");
            OrderedProperties.putIfAbsent("Azerbaijan.other.pitstops.maximum", "2");
            OrderedProperties.putIfAbsent("Azerbaijan.other.files.maxtotalTime", "6085");
            OrderedProperties.putIfAbsent("Azerbaijan.other.files.maxResults", "250");

            OrderedProperties.putIfAbsent("===================", "===============");
            OrderedProperties.putIfAbsent("England", "zie hieronder instellingen");
            OrderedProperties.putIfAbsent("EnglandSoft", "");
            OrderedProperties.putIfAbsent("England.tires.Soft.LapTime", "93.15");
            OrderedProperties.putIfAbsent("England.tires.Soft.FailLap", "25");
            OrderedProperties.putIfAbsent("England.tires.Soft.Degradation", "0.16");
            OrderedProperties.putIfAbsent("England.tires.Soft.StableDegLap", "3");
            OrderedProperties.putIfAbsent("England.tires.Soft.FastDegLap", "15");
            
            OrderedProperties.putIfAbsent("EnglandMedium", "");
            OrderedProperties.putIfAbsent("England.tires.Medium.LapTime", "93.4");
            OrderedProperties.putIfAbsent("England.tires.Medium.FailLap", "33");
            OrderedProperties.putIfAbsent("England.tires.Medium.Degradation", "0.1");
            OrderedProperties.putIfAbsent("England.tires.Medium.StableDegLap", "5");
            OrderedProperties.putIfAbsent("England.tires.Medium.FastDegLap", "20");

            OrderedProperties.putIfAbsent("EnglandHard", "");
            OrderedProperties.putIfAbsent("England.tires.Hard.LapTime", "93.7");
            OrderedProperties.putIfAbsent("England.tires.Hard.FailLap", "44");
            OrderedProperties.putIfAbsent("England.tires.Hard.Degradation", "0.05");
            OrderedProperties.putIfAbsent("England.tires.Hard.StableDegLap", "5");
            OrderedProperties.putIfAbsent("England.tires.Hard.FastDegLap", "29");

            OrderedProperties.putIfAbsent("Englandother", "");
            OrderedProperties.putIfAbsent("England.other.pitstops.Time", "23");
            OrderedProperties.putIfAbsent("England.other.pitstops.minimum", "1");
            OrderedProperties.putIfAbsent("England.other.pitstops.maximum", "2");
            OrderedProperties.putIfAbsent("England.other.files.maxtotalTime", "5625");
            OrderedProperties.putIfAbsent("England.other.files.maxResults", "250");

            OrderedProperties.putIfAbsent("==================", "================");
            OrderedProperties.putIfAbsent("Netherlands", "zie hieronder instellingen");
            OrderedProperties.putIfAbsent("NetherlandsSoft", "");
            OrderedProperties.putIfAbsent("Netherlands.tires.Soft.LapTime", "75.2");
            OrderedProperties.putIfAbsent("Netherlands.tires.Soft.FailLap", "23");
            OrderedProperties.putIfAbsent("Netherlands.tires.Soft.Degradation", "0.16");
            OrderedProperties.putIfAbsent("Netherlands.tires.Soft.StableDegLap", "2");
            OrderedProperties.putIfAbsent("Netherlands.tires.Soft.FastDegLap", "12");

            OrderedProperties.putIfAbsent("NetherlandsMedium", "");
            OrderedProperties.putIfAbsent("Netherlands.tires.Medium.LapTime", "75.6");
            OrderedProperties.putIfAbsent("Netherlands.tires.Medium.FailLap", "34");
            OrderedProperties.putIfAbsent("Netherlands.tires.Medium.Degradation", "0.1");
            OrderedProperties.putIfAbsent("Netherlands.tires.Medium.StableDegLap", "4");
            OrderedProperties.putIfAbsent("Netherlands.tires.Medium.FastDegLap", "24");

            OrderedProperties.putIfAbsent("NetherlandsHard", "");
            OrderedProperties.putIfAbsent("Netherlands.tires.Hard.LapTime", "75.7");
            OrderedProperties.putIfAbsent("Netherlands.tires.Hard.FailLap", "45");
            OrderedProperties.putIfAbsent("Netherlands.tires.Hard.Degradation", "0.05");
            OrderedProperties.putIfAbsent("Netherlands.tires.Hard.StableDegLap", "8");
            OrderedProperties.putIfAbsent("Netherlands.tires.Hard.FastDegLap", "34");

            OrderedProperties.putIfAbsent("Netherlandsother", "");
            OrderedProperties.putIfAbsent("Netherlands.other.pitstops.Time", "23");
            OrderedProperties.putIfAbsent("Netherlands.other.pitstops.minimum", "1");
            OrderedProperties.putIfAbsent("Netherlands.other.pitstops.maximum", "3");
            OrderedProperties.putIfAbsent("Netherlands.other.files.maxtotalTime", "5775");
            OrderedProperties.putIfAbsent("Netherlands.other.files.maxResults", "250");
        try {
            prop.store(os, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getvalues(String name) {
        int racelaps = 0;
        if ((name.equalsIgnoreCase("Azerbaijan") || (name.equalsIgnoreCase("baku") || name.equalsIgnoreCase("Azerbeidzjan")))) {
            name = "Azerbaijan";
            racelaps = 50;

        } else if ((name.equalsIgnoreCase("England") || (name.equalsIgnoreCase("Silverstone")) || (name.equalsIgnoreCase("Engeland")) || (name.equalsIgnoreCase("Great Britain")) || (name.equalsIgnoreCase("")))) {
            name = "England";
            racelaps = 52;

        } else if ((name.equalsIgnoreCase("Netherlands") || (name.equalsIgnoreCase("Zandvoort")) || (name.equalsIgnoreCase("nederland")))) {
            name = "Netherlands";
            racelaps = 70;
        } else {
            System.out.println("Race not recognized, please enter a valid race!");
            System.exit(0);
        }

        System.out.println("Loading data for race: " + name);
        double PitstopTime = Double.parseDouble(OrderedProperties.get(name + ".other.pitstops.Time"));
        int minPitstops = Integer.parseInt(OrderedProperties.get(name + ".other.pitstops.minimum"));
        int maxPitstops = Integer.parseInt(OrderedProperties.get(name + ".other.pitstops.maximum"));
        double maxRaceTime = Double.parseDouble(OrderedProperties.get(name + ".other.files.maxtotalTime"));
        int maxResults = Integer.parseInt(OrderedProperties.get(name + ".other.files.maxResults"));

        Main.race = new Race(name, PitstopTime, minPitstops, maxPitstops, maxRaceTime, racelaps, maxResults);

        }



}