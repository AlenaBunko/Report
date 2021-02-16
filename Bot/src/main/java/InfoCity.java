import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class InfoCity {
    public static String getInfoCity(String message, Model model) throws IOException,NullPointerException {
        URL url = new URL("http://api.geonames.org/wikipediaSearchJSON?q=" + message + "&maxRows=1&username=aledia");
        Scanner sc = new Scanner((InputStream) url.getContent());
        String result = " ";
        while (sc.hasNext()) {
            result += sc.nextLine();
        }

        JSONObject object = new JSONObject(result);
        JSONArray getArray=object.getJSONArray("geonames");
        for (int i=0; i<getArray.length(); i++){
            JSONObject obj=getArray.getJSONObject(i);
            model.setTitle((String)obj.get("title"));
            model.setSummary((String)obj.get("summary"));
            model.setCountryCode((String)obj.get("countryCode"));
            model.setElevation((Integer) obj.get("elevation"));
            model.setThumbnailImg((String)obj.get("thumbnailImg"));

        }
        return "Name: " + model.getTitle() + "\n" +
                "Facts: " + model.getSummary() + "\n" +
                "Country code: " + model.getCountryCode() + "\n" +
                "Elevation: " + model.getElevation() + "\n" +
                "Foto: " + model.getThumbnailImg();


    }
}