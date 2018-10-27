package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.mitologia.chin.Mapper;
import com.mitologia.chin.abstraction.BukkitMCMapFont;
import com.mitologia.chin.bukkit.MCAwtImage;
import com.mitologia.chin.bukkit.MCMapCanvas;
import com.mitologia.chin.bukkit.MCMapCursorCollection;
import org.bukkit.map.MinecraftFont;


@api
public class DrawCanvas extends AbstractFunction {
	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[0];
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

		String mcvkey = args[0].val();
		if (mcvkey.length() != 10)
			throw new CRECastException("Expecting key length 10 but recieved length " + mcvkey.length(), t);
		if (!Mapper.mapObj.keySet().contains(mcvkey))
			throw new CRENotFoundException("Cannot found value of " + mcvkey, t);
		MCMapCanvas canvas = (MCMapCanvas) Mapper.mapObj.get(mcvkey);

		if(args.length == 5){
			String cckey = args[4].val();
			if (cckey.length() != 12)
				throw new CRECastException("Expecting length 12 but recieved length " + cckey.length(), t);
			if (!Mapper.mapObj.keySet().contains(cckey))
				throw new CRENotFoundException("Cannot found value of " + cckey, t);
			MCMapCursorCollection cc = (MCMapCursorCollection) Mapper.mapObj.get(cckey);
			canvas.setCursors(cc);
		}

		int x = Integer.valueOf(args[1].val());
		int y = Integer.valueOf(args[2].val());

		if(args[3].val().length() == 8 && Mapper.mapObj.keySet().contains(args[3].val())){
			MCAwtImage image = (MCAwtImage) Mapper.mapObj.get(args[3].val());
			canvas.drawImage(x, y, image);
		} else if (args[3] instanceof CInt) {
			byte color = Byte.parseByte(args[3].val());
			canvas.setPixel(x, y, color);
			Mapper.mapObj.put(mcvkey, canvas);
		} else {
			canvas.drawText(x, y, new BukkitMCMapFont(MinecraftFont.Font), args[3].val());
		}

		return CVoid.VOID;
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "draw_canvas";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{4, 5};
	}

	@Override
	public String docs() {
		return "void (canvas, x, y, url/path/color/text[, CursorCollection]) draw Canvas!";
	}
}
