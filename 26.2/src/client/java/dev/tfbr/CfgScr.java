package dev.tfbr;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CfgScr {
	static Component t(String s) {
		return Component.literal(s);
	}

	public static Screen mk(Screen p) {
		ConfigBuilder b = ConfigBuilder.create().setParentScreen(p).setTitle(t("Tuff Bright")).setSavingRunnable(Cfg::sv);
		ConfigCategory cc = b.getOrCreateCategory(t("Settings"));
		ConfigEntryBuilder e = b.entryBuilder();
		Cfg c = Cfg.c;
		cc.addEntry(e.startBooleanToggle(t("Fullbright On"), c.on).setDefaultValue(true).setTooltip(t("Turn this off to go back to the normal game lighting.")).setSaveConsumer(v -> c.on = v).build());
		cc.addEntry(e.startTextDescription(t("Makes every block fully lit, in caves and at night, and removes the darkness effect. This is much stronger than the normal brightness slider.")).build());
		cc.addEntry(e.startIntSlider(t("Brightness"), c.pct, 0, 100).setDefaultValue(100).setTextGetter(v -> t(v + "%")).setTooltip(t("100% is the strongest, everything is lit like full daylight. Lower values give a softer boost. 0% does nothing.")).setSaveConsumer(v -> c.pct = v).build());
		return b.build();
	}
}