package org.example;
//clasa care arunca exceptie daca grupul nu exista in cazul in care se incearca gasirea unui ghid
public class GroupNotExistExceptionFind extends RuntimeException {
  public GroupNotExistExceptionFind(Person person, String[] parts) {
    super(parts[9] + " ## " + parts[10] + " ## GroupNotExistsException: Group does not exist. ## (find guide: " + person + ")" );
  }
}
