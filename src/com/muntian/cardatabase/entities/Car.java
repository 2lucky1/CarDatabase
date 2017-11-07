package com.muntian.cardatabase.entities;

import java.awt.*;
import java.util.*;

public class Car implements Cloneable {
    private final String _mark;
    private final String _model;
    private final Calendar _year;

    private Set<Driver> _drivers = new HashSet<>();
    private int _distance = 0;
    private Color _color;

    public Car(final String mark,
               final String model,
               final Calendar year,
               final Color color) {
        if (mark == null
                || model == null
                || year == null
                || color == null) {
            throw new IllegalArgumentException("Error creating car. Argument is null");
        }

        _mark = mark;
        _model = model;
        _year = year;
        _color = color;
    }

    private Car(Car car) {
        _color = car._color;
        _model = car._model;
        _mark = car._mark;
        _distance = car._distance;
        _drivers = car._drivers;
        _year = car._year;
    }

    public String getMark() {
        return _mark;
    }

    public String getModel() {
        return _model;
    }

    public Set<Driver> getDrivers() {
        return Collections.unmodifiableSet(_drivers);
    }

    public Calendar getYear() {
        return _year;
    }

    public int getDistance() {
        return _distance;
    }

    public void setDistance(final int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance is < 0");
        }
        _distance = distance;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new NullPointerException("Color cannot be null");
        }
        _color = color;
    }

    //My code
    @Override
    public String toString() {
        return "Car{" +
                "mark: " + this.getMark() +
                ", model: " + this.getModel() +
                ", year: " + this.getYear().get(Calendar.YEAR) +
                ", distance: " + this.getDistance() +
                ", color: " + this.getColor() + '}';
    }

    public boolean isModifiable() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (getDistance() != car.getDistance()) return false;
        if (!getMark().equals(car.getMark())) return false;
        if (!getModel().equals(car.getModel())) return false;
        if (!getDrivers().equals(car.getDrivers())) return false;
        if (!getYear().equals(car.getYear())) return false;
        return getColor().equals(car.getColor());
    }

    @Override
    public int hashCode() {
        int result = getMark().hashCode();
        result = 31 * result + getModel().hashCode();
        result = 31 * result + getYear().hashCode();
        return result;
    }

    public static Car unmodifiable(Car car) {
        return new UnmodifiableCar(car);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //////////////////////////////////////////////////////////////////////////////////////

    private static class UnmodifiableCar extends Car {

        public UnmodifiableCar(Car car) {
            super(car);
        }

        @Override
        public void setDistance(int distance) {
            throw new UnsupportedOperationException("Car is not modifiable");
        }

        @Override
        public void setColor(Color color) {
            throw new UnsupportedOperationException("Car is not modifiable");
        }

        @Override
        public boolean isModifiable() {
            return false;
        }
    }
}
