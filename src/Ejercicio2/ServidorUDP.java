/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	public static void main(String[] args) {
		System.out.println("--------------------SERVIDOR-----------------------");
		try {
			DatagramSocket socketUDP = new DatagramSocket(6543);
			//Recibimos un vector de bytes del cliente
			byte [] bufer = new byte[10000];
			//el while es para recibir varias peticiones de cliente
			while(true){
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				//Almacenamos lo que nos envio el cliente
				socketUDP.receive(peticion);
				//Imprimimos los datos, antes convirtiendo en caracteres
				System.out.println("DATOS: "+ new String(peticion.getData()));	
                                
                                String mensaje=new String(peticion.getData());
                                
                                System.out.println(mensaje);
                                String cantpalabras= contar(mensaje);
                                
                                byte[] resp=cantpalabras.getBytes();
                                peticion= new DatagramPacket(resp, resp.length);
                                socketUDP.send(peticion);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

    private static String contar(String mensaje) {
        return "0";
    }
}
