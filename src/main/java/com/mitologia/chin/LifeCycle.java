package com.mitologia.chin;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.AliasCore;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import com.mitologia.chin.event.Listener;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@MSExtension("CHINeedit")
public class LifeCycle extends AbstractExtension {

    @Override
    public void onStartup() {
        System.out.println("[CHINeedIt] Enabled!");
        Listener.register();
    }

    @Override
    public void onShutdown() {
        System.out.println("[CHINeedIt] Disabled.");
        Listener.unregister();
        Class c = AliasCore.ReloadOptions.class;
        Boolean globals = true;
        try {
            Field f = c.getField("globals");
            f.setAccessible(true);
            globals = Boolean.getBoolean(f.toString());
        } catch (NoSuchFieldException ignored) {
        }
        if (globals) {
            Mapper.reset();
        }
    }

    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 1, 0);
    }
}
