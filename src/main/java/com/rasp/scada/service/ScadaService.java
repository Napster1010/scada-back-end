package com.rasp.scada.service;

import com.pi4j.io.gpio.*;
import com.pi4j.io.serial.*;
import com.rasp.scada.bean.CurrentRelayStatus;
import com.rasp.scada.bean.RelayInstructionHistory;
import com.rasp.scada.bean.UserDetail;
import com.rasp.scada.repository.CurrentRelayStatusRepository;
import com.rasp.scada.repository.RelayInstructionHistoryRepository;
import com.rasp.scada.repository.UserDetailRepository;
import com.rasp.scada.util.PortUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.StringTokenizer;

@Service
public class ScadaService {
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private CurrentRelayStatusRepository currentRelayStatusRepository;

    @Autowired
    private RelayInstructionHistoryRepository relayInstructionHistoryRepository;

    public UserDetail authenticateUser(String userId, String password){
        UserDetail userDetail = userDetailRepository.findByUserId((userId));
        if(userDetail!=null){
            if(password.equals(userDetail.getPassword()))
                return userDetail;
            else
                return null;
        }
        else
            return null;
    }

    public String getRelayStatus(String relayNo){
        CurrentRelayStatus currentRelayStatus = currentRelayStatusRepository.findByRelayNo(relayNo);
        if(currentRelayStatus!=null)
            return currentRelayStatus.getStatus();
        else
            return null;
    }

    public void changeRelayStatus(String relayNo, String status, String userId) throws Exception{
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        if(userDetail!=null){
            changeStatus(relayNo, status); //Change GPIO state

            RelayInstructionHistory relayInstructionHistory = new RelayInstructionHistory(relayNo, status, userDetail, new Date());
            CurrentRelayStatus currentRelayStatus = currentRelayStatusRepository.findByRelayNo(relayNo);
            if(currentRelayStatus!=null){
                currentRelayStatus.setStatus(status);
                currentRelayStatusRepository.save(currentRelayStatus);
            }
            else{
                CurrentRelayStatus newCurrentRelayStatus = new CurrentRelayStatus(relayNo, status);
                currentRelayStatusRepository.save(newCurrentRelayStatus);
            }
            relayInstructionHistoryRepository.save(relayInstructionHistory);
        }
        else
            throw new Exception("Invalid user!!");
    }

    public void changeRelayStatusAll(JSONObject reqObj) throws Exception{
        String userId = reqObj.getString("userId");
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        if(userDetail!=null){
            JSONObject allRelayStatus = reqObj.getJSONObject("relayStatus");

            //For R-Phase
            changeStatus("R", allRelayStatus.getString("R"));
            RelayInstructionHistory relayInstructionHistoryR = new RelayInstructionHistory("R", allRelayStatus.getString("R"), userDetail, new Date());
            CurrentRelayStatus currentRelayStatusR = currentRelayStatusRepository.findByRelayNo("R");
            if(currentRelayStatusR!=null){
                currentRelayStatusR.setStatus(allRelayStatus.getString("R"));
                currentRelayStatusRepository.save(currentRelayStatusR);
            }
            else{
                CurrentRelayStatus newCurrentRelayStatusR = new CurrentRelayStatus("R", allRelayStatus.getString("R"));
                currentRelayStatusRepository.save(newCurrentRelayStatusR);
            }
            relayInstructionHistoryRepository.save(relayInstructionHistoryR);


            //For Y-Phase
            changeStatus("Y", allRelayStatus.getString("Y"));
            RelayInstructionHistory relayInstructionHistoryY = new RelayInstructionHistory("Y", allRelayStatus.getString("Y"), userDetail, new Date());
            CurrentRelayStatus currentRelayStatusY = currentRelayStatusRepository.findByRelayNo("Y");
            if(currentRelayStatusY!=null){
                currentRelayStatusY.setStatus(allRelayStatus.getString("Y"));
                currentRelayStatusRepository.save(currentRelayStatusY);
            }
            else{
                CurrentRelayStatus newCurrentRelayStatusY = new CurrentRelayStatus("Y", allRelayStatus.getString("Y"));
                currentRelayStatusRepository.save(newCurrentRelayStatusY);
            }
            relayInstructionHistoryRepository.save(relayInstructionHistoryY);

            //For B-Phase
            changeStatus("B", allRelayStatus.getString("B"));
            RelayInstructionHistory relayInstructionHistoryB = new RelayInstructionHistory("B", allRelayStatus.getString("B"), userDetail, new Date());
            CurrentRelayStatus currentRelayStatusB = currentRelayStatusRepository.findByRelayNo("B");
            if(currentRelayStatusB!=null){
                currentRelayStatusB.setStatus(allRelayStatus.getString("B"));
                currentRelayStatusRepository.save(currentRelayStatusB);
            }
            else{
                CurrentRelayStatus newCurrentRelayStatusB = new CurrentRelayStatus("B", allRelayStatus.getString("B"));
                currentRelayStatusRepository.save(newCurrentRelayStatusB);
            }
            relayInstructionHistoryRepository.save(relayInstructionHistoryB);
        }
        else
            throw new Exception("Invalid user!!");
    }

