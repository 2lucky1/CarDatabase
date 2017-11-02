package com.muntian.cardatabase.db.impl.memory;

import com.muntian.cardatabase.db.DataBase;
import com.muntian.cardatabase.db.OperationResult;
import com.muntian.cardatabase.entities.Car;
import com.muntian.cardatabase.entities.CarNumber;

import java.awt.*;
import java.util.*;

public class MemoryDataBase implements DataBase {

    private Map<CarNumber, Car> _data;

    public MemoryDataBase() {
        _data = new HashMap<>();
    }

    public Map<CarNumber, Car> get_data() {
        return _data;
    }

    @Override
    public OperationResult add(CarNumber carNumber, Car car) {
        Car result = _data.putIfAbsent(carNumber, car);
        if (result == null) {
            return OperationResult.SUCCESS;
        }
        return OperationResult.EXIST;
    }

    @Override
    public OperationResult delete(CarNumber carNumber) {
        Car removedCar = _data.remove(carNumber);
        if (removedCar == null) {
            return OperationResult.NOT_EXIST;
        }
        return OperationResult.SUCCESS;
    }

    @Override
    public OperationResult delete(Car car) {
        Set<CarNumber> carNumbers = new HashSet<>();
        for (Map.Entry<CarNumber, Car> entry : _data.entrySet()) {
            if (Objects.equals(entry.getValue(), car)) {
                carNumbers.add(entry.getKey());
            }
        }

        for (CarNumber carNumber : carNumbers) {
            _data.remove(carNumber);
        }


//        int oldSize = _data.size();
//        _data.entrySet().stream()
//                        .filter(entry -> Objects.equals(entry.getValue(), car))
//                        .map(entry -> entry.getKey())
//                        .forEach(key -> _data.remove(key));


        return carNumbers.size() == 0 ?
                OperationResult.NOT_EXIST :
                OperationResult.SUCCESS;
//        return oldSize == _data.size() ?
//                OperationResult.NOT_EXIST :
//                OperationResult.SUCCESS;
    }

    @Override
    public OperationResult deleteAll() {
        if (_data.size() == 0) {
            return OperationResult.NOT_EXIST;
        }
        _data.clear();

        return OperationResult.SUCCESS;
    }

    @Override
    public Car findByCarNumber(CarNumber carNumber) {
        Car modifiableCar = _data.get(carNumber);
        return Car.unmodifiable(modifiableCar);
    }

    @Override
    public Map<CarNumber, Car> findAll() {
        Map<CarNumber, Car> temp = new HashMap<>();
        for (Map.Entry entry : this._data.entrySet()) {
            temp.put((CarNumber) (entry.getKey()), Car.unmodifiable((Car) (entry.getValue())));
        }
        return temp;
    }

    @Override
    public OperationResult setNewColor(CarNumber carNumber, Color newColor) {
        if (this._data.get(carNumber) == null) {
            return OperationResult.NOT_EXIST;
        }
        this._data.get(carNumber).setColor(newColor);
        return OperationResult.SUCCESS;
    }

    @Override
    public OperationResult printDataBase() {
        System.out.println("Printing of the data base");
        if (this._data.isEmpty()) {
            return OperationResult.IS_EMPTY;
        }
        this._data.forEach((CarNumber, Car) -> System.out.println("CarNumber: " + CarNumber + "\t" + Car));
        return OperationResult.SUCCESS;
    }
}
