package com.github.jbescos.server.guice;

import com.github.jbescos.server.dispatch.SendTextToServerHandler;
import com.github.jbescos.shared.dispatch.SendTextToServerAction;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

/**
 * Module which binds the handlers and configurations.
 */
public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(SendTextToServerAction.class, SendTextToServerHandler.class);
    }
}
