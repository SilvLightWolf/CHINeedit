package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Security;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CRESecurityException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.mitologia.chin.Mapper;
import com.mitologia.chin.abstraction.BukkitMCAwtImage;
import com.mitologia.chin.lib.Validator;

import java.io.File;

@api
public class CreateAwtImage extends AbstractFunction {

	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[]{
				CRECastException.class,
				CRESecurityException.class
		};
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
	public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
		String key;
		if (Validator.is_file(Static.GetFileFromArgument(args[0].val(), env, t, null).getPath())) {
			key = Mapper.createKey("image", new BukkitMCAwtImage(Static.GetFileFromArgument(args[0].val(), env, t, null).getPath(), false));
			return new CString(key, t);
		} else if (Validator.is_url(args[0].val())) {
			key = Mapper.createKey("image", new BukkitMCAwtImage(args[0].val(), true));
			return new CString(key, t);
		} else {
			return CNull.NULL;
		}
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "create_image";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{1};
	}

	@Override
	public String docs() {
		return "key (path) create image val";
	}
}
