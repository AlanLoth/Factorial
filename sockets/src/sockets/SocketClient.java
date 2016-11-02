package sockets;
/*
    Loth
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress IP1=InetAddress.getLocalHost();
        String ip = IP1.toString();
        String numero, ips = "";
        int puerto = 1234;
        Scanner teclitas = new Scanner(System.in);
        
        
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        
        try {
            System.out.println(IP1);
            for(int i = 0; i < ip.length();i++){
                if(ip.charAt(i)=='0' || ip.charAt(i)=='1' || ip.charAt(i)=='2' || ip.charAt(i)=='3' || ip.charAt(i)=='4' || ip.charAt(i)=='5' || ip.charAt(i)=='6' || ip.charAt(i)=='7' || ip.charAt(i)=='8' || ip.charAt(i)=='9' || ip.charAt(i)=='.'){
                    ips = ips + ip.charAt(i);
                }
            }
            System.out.println(" socket " + ips + " " + puerto);
            System.out.println("Enviando solicitud de conexion...");
            //el cliente crea un socket para solicitar una conexion al SocketServer
            socketCliente = new Socket(ips, puerto);
            System.out.println("Conectado");
            
            System.out.println("Teclee el nunmero");
            numero = teclitas.nextLine();
            System.out.println("Enviando numero...");
            
            //obtengo la entrada y la salida de bytes 
            entrada = new BufferedReader( new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter( new OutputStreamWriter(socketCliente.getOutputStream()), true);
            
            //añado el nombre que envia el cliente
            
            salida.println(numero);
            salida.flush();
            
            //leo la respuesta del server 
            System.out.println("recibiendo ... ");
            System.out.println("El factorial es: " + entrada.readLine());
            
            //cerramos la conexion
            socketCliente.close();
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
        }
    }

}
