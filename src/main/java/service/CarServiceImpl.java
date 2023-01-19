package service;

import dao.CarDao;
import dao.CarDaoImpl;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService{

    private CarDao carDao;
    @Autowired
    public CarServiceImpl(CarDao carDao){this.carDao = carDao;}
    @Override
    public List<Car> getCarList(int count) {
        return carDao.getCarList(count);
    }
}
