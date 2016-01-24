package com.timandjake.checkyourself.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.timandjake.checkyourself.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Check Yourself";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Main(), config);

	}
}
