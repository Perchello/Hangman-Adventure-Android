package perchello.hangman.Model;

import android.app.DownloadManager;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Perchello on 22/03/2015.
 */
public class ConnectDatabase {
    private String [] mNames;
    private String [] mScores;
    private Highscore [] mHighscores;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public ConnectDatabase () {
        downloadScore();

    }
    private void downloadScore(){
        String highscoreURL = "http://10.0.3.2/hangman/get_all_userinfo.php";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(highscoreURL).build();
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
        mHighscores = new Highscore[highscores.length()];
        for (int i = 0; i<highscores.length(); i++){
            JSONObject jsonDay = highscores.getJSONObject(i);
            Highscore highscoreNew = new Highscore();

            highscoreNew.setName(jsonDay.getString("username"));
            highscoreNew.setScore(jsonDay.getInt("score"));
            mHighscores[i]=highscoreNew;
        }
        updateScore(mHighscores);

    }
    public void updateScore(Highscore [] highscore) {
        OkHttpClient client = new OkHttpClient();
       for (int i=0; i<highscore.length; i++){
           Highscore highscoreTemp = highscore[i];


           String json =  "{'username':'Katya'}";
           RequestBody body = RequestBody.create(JSON, json);
           Request request = new Request.Builder().url("http://10.0.3.2/hangman/create_user.php").post(body).build();
           try {
               Response response = client.newCall(request).execute();
               Log.e("Reply from server", response.body().string());
           } catch (IOException e) {
               e.printStackTrace();
           }


       }
    }
}
