/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
	public static void main(String[] args) {
		System.out.println("--------------------CLIENTE-----------------------");
		try {
			//Creacion del socket
			DatagramSocket socketUDP = new DatagramSocket();
			int puerto = 6543;
			InetAddress host = InetAddress.getByName("localhost");
			//Buffer para leer en consola
			BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
			
			String cad;
			//Leemos en consola
			cad = sc.readLine();
			
			//Creamos un vector de bytes de la cadena
			byte [] mensaje = cad.getBytes();
			//Enviar datos a traves del socket
			DatagramPacket peticion = new DatagramPacket(mensaje, cad.length(), host, puerto);
			//Enviamos al servidor
			socketUDP.send(peticion);
                        
                        //
                        byte [] respuesta=new byte[1000];
			DatagramPacket resp = new DatagramPacket(respuesta, respuesta.length);
                        socketUDP.receive(resp);
                        System.out.println("La cantidad de palabras es: "+resp.getData().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
