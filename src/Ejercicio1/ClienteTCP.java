/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author www96
 */
public class ClienteTCP {

    public static void main(String[] args) throws IOException {

        //Socket del Cliente
        Socket socketCliente = null;

        //Flujos de entrada y salida
        BufferedReader entrada = null;
        PrintWriter salida = null;

        //Iniciamos el Cliente
        System.out.println("----CLIENTE MENU----");
        try {

            //Conectamos el socket del Cliente al puerto 8888 y al host localhost
            socketCliente = new Socket("localhost", 8888);
            

            //Iniciamos los flujos de entrada y salida
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
        } catch (Exception e) {
            System.out.println(e);
        }

        //Creamos un buffer para ingresar peticiones mediante el sistema
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        
        
        try {
            String cad=null;
            while (true) {

                //Ingresamos las peticiones del Cliente
                System.out.println("Ingrese el numero de la opcion...\n1 .- Opcion 1\n2 .- Opcion 2\n3 .- Opcion 3\n4 .- Salir\n");
                System.out.print("Ingrese su opcion:\t");
                cad = sc.readLine();

                //La enviamos al Servidor
                salida.println(cad);

                //Se escribe exit para perminar la conexion con el servidor
               if (cad.equals("4")) {
                   System.out.println("Saliendo...");
                   break;
               }

                //Recibimos la respuesta del servidor y la mostramos al cliente
                cad = entrada.readLine();
                System.out.println(cad);

            }

        } catch (Exception e) {
            System.err.println(e);
        }

        //Cerramos los sockets y los flujos de E/S
        entrada.close();
        salida.close();
        sc.close();
        socketCliente.close();

    }
}
