package com.muntian.cardatabase.entities;

//This whole class is my code
public class Driver implements Cloneable {
    private String firstName;
    private String lastName;
    private String birthDay;
    private int iDnumber;

    public Driver(String firstName, String lastName, String birthDay, int iDnumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.iDnumber = iDnumber;
    }

    private void checkArgs(String firstName,
                           String lastName) {
        if (firstName == null || lastName == null || birthDay == null || iDnumber == 0) {
            throw new IllegalArgumentException(String.format("Invalid args: %s %s %s %d%n",
                    firstName, lastName,birthDay,iDnumber));
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

    public String getBirthDay() {
        return birthDay;
    }

    private void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getiDnumber() {
        return iDnumber;
    }

    private void setiDnumber(int iDnumber) {
        this.iDnumber = iDnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        return iDnumber == driver.iDnumber;
    }

    @Override
    public int hashCode() {
        return iDnumber;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d", getFirstName(), getLastName(), getBirthDay(), getiDnumber());
    }
}
