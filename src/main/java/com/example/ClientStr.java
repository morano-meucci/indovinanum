
package com.example;
import java.io.*;
import java.net.*;

public class ClientStr {
    String nomeServer="localhost";
    int portaServer=6789;
    Socket miosocket;
    BufferedReader tastiera;
    int numero;
    String stringaRicevutaDalServer;
    String stringa="indovinato";
    String stringa2="hai perso";
    DataOutputStream inDalServer;
    
    public Socket connetti(){
    System.out.println("2 CLIENT partito in esecuzione ...");
    try{
    tastiera=new BufferedReader(new InputStreamReader(System.in));
    miosocket=new Socket(nomeServer,portaServer);
    outVersoServer=new DataOutputStream(miosocket.getOutputStream());
    inDalServer=new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
    }
    catch(UnknownHostException e){
    System.err.println("Host sconosciuto");
    }catch(Exception e)
    {
    System.out.println(e.getMessage());
    System.out.println("Errore durante la connessione!");
    System.exit(1);
    }
    return miosocket;
    }
    public void comunica(){
        
    try{
        for(;;){
    System.out.println("4 ... inserisci il numero da trasmettere al server:"+'\n');
    numero= tastiera.readLine();
    System.out.println("5 ... invio il numero al server e attendo ...");
    outVersoServer.writeBytes(numero+'\n');
    stringaRicevutaDalServer=inDalServer.readLine();
    System.out.println("8 ... risposta dal server "+'\n'+stringaRicevutaDalServer);
     if(stringaRicevutaDalServer==stringa){
 String stringaMes="hai vinto";
    System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
    miosocket.close();
    break;
     }else if(stringaRicevutaDalServer==stringa2){
        String stringaMes="hai perso";
        System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
        miosocket.close();
        break;
     }
       }
    }
    catch(Exception e)
    {
    System.out.println(e.getMessage());
    System.out.println("Errore durante la comunicazione col server!");
    System.exit(1);
    }
    }
}
