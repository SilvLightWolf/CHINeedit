package com.mitologia.chin.function;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import org.bukkit.entity.Player;

@api
public class PlayerLocale extends AbstractFunction {

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{CREException.class};
    }

    @Override
    public boolean isRestricted() {
        return false;
    }

    @Override
    public Boolean runAsync() {
        return null;
    }

    @Override
    public Construct exec(Target target, Environment environment, Construct... constructs) throws ConfigRuntimeException {
        Player p;
        if (constructs.length == 0) {
            p = (Player) environment.getEnv(CommandHelperEnvironment.class).GetPlayer().getHandle();
        } else {
            p = (Player) Static.GetPlayer(constructs[0], target).getHandle();
        }
        return new CString(p.getLocale(), target);
    }

    @Override
    public Version since() {
        return new SimpleVersion(1, 0, 0);
    }

    @Override
    public String getName() {
        return "player_locale";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{0, 1};
    }

    @Override
    public String docs() {
        return "String ([player]) get Player's locale.";
    }
}
