/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Math.floor;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sr03.Allumette.jeu_ordi;
import static sr03.ClientAllumette.afficher_allu;

/**
 *
 * @author imineyou
 */
public class ServeurAlumette {

    public static int jeu_ordi (int nb_allum, int prise_max)
{
	int prise = 0;
	int s = 0;
	float t = 0;
	s = prise_max + 1;
	t = ((float) (nb_allum - s)) / (prise_max + 1);
	while (t != floor(t))
	{
		s--;
		t = ((float) (nb_allum-s)) / (prise_max + 1);
	}
	prise = s - 1;
	if (prise == 0)
	prise = 1;
	return (prise);
}


     public static void main(String[] args) {
        int nb_compte=0;
         int nb_max_d=0; /*nbre d'allumettes maxi au départ*/
        int nb_allu_max=0; /*nbre d'allumettes maxi que l'on peut tirer au maxi*/
        int qui=0; /*qui joue? 0=Nous --- 1=PC*/
        int prise=0; /*nbre d'allumettes prises par le joueur*/
        int nb_allu_rest=0; /*nbre d'allumettes restantes*/

        try {
            ServerSocket server=new ServerSocket(7000);
            System.out.println("Serveur à l'écoute ......");
            Socket  client=server.accept();
            System.out.println(" nouveau client ...");


           DataInputStream ins=new DataInputStream(client.getInputStream());
           String []info_client=ins.readUTF().split("/");
            System.out.println("info client"+info_client[0]);
           nb_allu_max=Integer.parseInt(info_client[0]);
           nb_max_d=Integer.parseInt(info_client[1]);
           qui=Integer.parseInt(info_client[2]);
           nb_allu_rest=nb_max_d;
           DataOutputStream outs=new DataOutputStream(client.getOutputStream());
             do
            {
            System.out.println ("\nNombre d'allumettes restantes :"+nb_allu_rest);
            afficher_allu(nb_allu_rest);
            if (qui==0)
            {
                prise=ins.readInt();
                System.out.println("prise reçuu .."+prise);
                /* On répète la demande de prise tant que le nombre donné n'est pas correct */
            }
            else
            {

                prise = jeu_ordi (nb_allu_rest , nb_allu_max);
                outs.writeInt(prise);
                outs.flush();
                System.out.println ("\nPrise de l'ordi :"+prise);
            }
            qui=(qui+1)%2;

            nb_allu_rest= nb_allu_rest - prise;
        }
        while (nb_allu_rest >0);







              //  InputStream in=client.getInputStream();
              //   byte []b=new byte[1024];
              //  in.read(b);
              //  System.out.println("le client à dit: "+new String(b));

              //   DataInputStream ins=new DataInputStream(client.getInputStream());
              //   System.out.println("le client à dit: "+ins.readUTF());


               // OutputStream out=client.getOutputStream();
               // out.write("bonjour mon client".getBytes());
            // DataOutputStream outs=new DataOutputStream(client.getOutputStream());
            // outs.writeUTF("bonjour mon client .....");

        } catch (IOException ex) {

            System.err.println("client deconnecté ...");

        }




    }

}
