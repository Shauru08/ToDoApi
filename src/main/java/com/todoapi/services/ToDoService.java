package com.todoapi.services;

import com.todoapi.controllers.ApiController;
import com.todoapi.entities.requests.AddMethodRequest;
import com.todoapi.entities.requests.UpdateMethodRequest;
import com.todoapi.entities.responses.AddMethodResponse;
import com.todoapi.entities.responses.DeleteMethodResponse;
import com.todoapi.entities.responses.GetMethodResponse;
import com.todoapi.entities.responses.UpdateMethodResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    private static final Logger logger = LogManager.getLogger(ApiController.class);

    public AddMethodResponse addToDo(AddMethodRequest request) {
        AddMethodResponse response = new AddMethodResponse();
        try {
            logger.info("addToDo [Init]");

            response.setResponseData(1, "00", "Exito al procesar");

        } catch (Exception ex) {
            //errorCode 13 significa excepcion.
            logger.error("addToDo Exception [" + ex.getMessage() + "]");
            response.setResponseData(0, "13", "No fue posible dar de alta el registro");
        } finally {
            
            logger.info("addToDo Response [" + response + "]");
            logger.info("addToDo [Fin]");
            return response;
        }
    }

    public DeleteMethodResponse deleteToDo() {
        return null;
    }

    public GetMethodResponse getToDo() {
        return null;
    }

    public UpdateMethodResponse updateToDo(UpdateMethodRequest request) {
        return null;
    }

}
