/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Poruka implements Serializable {

    private String poruka;
    private LocalDateTime vreme;
    private User posiljalac;
    private User primalac;

    public Poruka() {
    }

    public Poruka(String poruka, LocalDateTime vreme, User posiljalac, User primalac) {
        this.poruka = poruka;
        this.vreme = vreme;
        this.posiljalac = posiljalac;
        this.primalac = primalac;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public LocalDateTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalDateTime vreme) {
        this.vreme = vreme;
    }

    public User getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(User posiljalac) {
        this.posiljalac = posiljalac;
    }

    public User getPrimalac() {
        return primalac;
    }

    public void setPrimalac(User primalac) {
        this.primalac = primalac;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poruka other = (Poruka) obj;
        if (!Objects.equals(this.poruka, other.poruka)) {
            return false;
        }
        if (!Objects.equals(this.vreme, other.vreme)) {
            return false;
        }
        if (!Objects.equals(this.posiljalac, other.posiljalac)) {
            return false;
        }
        return Objects.equals(this.primalac, other.primalac);
    }

    @Override
    public String toString() {
        return "Poruka{" + "poruka=" + poruka + ", vreme=" + vreme + ", posiljalac=" + posiljalac + ", primalac=" + primalac + '}';
    }

}
