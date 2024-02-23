import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ClientUDP {
    private DatagramSocket socket;

    public ClientUDP() throws SocketException {
        socket = new DatagramSocket();
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("ERROR: Use Syntax `ClientUDP <server IP> <server port>`");
            return;
        }
        try {
            InetAddress serverIP = InetAddress.getByName(args[0]);
            int serverPort = Integer.parseInt(args[1]);
            ClientUDP client = new ClientUDP();
            client.timeService(serverIP, serverPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void timeService(InetAddress serverIP, int serverPort) throws IOException {
        /* TODO: Write time service to ping time server for the time and log results */

        /* Visit https://tf.nist.gov/tf-cgi/servers.cgi to get the IP address. Use Port 37.*/

        /* Step 1: Send request to server */
        // Hint: We don't need any data because it accepts any requests and gives the time

        /* Step 2: receive the response packet from server */
        // The time server will send back a 32-bit unsigned Integer

        /* Step 3: Verify the response and display the content */

        Integer time = 0; // This would be your response from the time server
        System.out.println("Seconds since 01/01/1900: " + time);
        String date = formatSecondsToDateObject(time); // Use this function to transform it into a readable date!
        System.out.println("Date: " + date);
    }

    public String formatSecondsToDateObject(Integer time){
        /* Create timezone and date format */
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        /* Set up 1900 epoch */
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.YEAR, 1900);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Need to convert seconds to minute and second intervals due to seconds not fitting inside primitive int type
        long unsignedMinutes = (time & 0x00000000ffffffffL)/60; // Gets how many minutes to add
        long unsignedSeconds = (time & 0x00000000ffffffffL)%60; // Adds the leftover seconds from the minute calculation
        cal.add(Calendar.MINUTE, (int)unsignedMinutes);
        cal.add(Calendar.SECOND, (int)unsignedSeconds);
        
        return sdf.format(cal.getTime());
    }
}