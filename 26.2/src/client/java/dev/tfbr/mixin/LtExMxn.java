package dev.tfbr.mixin;

import dev.tfbr.Cfg;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapRenderStateExtractor.class)
public abstract class LtExMxn {
	@Inject(method = "extract", at = @At("TAIL"))
	private void tfbr(LightmapRenderState st, float pt, CallbackInfo ci) {
		if (!Cfg.on()) return;
		st.brightness = Math.max(st.brightness, Cfg.bst());
		st.darknessEffectScale = 0f;
		st.bossOverlayWorldDarkening = 0f;
	}
}