package me.guid118.strategysimulation.files;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.guid118.strategysimulation.utils.Race;
import me.guid118.strategysimulation.utils.Round;
import me.guid118.strategysimulation.utils.Tire;
import me.guid118.strategysimulation.utils.TireType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONConfig {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Tire.class, name = "soft tire"),
            @JsonSubTypes.Type(value = Tire.class, name = "medium tire"),
            @JsonSubTypes.Type(value = Tire.class, name = "hard tire")
    })

    public static void main(String[] args) {
        JSONRace[] race = new JSONRace[3];
        race[0] = new JSONRace(Round.Azerbaijan,
                new Tire(TireType.SOFT, 25, 107.4, 0.16, 3, 19),
                new Tire(TireType.MEDIUM, 33, 107.6, 0.1, 5, 28),
                new Tire(TireType.HARD, 45, 108.2, 0.05, 10, 34),
                51, 1, 3, 6085, 250, 23);
        race[1] = new JSONRace(Round.Great_Britain,
                new Tire(TireType.SOFT, 25, 93.15, 0.16, 3, 15),
                new Tire(TireType.MEDIUM, 33, 93.4, 0.1, 5, 20),
                new Tire(TireType.HARD, 44, 93.7, 0.05, 5, 29),
                52, 1, 3, 5625, 250, 23);
        race[2] = new JSONRace(Round.Netherlands,
                new Tire(TireType.SOFT, 25, 107.4, 0.16, 3, 19),
                new Tire(TireType.MEDIUM, 33, 107.6, 0.1, 5, 28),
                new Tire(TireType.HARD, 45, 108.2, 0.05, 10, 34),
                72, 1, 3, 5775, 250, 23);
        writeToJsonFile(race, "config.json");
    }


    private static void writeToJsonFile(Object data, String fileName) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(fileName), data);
            System.out.println("Data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class JSONRace {
        public Round round;
        public me.guid118.strategysimulation.utils.Tire soft;
        public me.guid118.strategysimulation.utils.Tire medium;
        public me.guid118.strategysimulation.utils.Tire hard;
        public int racelaps;
        public int minPitstops;
        public int maxPitstops;
        public int maxRaceTime;
        public int maxResults;
        public int pitstopTime;

        public JSONRace(Round round, Tire soft, Tire medium, Tire hard, int racelaps, int minPitstops, int maxPitstops, int maxRaceTime, int maxResults, int pitstopTime) {
            this.round = round;
            this.soft = soft;
            this.medium = medium;
            this.hard = hard;
            this.racelaps = racelaps;
            this.minPitstops = minPitstops;
            this.maxPitstops = maxPitstops;
            this.maxRaceTime = maxRaceTime;
            this.maxResults = maxResults;
            this.pitstopTime = pitstopTime;
        }
        public JSONRace() {
        }
    }

    public static Race readFromJsonFile(Round round, String fileName) {
        try {
            List<JSONRace> racelist = objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructCollectionType(List.class, JSONConfig.JSONRace.class));
            for (JSONRace race : racelist) {
                if (race.round == round) {
                    return new Race(round, race.soft, race.medium, race.hard, race.racelaps, race.minPitstops, race.maxPitstops, race.maxRaceTime, race.maxResults, race.pitstopTime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //TODO return default values instead.
        return null;
    }
}
