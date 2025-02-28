package org.example;
// exceptie care semnaleaza faptul ca un grup nu exista in momentul in care se incearca adaugarea unui nou membru
public class GroupNotExistsException extends Exception {
    public GroupNotExistsException(Person person, String[] parts) {
        super(parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: Group does not exist. ## (new member: " + person + ")" );
    }
}
