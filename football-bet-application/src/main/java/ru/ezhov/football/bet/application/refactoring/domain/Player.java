/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.football.bet.application.refactoring.domain;

import javax.swing.*;

/**
 *
 * @author ezhov_da
 */
public class Player extends JLabel {
        private String id;
        private String fio;
        private String username;
        private String doubleFio;
        private String doubleUsername;
        private String chempion;

    public String getFio() {
        return fio;
    }

    public String getUsername() {
        return username;
    }

    public String getDoubleFio() {
        return doubleFio;
    }

    public String getDoubleUsername() {
        return doubleUsername;
    }

    public String getChempion() {
        return chempion;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDoubleFio(String doubleFio) {
        this.doubleFio = doubleFio;
    }

    public void setDoubleUsername(String doubleUsername) {
        this.doubleUsername = doubleUsername;
    }

    public void setChempion(String chempion) {
        this.chempion = chempion;
    }

    @Override
    public String toString() {
        return doubleFio;
    }

        
        

    

        
        
        
        
        
        
        
        
        
        
}
