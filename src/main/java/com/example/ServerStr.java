
package com.example;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server=null;
    Socket client=null;
    String stringaMessaggio=null;
    int numeroRicevuto;
    int tentativo=1;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi(){
    try{
    System.out.println("1 SERVER partito in esecuzione ...");
    server=new ServerSocket(6789);
    client=server.accept();
    server.close();
    inDalClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
    outVersoClient=new DataOutputStream(client.getOutputStream());
    }
    catch(Exception e){
    System.out.println(e.getMessage());
    System.out.println("Errore durante listanza dal server!");
    System.exit(1);
    }
    return client;
    }
    
    public void comunica(){
       try{
        for(;;){ 
    
    System.out.println("3 benvenuto client, generazione di un numero random.Indovina il numero da 1 a 100 in meno tentativi possibili. Attendo ...");
    int numero = new java.util.Random().nextInt(100) + 1;
    numeroRicevuto=inDalClient.readLine();
    System.out.println("6 ricevuto il numero dal cliente:"+numeroRicevuto);
    if(numeroRicevuto==numero){
    stringaMessaggio="indovinato";
    System.out.println("hai indovinato in"+tentativo+"tentativi");
    System.out.println("7 invio la stringa al client ...");
    outVersoClient.writeBytes(stringaMessaggio+'\n');
    System.out.println("9 SERVER: fine elaborazione ... buona notte!");
    client.close();
    break;
    }else if(numeroRicevuto<numero){
        stringaMessaggio="Troppo piccolo";
        tentativo++;
        System.out.println("7 invio la stringa al client ...");
    outVersoClient.writeBytes(stringaMessaggio+'\n');
    }else{
        stringaMessaggio="Troppo grande";
        tentativo++;
        System.out.println("7 invio la stringa al client ...");
        outVersoClient.writeBytes(stringaMessaggio+'\n');
    }
}
} catch(Exception e)
{
System.out.println(e.getMessage());
System.out.println("Errore durante la comunicazione col client!");
System.exit(1);
}
    }
    }



