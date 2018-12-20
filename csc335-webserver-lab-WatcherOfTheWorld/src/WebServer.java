import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class WebServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(80);
			while(true) {
				Socket socket = server.accept();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((socket.getInputStream())));
				PrintWriter printWriter = new PrintWriter((new OutputStreamWriter(socket.getOutputStream())));
				String filename;
				int count = 0;
				int chara = bufferedReader.read();
				String string = ((char) chara) + "";
				while (chara != -1) {
					chara = bufferedReader.read();
					string += (char) chara;
					if (((char) chara) == '\n') {
						count++;
						if (count == 2) {
							break;
						}
					}
				}
				String[] arr = string.split(" ");
				try {
					filename = arr[1].replace("/", "");
				}catch (ArrayIndexOutOfBoundsException e){
					printWriter.close();
					bufferedReader.close();
					printWriter.close();
					System.out.println("我也不知道这是什么情况\n总之他就是炸了");
					continue;
				}

				File file = new File(filename);
				if (!file.exists()) {
					printWriter.println("HTTP/1.1 404 Not Found\r");
					// close connection
					printWriter.close();
					bufferedReader.close();
					printWriter.close();
					continue;
				}

				BufferedReader br = new BufferedReader(new FileReader(file));
				long length = file.length();
				System.out.println("file" + filename);
				printWriter.println("HTTP/1.1 200 OK");
				printWriter.println(getServerTime());
				printWriter.println("Content-Length: " + length);
				printWriter.println("Connection: close");
				printWriter.println("Content-Type: text/html");
				printWriter.println();

				String str;
				while ((str = br.readLine()) != null) {
					printWriter.println(str);
				}
				printWriter.close();
				bufferedReader.close();
				br.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static String getServerTime() {
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat(
	        "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    return dateFormat.format(calendar.getTime());
	}

}
