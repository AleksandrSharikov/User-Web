package web.controller;

import service.CarService;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarsController {
    private CarService carService;
    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars")
    public String printCars(@RequestParam(required = false) Optional<Integer> count, ModelMap model){

        System.out.println("=========================");
        int countNotNull = count.orElse(5);
        List<String> messages = new ArrayList<>();
        List<Car> carList =carService.getCarList(countNotNull);
        for(Car car : carList)
        messages.add(car.toString());
        model.addAttribute("messages", messages);
        System.out.println(countNotNull);
        return "cars";
       // @RequestParam(value = "count", required = false) int count
    }

}
