package me.guid118.strategysimulation.files;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.guid118.strategysimulation.Main;
import me.guid118.strategysimulation.exceptions.UnknownRaceException;
import me.guid118.strategysimulation.utils.Race;
import me.guid118.strategysimulation.utils.Round;
import me.guid118.strategysimulation.utils.Tire;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JSONConfig {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Tire.class, name = "soft tire"),
            @JsonSubTypes.Type(value = Tire.class, name = "medium tire"),
            @JsonSubTypes.Type(value = Tire.class, name = "hard tire")
    })

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<JSONRace> defaultValues;
    private final List<JSONRace> loadedRaces;
    private final String filePath;

    public JSONConfig(String filePath) throws IOException {
        this.filePath = filePath;
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            defaultValues = objectMapper.readValue(Main.class.getResourceAsStream("/config.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, JSONRace.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!Files.exists(Paths.get(filePath))) {
            objectMapper.writeValue(new File(filePath), defaultValues);
            loadedRaces = defaultValues;
        } else {
            try {
                loadedRaces = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, JSONRace.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (loadedRaces.size() < defaultValues.size()) {
            addMissingRaces();
        }
    }

    private void addMissingRaces() throws IOException {
        for (JSONRace defaultRace : defaultValues) {
            if (!loadedRaces.contains(defaultRace)) {
                loadedRaces.add(defaultRace);
            }
        }
        objectMapper.writeValue(new File(filePath), loadedRaces);
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

        @SuppressWarnings("unused")
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

        @SuppressWarnings("unused")
        public JSONRace() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JSONRace jsonRace = (JSONRace) o;
            return round == jsonRace.round;
        }
    }

    public Race readFromJsonFile(Round round) throws UnknownRaceException, IOException {
        List<JSONRace> racelist = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, JSONConfig.JSONRace.class));
        for (JSONRace race : racelist) {
            if (race.round == round) {
                return new Race(round, race.soft, race.medium, race.hard, race.racelaps, race.minPitstops, race.maxPitstops, race.maxRaceTime, race.maxResults, race.pitstopTime);
            }
        }
        throw new UnknownRaceException("Race: " + round + " not found in " + filePath + ", or default config file.");
    }
}
