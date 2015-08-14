package com.fly.firefly.ui.module;

import com.fly.firefly.AppModule;
import com.fly.firefly.ui.fragment.LoginFragment;
import com.fly.firefly.ui.fragment.SearchFlightFragment;
import com.fly.firefly.ui.presenter.LoginPresenter;
import com.fly.firefly.ui.presenter.SearchFlightPresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = SearchFlightFragment.class,
        addsTo = AppModule.class,
        complete = false
)
public class SearchFlightModule {

    private final SearchFlightPresenter.SearchFlightView searchFlightView;

    public SearchFlightModule(SearchFlightPresenter.SearchFlightView searchFlightView) {
        this.searchFlightView = searchFlightView;
    }

    @Provides
    @Singleton
    SearchFlightPresenter provideSearchFlightPresenter(Bus bus) {
        return new SearchFlightPresenter(searchFlightView, bus);
    }
}
