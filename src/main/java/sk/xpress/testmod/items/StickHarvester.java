package sk.xpress.testmod.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import sk.xpress.testmod.TestMod;
import sk.xpress.testmod.Utils;
import sk.xpress.testmod.guis.TestGui;
import sk.xpress.testmod.guis.TestScreen;

public class StickHarvester extends Item {

    private static int HARVESTED = 0;

    public StickHarvester() {
        super(new Settings().maxCount(4).group(TestMod.ITEM_GROUP).rarity(Rarity.UNCOMMON));
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        if(context.getPlayer() == null)
            return null;

        if (!context.getWorld().isClient()) {
            if(context.getWorld().getBlockState(context.getBlockPos()).isOf(Blocks.GRASS)) {
                context.getWorld().breakBlock(context.getBlockPos(), false);
                context.getPlayer().playSound(SoundEvents.BLOCK_GRASS_HIT, 1f , 1f);
                context.getPlayer().swingHand(Hand.MAIN_HAND);

                { // spawn item "WHEAT SEEDS"
                    ItemStack stack = new ItemStack(Items.WHEAT_SEEDS);
                    PlayerEntity player = context.getPlayer();
                    ItemEntity itemEntity = new ItemEntity(player.world,
                            context.getBlockPos().getX(),
                            context.getBlockPos().getY(),
                            context.getBlockPos().getZ(),
                            stack);
                    Utils.log("World_ " + player.world.asString());
                    player.world.spawnEntity(itemEntity);
                }
        }


            if(context.getWorld().isClient())
                HARVESTED++;

            //MinecraftClient.getInstance().textRenderer.draw("OWO FOrnITEnITE", 0, 0, 0xffffff);
            return ActionResult.PASS;
        }

        return ActionResult.FAIL;
    }

    public static int getHarvestedAmount() {
        return HARVESTED;
    }
}
