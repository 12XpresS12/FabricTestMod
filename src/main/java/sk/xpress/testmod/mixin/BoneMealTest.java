package sk.xpress.testmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sk.xpress.testmod.items.StickHarvester;

@Mixin(BoneMealItem.class)
public class BoneMealTest {

    //public static boolean useOnGround(ItemStack stack, World world, BlockPos blockPos, @Nullable Direction facing) {
    @Inject(method = "useOnGround", at = @At("RETURN"))
    private static void useOnGround(ItemStack stack, World world, @NotNull BlockPos blockPos, Direction facing, CallbackInfoReturnable<Boolean> cir) {
        System.out.println("using on ground!");
        System.out.println(blockPos.toString());
    }
}
