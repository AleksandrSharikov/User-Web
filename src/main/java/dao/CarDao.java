package dao;

import model.Car;

import java.util.List;

public interface CarDao {
    //void addCar(Car car);
    List<Car> getCarList(int count);
}
