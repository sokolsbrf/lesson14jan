package com.example.sokol.testpaging;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumbersAdapter extends PagedListAdapter<Integer, NumbersAdapter.NumberHolder> {

    private static DiffUtil.ItemCallback<Integer> CALLBACK = new DiffUtil.ItemCallback<Integer>() {
        @Override
        public boolean areItemsTheSame(Integer oldItem, Integer newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(Integer oldItem, Integer newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected NumbersAdapter() {
        super(CALLBACK);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Nullable
    @Override
    protected Integer getItem(int position) {
        return position < super.getItemCount()? super.getItem(position) : null;
    }

    @NonNull
    @Override
    public NumberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == R.layout.item_number) {
            return new NumberHolder(inflater.inflate(R.layout.item_number, parent, false));
        }

        return new ProgressHolder(inflater.inflate(R.layout.item_loading, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) != null ? R.layout.item_number : R.layout.item_loading;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public static class NumberHolder extends RecyclerView.ViewHolder {
        public NumberHolder(View itemView) {
            super(itemView);
        }

        public void bind(@Nullable Integer value) {
            if (value != null) {
                ((TextView) itemView).setText(value.toString());
            } else {
                ((TextView) itemView).setText(R.string.placeholder);
            }
        }
    }

    public static class ProgressHolder extends NumberHolder {
        public ProgressHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(@Nullable Integer value) {
        }
    }

}
