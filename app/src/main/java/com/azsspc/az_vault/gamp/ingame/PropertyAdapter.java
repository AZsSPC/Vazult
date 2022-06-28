package com.azsspc.az_vault.gamp.ingame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.tiles.Property;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Property> tiles;

    public PropertyAdapter(Context context, List<Property> tiles) {
        this.tiles = tiles;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.gt_property, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Property tile = tiles.get(position);
        Context c = holder.context.getContext();
        int color = c.getColor(tile.getColor());

        holder.img.setImageResource(tile.getIcon());
        holder.top.setText(tile.getTop(c));
        holder.center.setText(tile.getCenter(c));
        if (!tile.getBottom(c).equals("")) holder.bottom.setText(tile.getBottom(c));
        else holder.bottom.setVisibility(View.GONE);

        holder.img.setColorFilter(color);
        holder.top.setTextColor(color);
        holder.center.setTextColor(color);
        //holder.bottom.setTextColor(color);

        holder.context.setOnClickListener(v -> holder.bottom.setVisibility(holder.bottom.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
        holder.context.setOnLongClickListener(v -> {
            holder.center.setVisibility(holder.center.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return tiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView img;
        final TextView top, center, bottom;
        final View context;

        ViewHolder(View view) {
            super(view);
            context = view.findViewById(R.id.gt_p_context);
            img = view.findViewById(R.id.gt_p_img);
            top = view.findViewById(R.id.gt_p_top);
            center = view.findViewById(R.id.gt_p_center);
            bottom = view.findViewById(R.id.gt_p_bottom);
        }
    }
}