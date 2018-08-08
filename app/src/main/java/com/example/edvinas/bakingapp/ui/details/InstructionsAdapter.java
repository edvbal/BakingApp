package com.example.edvinas.bakingapp.ui.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseAdapter;
import com.example.edvinas.bakingapp.utils.ItemClickListener;
import com.example.edvinas.bakingapp.utils.network.pojo.Steps;

public class InstructionsAdapter extends BaseAdapter<Steps> {
    private final ItemClickListener<Steps> clickListener;

    public InstructionsAdapter(ItemClickListener<Steps> clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public InstructionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_instructions, parent, false);
        return new InstructionsViewHolder(itemView, clickListener);
    }
}
