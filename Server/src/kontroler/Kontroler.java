/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import main.ServerskaForma;
import model.Poruka;
import model.User;

/**
 *
 * @author Korisnik
 */
public class Kontroler {

    private static Kontroler instance;
    private List<User> useri;
    private List<User> ulogovani;
    private List<Poruka> poruke;
    private ServerskaForma sf;

    public Kontroler() {
        useri = new ArrayList<>();
        useri.add(new User("daki@gmail.com", "daki", "koka", "kola"));
        useri.add(new User("aki@gmail.com", "aki", "boban", "ola"));
        useri.add(new User("ogi@gmail.com", "ogi", "ogi", "la"));
        useri.add(new User("ki@gmail.com", "ki", "mistican", "covek"));
        ulogovani = new ArrayList<>();
        poruke = new ArrayList<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public static void setInstance(Kontroler instance) {
        Kontroler.instance = instance;
    }

    public List<User> getUseri() {
        return useri;
    }

    public void setUseri(List<User> useri) {
        this.useri = useri;
    }

    public List<User> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(List<User> ulogovani) {
        this.ulogovani = ulogovani;
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public User login(User u) {
        for (User k : useri) {
            if (k.getMejl().equals(u.getMejl()) && k.getPass().equals(u.getPass())) {
                if (!ulogovani.contains(k)) {
                    ulogovani.add(k);
                    return k;
                }
            }
        }
        return null;
    }

    public List<User> vratiUlogovane() {
        return ulogovani;
    }

    public void posaljiPorukuSvima(String mess, User u) {
        LocalDateTime vreme = LocalDateTime.now();
        for (User user : ulogovani) {
            if (!user.equals(u)) {
                Poruka p = new Poruka(mess, vreme, u, user);
                poruke.add(p);
                System.out.println(p);
            }
        }
    }

    public void posaljiPorukuJednom(String por, User posiljalac, String primalac) {
        LocalDateTime vreme = LocalDateTime.now();
        User prima = new User();
        for (User user : ulogovani) {
            if (user.getIme().equals(primalac)) {
                prima = user;
            }
        }
        Poruka p = new Poruka(por, vreme, posiljalac, prima);
        poruke.add(p);
        System.out.println(p);
    }

    public List<Poruka> vratiPorukeZa(User u) {
        List<Poruka> poslate = new ArrayList<>();
        for (Poruka poruka : poruke) {
            if (poruka.getPrimalac().equals(u)) {
                poslate.add(poruka);
            }
        }
        poslate.sort((a, b) -> b.getVreme().compareTo(a.getVreme()));
        return poslate;
    }

}
