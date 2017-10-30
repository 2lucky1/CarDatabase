package com.muntian.cardatabase;

import com.muntian.cardatabase.db.DataBase;
import com.muntian.cardatabase.db.impl.memory.MemoryDataBase;
import com.muntian.cardatabase.entities.Car;
import com.muntian.cardatabase.entities.CarNumber;

import java.awt.*;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
//        testIsModifiable();
//        testSetNewColor();
//        testFindAll();
//        testFindByCarNumber();
//        testAdd();
//        testDeleteByKey();
//        testDeleteByValue();
//        testDeleteAll();
//        testUnmodifiable();

        DataBase dataBase = new MemoryDataBase();
        Car car = new Car("", "", new GregorianCalendar(4, 2, 2), Color.BLACK);
        dataBase.add(new CarNumber("AA", 2546, "BB"), car);

        System.out.println(dataBase.delete(car).name());
        System.out.println(dataBase.delete(car).name());
        //fill

        CarNumber carNumber = new CarNumber("AA", 2546, "BB");
//        Car car = dataBase.findByCarNumber(carNumber);
        car.setColor(Color.BLACK);
    }
}
