package dev.tfbr;

import net.fabricmc.api.ClientModInitializer;

public class TfbrClient implements ClientModInitializer {
	public void onInitializeClient() {
		Cfg.ld();
	}
}
