package org.example;
//clasa care creeaza peroane in functie de tipul lor
//urmarind design pattern-ul Factory
public class Factory {
    //metoda specifica design pattern-ului Factory
    public static Person creeazaPersoana(String Tip, String[] parts) {
        //in functie de tipul persoanei se creeaza un obiect de tipul respectiv
        switch (Tip) {
            case "profesor":
                Professor profesor = new Professor(parts[1], parts[2], "profesor");
                profesor.setAge(parts[4]).setEmail(parts[5]).setRole(parts[8]);
                return profesor;
            case "student":
                Student student =  new Student(parts[1], parts[2], "student");
                student.setAge(parts[4]).setEmail(parts[5]).setRole(parts[8]);
                return student;
            default:
                return null;
        }
    }
}
