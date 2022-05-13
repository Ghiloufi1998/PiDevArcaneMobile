/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnone.iheb.entities;

/**
 *
 * @author Acer
 */
public class Weather {
    
    private String weather;
    private String speed;
    private String temp;

    
    private String fl;
    private String desc;

    public Weather() {
    }
    
        
    public Weather(String weather, String speed, String temp, String fl, String desc) {
        this.weather = weather;
        this.speed = speed;
        this.temp = temp;
        this.fl = fl;
        this.desc = desc;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Weather{" + "weather=" + weather + ", speed=" + speed + ", temp=" + temp + ", fl=" + fl + ", desc=" + desc + '}';
    }
    
}
