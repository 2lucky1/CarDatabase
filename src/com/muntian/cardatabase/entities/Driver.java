package com.muntian.cardatabase.entities;

public class Driver implements Cloneable {
    private String firstName;
    private String lastName;

    public Driver(String firstName, String lastName) {
        checkArgs(firstName,lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private void checkArgs(String firstName,
                           String lastName) {
        if (firstName == null|| lastName == null) {
            throw new IllegalArgumentException(String.format("Invalid args: %s %s%n",
                    firstName, lastName));
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (!firstName.equals(driver.firstName)) return false;
        return lastName.equals(driver.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
