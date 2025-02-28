package org.example;
//exceptie pentru cazul in care se doreste adaugarea unui student ca ghid
public class GuideTypeException extends RuntimeException {
    public GuideTypeException(Student student, String[] parts) {
        super(parts[9] + " ## " + parts[10] + " ## GuideTypeException: Guide must be a professor. ## (new guide: " + student + ")" );
    }
}
