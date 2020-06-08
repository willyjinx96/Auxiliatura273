/*
Williams Fernando Qusipe Condori 10001379 LP
LAB-273
07/06/2020
 */
package Ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class ServidorTCP {


    public static void main(String[] args) throws IOException {
        //Creamos los socket TCP
        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        //Creamos los flujos de entrada y salidad
        BufferedReader entrada = null;
        PrintWriter salida = null;

        //Capturamos la fecha del sistema
        Date fecha = new Date();

        //Mostramos la fecha de la conexion

        //Iniciamos el Servidor
        System.out.println("-------------SERVIDOR-----------------");
        System.out.println("Esperando al cliente...");

        try {
            //Conectamos el socket al puerto
            socketServidor = new ServerSocket(8888);

            

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            while (true) {
                //Aceptamos la conexion del Cliente
                socketCliente = socketServidor.accept();

                //Habilitamos el flujo de entrada y de salida
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

                //Esperamos las peticiones del Cliente
                while (true) {

                    //Leemos el mensaje del Cliente
                    String cad = entrada.readLine();
                    System.out.println("Mensaje del cliente: " + cad);

                    //Al escribir la palabra exit, se cerrara la Conexion del Cliente
                    if (cad.equals("4")) {
                        break;
                    }

                    //Guardamos la informacion del sockte puerto e IP
                    int puertoCliente = socketCliente.getPort();
                    String ipCliente = socketCliente.getInetAddress().toString();


                    //Enviamos al Cliente la respuesta en mayusculas con la fecha de envio...
                    //Ademas anadimos a la respuesta la informacion del socket
                    //salida.println("Respuesta del Servidor:\t" + cad.toUpperCase() + "\nMensaje enviado en fecha:\t" + fecha);
                    //salida.println("Respuesta del Servidor:\t" + cad.toUpperCase() + "\tMensaje enviado en fecha:\t" + fecha
                    //       + "\t\tINFORMACION DEL CLIENTE => (Puerto: " + puertoCliente + "\tDireccion IP: " + ipCliente + ")");
                    salida.println(Funcion1(cad));

                }

            }
        } catch (java.lang.NullPointerException n) {
            System.out.println("No se ingreso ninguna peticion");
        } catch (java.net.SocketTimeoutException t) {
            System.out.println("Tiempo de espera para un Cliente terminado...");
            System.out.println("Cerrando Servidor...");
            

        //Liberamos los sockets y los buffer
        try {
            entrada.close();
            salida.close();
            socketServidor.close();
            socketCliente.close();
        } catch (Exception e) {
        }

    }
}

    private static String Funcion1(String cad) {
        int num=Integer.parseInt(cad);
        switch (num){
            case 1:
                cad="Papel";
                break;
            case 2:
                cad="Piedra";
                break;
            case 3:
                cad="Tijera";
                break;
            default:
                cad="Fuera del rango de las opciones, ingrese de nuevo...";
                break;
        }
                
        return cad;
    }
}

/*
Williams Fernando Qusipe Condori 10001379 LP
LAB-273
07/06/2020
 */
