package ConnectionLayer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Controllers.ConsultaController;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.util.Properties;

/**
 *
 * @author Irina
 */
public class ConexiuneProlog {

    private final String caleExecutabilSicstus = "C:\\Program Files (x86)\\SICStus Prolog 4.0.2\\bin\\spwin.exe";
    final String nume_fisier="sistem_expert_filme.pl";
    final String scop="inceput.";
//    final String scop="pornire.";
    //final String scop="main.";

    public Process procesSicstus;
    public ExpeditorMesaje expeditor;
    CititorMesaje cititor;
    //Fereastra fereastra;
    public ConsultaController controller; //hack
    Stage fereastra;
    int port;


    public Stage getFereastra(){
        return fereastra;
    }

    public ConexiuneProlog(int _port, Stage _fereastra) throws IOException, InterruptedException{
        InputStream processIs, processStreamErr;
        port=_port;
        fereastra=_fereastra;
        //obtin cale executabil
        //String caleExecutabilSicstus = System.getProperty("sicstusProgram", "C:\\Users\\Irina\\Desktop\\SICStus Prolog 4.0.2\\SICStus Prolog 4.0.2\\bin\\sicstus.exe");
        //acces la mediul curent de rulare
        ServerSocket servs=new ServerSocket(port);
        //Socket sock_s=servs.accept();
        cititor=new CititorMesaje(this,servs);
        cititor.start();
        expeditor=new ExpeditorMesaje(cititor);
        expeditor.start();


        Runtime rtime= Runtime.getRuntime();

        String comanda=caleExecutabilSicstus+" -f -l "+nume_fisier+" --goal "+scop+" -a "+port;

        procesSicstus=rtime.exec(comanda);

        //InputStream-ul din care citim ce scrie procesul
        processIs=procesSicstus.getInputStream();
        //stream-ul de eroare
        processStreamErr=procesSicstus.getErrorStream();
    }

    public void opresteProlog() throws InterruptedException{
        PipedOutputStream pos= this.expeditor.getPipedOutputStream();
        PrintStream ps=new PrintStream(pos);
        ps.println("exit.");
        ps.flush();
    }
}
