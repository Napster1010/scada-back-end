package com.rasp.scada;

import com.pi4j.io.serial.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SerialReader implements Runnable {
    public void run() {

        final Serial serial = SerialFactory.createInstance();
/*
        serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {
                try{
                    String data = event.getAsciiString();
                    //System.out.println(data);
                    //System.out.println(data.length());
                    if(data.charAt(data.length()-2)=='$'){
                        serData = serData.concat(data);
                        System.out.println("DATA -> " + serData);
                        serData="";
                    }else{
                        serData = data;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
*/
        try {
            SerialConfig config = new SerialConfig();

            System.out.println("/dev/ttyUSB0");
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
            while((line = reader.readLine())!=null){
                System.out.println(line);
            }

/*
            while(true){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
*/
        }catch (Exception e){
            System.out.println("Serial setup failed !!");
            e.printStackTrace();
        }
    }

}
