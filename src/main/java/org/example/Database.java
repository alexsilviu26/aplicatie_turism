package org.example;

import java.util.HashSet;
import java.util.Set;

//clasa care defineste baza de date si atributele sale
public class Database {
    private static Database database;
    private final Set<Museum> museums;
    private final Set<Group> groups;

    //constructor privat pentru a evita instantieri multiple (singleton)
    private Database() {
        museums = new HashSet<>();
        groups = new HashSet<>();
    }

    // metodda care returneaza un anumit muzeu in functie de codul sau
    public Museum findMuseumByCode(long id) {
        for (Museum museum : museums) {
            if (museum.getCode() == id) {
                return museum;
            }
        }
        return null;
    }

    //metoda care returneaza un anumit grup in functie de codul muzeului si orar
    public Group findGroup(String museumCode, String timetable, Database database) {
        Group groupCrt = null;
        for (Group group : database.getGroups()) {
            if(group.getMuseumCode().equals(Integer.parseInt(museumCode)) && group.getTimetable().equals(timetable)) {
                groupCrt = group;
                break;
            }
        }
        return groupCrt;
    }

    //metoda care returneaza o instanta a clasei Database
    //conform design pattern-ului Singleton
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    //metoda care adauga un muzeu in baza de date
    public void addMuseum(Museum museum) {
        museums.add(museum);
    }

    //metoda care adauna un grup in baza de date
    public void addGroup(Group group) {
        groups.add(group);
    }

    //metoda care retuneaza grupurile din baza de date
    public Set<Group> getGroups() {
        return groups;
    }
}
