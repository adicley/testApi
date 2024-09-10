
package com.testapi.services.apiservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.testapi.model.UserModel;
import com.testapi.services.dao.ApiDbManagement;
import com.testapi.services.dao.ConnectionManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiService {
    
    public ApiService(){
        
    }
    
    public ResponseEntity createUser(UserModel model) throws Exception{
        ConnectionManagement.createConnection();
        
        ApiDbManagement dbApi = new ApiDbManagement();
        int dbResp = dbApi.create(model);
        
        ConnectionManagement.closeConnection();
        if (dbResp == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar usuario.");
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso!");
    }
    
    public ResponseEntity readUserByCpf(UserModel model) throws Exception{
        ConnectionManagement.createConnection();
        
        ApiDbManagement dbApi = new ApiDbManagement();
        model = dbApi.read(model);
        
        ConnectionManagement.closeConnection();
        
        if (model == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuario nao esta cadastrado no sistema.");
        
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jobj = mapper.createObjectNode();
        
        jobj.put("cpf", model.getCpf());
        jobj.put("nome", model.getNome());
        jobj.put("dataNascimento", model.toDateFormat());
        
        String jsonObj = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jobj);
        
        return ResponseEntity.status(HttpStatus.OK).body(jsonObj);
    }
}
