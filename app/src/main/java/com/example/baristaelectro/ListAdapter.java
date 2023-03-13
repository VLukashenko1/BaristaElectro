package com.example.baristaelectro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baristaelectro.DataCollector.DrinkModel;
import com.example.baristaelectro.DataCollector.TempResultHolder;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<DrinkModel> drinkList;

    public ListAdapter(Context context, List<DrinkModel> drinkList) {
        inflater = LayoutInflater.from(context);
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drink_item_view, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageDrawable(drinkList.get(position).drawable);
        holder.radioButton.setText(drinkList.get(position).name);

        holder.imageView.setOnClickListener(view -> {
            holder.radioButton.toggle();
        });

        holder.radioButton.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                DrinkModel drinkModel = new DrinkModel(drinkList.get(holder.getAdapterPosition()).name,
                        drinkList.get(holder.getAdapterPosition()).drawable,
                        true);
                drinkList.set(position, drinkModel);
            } else {
                DrinkModel drinkModel = new DrinkModel(drinkList.get(holder.getAdapterPosition()).name,
                        drinkList.get(holder.getAdapterPosition()).drawable,
                        false);
                drinkList.set(position, drinkModel);
            }
            TempResultHolder.getInstance().getListMutableLiveData().setValue(drinkList);
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final Switch radioButton;

        ViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.RVimageView);
            radioButton = view.findViewById(R.id.switch1);
        }
    }

}