    //Method which changes relay status
    private void changeStatus(String relayNo, String status) throws Exception{
        final GpioController gpio = GpioFactory.getInstance();
        if(relayNo.equals("R")){
            if(status.equals("ON")){
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PortUtil.R_PHASE_PIN_ON, "R Phase Pin ON");
                pin.pulse(PortUtil.PULSE_TIME, true);

                Thread.sleep(250);
                gpio.unprovisionPin(pin);
            }
            else if(status.equals("OFF")){
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PortUtil.R_PHASE_PIN_OFF, "R Phase Pin OFF");
                pin.pulse(PortUtil.PULSE_TIME, true);

                Thread.sleep(250);
                gpio.unprovisionPin(pin);
            }
            else{
                throw new Exception("Invalid status");
            }
        }
        else if(relayNo.equals("Y")){
            if(status.equals("ON")){
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PortUtil.Y_PHASE_PIN_ON, "Y Phase Pin ON");
                pin.pulse(PortUtil.PULSE_TIME, true);

                Thread.sleep(250);
                gpio.unprovisionPin(pin);
            }
            else if(status.equals("OFF")){
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PortUtil.Y_PHASE_PIN_OFF, "Y Phase Pin OFF");
                pin.pulse(PortUtil.PULSE_TIME, true);

                Thread.sleep(250);
                gpio.unprovisionPin(pin);
            }
            else{
                throw new Exception("Invalid status");
            }
        }
        else if(relayNo.equals("B")){
            if(status.equals("ON")){
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PortUtil.B_PHASE_PIN_ON, "B Phase Pin ON");
                pin.pulse(PortUtil.PULSE_TIME, true);

                Thread.sleep(250);
                gpio.unprovisionPin(pin);
            }
            else if(status.equals("OFF")){
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(PortUtil.B_PHASE_PIN_OFF, "B Phase Pin OFF");
                pin.pulse(PortUtil.PULSE_TIME, true);

                Thread.sleep(250);
                gpio.unprovisionPin(pin);
            }
            else{
                throw new Exception("Invalid status");
            }
        }
        else{
            throw new Exception("Invalid relay number!");
        }
    }

    //Method which gets the current value
    public String[] getCurrent(double ctRatio) throws Exception{
        String[] currentValues = new String[3];
        try{
            final Serial serial = SerialFactory.createInstance();
                SerialConfig config = new SerialConfig();

                config.device(SerialPort.getDefaultPort())
                        .baud(Baud._9600)
                        .dataBits(DataBits._8)
                        .parity(Parity.NONE)
                        .stopBits(StopBits._1)
                        .flowControl(FlowControl.NONE);

                serial.open(config);

            InputStream inpStrm = serial.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inpStrm));
            String line;
            boolean read = true;
            while(read){
                line = reader.readLine();
                System.out.println(line);
                if(line.length()>=2 && line.charAt(0)=='#' && line.charAt(line.length()-1)=='$'){
                    String currentLine = line.substring(1, line.length()-2);
                    System.out.println(currentLine+"\n");
                    StringTokenizer tokenizer = new StringTokenizer(currentLine, ",");
                    String rPhase = tokenizer.nextToken().trim();
                    String yPhase = tokenizer.nextToken().trim();
                    String bPhase = tokenizer.nextToken().trim();
                    double rPhaseCurrent = Double.parseDouble(rPhase)*ctRatio;
                    double yPhaseCurrent = Double.parseDouble(yPhase)*ctRatio;
                    double bPhaseCurrent = Double.parseDouble(bPhase)*ctRatio;
                    currentValues[0] = Double.toString(rPhaseCurrent);
                    currentValues[1] = Double.toString(yPhaseCurrent);
                    currentValues[2] = Double.toString(bPhaseCurrent);
                    read = false;
                }
            }

            serial.close();
            inpStrm.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Couldn't read current!");
        }

        return currentValues;
    }
}
