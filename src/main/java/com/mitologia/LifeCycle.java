package com.mitologia;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.mitologia.event.Listener;

public class LifeCycle extends AbstractExtension {

    @Override
    public void onStartup(){
        System.out.println("[CHINeedIt] Enabled!");
        Listener.register();
    }

    @Override
    public void onShutdown(){
        System.out.println("[CHINeedIt] Disabled.");
        Listener.unregister();
    }

    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 0, 0);
    }
}
