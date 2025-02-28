package org.example;
// clasa Student care extinde clasa Person
public class Student extends Person {
    // variabilele specifice clasei Student
    private String school;
    private int studyYear;
    //constructor pentru clasa Student
    public Student(String surname, String name, String role) {
        super(surname, name, role);
    }
    //getere pentru atributele unui student
    public String getSchool() {
        return school;
    }

    public int getStudyYear() {
        return studyYear;
    }
    //setere pentru atributele unui student
    public Student setStudyYear(int studyYear) {
        this.studyYear = studyYear;
        //returnam pentru a putea folosi in cascada
        return this;
    }

    public Student setSchool(String school) {
        this.school = school;
        //returnam pentru a putea folosi in cascada
        return this;
    }

    //suprascriere a metodei toString pentru a afisa atributele unui student
    @Override
    public String toString() {
        return
                "surname=" + surname +
                        ", name=" + name +
                        ", role=" + role +
                        ", age=" + age +
                        ", email=" + email +
                        ", school=" + school +
                        ", studyYear=" + studyYear;
    }
}
