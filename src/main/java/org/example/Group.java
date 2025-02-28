package org.example;

import java.util.ArrayList;
import java.util.List;
//clasa care defineste un grup
public class Group {
    //atributele specifice clasei
    private List<Person> members = new java.util.ArrayList<>();
    private Professor guide;
    private Integer museumCode;
    private final String timetable;
    private int nrMembers;

    //gettere pentru atributele clasei
    public int getNrMembers() {
        return nrMembers;
    }

    public void setMuseumCode(Integer museumCode) {
        this.museumCode = museumCode;
    }

    public List<Person> getMembers() {
        return members;
    }

    public Integer getMuseumCode() {
        return museumCode;
    }

    public String getTimetable() {
        return timetable;
    }

    public Professor getGuide() {
        return guide;
    }

    //metode pentru a adauga sau elimina membrii din grup
    public void incNrMembers() {
        this.nrMembers++;
    }

    public void decNrMembers() {
        this.nrMembers--;
    }

    public void addMember(Person person) {
        members.add(person);
        incNrMembers();
    }

    //metoda pentru a adauna un ghid in grup
    public void addGuide(Professor professor) {
        guide = professor;
    }

    //metoda pentru a elimina un ghid din grup
    public void removeGuide() {
        guide = null;
    }

    //metode pentru a confirma prin scris adaugarea sau eliminarea unui membru sau ghid
    public String confirmGuide() {
        return this.getMuseumCode() + " ## " + this.timetable + " ## new guide: " + guide;
    }

    public String confirmMember() {
        return this.getMuseumCode() + " ## " + this.timetable + " ## new member: ";
    }

    public String confirmRem() {
        return this.getMuseumCode() + " ## " + this.timetable + " ## removed member: ";
    }

    public String confirmRemGuide() {
        return this.getMuseumCode() + " ## " + this.timetable + " ## removed guide: ";
    }

    //constructorul clasei de tip privat
    private Group(Builder builder) {
        this.members = builder.members;
        this.guide = builder.guide;
        this.museumCode = builder.museumCode;
        this.timetable = builder.timetable;
        this.nrMembers = builder.nrMembers;
    }

    //subclasa Builder pentru a construi un obiect de tip Group
    //specifica design pattern-ului Builder
    public static class Builder {
        private List<Person> members = new ArrayList<>();
        private Professor guide;
        private Integer museumCode;
        private String timetable;
        private int nrMembers = 0;

        //constructorul subclasei
        public Builder(int museumCode, String timetable) {
            this.museumCode = museumCode;
            this.timetable = timetable;
        }

        //metode pentru a seta atributele obiectului
        public Builder setMuseumCode(Integer museumCode) {
            this.museumCode = museumCode;
            return this;
        }

        public Builder setTimetable(String timetable) {
            this.timetable = timetable;
            return this;
        }

        public Builder addMember(Person person) {
            this.members.add(person);
            this.nrMembers++;
            return this;
        }

        public Builder setGuide(Professor guide) {
            this.guide = guide;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }


}
