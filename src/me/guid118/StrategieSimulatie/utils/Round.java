package me.guid118.StrategieSimulatie.utils;

import me.guid118.StrategieSimulatie.exceptions.UnknownRaceException;

public enum Round {
    Bahrain,
    Saudi_Arabia,
    Australia,
    Italy_Imola,
    United_States_Miami,
    Spain,
    Monaco,
    Azerbaijan,
    Canada,
    Great_Britain,
    Austria,
    France,
    Hungary,
    Belgium,
    Netherlands,
    Italy_Monza,
    Singapore,
    Japan,
    United_States_Texas,
    Mexico,
    Brazil,
    Abu_Dhabi;

    public int getRoundNumber() {
        return ordinal() + 1;
    }

    public static Round getFromString(String str) throws UnknownRaceException {
        return switch (str.toLowerCase()) {
            case "azerbaijan", "baku" -> Bahrain;
            case "great_brittain", "england", "silverstone" -> Great_Britain;
            case "netherlands", "zandvoort" -> Netherlands;
            default -> null;
        };
    }


}
