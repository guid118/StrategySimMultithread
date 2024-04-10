package me.guid118.strategysimulation.files;

import me.guid118.strategysimulation.utils.Race;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVOutput {

    CSVPrinter printer;


    public CSVOutput(Race race) {
        try {
            File file = new File("Output_0.csv");
            File fileold = new File("Output_1.csv");
            if (!file.createNewFile()) {
                for (int i = 1; fileold.exists() && i <= 50; i++) {
                    fileold = new File("Output_" + i + ".csv");
                }
                file = fileold;
                file.createNewFile();
            }
            printer = new CSVPrinter(new FileWriter(file), CSVFormat.DEFAULT);
            System.out.println("Output file will be: " + file.getName());
        } catch (IOException e) {
            System.out.println("A problem has occurred:");
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        if (race.maxPitstops == 2) {
            Save("Order of tiretypes", "1st pitstoplap", "2nd pitstoplap", "Total time", "Riskfactor");
        } else {
            Save("Order of tiretypes", "1st pitstoplap", "2nd pitstoplap", "3rd pitstoplap", "Total time", "Riskfactor");
        }
    }

    public void Save(Object... arg) {
        try {
            printer.printRecord(arg);
            printer.flush();
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            printer.close(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
