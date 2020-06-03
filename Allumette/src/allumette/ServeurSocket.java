package allumette;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;

/**
 *
 * @author lounis
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.floor;

public class ServeurSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int nb_max_d=0; /*nbre d'allumettes maxi au départ*/
        int nb_allu_max=0; /*nbre d'allumettes maxi que l'on peut tirer au maxi*/
        int qui=0; /*qui joue? 0=Nous --- 1=PC*/
        int prise=0; /*nbre d'allumettes prises par le joueur*/
        int nb_allu_rest=0; /*nbre d'allumettes restantes*/

        int [] gameData = {nb_max_d, nb_allu_max, qui, prise, nb_allu_rest};


        try {
            ServerSocket conn = new ServerSocket(10080);
            Socket comm = conn.accept();
            System.out.println("Server listening");
            DataOutputStream out = new DataOutputStream(comm.getOutputStream());
            DataInputStream in = new DataInputStream(comm.getInputStream());

            do{

                nb_max_d = in.readInt();
                nb_allu_max = in.readInt();
                qui = in.readInt();
                prise = in.readInt();
                nb_allu_rest = in.readInt();

                if (qui == 1){
                    nb_max_d = in.readInt();
                    nb_allu_max = in.readInt();
                    qui = in.readInt();
                    prise = jeu_ordi (nb_allu_rest , nb_allu_max);
                    nb_allu_rest = in.readInt();


                    qui=(qui+1)%2;
                    nb_allu_rest= nb_allu_rest - prise;

                    out.writeInt(nb_max_d);
                    out.writeInt(nb_allu_max);
                    out.writeInt(qui);
                    out.writeInt(prise);
                    out.writeInt(nb_allu_rest);
                }

            } while(nb_allu_rest > 0);

            System.out.println("Fin");

            in.close();
            out.close();
            comm.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static int jeu_ordi (int nb_allum, int prise_max) {
        int prise = 0;
        int s = 0;
        float t = 0;
        s = prise_max + 1;
        t = ((float) (nb_allum - s)) / (prise_max + 1);
        while (t != floor(t)) {
            s--;
            t = ((float) (nb_allum - s)) / (prise_max + 1);
        }
        prise = s - 1;
        if (prise == 0)
            prise = 1;
        return (prise);
    }

}

