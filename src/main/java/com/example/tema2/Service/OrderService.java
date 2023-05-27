package com.example.tema2.Service;

import com.example.tema2.Repo.MenuREPO;
import com.example.tema2.Repo.OrderREPO;
import com.example.tema2.Model.Dish;
import com.example.tema2.Model.OrderFromMenu;
import com.example.tema2.Model.Status;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderREPO orderREPO;
    @Autowired
    private MenuREPO menuREPO;

    public List<OrderFromMenu> addOrder(List<String> selectedDishes){
        List<Dish> dishes = new ArrayList<>();
        for (String dishName: selectedDishes) {
            Dish dish = menuREPO.findAll().stream().filter(dish1 -> dish1.getName().equals(dishName)).findFirst().orElse(null);
            if(dish == null)
                return orderREPO.findAll();
            dishes.add(dish);
        }
        float total = calculateTotalCost(dishes);
        for(Dish dish: dishes){
            menuREPO.save(dish);
        }
        OrderFromMenu order = new OrderFromMenu(dishes, Status.New,total, Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()));

        orderREPO.save(order);
        return orderREPO.findAll();
    }
    public float calculateTotalCost(List<Dish> dishes){
        float sum = 0;
        for (Dish dish: dishes) {
            sum += dish.getPrice();
        }
        return sum;

    }
    public void changeStatus(int id, String status){
        OrderFromMenu order = orderREPO.findById(id).orElse(null);
        if(order != null){
        if(status.equals("New"))
            order.setStatus(Status.New);
        else if(status.equals("InProgress"))
            order.setStatus(Status.InProgress);
        else if(status.equals("Done"))
            order.setStatus(Status.Done);
        orderREPO.save(order);
        } else{
            System.out.println("Order not found");
        }
    }
    public List<OrderFromMenu> raportOrder(Date date1, Date date2){

        List <OrderFromMenu> orders = new ArrayList<>();
        for (OrderFromMenu order: orderREPO.findAll()) {
            if(order.getDate().after(date1) && order.getDate().before(date2)){
                orders.add(order);
            }
        }
        return orders;

    }
    public HashMap statisticsOrder(){
        HashMap<String, Integer> statistics = new HashMap<>();
        for (OrderFromMenu order: orderREPO.findAll()) {
            for (Dish dish: order.getDishList()) {
                if(statistics.containsKey(dish.getName())){
                    statistics.put(dish.getName(), statistics.get(dish.getName()) + 1);
                } else{
                    statistics.put(dish.getName(), 1);
                }
            }
        }

        return statistics;
    }

    public List statisticsOrder(Date startDate, Date finalDate){
        HashMap<String, Integer> statistics = new HashMap<>();
        List<Pair<String,Integer>> list = new ArrayList<>();
        for (OrderFromMenu order: orderREPO.findAll()) {
            if(order.getDate().after(startDate) && order.getDate().before(finalDate)) {
                for (Dish dish : order.getDishList()) {
                    if (statistics.containsKey(dish.getName())) {
                        statistics.put(dish.getName(), statistics.get(dish.getName()) + 1);
                    } else {
                        statistics.put(dish.getName(), 1);
                    }
                }
            }
        }
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            list.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        list.sort(Comparator.comparing(Pair::getValue));
        Collections.reverse(list);
        if(list.size() < 10)
            return list;
        else
            return list.subList(0,9);
    }

    public void exportOrders(String dateStart, String dateFinal, String type) throws IOException {
        List<OrderFromMenu> orders = raportOrder(Date.valueOf(dateStart), Date.valueOf(dateFinal));
        Objects.requireNonNull(ExporterFactory.getExporter(type)).export(orders);
    }

    public OrderREPO getOrderDAO() {
        return orderREPO;
    }

    public void setOrderDAO(OrderREPO orderREPO) {
        this.orderREPO = orderREPO;
    }

    public MenuREPO getMenuDAO() {
        return menuREPO;
    }

    public void setMenuDAO(MenuREPO menuREPO) {
        this.menuREPO = menuREPO;
    }

    public List<OrderFromMenu> getAllOrders() {
        return orderREPO.findAll();
    }
}
