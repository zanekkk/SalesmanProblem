package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    InetAddress ip;
    Scanner scn;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;




    public Tour[] connection(int i, Tour[] tourss) {
        Tour[] tourArray = tourss;
        try {
            scn = new Scanner(System.in);
            s = new Socket("192.168.1.29", 4000);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

//                System.out.println(dis.readUTF());

            //               String tosend = scn.nextLine();

            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            System.out.println("Server Connect: OK");

            os.writeObject(tourss);
//                dos.writeUTF(tosend);

            System.out.println("Pobieranie Danych...");
            // If client sends exit,close this connection
            // and then break from the while loop


            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            tourArray = (Tour[]) is.readObject();

            // printing date or time as requested by client
//                String received = dis.readUTF();
//                System.out.println(received);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tourArray;
    }


}



