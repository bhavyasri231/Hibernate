package com.springdemo.controller;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject the customer DAO
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel)
    {
        //get the customers
        List<Customer> theCustomer = customerService.getCustomers();

        //add the customers
        theModel.addAttribute("customers",theCustomer);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel)
    {
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer",theCustomer);
        return  "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
    {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel)
    {
        Customer theCustomer = customerService.getCustomer(theId);

        theModel.addAttribute("customer",theCustomer);

        return "customer-form";

    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId)
    {
       customerService.deleteCustomer(theId);
       return "redirect:/customer/list";
    }
}
