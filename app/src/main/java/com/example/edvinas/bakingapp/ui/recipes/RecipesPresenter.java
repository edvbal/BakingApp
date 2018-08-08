package com.example.edvinas.bakingapp.ui.recipes;

import com.example.edvinas.bakingapp.base.BasePresenterImpl;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class RecipesPresenter extends BasePresenterImpl<RecipesContract.MainView>
        implements RecipesContract.RecipePresenter {
    private final RecipesContract.RecipeModel model;
    private final Scheduler scheduler;
    private Disposable disposable = Disposables.disposed();

    public RecipesPresenter(RecipesContract.RecipeModel model, Scheduler scheduler) {
        this.model = model;
        this.scheduler = scheduler;
    }

    @Override
    public void onViewCreated() {
        disposable = model.fetchRecipes()
                .observeOn(scheduler)
                .subscribe(recipes -> onView(view -> view.showRecipes(recipes)));
    }

    @Override
    public void dropView() {
        disposable.dispose();
        super.dropView();
    }
}
