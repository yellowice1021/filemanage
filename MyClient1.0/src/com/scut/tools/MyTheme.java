package com.scut.tools;

import javax.swing.UIManager;

public class MyTheme {
	public static Object[] getThemes()
	{
		Object[] object = {
				"windows", "Nimbus", "Motif",
				"BusinessBlackSteelSkin", "OfficeBlue2007Skin",
				"RavenGraphiteSkin","RavenGraphiteGlassSkin",
				"MangoSkin","NebulaBrickWallSkin",
				"EmeraldDuskSkin","GreenMagicSkin"
		};
		
		return object;
	}
	
	
	/*
	 * 1.0版本，建议不再使用
	 */
	@Deprecated 
	public static String getTheme(String name)
	{
		String theme = null;
		switch(name)
		{
		case "windows" :
			theme = UIManager.getSystemLookAndFeelClassName();
			break;
		case "Nimbus" :
			theme = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			break;
		case "Motif" : 
			theme = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			break;
		case "BusinessBlackSteelSkin":
			theme = "org.jvnet.substance.skin.BusinessBlackSteelSkin";
			break;
		case "OfficeBlue2007Skin":
			theme = "org.jvnet.substance.skin.OfficeBlue2007Skin";
			break;
		case "BusinessBlackSteelSk":
			theme = "org.jvnet.substance.skin.BusinessBlackSteelSkin";
			break;
		case "BusinessBlackSteelS":
			theme = "org.jvnet.substance.skin.BusinessBlackSteelSkin";
			break;
		
		}
		return theme;
	}
}
