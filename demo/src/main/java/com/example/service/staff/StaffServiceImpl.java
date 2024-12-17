package com.example.service.staff;

import com.example.entity.Address;
import com.example.entity.Role;
import com.example.entity.Staff;
import com.example.mapper.CustomUserDetails;
import com.example.repo.AddressRepository;
import com.example.repo.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements UserDetailsService {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    AddressRepository addressRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Staff> staff = staffRepository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = staff.get().getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(authority);
        if (!staff.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(staff.get(), authorities);
    }
}
