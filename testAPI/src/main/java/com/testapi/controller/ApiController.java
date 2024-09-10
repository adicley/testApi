
package com.testapi.controller;

import com.testapi.dtos.UserRecordDto;
import com.testapi.model.UserModel;
import com.testapi.services.apiservice.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final ApiService service;
    
    public ApiController(){
        this.service = new ApiService();
    }
    
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPixKey(@RequestBody @Valid UserRecordDto userDto) throws Exception{
        UserModel userModel = new UserModel();
        
        userModel.setCpf(userDto.cpf());
        userModel.setNome(userDto.nome());
        userModel.setDataNascimento(userDto.dataNascimento());
        
        return service.createUser(userModel);
    }
    
    
    @GetMapping(value = "/read", consumes = "application/json", produces = "application/json")
    public ResponseEntity readPixKey(@RequestBody @Valid UserRecordDto userDto) throws Exception{
        UserModel userModel = new UserModel();
        
        BeanUtils.copyProperties(userDto, userModel);
        
        return service.readUserByCpf(userModel);
    }
}
