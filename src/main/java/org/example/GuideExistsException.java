package org.example;
//excepe pt cazul in care exista deja un ghid
public class GuideExistsException extends RuntimeException {
    public GuideExistsException(Professor profesor, String[] parts) {
        super(parts[9] + " ## " + parts[10] + " ## GuideExistsException: Guide already exists. ## (new guide: " + profesor + ")" );
    }
}
