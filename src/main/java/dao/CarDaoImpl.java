package dao;

import model.Car;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarDaoImpl implements CarDao{
    private static List<Car> carList;
    public CarDaoImpl(){
        carList = new ArrayList<>();
        carList.add(new Car("Model1","Manufacture1",1));
        carList.add(new Car("Model2","Manufacture1",2));
        carList.add(new Car("Model3","Manufacture1",3));
        carList.add(new Car("Model4","Manufacture1",4));
        carList.add(new Car("Model5","Manufacture1",5));
    }
    @Override
    public List<Car> getCarList(int count) {
        List<Car> answer = new ArrayList<>();
        if (count >= 5|| count <0) {
            answer = carList;
        } else {
            answer = carList.subList(0, count);
        }
        return answer;
    }
}
