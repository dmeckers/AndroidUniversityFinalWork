package com.example.classproject3;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestPostData extends AsyncTask<Void , Void , Void> {

    ProgressDialog progressDialog;
    OnDataReceived listener;

    ArrayList<HashMap<String , String>> blogPostList ;
    JSONArray blogPosts = null;

    int[] iconResources = {R.drawable.baseline_audiotrack_24 , R.drawable.baseline_cabin_24 , R.drawable.baseline_bakery_dining_24 ,
            R.drawable.baseline_beach_access_24 , R.drawable.baseline_sports_volleyball_24 ,
            R.drawable.baseline_style_24,R.drawable.baseline_pix_24 , R.drawable.baseline_storm_24 ,
            R.drawable.baseline_bakery_dining_24 , R.drawable.baseline_sports_football_24};



    public RequestPostData(ProgressDialog progressDialog , OnDataReceived listener){
        this.progressDialog = progressDialog;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Downloading data... please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... _) {

        ServiceHandler sh = new ServiceHandler(MainActivity.urlPosts);
        String jsonString = sh.makeServiceCall();
        if(jsonString != null){
            try {
                blogPostList  = new ArrayList<>();
                blogPosts = new JSONArray(jsonString);
                for (int i = 0; i < blogPosts.length();i++ ){
                    JSONObject b = blogPosts.getJSONObject(i);
                    String title = b.getString(GlobalVariables.TITLE);




                    HashMap<String, String> oneBlogPost = new HashMap<>();
                    oneBlogPost.put(GlobalVariables.TITLE , title);
                    oneBlogPost.put(GlobalVariables.BODY , b.getString(GlobalVariables.BODY));
                    oneBlogPost.put(GlobalVariables.USER_ID , b.getString(GlobalVariables.USER_ID));
                    oneBlogPost.put(GlobalVariables.ID , b.getString(GlobalVariables.ID));
                    String imageResource = ((Integer) iconResources[Integer.parseInt(b.getString(GlobalVariables.USER_ID)) -1 ]).toString();
                    oneBlogPost.put("imageResource" , imageResource);
                    blogPostList.add(oneBlogPost);
                }
            } catch (JSONException e) {
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
        listener.onDataReceive(blogPostList);
    }

    public interface OnDataReceived {
        void onDataReceive(ArrayList<HashMap<String , String>> blogData);
    }

}
