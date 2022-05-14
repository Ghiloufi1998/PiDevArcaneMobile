/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author khaledguedria
 */
public class Task_1 {
    
    //var
    private double id;
    private String name;
    private double status;
    
    //constructor
    public Task_1(String name, double status) {    
        this.name = name;
        this.status = status;
    }
    //..

    public Task_1() {
    }
    
    //..
    public Task_1(double id, String name, double status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
    //Getters and setters

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }
    
    //Show

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", name=" + name + ", status=" + status + '}';
    }
    
    
}
