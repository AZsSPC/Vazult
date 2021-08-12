package com.azsspc.az_vault.smsl_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.game_comp.Item;
import com.azsspc.az_vault.game_comp.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.azsspc.az_vault.DataLoader.as_items;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Property> tiles;

    public PropertyAdapter(Context context, List<Property> tiles) {
        this.tiles = tiles;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.ad_property, parent, false));
    }

    @Override
    public void onBindViewHolder(PropertyAdapter.ViewHolder holder, int position) {
        Property tile = tiles.get(position);
        int color = tile.getColor();

        holder.img.setImageResource(tile.getImg());
        holder.top.setText(tile.getTop());
        holder.center.setText(tile.getCenter());
        holder.bottom.setText(tile.getBottom());

        holder.img.setColorFilter(color);
        holder.top.setTextColor(color);
        holder.center.setTextColor(color);
        //holder.bottom.setTextColor(color);

        ArrayList<Item> items = new ArrayList<>();
        for (String item : tile.getItems()) items.add(as_items.get(item));
        holder.rv_items.setAdapter(new ItemAdapter(holder.context.getContext(), items));

        holder.context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View c = holder.rv_items;
                if (c.getVisibility() == View.VISIBLE) c.setVisibility(View.GONE);
                else c.setVisibility(View.VISIBLE);
            }
        });
        holder.context.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View c = holder.center;
                if (c.getVisibility() == View.VISIBLE) c.setVisibility(View.GONE);
                else c.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return tiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView img;
        final TextView top, center, bottom;
        final RecyclerView rv_items;
        final View context;

        ViewHolder(View view) {
            super(view);
            context = view.findViewById(R.id.ad_p_context);
            img = view.findViewById(R.id.ad_p_img);
            top = view.findViewById(R.id.ad_p_top);
            center = view.findViewById(R.id.ad_p_center);
            bottom = view.findViewById(R.id.ad_p_bottom);
            rv_items = view.findViewById(R.id.ad_p_items);
        }
    }
}