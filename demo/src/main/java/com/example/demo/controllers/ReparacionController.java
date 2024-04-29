package com.example.demo.controllers;


import com.example.demo.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autofix/reparaciones")
@CrossOrigin("*")
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;
}
