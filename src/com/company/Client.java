package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner scn;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;




    public Tour[] connection(int i, Tour[] tourss) {
        Tour[] tourArray = tourss;
        try {
            scn = new Scanner(System.in);
            s = new Socket("192.168.32.71", 4000);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

            os.writeObject(tourss);

            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            tourArray = (Tour[]) is.readObject();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tourArray;
    }


}



