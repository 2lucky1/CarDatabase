package com.muntian.cardatabase.db;

import com.muntian.cardatabase.entities.Car;
import com.muntian.cardatabase.entities.CarNumber;

import java.awt.*;
import java.util.Map;

public interface DataBase {

    Map<CarNumber, Car> findAll();

    Car findByCarNumber(CarNumber carNumber);

    OperationResult add(CarNumber carNumber, Car car);

    OperationResult delete(CarNumber carNumber);

    OperationResult delete(Car car);

    OperationResult deleteAll();

    OperationResult setNewColor(CarNumber carNumber, Color newColor);

    //My code
    OperationResult printDataBase();
}
