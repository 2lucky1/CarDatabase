package com.muntian.cardatabase.db.impl.memory;

import com.muntian.cardatabase.db.DataBase;
import com.muntian.cardatabase.db.OperationResult;
import com.muntian.cardatabase.entities.Car;
import com.muntian.cardatabase.entities.CarNumber;

import java.awt.*;
import java.util.*;
import java.util.function.BiFunction;

public class MemoryDataBase implements DataBase {

    public static final String MEMORY_BASE_IS_NOT_MODIFIABLE = "MemoryBase is not modifiable";
    private Map<CarNumber, Car> _data;

    public MemoryDataBase() {
        _data = new HashMap<>();
    }

    private MemoryDataBase(MemoryDataBase memoryDataBase) {
        _data = memoryDataBase._data;
    }

    public Map<CarNumber, Car> get_data() {
        return _data;
    }

    public boolean isModifiable(){
        return true;
    }

    @Override
    public OperationResult setNewColor(CarNumber carNumber, Color newColor) {
        return null;
    }

    @Override
    public Map<CarNumber, Car> findAll() {
        return MemoryDataBase.unmodifiable(_data);
//        return Collections.unmodifiableMap(_data);//TODO Запретить изменения автомобилей внутри базы
    }

    @Override
    public Car findByCarNumber(CarNumber carNumber) {
        Car modifiableCar = _data.get(carNumber);
        return Car.unmodifiable(modifiableCar);
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

    public static Map unmodifiable(Map map){
        return new UnmodifiableMap(map);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static class UnmodifiableMap extends HashMap{
        public UnmodifiableMap(Map map){
            super(map);
        }

        @Override
        public Object put(Object key, Object value) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public void putAll(Map m) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public Object remove(Object key) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public Object putIfAbsent(Object key, Object value) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public boolean replace(Object key, Object oldValue, Object newValue) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public Object replace(Object key, Object value) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }

        @Override
        public void replaceAll(BiFunction function) {
            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
        }
    }

//    private static class UnmodifiableMemoryDataBase extends MemoryDataBase {
//
//        public UnmodifiableMemoryDataBase(MemoryDataBase memoryDataBase) {
//            super(memoryDataBase);
//        }
//
//        @Override
//        public OperationResult setNewColor(CarNumber carNumber, Color newColor) {
//            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
//        }
//
//        @Override
//        public OperationResult add(CarNumber carNumber, Car car) {
//            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
//        }
//
//        @Override
//        public OperationResult delete(CarNumber carNumber) {
//            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
//        }
//
//        @Override
//        public OperationResult delete(Car car) {
//            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
//        }
//
//        @Override
//        public OperationResult deleteAll() {
//            throw new UnsupportedOperationException(MEMORY_BASE_IS_NOT_MODIFIABLE);
//        }
//
//        @Override
//        public boolean isModifiable() {
//            return false;
//        }
//    }
}
