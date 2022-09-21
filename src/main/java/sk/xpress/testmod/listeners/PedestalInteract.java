package sk.xpress.testmod.listeners;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import sk.xpress.testmod.Utils;
import sk.xpress.testmod.blocks.ModBlocks;
import sk.xpress.testmod.blocks.anticpedestal.AnticPedestalBlock;
import sk.xpress.testmod.blocks.anticpedestal.AnticPedestalBlockEntity;

public class PedestalInteract {

    public PedestalInteract() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            if(!state.isOf(ModBlocks.ANTIC_PEDESTAL))
                return ActionResult.PASS;

            if (player.isSpectator())
                return ActionResult.CONSUME;

            //if(world.isClient())
             //   return ActionResult.CONSUME;

            //Utils.log((world.isClient() ? "Client" : "Server") + "-side [B-IF]");
            if(world.isClient()) {
                return ActionResult.SUCCESS;
            }
            //Utils.log((world.isClient() ? "Client" : "Server") + "-side [A-IF]");

            AnticPedestalBlock anticPedestalBlock = (AnticPedestalBlock) state.getBlock();
            if(anticPedestalBlock == null)
                return ActionResult.CONSUME;

            if(player.getMainHandStack().isEmpty()) {
                anticPedestalBlock.entityInteract(AnticPedestalBlock.Interact.REMOVE_ITEM, null, world);
            } else {
                ItemStack itemStack = player.getMainHandStack();
                if(itemStack == null)
                    itemStack = player.getOffHandStack();

                anticPedestalBlock.entityInteract(AnticPedestalBlock.Interact.ADD_ITEM, itemStack, world);
            }


            return ActionResult.SUCCESS;
        });
    }
}
