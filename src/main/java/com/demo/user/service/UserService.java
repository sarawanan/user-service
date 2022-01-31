package com.demo.user.service;

import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;
import com.demo.user.vo.Department;
import com.demo.user.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebClient.Builder webClient;

    public UserResponse saveUser(User user) {
        log.info("Inside UserService::saveUser");
        User saveUser = userRepository.save(user);
        Department department = getDepartment(saveUser.getDepartmentId());
        return new UserResponse(saveUser, department);
    }

    public UserResponse getUserResponse(Long id) {
        log.info("Inside UserService::getUserResponse");
        User user = userRepository.getUserByUserId(id);
        Department department = getDepartment(user.getDepartmentId());
        return new UserResponse(user, department);
    }

    private Department getDepartment(Long departmentId) {
        log.info("Inside UserService::getDepartment");
        return webClient.baseUrl("http://DEPARTMENT-SERVICE/departments/")
                .build().get()
                .uri(departmentId.toString())
                .retrieve().bodyToMono(Department.class)
                .block();
    }
}
