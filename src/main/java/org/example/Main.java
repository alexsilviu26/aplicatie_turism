package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{
        //variabilele necesare rularii programului
        Database database = Database.getInstance();
        Enum<PathTypes> pathType = null;
        String filePath;
        String museumPath;
        String grroupPath;
        String eventPath;
        String filePathIn;
        String filePathOut;
        String museumPathIn;
        String museumPathOut;
        String groupPathIn;
        String groupPathOut;
        String eventPathIn;
        String eventPathOut;
        //fisierele de output pentru fiecare tip de comanda
        File museumFileOut = null;
        File groupFileOut = null;
        File eventFileOut = null;
        //comenzile pentru fiecare tip de comanda
        ArrayList<String> museumCommands = new ArrayList<>();
        ArrayList<String> groupCommands = new ArrayList<>();
        ArrayList<String> eventCommands = new ArrayList<>();
        ArrayList<String> commands = new ArrayList<>();
        //verificam daca avem 2 sau 4 argumente si prelucram in conform cerintei
        if(args.length == 2)
        {
            //verificam tipul cemenzii si il setam
            pathType = switch (args[0]) {
                case "museums" -> PathTypes.MUSEUMS;
                case "groups" -> PathTypes.GROUPS;
                case "listener" -> PathTypes.LISTENER;
                default -> pathType;
            };
            //prelucram parametrii programului
            filePath = args[1];
            filePathIn = filePath + ".in";
            filePathOut = filePath + ".out";
            //in functie de tipul comenzii, cream fisierele de output
            if(pathType == PathTypes.MUSEUMS) {
                museumFileOut = new File(filePathOut);
                museumFileOut.createNewFile();
                Utils.Parse(filePathIn, museumCommands);
            }
            else if(pathType == PathTypes.GROUPS) {
                groupFileOut = new File(filePathOut);
                groupFileOut.createNewFile();
                Utils.Parse(filePathIn, groupCommands);
            }
            //in acest caz nu avem pathType de tip listener, deci nu trebuie abordat
        }
        //daca avem 4 argumente, prelucram in conformitate cu cerinta
        if(args.length == 4) {
            //setam tipul pathului
            pathType = switch (args[0]) {
                case "museums" -> PathTypes.MUSEUMS;
                case "groups" -> PathTypes.GROUPS;
                case "listener" -> PathTypes.LISTENER;
                default -> pathType;
            };
            //preluam parametrii programului
            museumPath = args[1];
            grroupPath = args[2];
            eventPath = args[3];
            //cream fisierele de input si output pentru fiecare tip de comanda
            museumPathIn = museumPath + ".in";
            museumPathOut = museumPath + ".out";
            groupPathIn = grroupPath + ".in";
            groupPathOut = grroupPath + ".out";
            eventPathOut = eventPath + ".out";
            eventPathIn = eventPath + ".in";
            museumFileOut = new File(museumPathOut);
            groupFileOut = new File(groupPathOut);
            eventFileOut = new File(eventPathOut);
            //parsam fisierele de input si le adaugam in arraylistul corespunzator
            //cu ajutorul metodei Parse din clasa Utils
            Utils.Parse(museumPathIn, museumCommands);
            Utils.Parse(groupPathIn, groupCommands);
            Utils.Parse(eventPathIn, eventCommands);
            //cream fisierele de output
            museumFileOut.createNewFile();
            groupFileOut.createNewFile();
            eventFileOut.createNewFile();
        }
        // in functie de tipul comenzii, interpretam comenzile si le executam
        //daca avem path de tip museums, interpretam comenzile pentru muzee
        if(pathType == PathTypes.MUSEUMS) {
            //parcurgem comenzile si le interpretam
            for(String command: museumCommands) {
                //impartim fiecare comanda in functie de caracterul "|" in array de stringuri
                String[] parts = command.split("\\|");
                String toDo = parts[0];
                //in functie de comanda, apelam metoda corespunzatoare
                if(toDo.equals("ADD MUSEUM"))
                    MuseumCommands.addMuseum(parts, database, museumFileOut, command);
                }
        }
        //daca avem path de tip groups, interpretam comenzile pentru grupuri
        if(pathType == PathTypes.GROUPS) {
            // parcurgem comenzile si le interpretam
            for(String command: groupCommands) {
                // impartim fiecare comanda in functie de caracterul "|" in array de stringuri
                String[] parts = command.split("\\|");
                String toDo = parts[0];
                // in functie de comanda, apelam metoda corespunzatoare
                switch (toDo) {
                    case "ADD GUIDE":
                        try {
                            GroupCommands.addGuide(parts, database, groupFileOut);
                        }
                        catch (GuideTypeException | GuideExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "FIND GUIDE":
                        try {
                            GroupCommands.findGuide(parts, database, groupFileOut);
                        }
                        catch (GuideTypeException | GuideExistsException | GroupNotExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "REMOVE GUIDE":
                        GroupCommands.removeGuide(parts, database, groupFileOut);
                        break;
                    case "ADD MEMBER":
                        try {
                            GroupCommands.addMember(parts, database, groupFileOut);
                        }
                        catch (GroupNotExistsException | GroupThresholdException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "FIND MEMBER":
                        try {
                            GroupCommands.findMember(parts, database, groupFileOut);
                        }
                        catch (GroupNotExistsException | PersonNotExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "REMOVE MEMBER":
                        try {
                            GroupCommands.removeMember(parts, database, groupFileOut);
                        }
                        catch (PersonNotExistsException | GroupNotExistsExceptionRemove | GroupNotExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                }
            }
        }
        // daca avem path de tip listener, interpretam comenzile pentru fiecare tip de comanda
        if(pathType == PathTypes.LISTENER) {
            // adaugam comenzile pentru fiecare tip de comanda in arraylistul commands
            //pentru a le interpreta si executa pe rand pe toate
            commands.addAll(museumCommands);
            commands.addAll(groupCommands);
            commands.addAll(eventCommands);
            // parcurgem comenzile si le interpretam
            for(String command: commands) {
                // impartim fiecare comanda in functie de caracterul "|" in array de stringuri
                String[] parts = command.split("\\|");
                String toDo = parts[0];
                // in functie de comanda, apelam metoda corespunzatoare
                switch (toDo) {
                    case "ADD EVENT":
                        EventCommands.addEvent(parts, database, eventFileOut);
                        break;
                    case "ADD MUSEUM":
                        MuseumCommands.addMuseum(parts, database, museumFileOut, command);
                        break;
                    case "ADD GUIDE":
                        try {
                            GroupCommands.addGuide(parts, database, groupFileOut);
                        }
                        catch (GuideTypeException | GuideExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "FIND GUIDE":
                        try {
                            GroupCommands.findGuide(parts, database, groupFileOut);
                        }
                        catch (GuideTypeException | GuideExistsException | GroupNotExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "REMOVE GUIDE":
                        GroupCommands.removeGuide(parts, database, groupFileOut);
                        break;
                    case "ADD MEMBER":
                        try {
                            GroupCommands.addMember(parts, database, groupFileOut);
                        }
                        catch (GroupNotExistsException | GroupThresholdException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "FIND MEMBER":
                        try {
                            GroupCommands.findMember(parts, database, groupFileOut);
                        }
                        catch (GroupNotExistsException | PersonNotExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                    case "REMOVE MEMBER":
                        try {
                            GroupCommands.removeMember(parts, database, groupFileOut);
                        }
                        catch (PersonNotExistsException | GroupNotExistsExceptionRemove | GroupNotExistsException e) {
                            Utils.handleException(e, groupFileOut);
                        }
                        break;
                }
            }
        }
    }
}
