package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FoodOrder;
import com.example.service.FoodOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    public FoodOrderController(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    @PostMapping("/addOrder")
    public FoodOrder addOrders(@Valid @RequestBody FoodOrder requests) {
        return foodOrderService.addOrder(requests);
    }
    
    @GetMapping("/getAllOrders")
    public List<FoodOrder> getAllOrders() {
        return foodOrderService.getAllOrders();
    }
    
    @GetMapping("/getOrderById/{id}")
    public FoodOrder readByOrderId(@PathVariable Integer id) {
        return foodOrderService.readByOrderId(id);
    }
    
    @PutMapping("/updateOrder")
    public FoodOrder updateOrder(@Valid @RequestBody FoodOrder request) {
        return foodOrderService.updateOrder(request);
    }
    
    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        foodOrderService.deleteOrder(id);
        return "Food order deleted successfully";
    }
    
    @PatchMapping("/patchOrder/{id}")
    public FoodOrder patchOrder(@PathVariable Integer id,@Valid @RequestBody FoodOrder request) {
        return foodOrderService.patchOrder(id, request);
    }
    
    @GetMapping("/getFoodNameAndQuantity")
    public Map<String, Integer> getFoodNameAndQuantity() {
        return foodOrderService.getFoodNameAndQuantity();
    }
    
    @GetMapping("/getOrdersByFoodName/{foodName}")
    public List<FoodOrder> getOrdersByFoodName(@PathVariable String foodName) {

        return foodOrderService.getOrdersByFoodName(foodName);
    }

}
