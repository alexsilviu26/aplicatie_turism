package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

//metode ce modeleaza comenzile pentru grupuri
public class GroupCommands {
    //metoda care contine logica pentru adaugarea unui ghid
    public static void addGuide(String[] parts, Database database, File file) throws FileNotFoundException {
        //instatierea folosind Factory a unei persoane cu datele din fisierul de intrare
        Person person = Factory.creeazaPersoana(parts[3], parts);

        //daca persoana este de tip Student lansam eroarea de tip GuideTypeException
        if (person instanceof Student student) {
            //setam atributele suplimentare pentru student
            student.setStudyYear(Integer.parseInt(parts[7])).setSchool(parts[6]);
            throw new GuideTypeException(student, parts);
        }

        //daca persoana este de tip profesor
        if (person instanceof Professor professor) {
            //setam atributele suplimentare pentru profesor
            professor.setExperience(Integer.parseInt(parts[7])).setSchool(parts[6]);
            //cautam grupul in care dorim sa adaugam ghidul si
            //in cazul in care acesta exista si are deja un ghid aruncam eroarea de tip GuideExistsException
            Group groupCrt = null;
            boolean guideExists = false;
            for (Group group : database.getGroups()) {
                if(group.getMuseumCode().equals(Integer.parseInt(parts[9])) && group.getTimetable().equals(parts[10]))
                    if (group.getGuide() != null) {
                        groupCrt = group;
                        guideExists = true;
                        break;
                    }
            }
            if (guideExists) {
                throw new GuideExistsException(groupCrt.getGuide(), parts);
            }
                //daca grupul nu are ghid, adaugam ghidul in grup si il adaugam la lista de observatori a muzeului
                //de care apartine grupul
                //cream un grup nou si il adaugam in baza de date
                Group newGroup = new Group.Builder(Integer.parseInt(parts[9]), parts[10]).build();
                newGroup.addGuide(professor);
                database.addGroup(newGroup);
                //cautam muzeul in care se afla grupul si adaugam ghidul ca observator
                Museum museum = database.findMuseumByCode(Integer.parseInt(parts[9]));
                if(museum != null) {
                    museum.addObserver(newGroup.getGuide());
                }
                //scriem in fisierul de iesire confirmarea adaugarii ghidului
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                    writer.println(newGroup.confirmGuide());
                }
        }
    }

    //metoda care contine logica pentru adaugarea unui membru
    //in cadrul metodei am ales o abordare separata pentru profesori si studenti deoarece
    //acestia au atribute suplimentare diferite ce trebuie afisate in fisierul de iesire
    public static void addMember(String[] parts, Database database, File file) throws FileNotFoundException, GroupNotExistsException {
        //cautam grupul in care dorim sa adaugam membrul
        Group groupCrt = database.findGroup(parts[9], parts[10], database);
        //instantiem o persoana cu datele din fisierul de intrare
        Person person = Factory.creeazaPersoana(parts[3], parts);
        //daca persoana este de tip profesor
        if(person instanceof Professor professor) {
            //ii setam atributele suplimentare
            professor.setSchool(parts[6]).setExperience(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa adaugam profesorul nu exista aruncam eroarea de tip GroupNotExistsException
            if(groupCrt == null)
                throw new GroupNotExistsException(professor, parts);
            //verificam daca grupul are deja 10 membri si in caz afirmativ aruncam eroarea de tip GroupThresholdException
            if(groupCrt.getNrMembers() > 9)
                throw new GroupThresholdException(professor, parts);
            //adaugam membrul in grup si scriem in fisierul de iesire
            groupCrt.addMember(professor);
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                writer.println(groupCrt.confirmMember() + professor);
            }
        }
        //daca persoana este de tip student
        if(person instanceof Student student) {
            //ii setam atributele suplimentare
            student.setSchool(parts[6]).setStudyYear(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa adaugam studentul nu exista aruncam eroarea de tip GroupNotExistsException
            if(groupCrt == null)
                throw new GroupNotExistsException(student, parts);
            //verificam daca grupul are deja 10 membri si in caz afirmativ aruncam eroarea de tip GroupThresholdException
            if(groupCrt.getNrMembers() > 9)
                throw new GroupThresholdException(student, parts);
            //adaugam membrul in grup si scriem in fisierul de iesire
            groupCrt.addMember(student);
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                writer.println(groupCrt.confirmMember() + student);
            }
        }
    }

    //metoda care contine logica pentru eliminarea unui membru
    //in cadrul metodei am ales o abordare separata pentru profesori si studenti deoarece
    //acestia au atribute suplimentare diferite ce trebuie afisate in fisierul de iesire
    public static void removeMember(String[] parts, Database database, File file) throws FileNotFoundException, PersonNotExistsException, GroupNotExistsException {
        //cautam grupul din care dorim sa eliminam membrul
        Group groupCrt = database.findGroup(parts[9], parts[10], database);
        //instantiem o persoana cu datele din fisierul de intrare
        Person person = Factory.creeazaPersoana(parts[3], parts);
        //daca persoana este de tip profesor
        if(person instanceof Professor professor) {
            //ii setam atributele suplimentare
            professor.setSchool(parts[6]).setExperience(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa eliminam profesorul nu exista aruncam eroarea de tip GroupNotExistsExceptionRemove
            if(groupCrt == null)
                throw new GroupNotExistsExceptionRemove(professor, parts);
        }
        //daca persoana este de tip student
        if(person instanceof Student student) {
            //ii setam atributele suplimentare
            student.setSchool(parts[6]).setStudyYear(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa eliminam studentul nu exista aruncam eroarea de tip GroupNotExistsExceptionRemove
            if(groupCrt == null)
                throw new GroupNotExistsExceptionRemove(student, parts);
        }

        //verificam daca membrul exista in grup si in caz afirmativ il eliminam
        //in cadrul metodei am ales o abordare separata pentru profesori si studenti deoarece
        //acestia au atribute suplimentare diferite ce trebuie afisate in fisierul de iesire
        List<Person> members = groupCrt.getMembers();
        boolean exists = false;
        for (Person member : members) {
            if (member.getSurname().equals(parts[1]) && member.getName().equals(parts[2])) {
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                    writer.println(groupCrt.confirmRem() + member);
                }
                exists = true;
                members.remove(member);
                groupCrt.decNrMembers();
                break;
            }
        }
        //daca membrul nu exista aruncam eroarea de tip PersonNotExistsException
        if (!exists) {
            throw new PersonNotExistsException(person, parts);
        }

    }

    //metoda care contine logica pentru eliminarea unui ghid
    //in cadrul metodei am ales o abordare separata pentru profesori si studenti deoarece
    //acestia au atribute suplimentare diferite ce trebuie afisate in fisierul de iesire
    public static void removeGuide(String[] parts, Database database, File file) throws FileNotFoundException {
        //cautam grupul din care dorim sa eliminam ghidul
        Group groupCrt = database.findGroup(parts[9], parts[10], database);
        //eliminam ghidul din grup si scriem in fisierul de iesire
        Person guide = groupCrt.getGuide();
        groupCrt.removeGuide();
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println(groupCrt.confirmRemGuide() + guide);
        }
    }

    //metoda care contine logica pentru gasirea unui ghid sau a unui membru
    //in cadrul metodei am ales o abordare separata pentru profesori si studenti deoarece
    //acestia au atribute suplimentare diferite ce trebuie afisate in fisierul de iesire
    public static void findGuide(String[] parts, Database database, File file) throws FileNotFoundException, GroupNotExistsException {
        //cautam grupul in care dorim sa gasim ghidul
        Group groupCrt = database.findGroup(parts[9], parts[10], database);
        //instantiem o persoana cu datele din fisierul de intrare
        Person person = Factory.creeazaPersoana(parts[3], parts);
        //daca persoana este de tip student aruncam eroarea de tip GuideTypeException
        if(person instanceof Student student) {
            //ii setam atributele suplimentare
            student.setSchool(parts[6]).setStudyYear(Integer.parseInt(parts[7]));
            throw new GuideTypeException(student, parts);
        }
        //daca persoana este de tip profesor
        if(person instanceof Professor professor) {
            //ii setam atributele suplimentare
            professor.setSchool(parts[6]).setExperience(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa gasim ghidul nu exista aruncam eroarea de tip GroupNotExistsExceptionFind
            if (groupCrt == null)
                throw new GroupNotExistExceptionFind(professor, parts);
            //daca ghidul nu exista in grup aruncam eroarea de tip GuideNotExistsException
            if (!groupCrt.getGuide().getSurname().equals(professor.getSurname())) {
                Utils.notFound(file, groupCrt, professor, "guide");
            }
            else
                //daca ghidul exista in grup scriem in fisierul de iesire folosind functia found din clasa Utils
                Utils.found(file, groupCrt, professor, "guide");
        }
    }

    //metoda care contine logica pentru gasirea unui membru
    //in cadrul metodei am ales o abordare separata pentru profesori si studenti deoarece
    //acestia au atribute suplimentare diferite ce trebuie afisate in fisierul de iesire
    public static void findMember(String[] parts, Database database, File file) throws FileNotFoundException, GroupNotExistsException {
        //cautam grupul in care dorim sa gasim membrul
        Group groupCrt = database.findGroup(parts[9], parts[10], database);
        //instantiem o persoana cu datele din fisierul de intrare
        Person person = Factory.creeazaPersoana(parts[3], parts);
        //daca persoana este de tip student
        if(person instanceof Student student) {
            //setam atributele suplimentare pentru student
            student.setSchool(parts[6]).setStudyYear(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa gasim studentul nu exista aruncam eroarea de tip GroupNotExistsExceptionFind
            if (groupCrt == null)
                throw new GroupNotExistExceptionFind(student, parts);
            //verificam daca membrul exista sau nu in grup
            boolean exists = false;
            for (Person member : groupCrt.getMembers()) {
                //daca membrul exista scriem in fisierul de iesire folosind functia found din clasa Utils
                if (member.getSurname().equals(student.getSurname()) && member.getName().equals(student.getName())) {
                    exists = true;
                    Utils.found(file, groupCrt, student, "member");
                    break;
                }
            }
            //daca membrul nu exista scriem in fisierul de iesire folosind functia notFound din clasa Utils
            if(!exists)
                Utils.notFound(file, groupCrt, student, "member");
        }
        //daca persoana este de tip profesor
        if(person instanceof Professor professor) {
            //setam atributele suplimentare pentru profesor
            professor.setSchool(parts[6]).setExperience(Integer.parseInt(parts[7]));
            //daca grupul la care am dori sa gasim profesorul nu exista aruncam eroarea de tip GroupNotExistsExceptionFind
            if (groupCrt == null)
                throw new GroupNotExistExceptionFind(professor, parts);
            //verificam daca membrul exista sau nu in grup
            boolean exists = false;
            for (Person member : groupCrt.getMembers()) {
                if (member.getSurname().equals(professor.getSurname()) && member.getName().equals(professor.getName())) {
                    exists = true;
                    //daca membrul exista scriem in fisierul de iesire folosind functia found din clasa Utils
                    Utils.found(file, groupCrt, professor, "member");
                    break;
                }
            }
            //daca membrul nu exista scriem in fisierul de iesire folosind functia notFound din clasa Utils
            if (!exists)
                Utils.notFound(file, groupCrt, professor, "member");
        }
    }
}