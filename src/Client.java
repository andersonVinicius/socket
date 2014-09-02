/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anderson
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static void main(String args[]) throws Exception {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
                Criptografia cript = new Criptografia();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[32];
		byte[] receiveData = new byte[32];
		while(true){
			System.out.println("Cliente preparado para enviar: ");
			//Lê entrada do usuário
			String sentence = inFromUser.readLine();
			sendData = cript.encrypt(sentence);
                       // String sentenc = new String(sendData);
//  
                        String decrypted = cript.decrypt(sendData);
//
			System.out.println("decrypt: "+ decrypted);
                        
                        System.out.print("cipher:  ");
			for (int i = 0; i < sendData.length; i++)
				System.out.print(new Integer(sendData[i]) + " ");
			System.out.println("");
                        
                        
			//Cria pacote udp
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, 9876);
			//envia ao servidor
			clientSocket.send(sendPacket);
			//Recebe resposta do servidor
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
                       
			System.out.println("Recebido do servidor UDP:" + modifiedSentence);
			//Fecha conexão: clientSocket.close();
                       
		}
	}
}