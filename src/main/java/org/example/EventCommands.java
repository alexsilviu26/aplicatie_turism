package org.example;

import java.io.File;
import java.io.FileNotFoundException;

// clasa care contine metode ce modeleaza comenzile pentru evenimente
public class EventCommands {
    //metoda care contine logica pentru adaugarea unui eveniment
    //urmarind dessign pattern-ul Observer
    static void addEvent(String[] parts, Database database, File eventFileOut) throws FileNotFoundException {
        long museumCode = Long.parseLong(parts[1]);
        String eventMessage = parts[2];

        Museum museum = database.findMuseumByCode(museumCode);
        if (museum != null) {
            museum.addEvent(eventMessage, eventFileOut);
        }
    }
}
