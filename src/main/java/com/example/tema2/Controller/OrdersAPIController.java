package com.example.tema2.Controller;

import com.example.tema2.Model.OrderFromMenu;
import com.example.tema2.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrdersAPIController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addPublicOrder")
    @ResponseBody()
    public List<String> addPublicOrder(@RequestBody List<String> selectedDishes){
        List<String> orderFromMenus=  orderService.addOrder(selectedDishes).stream().map(OrderFromMenu::toString).collect(Collectors.toList());
        return orderFromMenus;
    }

    @GetMapping("/getPublicOrders")
    @ResponseBody()
    public List<String> raportOrder(@RequestParam(name = "dateStart", required = false, defaultValue = "") String dateStart,
                                    @RequestParam(name = "dateFinal", required = false, defaultValue = "") String dateFinal){
        Date startDate = Date.valueOf(dateStart);
        Date finalDate = Date.valueOf(dateFinal);
        List <OrderFromMenu> orders = orderService.raportOrder(startDate, finalDate);
        return orders.stream().map(OrderFromMenu::toString).collect(Collectors.toList());
    }

    @GetMapping("/getPublicStatistics")
    @ResponseBody()
    public List getPublicstatistics(@RequestParam(name = "dateStart", required = false, defaultValue = "") String dateStart,
                                    @RequestParam(name = "dateFinal", required = false, defaultValue = "") String dateFinal){
        if(dateStart.equals("") || dateFinal.equals("")){
            return null;
        }
        Date startDate = Date.valueOf(dateStart);
        Date finalDate = Date.valueOf(dateFinal);
        return orderService.statisticsOrder(startDate, finalDate);
    }
}
