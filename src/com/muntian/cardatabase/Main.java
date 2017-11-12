package com.muntian.cardatabase;

import com.muntian.cardatabase.db.DataBase;
import com.muntian.cardatabase.db.impl.memory.MemoryDataBase;
import com.muntian.cardatabase.entities.Car;
import com.muntian.cardatabase.entities.CarNumber;
import com.muntian.cardatabase.entities.Driver;

import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

//This whole class is my code
public class Main {

    private static final String ADDITION_TO_THE_DATA_BASE = "Addition to the data base";
    private static final String DONE = "Done";
    private static final String CHANGED_DATA_BASE = "Changed data base";

    public static void main(String[] args) {
        serializationOfMemoryDataBase();
//        testClone();
//        testAdd();
//        testDeleteByKey();
//        testDeleteByValue();
//        testDeleteAll();
//        testFindByCarNumber();
//        testFindAll();
//        testSetNewColor();

    }

    private static void serializationOfMemoryDataBase() {
        //Create object
        DataBase dataBase = new MemoryDataBase();
        fillDataBase(dataBase);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        //Write object in file
        System.out.println("Start of writing in the file");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cars.dat"))) {
            oos.writeObject(dataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Writing is finished");

        //Read object
        System.out.println("Reading from the file");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cars.dat"))) {
            DataBase readDataBase = (DataBase) ois.readObject();
            System.out.println(readDataBase.printDataBase());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testClone() {
        Car car = new Car("Jaguar", "X40", new GregorianCalendar(2014, 11, 4), Color.BLUE);

        try {
            Car car1 = (Car) car.clone();
            car1.setColor(Color.BLACK);
            car1.setDistance(325);
            car1.getYear().setTime(new Date(455543L));
            System.out.println("car");
            System.out.println(car);
            System.out.println("car1");
            System.out.println(car1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println();

        CarNumber carNumber = new CarNumber("EE", 3333, "BB");
        try {
            CarNumber carNumber1 = (CarNumber) carNumber.clone();
            System.out.println("carNumber");
            System.out.println(carNumber);
            System.out.println("carNumber1");
            System.out.println(carNumber1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println();

        Driver driver = new Driver("Hulio", "Ignacio", "13.01.1982", 36787612);
        try {
            Driver driver1 = (Driver) driver.clone();
            driver1.setFirstName("David");
            driver1.setLastName("Balfure");
            System.out.println("driver");
            System.out.println(driver);
            System.out.println("driver1");
            System.out.println(driver1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void fillDataBase(DataBase dataBase) {
        Car car1 = new Car("Ford", "Sierra", new GregorianCalendar(1994, 2, 14), Color.WHITE);
        Car car2 = new Car("Opel", "Astra", new GregorianCalendar(2000, 4, 25), Color.RED);
        Car car3 = new Car("Renault", "Megan", new GregorianCalendar(2014, 12, 7), Color.BLACK);

        CarNumber carNumber1 = new CarNumber("AE", 4567, "BB");
        CarNumber carNumber2 = new CarNumber("AH", 3979, "AB");
        CarNumber carNumber3 = new CarNumber("XX", 7777, "XO");
        CarNumber carNumber4 = new CarNumber("BH", 4513, "BP");

        dataBase.add(carNumber1, car1);
        dataBase.add(carNumber2, car2);
        dataBase.add(carNumber3, car3);
        dataBase.add(carNumber4, car3);
    }

    private static void testAdd() {
        DataBase dataBase = new MemoryDataBase();
        Car car1 = new Car("Ford", "Sierra", new GregorianCalendar(1994, 2, 14), Color.WHITE);
        Car car2 = new Car("Opel", "Astra", new GregorianCalendar(2000, 4, 25), Color.RED);
        Car car3 = new Car("Renault", "Megan", new GregorianCalendar(2014, 12, 7), Color.BLACK);

        CarNumber carNumber1 = new CarNumber("AE", 4567, "BB");
        CarNumber carNumber2 = new CarNumber("AH", 3979, "AB");
        CarNumber carNumber3 = new CarNumber("XX", 7777, "XO");
        CarNumber carNumber4 = new CarNumber("BH", 4513, "BP");

        System.out.print(ADDITION_TO_THE_DATA_BASE + " ");
        System.out.println(dataBase.add(carNumber1, car1));
        System.out.print(ADDITION_TO_THE_DATA_BASE + " ");
        System.out.println(dataBase.add(carNumber2, car2));
        System.out.print(ADDITION_TO_THE_DATA_BASE + " ");
        System.out.println(dataBase.add(carNumber3, car3));
        System.out.print(ADDITION_TO_THE_DATA_BASE + " with existed car number ");
        System.out.println(dataBase.add(carNumber3, car3));
        System.out.print(ADDITION_TO_THE_DATA_BASE + " with existed car ");
        System.out.println(dataBase.add(carNumber4, car3));
        System.out.println();

        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println(DONE + " testAdd()");
    }

    private static void testDeleteByKey() {
        DataBase dataBase = new MemoryDataBase();
        fillDataBase(dataBase);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.print("Removal by key: ");
        System.out.println(dataBase.delete(new CarNumber("AE", 4567, "BB")));
        System.out.println();

        System.out.println(CHANGED_DATA_BASE);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println(DONE + " testDeleteByKey()");
    }


    private static void testDeleteByValue() {
        DataBase dataBase = new MemoryDataBase();
        fillDataBase(dataBase);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println("Removal by value: " + dataBase.delete(new Car("Renault", "Megan",
                new GregorianCalendar(2014, 12, 7), Color.BLACK)));
        System.out.println();

        System.out.println(CHANGED_DATA_BASE);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println(DONE + " testDeleteByValue()");
    }

    private static void testDeleteAll() {
        DataBase dataBase = new MemoryDataBase();
        fillDataBase(dataBase);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println("Removal of all elements: " + dataBase.deleteAll());
        System.out.println();

        System.out.println(CHANGED_DATA_BASE);
        System.out.println(dataBase.printDataBase());
        System.out.println();
        System.out.println(DONE + " testDeleteAll()");
    }

    private static void testFindByCarNumber() {
        DataBase dataBase = new MemoryDataBase();
        CarNumber searchNumber = new CarNumber("AE", 4567, "BB");
        fillDataBase(dataBase);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println("Searching by CarNumber " + "\"" + searchNumber + "\"" + ":");
        System.out.println(dataBase.findByCarNumber(searchNumber));
        System.out.println();

        System.out.println(DONE + " testFindByCarNumber()");
    }

    private static void testFindAll() {
        DataBase dataBase = new MemoryDataBase();
        fillDataBase(dataBase);

        System.out.println("database");
        dataBase.printDataBase();
        System.out.println();

        DataBase copy1 = new MemoryDataBase();
        dataBase.findAll().forEach(copy1::add);
        System.out.println("copy1");
        copy1.printDataBase();
        System.out.println();

        System.out.println("Trying to " + CHANGED_DATA_BASE + ": ");
        try {
            copy1.findByCarNumber(new CarNumber("AE", 4567, "BB")).setDistance(562);
        } finally {
            System.out.println(DONE + " testFindAll()");
        }
    }

    private static void testSetNewColor() {
        DataBase dataBase = new MemoryDataBase();
        fillDataBase(dataBase);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println("Set new color");
        System.out.println(dataBase.setNewColor(new CarNumber("AE", 4567, "BB"), Color.BLUE));
        System.out.println();

        System.out.println(CHANGED_DATA_BASE);
        System.out.println(dataBase.printDataBase());
        System.out.println();

        System.out.println(DONE + " testSetNewColor()");
    }
}
