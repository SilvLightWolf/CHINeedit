package com.mitologia.function;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import org.bukkit.Material;

@api
public class is_material extends AbstractFunction {

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
        for(Material m : Material.values())
            if(m.name().equalsIgnoreCase(constructs[0].val()))
                return CBoolean.TRUE;
        return CBoolean.FALSE;
    }

    @Override
    public Version since() {
        return new SimpleVersion(1, 0, 0);
    }

    @Override
    public String getName() {
        return "is_material";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{ 1 };
    }

    @Override
    public String docs() {
        return "boolean (name) check it is material.";
    }
}
