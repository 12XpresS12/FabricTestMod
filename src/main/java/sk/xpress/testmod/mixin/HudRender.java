package sk.xpress.testmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sk.xpress.testmod.items.StickHarvester;

@Mixin(InGameHud.class)
public class HudRender {

    @Shadow private int scaledWidth;

    @Inject(method = "render", at = @At("RETURN"))
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Harvested wheat: " + StickHarvester.getHarvestedAmount(), (float) this.scaledWidth/2, 10, -1);

    }
}