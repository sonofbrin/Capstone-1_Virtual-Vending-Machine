package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BalanceLog {

    public static void log(String message) {
        File dataFile = new File("Log.txt");
        try (PrintWriter dataOutput = new PrintWriter(
                new FileOutputStream(dataFile, true))) {
            dataOutput.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"))
                    + " " + message);
        } catch (FileNotFoundException e) {
            System.err.println("Error when accessing log file.");
        }
    }
}
