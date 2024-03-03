package me.guid118.strategysimulation.files;


import java.io.*;
import java.util.HashMap;

import static me.guid118.strategysimulation.Main.*;


public class Output {

    static File file;

    public static void CreateFile() {
        try {
            file = new File("Output_0.txt");
            File fileold = new File("Output_1.txt");
            if (!file.createNewFile()) {
                for (int i = 1; fileold.exists() && i <= 50; i++) {
                    fileold = new File("Output_" + i + ".txt");

                }
                file = fileold;
                file.createNewFile();
            }
            System.out.println("Output file will be: " + file.getName());
        } catch (IOException e) {
            System.out.println("A problem has occurred:");
            e.printStackTrace();
        }
        if (race.maxPitstops == 2) {
            Save("order of tiretypes \t 1st pitstoplap \t 2nd pitstoplap \t total race time \t riskfactor");
        } else {
            Save("order of tiretypes \t 1st pitstoplap \t 2nd pitstoplap \t 3rd pitstoplap \t total race time \t riskfactor");
        }
    }

    static HashMap<Integer, String> lines = new HashMap<>();
    static int linenr = 0;

    public static void Save(String arg) {
        lines.put(linenr, arg);
        linenr++;
    }


    public static void Write() {
        FileWriter fileWriter = null;
        String pathname = file.getName();
        try {
            File file = new File(pathname);
            fileWriter = new FileWriter(file);

            for (String line : lines.values()) {
                fileWriter.write(line + "\n");
            }
        } catch (IOException iOException) {
            System.out.println("Error : " + iOException.getMessage());
        } finally {
            int results = linenr -1;
            int plannedresults = race.strategies.size();
            System.out.println(plannedresults + " Strategies Simulated");
            System.out.println("of which " + (plannedresults - results) + " were too slow");
            System.out.println(Math.min(results, race.maxResults) + " results saved in: " + file.getName());
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException iOException) {
                    System.out.println("Error : " + iOException.getMessage());
                }
            }
        }
    }
}