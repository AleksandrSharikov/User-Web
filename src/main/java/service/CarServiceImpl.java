package service;

import dao.CarDao;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarDao carDao;
    @Override
    public List<Car> getCarList(int count) {
        return carDao.getCarList(count);
    }
}
