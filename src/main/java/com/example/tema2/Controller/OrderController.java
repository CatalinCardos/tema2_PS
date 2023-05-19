package com.example.tema2.Controller;

import com.example.tema2.Model.OrderFromMenu;
import com.example.tema2.Model.Status;
import com.example.tema2.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/user")
    public String userPage(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "user";
    }
    @GetMapping("/modifyStatus")
    public String changeSatusPage(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "modifyStatus";
    }
    @PostMapping("/addOrder")
    public void addOrder(@RequestBody List<String> selectedDishes){
        List<OrderFromMenu> orders = orderService.addOrder(selectedDishes);

    }

    @PutMapping("/modifyStatus")
    public void changeStatus(@RequestBody Order order){
        orderService.changeStatus(order.id(), order.status());
    }
    record Order(int id, String status){}

    @GetMapping("/raportOrder")
    public String raportOrder(@RequestParam(name = "dateStart", required = false, defaultValue = "") String dateStart,
                              @RequestParam(name = "dateFinal", required = false, defaultValue = "") String dateFinal,
                              Model model){
        if(dateStart.equals("") || dateFinal.equals("")){
            model.addAttribute("orders", orderService.getAllOrders());
            return "raportOrder";
        }
        Date startDate = Date.valueOf(dateStart);
        Date finalDate = Date.valueOf(dateFinal);
        model.addAttribute("orders", orderService.raportOrder(startDate, finalDate));
        return "raportOrder";
    }


    record OrderToExport(String dateStart, String dateFinal, String type){}

    @PostMapping("/exportFile")
    @ResponseBody()
    public void exportFile(@RequestBody OrderToExport orderToExport) throws IOException {
        orderService.exportOrders(orderToExport.dateStart, orderToExport.dateFinal, orderToExport.type);
    }


    @GetMapping("/statisticView")
    public String statistics(Model model){
        model.addAttribute("statistics", orderService.statisticsOrder());

        return "statisticView";
    }




}
