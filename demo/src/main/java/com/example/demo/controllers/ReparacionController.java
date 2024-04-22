package com.example.demo.controllers;


import com.example.demo.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;
}
