package com.github.jbescos.client.gin;

import com.github.jbescos.client.application.ApplicationModule;
import com.github.jbescos.client.place.NameTokens;
import com.github.jbescos.client.resources.ResourceLoader;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.GaAccount;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {
    private static final String ANALYTICS_ACCOUNT = "UA-8319339-6";

    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
                .defaultPlace(NameTokens.home)
                .errorPlace(NameTokens.home)
                .unauthorizedPlace(NameTokens.home)
                .build());
        install(new RpcDispatchAsyncModule());
        install(new ApplicationModule());

        bindConstant().annotatedWith(GaAccount.class).to(ANALYTICS_ACCOUNT);

        bind(ResourceLoader.class).asEagerSingleton();
    }
}
