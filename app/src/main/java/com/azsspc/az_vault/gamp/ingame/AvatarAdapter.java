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
import com.azsspc.az_vault.gamp.tiles.Avatar;

import java.util.List;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Avatar> tiles;

    public AvatarAdapter(Context context, List<Avatar> tiles) {
        this.tiles = tiles;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.gt_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Avatar tile = tiles.get(position);
        Context c = holder.context.getContext();
        int color = c.getColor(tile.getColor());

        holder.img.setImageResource(tile.getIcon());
        holder.top.setText(tile.getTop(c));
        holder.center.setText(tile.getCenter(c));
        holder.bottom.setText(tile.getBottom(c));

        holder.img.setColorFilter(color);
        holder.top.setTextColor(color);
        holder.center.setTextColor(color);
        //holder.bottom.setTextColor(color);

        holder.context.setOnClickListener(v -> {
            View c1 = holder.bottom;
            if (c1.getVisibility() == View.VISIBLE) c1.setVisibility(View.GONE);
            else c1.setVisibility(View.VISIBLE);
        });
        holder.context.setOnLongClickListener(v -> {
            View c12 = holder.center;
            if (c12.getVisibility() == View.VISIBLE) c12.setVisibility(View.GONE);
            else c12.setVisibility(View.VISIBLE);
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
            context = view.findViewById(R.id.gt_a_context);
            img = view.findViewById(R.id.gt_a_img);
            top = view.findViewById(R.id.gt_a_top);
            center = view.findViewById(R.id.gt_a_center);
            bottom = view.findViewById(R.id.gt_a_bottom);
        }
    }
}