package sk.xpress.testmod.blocks.anticpedestal;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import sk.xpress.testmod.Utils;
import sk.xpress.testmod.blocks.ModBlocks;

public class AnticPedestalBlockEntity extends BlockEntity {

    private ItemEntity itemEntity;
    private final BlockPos blockPos;

    private final double offsetY = 0.5;

    public AnticPedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.ANTIC_PEDESTAL_BLOCK_ENTITY, pos, state);
        this.blockPos = pos;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        if(itemEntity == null)
            return;


        Utils.log("Despawning (writeNbt)");
        super.writeNbt(itemEntity.getStack().getNbt());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if(nbt == null)
            return;

        ItemStack itemStack = ItemStack.fromNbt(nbt);

        if(itemStack == null)
            return;

        if(getWorld() == null)
            return;

        ItemEntity itemEntity = new ItemEntity(getWorld(),
                blockPos.getX(),
                blockPos.getY(),
                blockPos.getZ(),
                itemStack);
        world.spawnEntity(itemEntity);

        Utils.log("Spawning (readNBT)");
        super.readNbt(nbt);
    }

    public void createEntity(ItemStack itemStack, World world) {
        if(itemEntity == null) {
            if(world == null)
                return;

            if(world.isClient())
                return;

            double lOffsetY = offsetY + blockPos.getY();
            itemEntity = new ItemEntity(world, blockPos.getX() +.5, lOffsetY, blockPos.getZ() +.5, itemStack);
            itemEntity.setNeverDespawn();
            itemEntity.setPickupDelayInfinite();
            itemEntity.setGlowing(true);
            itemEntity.setNoGravity(true);
            itemEntity.setVelocity(0,0,0);

            world.spawnEntity(itemEntity);
        } else removeEntity();
    }

    public void removeEntity() {
        if(itemEntity == null)
            return;

        itemEntity.remove(Entity.RemovalReason.DISCARDED);
        itemEntity = null;
    }

    @Override
    public String toString() {
        return "AnticPedestalBlockEntity{" +
                "itemEntity=" + itemEntity +
                ", blockPos=" + blockPos +
                '}';
    }
}
