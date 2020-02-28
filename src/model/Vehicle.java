package model;

public class Vehicle extends VehicleAbstract {

    private Long license;
    private Type type;
    private String description;
    private Integer year;
    private Status status;

    public Vehicle() {}

    public Vehicle(Long license, Type type, String description, Integer year, Status status) {
        this.license = license;
        this.type = type;
        this.description = description;
        this.year = year;
        this.status = status;
    }

    public Vehicle(Builder<?> builder) {
        this.license = builder.license;
        this.type = builder.type;
        this.description = builder.description;
        this.year = builder.year;
        this.status = builder.status;
    }

    public static class Car extends Vehicle {

        public Car() {}

        public Car(Long license, Type type, String description, Integer year, Status status) {
            super(license, type, description, year, status);
        }

        @Override
        public String toString() {
            return "Car { " + super.toString()+" }";
        }

        public static class Builder extends Vehicle.Builder<Builder> {

            public Builder() {}

            @Override
            public Car build() {
                return new Car(this);
            }
        }

        public Car(Builder builder) {
            super(builder);
        }
    }

    public static class Motorcycle extends Vehicle {

        private Boolean extraSit;

        public Motorcycle(Long license, Type type, String description, Integer year, Status status, Boolean extraSit) {
            super(license, type, description, year, status);
            this.extraSit=extraSit;
        }

        public Boolean getExtraSit() {
            return extraSit;
        }

        @Override
        public String toString() {
            return "Motorcycle { " +
                    super.toString() + ", extraSit=" + extraSit + " }";
        }

        public static class Builder extends Vehicle.Builder<Builder> {

            private Boolean extraSit;

            public Builder() {}

            public Motorcycle.Builder setExtraSit(Boolean extraSit) {
                this.extraSit = extraSit;
                return this;
            }

            @Override
            public Motorcycle build() {
                return new Motorcycle(this, extraSit);
            }
        }

        public Motorcycle(Builder builder, Boolean extraSit) {
            super(builder);
            this.extraSit = extraSit;
        }
    }

    public static class Truck extends Vehicle {

        private Double weightLimit;

        public Truck() {}

        public Truck(Long license, Type type, String description, Integer year, Status status, Double weightLimit) {
            super(license, type, description, year, status);
            this.weightLimit=weightLimit;
        }

        public Double getWeightLimit() {
            return weightLimit;
        }

        @Override
        public String toString() {
            return "Truck { " +
                    super.toString() + ", weightLimit=" + String.format("%.2f", weightLimit) +
                    " }";
        }

        public static class Builder extends Vehicle.Builder<Builder> {

            private Double weightLimit;

            public Builder() {}

            public Truck.Builder setWeightLimit(Double weightLimit) {
                this.weightLimit = weightLimit;
                return this;
            }

            @Override
            public Truck build() {
                return new Truck(this, weightLimit);
            }
        }

        public Truck(Builder builder, Double weightLimit) {
            super(builder);
            this.weightLimit = weightLimit;
        }
    }

    public Long getLicense() {
        return license;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Integer getYear() {
        return year;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return  "license=" + license +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", status=" + status;
    }

    public static class Builder<T extends Builder<T>> {

        private Long license;
        private Type type;
        private String description;
        private Integer year;
        private Status status;

        public Builder() {}

        public T setLicense(Long license) {
            this.license = license;
            return (T) this;
        }

        public T setType(Type type) {
            this.type = type;
            return (T) this;
        }

        public T setDescription(String description) {
            this.description = description;
            return (T) this;
        }

        public T setYear(Integer year) {
            this.year = year;
            return (T) this;
        }

        public T setStatus(Status status) {
            this.status = status;
            return (T) this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
