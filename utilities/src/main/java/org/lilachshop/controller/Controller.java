package org.lilachshop.controller;

import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.panels.Panel;

public abstract class Controller {
    protected Panel panel;

//    @Subscribe
    public abstract void handleMessageReceivedFromClient(String msg);
}
