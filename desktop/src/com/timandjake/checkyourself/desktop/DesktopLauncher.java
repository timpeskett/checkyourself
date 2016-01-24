package com.timandjake.checkyourself.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.timandjake.checkyourself.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Check Yourself";
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		new LwjglApplication(new Main(), config);
	}
}
