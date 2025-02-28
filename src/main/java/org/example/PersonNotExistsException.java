package org.example;
//exceptie pentru cazul in care o persoana nu exista in grup
public class PersonNotExistsException extends RuntimeException {
    public PersonNotExistsException(Person person, String[] parts) {
        super(parts[9] + " ## " + parts[10] + " ## PersonNotExistsException: Person was not found in the group. ## (" + person + ")" );
    }
}
