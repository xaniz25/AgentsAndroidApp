/*Created by Shanice Talan on Sept 19, 2019
CMPP 264 Java: Day 12 Assignment - Android App
Connects to Travel Experts DB to do Select, Insert, Update & Delete
on Agents table */

package com.example.agentsapp;

import java.io.Serializable;

public class Agent implements Serializable {

    private Integer AgentId;
    private String FirstName;
    private String MidInitial;
    private String LastName;
    private String Phone;
    private String Email;
    private String Position;
    private Integer AgcyId;

    //constructor
    public Agent(Integer agentId, String firstName, String midInitial, String lastName,
                 String phone, String email, String position, Integer agcyId) {
        AgentId = agentId;
        FirstName = firstName;
        MidInitial = midInitial;
        LastName = lastName;
        Phone = phone;
        Email = email;
        Position = position;
        AgcyId = agcyId;
    }

    //constructor that accepts an array
    public Agent(String[] data){
        AgentId = Integer.parseInt(data[0]);
        FirstName = data[1];
        MidInitial = data[2];
        LastName = data[3];
        Phone = data[4];
        Email = data[5];
        Position = data[6];
        AgcyId = Integer.parseInt(data[7]);
    }

    //getters and setters

    public Integer getAgentId() {
        return AgentId;
    }

    public void setAgentId(Integer agentId) {
        AgentId = agentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMidInitial() {
        return MidInitial;
    }

    public void setMidInitial(String midInitial) {
        MidInitial = midInitial;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public Integer getAgcyId() {
        return AgcyId;
    }

    public void setAgcyId(Integer agcyId) {
        AgcyId = agcyId;
    }


    //showing only id and name to show agents in ListView
    @Override
    public String toString(){
        return AgentId + " " + LastName + ", " + FirstName;
    }
}
