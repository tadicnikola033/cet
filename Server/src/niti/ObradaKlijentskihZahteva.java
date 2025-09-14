/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;
import model.Poruka;
import model.User;
import operacije.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacija.LOGIN:
                    User u = (User) kz.getParam();
                    User ulogovani = Kontroler.getInstance().login(u);
                    so.setOdgovor(ulogovani);

                    break;

                case Operacija.POSALJI_JEDNOM:
                    Object[] poruka = (Object[]) kz.getParam();
                    String por = (String) poruka[0];
                    User posiljalac = (User) poruka[1];
                    String primalac = (String) poruka[2];
                    Kontroler.getInstance().posaljiPorukuJednom(por, posiljalac, primalac);
                    Kontroler.getInstance().getSf().osveziTabelu();
                    so.setOdgovor(true);
                    break;

                case Operacija.POSALJI_SVIMA:
                    Object[] poruka3 = (Object[]) kz.getParam();
                    String por3 = (String) poruka3[0];
                    User posiljalac3 = (User) poruka3[1];
                    Kontroler.getInstance().posaljiPorukuSvima(por3, posiljalac3);
                    Kontroler.getInstance().getSf().osveziTabelu();
                    so.setOdgovor(true);
                    break;

                case Operacija.VRATI_ULOGOVANE:
                    List<User> lista = Kontroler.getInstance().vratiUlogovane();
                    so.setOdgovor(lista);
                    break;

                case Operacija.VRATI_PORUKE:
                    User kome = (User) kz.getParam();
                    List<Poruka> svePoruke = Kontroler.getInstance().vratiPorukeZa(kome);
                    so.setOdgovor(svePoruke);

                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(so);
        }
    }

    public KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
