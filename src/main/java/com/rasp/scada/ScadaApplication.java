package com.rasp.scada;

import com.pi4j.io.serial.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScadaApplication.class, args);
/*
		SerialReader reader = new SerialReader();
		Thread t1 = new Thread(reader);
		t1.start();
		System.out.println("SerialReader thread started !!");
*/
	}

}

