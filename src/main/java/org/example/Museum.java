package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//clasa care modeleaza un muzeu
public class Museum {
    //atributele specifice clasei
    private final String name;
    private final long code;
    private final long supervisorCode;
    private final Location location;

    private final Person manager;
    private final Integer foundingYear;
    private final String phoneNumber;
    private final String fax;
    private final String email;
    private final String url;
    private final String profile;
    //lista de observatori a clasei specifice design pattern-ului Observer
    private final List<Person> observers = new ArrayList<>();
    //metode specifice design pattern-ului Observer
    public void addObserver(Person observer) {
        observers.add(observer);
    }

    public void addEvent(String eventMessage, File file) throws FileNotFoundException {
        notifyObservers(eventMessage, file);
    }

    private void notifyObservers(String eventMessage, File file) throws FileNotFoundException {
        for (Person observer : observers) {
            observer.update(name, code, eventMessage, file);
        }
    }

    //constructorul clasei specific design pattern-ului Builder
    private Museum(Builder builder) {
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
        this.manager = builder.manager;
        this.foundingYear = builder.foundingYear;
        this.phoneNumber = builder.phoneNumber;
        this.fax = builder.fax;
        this.email = builder.email;
        this.url = builder.url;
        this.profile = builder.profile;
    }

    //gettere pentru atributele clasei
    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    //metoda care returneaza un mesaj de confirmare a adaugarii unui muzeu
    public String confirmAdd() {
        return this.getCode() + ": " + this.getName();
    }

    //subclasa Builder specifica design pattern-ului Builder
    public static class Builder {
        //atributele builderului
        private final String name;
        private final long code;
        private final long supervisorCode;
        private final Location location;

        private Person manager = null;
        private Integer foundingYear = null;
        private String phoneNumber = null;
        private String fax = null;
        private String email = null;
        private String url = null;
        private String profile = null;

        //constructorul subclasei
        public Builder(String name, long code, long supervisorCode, Location location) {
            this.name = name;
            this.code = code;
            this.supervisorCode = supervisorCode;
            this.location = location;
        }

        //metodele pentru a seta atributele obiectului
        public Builder manager(Person manager) {
            this.manager = manager;
            return this;
        }

        public Builder foundingYear(Integer foundingYear) {
            this.foundingYear = foundingYear;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder profile(String profile) {
            this.profile = profile;
            return this;
        }

        public Museum build() {
            return new Museum(this);
        }
    }
}
