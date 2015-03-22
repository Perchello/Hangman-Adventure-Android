package perchello.hangman.Model;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Perchello on 22/03/2015.
 */
public class ConnectDatabase {
    private String [] mNames;
    private String [] mScores;

    public ConnectDatabase () {
        downloadScore();
    }
    private void downloadScore(){
        String forecastURL = "http://10.0.3.2/hangman/get_all_userinfo.php";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(forecastURL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("onFailure downloading", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException{
                try {
                    String jsonData = response.body().string();
                    Log.d("Json Data ", jsonData);
                    formatHighscore(jsonData);

                } catch (IOException e) {
                    Log.e("Exception downloading", e.getMessage());
                } catch (JSONException e) {
                    Log.e("Exception downloading", e.getMessage());
                }
            }
        });

    }

    private void formatHighscore(String jsonData) throws JSONException {
        JSONObject userInfo = new JSONObject(jsonData);
        JSONArray highscores = userInfo.getJSONArray("highscores");
        Highscore [] highscore = new Highscore[highscores.length()];
        for (int i = 0; i<highscores.length(); i++){
            JSONObject jsonDay = highscores.getJSONObject(i);
            Highscore highscoreNew = new Highscore();

            highscoreNew.setName(jsonDay.getString("username"));
            highscoreNew.setScore(jsonDay.getInt("score"));
            highscore[i]=highscoreNew;
            Log.d ("User number :"+i, highscoreNew.getName());
            Log.d ("Score number :"+i, highscoreNew.getScore()+"");
        }

    }
}
