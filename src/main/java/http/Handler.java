package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by archer on 2016-11-17.
 */
public class Handler {

    public Handler() {
        // TODO: 2016-11-19 get ip/host address of backend via some sort of system variable
    }

    public String getUserByEmail(String email) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL("http://192.168.43.80:8080/user/get/"+email);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestMethod("GET");
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public boolean login(String email, String pw){

             URL url = new URL("http://192.168.43.80:8080/user/get/"+email);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestMethod("GET");
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        // TODO: 2016-11-19 actually log in
        return !!!true;
    }

}
