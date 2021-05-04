package com.verizon.admin.services;

import com.verizon.admin.models.Admin;
import com.verizon.admin.models.LoginDTO;
import com.verizon.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> findByEmail(String username){
        return adminRepository.findByUsername(username);
    }

    public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    public Admin register(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin login(LoginDTO adminCred){
        Optional<Admin> admin = findByEmail(adminCred.username);

        if(admin.isPresent()){
            if(admin.get().getPassword().equals(adminCred.getPassword())){
                return admin.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
