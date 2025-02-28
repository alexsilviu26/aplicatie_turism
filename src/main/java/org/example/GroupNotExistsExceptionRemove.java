package org.example;
//exceptie care se arunca atunci cand se incearca stergerea unui membru dintr-un grup care nu exista
public class GroupNotExistsExceptionRemove extends RuntimeException {
    public GroupNotExistsExceptionRemove(Person person, String[] parts) {
        super(parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: Group does not exist. ## (removed member: " + person + ")" );
    }
}
