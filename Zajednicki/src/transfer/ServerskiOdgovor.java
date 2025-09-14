/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class ServerskiOdgovor implements Serializable {

    private String poruka;
    private Object odgovor;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(String poruka, Object odgovor) {
        this.poruka = poruka;
        this.odgovor = odgovor;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

}
