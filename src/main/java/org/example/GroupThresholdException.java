package org.example;
//exceptie pentru cazul in care un grup are mai mult de 10 membri
public class GroupThresholdException extends RuntimeException {
    public GroupThresholdException(Person person, String[] parts) {
        super(parts[9] + " ## " + parts[10] + " ## GroupThresholdException: Group cannot have more than 10 members. ## (new member: " + person + ")" );
    }
}
