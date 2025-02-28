package org.example;

import java.io.*;
import java.util.ArrayList;

//clasa care contine metode utile
public class Utils {
    //functie de parsare  a fisierului de intrare, trasnformand randurile acestuia intr-o lista de comenzi
    public  static void  Parse(String inputFilePath, ArrayList<String> comenzi) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                comenzi.add(line);
            }
        } catch (IOException exception) {
            System.err.println("Eroare la citirea fisierului: " + exception.getMessage());
        }
    }

    //metoda folosita in cazul in care vrem sa afisam ca un membru/ghid nu a fost gasit
    static void notFound(File file, Group groupCrt, Person person, String type) throws FileNotFoundException {
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                writer.println(groupCrt.getMuseumCode() + " ## " + groupCrt.getTimetable() + " ## " + type + " not exists: " + person);
            }
    }

    //metoda folosita in cazul in care vrem sa afisam ca un membru/ghid a fost gasit
    static void found(File file, Group groupCrt, Person person, String type) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println(groupCrt.getMuseumCode() + " ## " + groupCrt.getTimetable() + " ## " + type + " found: " + person);
        }
    }
    //metoda care gestioneaza afisarea in cazul in care apare o exceptie
    static void handleException(Exception e, File groupFileOut) throws IOException {
        try (FileWriter fw = new FileWriter(groupFileOut, true); PrintWriter writer = new PrintWriter(fw)) {
            writer.println(e.getMessage());
        }
    }

}