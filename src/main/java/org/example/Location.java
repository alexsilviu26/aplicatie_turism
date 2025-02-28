package org.example;

//clasa care implementeaza un obiect de tip Location
public class Location {
    //atributele unui obiect de tip Location
    private final String county;
    private final Integer sirutaCode;

    private final String locality;
    private final String adminUnit;
    private final String address;
    private final Integer latitude;
    private final Integer longitude;

    //constructorul clasei specific design pattern-ului Builder
    private Location(Builder builder) {
        this.county = builder.county;
        this.sirutaCode = builder.sirutaCode;
        this.locality = builder.locality;
        this.adminUnit = builder.adminUnit;
        this.address = builder.address;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    //subclasa Builder specifica design pattern-ului Builder
    public static class Builder {
        //atributele builderului
        private final String county;
        private final Integer sirutaCode;

        private String locality = null;
        private String adminUnit = null;
        private String address = null;
        private Integer latitude = null;
        private Integer longitude = null;

        //constructorul subclasei
        public Builder(String county, Integer sirutaCode) {
            this.county = county;
            this.sirutaCode = sirutaCode;
        }

        //metodele pentru a seta atributele obiectului
        public Builder locality(String locality) {
            this.locality = locality;
            return this;
        }

        public Builder adminUnit(String adminUnit) {
            this.adminUnit = adminUnit;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder latitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(Integer longitude) {
            this.longitude = longitude;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}

