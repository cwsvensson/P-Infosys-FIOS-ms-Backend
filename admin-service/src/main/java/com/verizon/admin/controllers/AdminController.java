package com.verizon.admin.controllers;

import com.verizon.admin.models.Admin;
import com.verizon.admin.models.LoginDTO;
import com.verizon.admin.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable("username") String username) {
        Optional<Admin> optional = adminService.findByEmail(username);
        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmin() {
        return ResponseEntity.status(200).body(adminService.findAll());
    }

    @PostMapping
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin subscription) {
        return ResponseEntity.status(200).body(adminService.register(subscription));
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody LoginDTO subscription) {
        return ResponseEntity.status(200).body(adminService.login(subscription));
    }

}
