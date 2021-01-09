import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //ba7bf21e35c52c910bc87b6ad442114d
    public static String gatWeather(String massage, Model model) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + massage + "&units=metric&appid=ba7bf21e35c52c910bc87b6ad442114d");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++){
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

            return "Город: "+model.getName()+"\n"+
                    "Температура: "+model.getTemp()+"c"+"\n"+
                    "Влажность: " + model.getHumidity()+"\n"+
                    "Описание: " + model.getMain() + "\n"+
                    "https://openweathermap.org/img/w/"+ model.getIcon()+".png";
    }
}
