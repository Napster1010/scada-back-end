package com.rasp.scada.util;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public interface PortUtil {
    final int PULSE_TIME = 250;
    final Pin R_PHASE_PIN_ON = RaspiPin.GPIO_00;
    final Pin R_PHASE_PIN_OFF = RaspiPin.GPIO_01;
    final Pin Y_PHASE_PIN_ON = RaspiPin.GPIO_02;
    final Pin Y_PHASE_PIN_OFF = RaspiPin.GPIO_03;
    final Pin B_PHASE_PIN_ON = RaspiPin.GPIO_04;
    final Pin B_PHASE_PIN_OFF = RaspiPin.GPIO_05;
}
