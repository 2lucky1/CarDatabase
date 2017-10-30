package com.muntian.cardatabase.entities;

public class CarNumber implements Cloneable {
    private final String _serialBeforeNumber;
    private final int _number;
    private final String _serialAfterNumber;

    public CarNumber(final String serialBeforeNumber,
                     final int number,
                     final String serialAfterNumber) {
        checkArgs(serialBeforeNumber, number, serialAfterNumber);
        _serialBeforeNumber = serialBeforeNumber;
        _number = number;
        _serialAfterNumber = serialAfterNumber;
    }

    private void checkArgs(final String serialBeforeNumber,
                           final int number,
                           final String serialAfterNumber) {
        if (serialAfterNumber == null
                || serialBeforeNumber == null
                || number > 9999 || number < 0) {
            throw new IllegalArgumentException(String.format("Invalid args: %s %d %s%n",
                                               serialBeforeNumber, number, serialAfterNumber));
        }
    }

    public String getSerialBeforeNumber() {
        return _serialBeforeNumber;
    }

    public int getNumber() {
        return _number;
    }

    public String getSerialAfterNumber() {
        return _serialAfterNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarNumber carNumber = (CarNumber) o;

        if (getNumber() != carNumber.getNumber()) return false;
        if (!getSerialBeforeNumber().equals(carNumber.getSerialBeforeNumber())) return false;
        return getSerialAfterNumber().equals(carNumber.getSerialAfterNumber());
    }

    @Override
    public int hashCode() {
        int result = getSerialBeforeNumber().hashCode();
        result = 31 * result + getNumber();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %.4d %s", getSerialBeforeNumber(), getNumber(), getSerialAfterNumber());
    }
}
