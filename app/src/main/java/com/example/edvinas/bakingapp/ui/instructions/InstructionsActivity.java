package com.example.edvinas.bakingapp.ui.instructions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseActivity;
import com.example.edvinas.bakingapp.utils.network.pojo.Steps;

public class InstructionsActivity extends BaseActivity {
    private static final String KEY_INSTRUCTIONS = "key.instructions";

    public static void starter(Steps steps, Context context) {
        Intent intent = new Intent(context, InstructionsActivity.class);
        intent.putExtra(KEY_INSTRUCTIONS, steps);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHomeAsUp();
        setTitle(getString(R.string.instructions_tooblar_title));
        Steps steps = (Steps) getIntent().getSerializableExtra(KEY_INSTRUCTIONS);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.instructionsContainer, InstructionsFragment.newInstance(steps))
                    .commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_instructions;
    }
}
