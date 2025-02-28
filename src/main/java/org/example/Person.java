package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Objects;

//clasa care modeleaza o persoana
public class Person {
    //atributele specifice clasei
    protected final String surname;
    protected final String name;
    protected String role;
    protected int age = 0;
    protected String email = null;

    //metoda de update specifica design pattern-ului Observer
    public void update(String museumName, long museumId, String eventMessage, File file) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println("To: " + email + " ## Message: " + museumName + " (" + museumId + ") " + eventMessage);
        }
    }

    //constructorul clasei specific design pattern-ului Builder
    private Person(PersonBuilder builder) {
        this.surname = builder.surname;
        this.name = builder.name;
        this.role = builder.role;
        this.age = builder.age;
        this.email = builder.email;
    }

    //constructorul clasei conform cerintei
    public Person(String surname, String name, String role) {
        this.surname = surname;
        this.name = name;
        this.role = role;
    }

    //gettere pentru atributele clasei
    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    //settere pentru atributele clasei
    public Person setAge(String age) {
        int ageInt = Integer.parseInt(age);
        if(age.isEmpty()){
            this.age = 0;
        }
        else
            this.age = ageInt;
        return this;
    }

    public Person setEmail(String email) {
        if(Objects.equals(email, "")){
            this.email = null;
        }
        else
            this.email = email;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //builder pentru a construi un obiect de tip Person
    public static class PersonBuilder {
        private final String surname;
        private final String name;
        private final String role;
        private final int age = 0;
        private String email = null;

        //constructorul subclasei
        public PersonBuilder(String surname, String name, String role) {
            this.surname = surname;
            this.name = name;
            this.role = role;
        }

        //metode pentru a seta atributele obiectului
        public PersonBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
