package com.rasp.scada.controller;

import com.rasp.scada.bean.UserDetail;
import com.rasp.scada.response.Response;
import com.rasp.scada.response.LoginResponse;
import com.rasp.scada.service.ScadaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/scada")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.2.8:4200"})
public class ScadaController {
    @Autowired
    private ScadaService scadaService;

    /////////////////////////////////////////////////User Authentication//////////////////////////////////////////////
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> authenticateUser(String userId, String password){
        System.out.println("user-id -> " + userId + ", password -> " + password);
        UserDetail userDetail = scadaService.authenticateUser(userId, password);
        if(userDetail!=null){
            LoginResponse response = new LoginResponse(userDetail.getUserId(), userDetail.getName(), userDetail.getRole());
            return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
        }
        else
            return new ResponseEntity<Response>(new Response(HttpStatus.OK, "Invalid user ID or password"), HttpStatus.OK);
    }

    /////////////////////////////////////////////////Current value/////////////////////////////////////////////////////
    @RequestMapping(value = "/current", method = RequestMethod.GET, params = "relay-no")
    public ResponseEntity<?> getCurrent(@RequestParam("relay-no") String relayNo){
        try{
            BigDecimal current = scadaService.getCurrent(relayNo);
            return new ResponseEntity<BigDecimal>(current, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST, "Error occurred while reading the value of current"), HttpStatus.BAD_REQUEST);
        }
    }

    /////////////////////////////////////////////////Relay Operation and status////////////////////////////////////////
    @RequestMapping(value = "/relay-status", method = RequestMethod.GET, params = "relay-no")
    public ResponseEntity<?> getRelayStatus(@RequestParam("relay-no") String relayNo){
        String relayStatus = scadaService.getRelayStatus(relayNo);
        if(relayStatus!=null)
            return new ResponseEntity<String>(relayStatus, HttpStatus.OK);
        else
            return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST, "No such relay number found"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/relay-status", method = RequestMethod.POST)
    public ResponseEntity<Response> changeRelayStatus(String relayNo, String status, String userId){
        System.out.println("relayNo: " + relayNo + ", status: " + status + ", userId: " + userId);
        try{
            scadaService.changeRelayStatus(relayNo, status, userId);
            return new ResponseEntity<Response>(new Response(HttpStatus.OK, "Successfully changed the status of relay " + relayNo + " to " + status), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/relay-status/all", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Response> changeRelayStatusAll(@RequestBody String relayStatus){
        System.out.println(relayStatus);
        JSONObject reqObj = new JSONObject(relayStatus);
        try{
            scadaService.changeRelayStatusAll(reqObj);
            return new ResponseEntity<Response>(new Response(HttpStatus.OK, "Successfully changed the status of all relays"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Response>(new Response(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
