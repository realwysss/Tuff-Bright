package dev.tfbr;

import net.fabricmc.fabric.api.client.gametest.v1.FabricClientGameTest;
import net.fabricmc.fabric.api.client.gametest.v1.context.ClientGameTestContext;
import net.fabricmc.fabric.api.client.gametest.v1.context.TestSingleplayerContext;

public class TfbrTst implements FabricClientGameTest {
	void ck(boolean b, String s) {
		if (!b) throw new AssertionError(s);
		System.out.println("TFBR OK: " + s);
	}

	void shot(ClientGameTestContext c, TestSingleplayerContext s, String n, boolean on, int pct, boolean inside) {
		c.runOnClient(m -> {
			Cfg.c.on = on;
			Cfg.c.pct = pct;
		});
		s.getServer().runCommand("tp @p " + (inside ? "7.5 -61 7.5" : "0.5 -60 0.5") + " 0 " + (inside ? "0" : "-20"));
		c.waitTicks(25);
		c.takeScreenshot(n);
	}

	public void runTest(ClientGameTestContext ctx) {
		try (TestSingleplayerContext s = ctx.worldBuilder().create()) {
			ctx.waitTicks(40);
			ck(Cfg.c.on && Cfg.c.pct == 100, "defaults are on at 100%");
			ck(Cfg.bst() == 20f, "100% boost is 20");
			Cfg.c.pct = 50;
			ck(Cfg.bst() == 5f, "50% boost is 5");
			Cfg.c.pct = 0;
			ck(!Cfg.on(), "0% is off");
			Cfg.c = new Cfg();
			Cfg.c.pct = 250;
			Cfg.sv();
			ck(Cfg.c.pct == 100, "percent is clamped");
			Cfg.c = new Cfg();
			Cfg.c.pct = 30;
			Cfg.sv();
			Cfg.c = new Cfg();
			Cfg.ld();
			ck(Cfg.c.pct == 30, "config reloads from the json file");
			Cfg.c = new Cfg();
			Cfg.sv();
			s.getServer().runCommand("gamerule advance_time false");
			s.getServer().runCommand("time set 18000");
			s.getServer().runCommand("fill 5 -62 5 9 -58 9 stone hollow");
			shot(ctx, s, "1_night_off", false, 100, false);
			shot(ctx, s, "2_night_on", true, 100, false);
			shot(ctx, s, "3_night_half", true, 50, false);
			shot(ctx, s, "4_cave_off", false, 100, true);
			shot(ctx, s, "5_cave_on", true, 100, true);
			shot(ctx, s, "6_cave_half", true, 50, true);
			shot(ctx, s, "7_cave_low", true, 15, true);
			ctx.runOnClient(m -> Cfg.c = new Cfg());
			ctx.runOnClient(m -> m.gui.setScreen(CfgScr.mk(null)));
			ctx.waitTicks(10);
			ck(ctx.computeOnClient(m -> m.gui.screen().getClass().getSimpleName().equals("ClothConfigScreen")), "cloth config screen opens");
			ctx.takeScreenshot("8_cfg");
		}
	}
}