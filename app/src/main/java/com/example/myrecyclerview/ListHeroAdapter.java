package com.example.myrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.CategoryViewHolder> {
    private ArrayList<Hero> listHero;

    public ListHeroAdapter(ArrayList<Hero> list) {
        this.listHero = list;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_hero, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        Hero hero = listHero.get(position);

        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);

        holder.tvName.setText(hero.getName());
        holder.tvFrom.setText(hero.getFrom());

        //ubah detail disini
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    public interface OnItemClickCallback{
        void onItemClicked(Hero data);
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
        }
    }
}
