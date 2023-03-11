package com.example.classproject3;

import android.app.ProgressDialog;
import android.app.Service;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import androidx.loader.content.AsyncTaskLoader;

import com.example.classproject3.models.Person;
import com.example.classproject3.models.Phone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RequestPersonalData extends AsyncTask<Void, Void , Void> {

    ProgressDialog progressDialog;
    ArrayList<Person> persons;
    OnPersonListDataReceived listener;

    public RequestPersonalData(ProgressDialog progressDialog , OnPersonListDataReceived listener ){
        this.progressDialog = progressDialog ; this.listener = listener;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        ServiceHandler serviceHandler = new ServiceHandler(MainActivity.url);
        String jsonString = serviceHandler.makeServiceCall();

        if(jsonString != null){
            jsonString = jsonString.replace("<pre>", "").replace("</pre>" , "");
            persons = new ArrayList<>();
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                for(int  i= 0 ; i < jsonArray.length(); i++){
                    JSONObject c = jsonArray.getJSONObject(i);
                    String name = c.getString("name");
                    String id = c.getString("id");
                    Phone phone = new Phone(
                            c.getJSONObject("phone").getString("mobile"),
                            c.getJSONObject("phone").getString("home"),
                            c.getJSONObject("phone").getString("office")
                    );

                    ArrayList<String> song = new ArrayList<>();
                    JSONArray jsonSongArray = c.getJSONObject("song_play_list").getJSONArray("song");
                    for(int j = 0; j < jsonSongArray.length() ; j++){
                        song.add(jsonSongArray.getString(j));
                    }

                    ArrayList<String> artists = new ArrayList<>();
                    JSONArray jsonArtistArray = c.getJSONObject("song_play_list").getJSONArray("artists");
                    for (int z = 0 ; z < jsonArtistArray.length(); z++){
                        artists.add(jsonArtistArray.getString(z));
                    }


                    Person p = new Person.Builder()
                            .withName(name)
                            .withId(id)
                            .withPhoneList(phone)
                            .withSongPlaylist(song)
                            .withArtistPlaylist(artists)
                            .build();

                    persons.add(p);



                }
            }catch (JSONException e){
                e.printStackTrace();
            }


        }
        return null;


    }

    @Override
    protected void onPostExecute(Void unused) {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        listener.onDataReceived(persons);
    }


    public interface OnPersonListDataReceived{
        void onDataReceived(ArrayList<Person> list);
    }

}
