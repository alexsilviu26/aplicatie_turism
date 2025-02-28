package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
//clasa care contine metode ce modeleaza comenzile pentru muzee
public class MuseumCommands {
    //metoda ce adauga un muzeu in baza de date si in fisierul de output
    public static void addMuseum(String[] parts, Database database, File file, String command) throws FileNotFoundException {
        //pe parcursul metodei incercam prinderea exceptiilor care pot aparea
        //si le gestionam la finalul metodei
        try {
            //variabile pentru longitudine si latitudine care ajuta la parsarea acestora
            String longitude = parts[19];
            String latitude = parts[18];
            String cleanedLongitude = longitude.replace(",", "");
            String cleanedLatitude = latitude.replace(",", "");
            //crearea unui obiect de tip Location folosind design pattern-ul Builder pentru al putea adauga muzeului
            Location location = new Location.Builder(parts[3], Integer.parseInt(parts[16]))
                    .locality(parts[4])
                    .adminUnit(parts[5])
                    .address(parts[6])
                    .latitude(Integer.valueOf(cleanedLatitude))
                    .longitude(Integer.valueOf(cleanedLongitude))
                    .build();
            Person manager = null;
            //verificam daca exista un manager pentru muzeu si il adaugam in caz afirmativ
            if (!parts[13].isEmpty()) {
                StringBuilder name = null;
                String[] managerName = parts[13].split(" ");
                //formam prenumele acestuia
                if (managerName.length >= 2) {
                    name = new StringBuilder(managerName[0]);
                    for(int i = 0; i < managerName.length - 1; i++) {
                        name.append(" ").append(managerName[i]);
                    }
                }
                manager = new Person.PersonBuilder(name.toString(), managerName[managerName.length - 1], "director").build();

            }
            //verificam daca se cunoaste anul de infiintare pentru muzeu si il adaugam in caz afirmativ
            int foundingYear = 0;
            if (!parts[10].isEmpty()) {
                foundingYear = Integer.parseInt(parts[10]);
            }
            //instantiem un obiect de tip Museum folosind design pattern-ul Builder
            Museum museum = new Museum.Builder(parts[2], Integer.parseInt(parts[1]), Integer.parseInt(parts[14]), location)
                    .email(parts[12])
                    .manager(manager)
                    .foundingYear(foundingYear)
                    .phoneNumber(parts[8])
                    .fax(parts[9])
                    .url(parts[11])
                    .profile(parts[17])
                    .build();
            //adaugam muzeul in baza de date si in fisierul de output
            database.addMuseum(museum);
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                writer.println(museum.confirmAdd());
            }
        //prinderea exceptiilor care pot aparea si gestionarea lor
        } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                writer.println("Exception: Data is broken. ## (" + command + ")");
            }
        }
    }
}
