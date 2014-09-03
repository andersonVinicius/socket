/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anderson
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String args[]) throws Exception {
        Criptografia cript = new Criptografia();
        //Cria um servidor UDP na porta 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);
        //Sockets apenas enviam bytes

        while (true) {
            byte[] receiveData = new byte[16];
            byte[] sendData = new byte[32];

            System.out.println("Servidor UDP ouvindo...");
            //Recebe as mensagens dos clientes
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            //teste verificar a sequencia decriptografada no servidor
            System.out.print("cipher:  ");
            for (int i = 0; i < receiveData.length; i++) {
                System.out.print(new Integer(receiveData[i]) + " ");
            }
            System.out.println("");

            String sentence = cript.decrypt(receivePacket.getData());
            System.out.println("Recebido: " + sentence);
            //Responde ao mesmo IP e Porta do pacote recebido.
//            InetAddress IPAddress = receivePacket.getAddress();
//            int port = receivePacket.getPort();
//            String capitalizedSentence = sentence.toUpperCase();
//            sendData = capitalizedSentence.getBytes();
//            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//            serverSocket.send(sendPacket);

//            for (int i = 0; i < receiveData.length; i++) {
//                receiveData[i] = 0;
//            }
        }
    }
}
