package com.github.jbescos.client.application;

import javax.inject.Inject;

import com.github.jbescos.client.place.NameTokens;
import com.github.jbescos.client.place.TokenParameters;
import com.github.jbescos.shared.FieldVerifier;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements ApplicationUiHandlers {
    @ProxyStandard
    @NameToken(NameTokens.home)
    interface MyProxy extends ProxyPlace<ApplicationPresenter> {
    }

    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> {
        void resetAndFocus();

        void setError(String errorText);
    }

    private final PlaceManager placeManager;

    @Inject
    ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void sendName(String name) {
        sendNameToServer(name);
    }

    @Override
    protected void onReset() {
        super.onReset();

        getView().resetAndFocus();
    }

    private void sendNameToServer(String name) {
        getView().setError("");
        if (!FieldVerifier.isValidName(name)) {
            getView().setError("<p><em>Please enter at least four characters</em></p>");
            return;
        }

        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.response)
                .with(TokenParameters.TEXT_TO_SERVER, name)
                .build();
        placeManager.revealPlace(responsePlaceRequest);
    }
}
