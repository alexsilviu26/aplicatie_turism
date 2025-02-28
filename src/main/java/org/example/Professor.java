package org.example;
//clasa profesor care mosteneste clasa Person
public class Professor extends Person {
    //atributele specifice unui profesor
    private String school;
    private int experience;

    //constructor pentru clasa Professor
    public Professor(String surname, String name, String role) {
        super(surname, name, role);
    }
    //getere si setere pentru atributele unui profesor
    public String getSchool() {
        return school;
    }

    public int getExperience() {
        return experience;
    }

    public Professor setExperience(int experience) {
        this.experience = experience;
        //returnam pentru a putea folosi in cascada
        return this;
    }

    public Professor setSchool(String school) {
        this.school = school;
        //returnam pentru a putea folosi in cascada
        return this;
    }

    //suprascriere a metodei toString pentru a afisa atributele unui profesor
    @Override
    public String toString() {
        return
                "surname=" + surname +
                ", name=" + name +
                ", role=" + role +
                ", age=" + age +
                ", email=" + email +
                ", school=" + school +
                ", experience=" + experience;
    }
}
