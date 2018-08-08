package com.example.edvinas.bakingapp.ui.ingredients;

import android.view.View;
import android.widget.TextView;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseViewHolder;
import com.example.edvinas.bakingapp.utils.network.pojo.Ingredients;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsViewHolder extends BaseViewHolder<Ingredients> {
    @BindView(R.id.nameTextView)
    TextView nameTextView;
    @BindView(R.id.quantityValueTextView)
    TextView quantityValueTextView;

    public IngredientsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Ingredients ingredients) {
        nameTextView.setText(ingredients.getIngredient());
        quantityValueTextView.setText(String.format(
                Locale.getDefault(),
                "%.0f %s",
                ingredients.getQuantity(), ingredients.getMeasure())
        );
    }
}
