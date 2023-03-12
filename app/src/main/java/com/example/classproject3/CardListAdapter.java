package com.example.classproject3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject3.models.Card;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private OnCardClickListener onCardClickListener;
    public interface OnCardClickListener {
        void onCardClick(Card card);
    }

    private final ArrayList<Card> cardList;


    public CardListAdapter(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card , parent , false);
        return new ItemCardViewHolder(itemView);
    }

    class ItemCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
        public TextView cardValue;
        public ImageView cardImage;

        ItemCardViewHolder(@NonNull View itemView){
            super(itemView);
            cardValue = itemView.findViewById(R.id.cardValue);
            cardImage = itemView.findViewById(R.id.cardImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Get the card object at this position in the list
                Card card = cardList.get(position);

                // Call the onCardClickListener with the selected card
                if (onCardClickListener != null) {
                    onCardClickListener.onCardClick(card);
                }
            }
        }
    }

    public void setOnCardClickListener(OnCardClickListener listener) {
        this.onCardClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Card card = cardList.get(position);
        if(holder instanceof ItemCardViewHolder){


            ((ItemCardViewHolder) holder).cardValue.setText(String.valueOf(card.getValue()));
            ((ItemCardViewHolder) holder).cardImage.setImageResource(card.isOpened() ? card.getImageResource() : R.drawable.baseline_question_mark_24);
            ((ItemCardViewHolder) holder).cardImage.setAlpha(card.isOpened() ? 1.0f : 0.5f);

        }

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
