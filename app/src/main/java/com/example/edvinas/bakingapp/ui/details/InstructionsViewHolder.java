package com.example.edvinas.bakingapp.ui.details;

import android.view.View;
import android.widget.Button;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseViewHolder;
import com.example.edvinas.bakingapp.utils.ItemClickListener;
import com.example.edvinas.bakingapp.utils.network.pojo.Steps;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionsViewHolder extends BaseViewHolder<Steps> {
    @BindView(R.id.instructionsButton)
    Button instructionsButton;
    private final ItemClickListener<Steps> clickListener;

    public InstructionsViewHolder(View itemView, ItemClickListener<Steps> clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Steps steps) {
        instructionsButton.setText(steps.getShortDescription());
        instructionsButton.setOnClickListener(view ->
                clickListener.onItemClicked(getItem())
        );
    }
}
