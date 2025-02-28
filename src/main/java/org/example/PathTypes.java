package org.example;
//enumerare de tipuri de cale
public enum PathTypes {
    GROUPS("groups"),
    LISTENER("listener"),
    MUSEUMS("museums");
    //campul care retine valoarea
    private final String value;

    //metoda care returneaza valoarea
    public String getValue() {
        return this.value;
    }

    //constructorul enumerarii
    PathTypes(String value) {
        this.value = value;
    }


}
