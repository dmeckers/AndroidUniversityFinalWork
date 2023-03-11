


package com.example.classproject3;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Path;
        import android.util.TypedValue;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.io.FileNotFoundException;
        import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> list;
    int viewType;
    Context context;

    public MusicListAdapter(ArrayList<String> list , int viewType , Context context){
        this.list = list; this.viewType = viewType; this.context = context;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(this.viewType == 1){
            return new ItemSongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music , parent , false));
        }else if(this.viewType == 2){
            return new ItemArtistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music , parent , false));
        }
        return new ItemSongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music , parent , false));

    }

    private  String convertToUnderscore(String input) {
        String output = input.replace(" ", "_");

        output = output.toLowerCase();

        return output;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            String musicArtistName = list.get(position);
//            String path = Path.;
            Bitmap bmImg;
//        bmImg = BitmapFactory.decodeFile(path + convertToUnderscore(musicArtistName)+".jpg");
            int resourceId = context.getResources()
                    .getIdentifier(convertToUnderscore(musicArtistName) , "drawable" , context.getPackageName());

            bmImg = BitmapFactory.decodeResource( context.getResources() , resourceId  );




        if (holder instanceof ItemSongViewHolder) {
            if(bmImg != null){
                ((ItemSongViewHolder) holder).icon.setImageBitmap(bmImg);
                ((ItemSongViewHolder) holder).musicName.setVisibility(View.GONE);

            }else{
                ((ItemSongViewHolder) holder).icon.setImageResource(R.drawable.baseline_music_note_24);
                ((ItemSongViewHolder) holder).musicName.setText(musicArtistName);
                ((ItemSongViewHolder) holder).icon.setPadding(10 , 0 ,10 ,0);

                float textSize = 20f;
                float scale = context.getResources().getDisplayMetrics().scaledDensity;
                ((ItemSongViewHolder) holder).musicName.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize / scale);

            }




        }else if(holder instanceof ItemArtistViewHolder){

            if(bmImg != null){
                ((ItemArtistViewHolder) holder).icon.setImageBitmap(bmImg);
                ((ItemArtistViewHolder) holder).songTitle.setVisibility(View.GONE);
            }else{
                ((ItemArtistViewHolder) holder).icon.setImageResource(R.drawable.baseline_mic_24);
                ((ItemArtistViewHolder) holder).songTitle.setText(musicArtistName);
                ((ItemArtistViewHolder) holder).icon.setPadding(10 , 0 ,10 ,0);
                float textSize = 20f;
                float scale = context.getResources().getDisplayMetrics().scaledDensity;
                ((ItemArtistViewHolder) holder).songTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize / scale);
            }
        }
    }



    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    class ItemSongViewHolder extends RecyclerView.ViewHolder  {
        TextView musicName;
        ImageView icon;

        ItemSongViewHolder(@NonNull View itemView){
            super(itemView);

            musicName = itemView.findViewById(R.id.tv_music_item_name);
            icon = itemView.findViewById(R.id.iv_music_icon);
        }
    }

    class ItemArtistViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle;
        ImageView icon;

        ItemArtistViewHolder(@NonNull View itemView){
            super(itemView);
            songTitle = itemView.findViewById(R.id.tv_music_item_name);
            icon = itemView.findViewById(R.id.iv_music_icon);
        }
    }


}

