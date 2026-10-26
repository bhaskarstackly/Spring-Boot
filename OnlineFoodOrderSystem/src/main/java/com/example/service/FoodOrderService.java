package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.exception.FoodOrderNotFoundException;
import com.example.model.FoodOrder;


@Service
public class FoodOrderService {
	
	private int nextOrderId = 101;

	    private List<FoodOrder> orders = new ArrayList<>();
	    
	    
	    public List<FoodOrder> addOrders(List<FoodOrder> requests) {

	        List<FoodOrder> addedOrders = new ArrayList<>();

	        for (FoodOrder request : requests) {

	            FoodOrder order = new FoodOrder(
	                nextOrderId++,
	                request.getCustomerName().trim(),
	                request.getFoodItem().trim(),
	                request.getQuantity(),
	                request.getPrice()
	            );

	            orders.add(order);
	            addedOrders.add(order);
	        }

	        return addedOrders;
	    }

	    
	    public List<FoodOrder> getAllOrders() {
	        return orders;
	    }

	    
	    public FoodOrder updateOrder(FoodOrder request) {

	        for (FoodOrder f : orders) {

	            if (f.getOrderId() == request.getOrderId()) {

	                f.setCustomerName(request.getCustomerName());
	                f.setFoodItem(request.getFoodItem());
	                f.setQuantity(request.getQuantity());
	                f.setPrice(request.getPrice());

	                return f;
	            }
	        }

	        throw new FoodOrderNotFoundException("Food order not found with ID: " + request.getOrderId());
	    }
	    
	    
	    public List<FoodOrder> deleteOrder(Integer id) {

	        boolean removed = orders.removeIf(order -> order.getOrderId() == id);

	        if (!removed) { 
	            throw new FoodOrderNotFoundException("Food order not found with ID: " + id);
	        }

	        return orders;
	    }
	    
	    
	    public FoodOrder readByOrderId(Integer id) {

	        for (FoodOrder f : orders) {

	            if (f.getOrderId() == id) {
	                return f;
	            }
	        }
	        throw new FoodOrderNotFoundException("Food order not found with ID: " + id);
	    }
	    
	    
	    public FoodOrder patchOrder(Integer id, FoodOrder request) {

	        for (FoodOrder f : orders) {

	            if (f.getOrderId() == id) {

	                if (request.getCustomerName() != null) {
	                    f.setCustomerName(request.getCustomerName().trim());
	                }

	                if (request.getFoodItem() != null) {
	                    f.setFoodItem(request.getFoodItem().trim());
	                }

	                if (request.getQuantity() != null) {
	                    f.setQuantity(request.getQuantity());
	                }

	                if (request.getPrice() != null) {
	                    f.setPrice(request.getPrice());
	                }

	                return f;
	            }
	        }

	        throw new FoodOrderNotFoundException("Food order not found with ID: " + id);
	    }
	    
	    
	    public Map<String, Integer> getFoodNameAndQuantity() {

	        Map<String, Integer> result = new HashMap<>();

	        for (FoodOrder f : orders) {

	            String foodName = f.getFoodItem();
	            int quantity = f.getQuantity();

	            if (result.containsKey(foodName)) {
	                result.put(foodName, result.get(foodName) + quantity);
	            } else {
	                result.put(foodName, quantity);
	            }
	        }

	        return result;
	    }
	    
	    
	    public List<FoodOrder> getOrdersByFoodName(String foodName) {

	        List<FoodOrder> result = new ArrayList<>();

	        for (FoodOrder f : orders) {

	            if (f.getFoodItem().equalsIgnoreCase(foodName)) {
	                result.add(f);
	            }
	        }

	        if (result.isEmpty()) {
	        	throw new FoodOrderNotFoundException("No orders found for food: " + foodName);
	        }

	        return result;
	    }
	    
}
	
