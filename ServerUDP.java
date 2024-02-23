import java.io.IOException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.DatagramSocket;

public class ServerUDP {

    private DatagramSocket socket;
    /*
     ** port: the port number the server will be running on
     */
    public ServerUDP(int port) throws SocketException{
        socket = new DatagramSocket(port);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Syntax: ServerUDP <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        try {
            ServerUDP server = new ServerUDP(port);
            //server.helloWorldService();
            server.timeService();
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    private void helloWorldService() throws IOException {
        while (true) {
            // Step 1: recieve a character from client
            DatagramPacket request =
                    new DatagramPacket(new byte[1024], 1024);
            //socket.receive is a blocking call!
            socket.receive(request);
            //retrieve the character sent by the client
            byte[] buffer = request.getData();
            //note: Do NOT do buffer.toString() as it returns
            // the string representation of the memory address of buffer!
            System.out.print("Request data: " + new String(buffer));
            System.out.println();

            // Step 2: send the same character back to the client
            InetAddress clientIP = request.getAddress();
            int clientPort = request.getPort();

            DatagramPacket response =
                    new DatagramPacket(buffer,
                            buffer.length,
                            clientIP,
                            clientPort);
            socket.send(response);
        }
    }

    private void timeService() throws IOException {
        while (true) {
            // Step 1: recieve a request from client
            DatagramPacket request =
                    new DatagramPacket(new byte[1024], 1024);
            //socket.receive is a blocking call!
            socket.receive(request);
            //retrieve the character sent by the client
            byte[] buffer = request.getData();
            //note: Do NOT do buffer.toString() as it returns
            // the string representation of the memory address of buffer!
            System.out.print("Request data: " + new String(buffer));
            System.out.println();

            // Step 2: send the same character back to the client
            InetAddress clientIP = request.getAddress();
            int clientPort = request.getPort();

            DatagramPacket response =
                    new DatagramPacket(buffer,
                            buffer.length,
                            clientIP,
                            clientPort);
            socket.send(response);
        }
    }
}

