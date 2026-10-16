package dev.tfbr;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class TfbrClient implements ClientModInitializer {
	public void onInitializeClient() {
		Cfg.ld();
		KeyMapping k = KeyMappingHelper.registerKeyMapping(new KeyMapping("key.tuffbright.toggle", InputConstants.KEY_B, KeyMapping.Category.register(Identifier.fromNamespaceAndPath("tuffbright", "main"))));
		ClientTickEvents.END_CLIENT_TICK.register(m -> {
			while (k.consumeClick()) {
				Cfg.c.on = !Cfg.c.on;
				Cfg.sv();
				if (m.player != null) m.player.sendOverlayMessage(Component.literal("tuff bright " + (Cfg.c.on ? "on" : "off")));
			}
		});
	}
}
