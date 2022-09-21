package sk.xpress.testmod.blocks.anticpedestal;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.xpress.testmod.TestMod;
import sk.xpress.testmod.Utils;
import sk.xpress.testmod.blocks.ModBlocks;

public class AnticPedestalBlock extends BlockWithEntity implements BlockEntityProvider {

    protected static final VoxelShape COLLISION_SHAPE;
    protected static final VoxelShape OUTLINE_SHAPE;

    private ItemStack displayItem = null;

    private Interact interact;
    private boolean tickItemEntity = false;
    private World world;

    public AnticPedestalBlock() {
        super(FabricBlockSettings.of(Material.METAL).nonOpaque().strength(4.0f));
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    static {
        COLLISION_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 10.2, 15.0);
        OUTLINE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 10.4, 15.0);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlocks.ANTIC_PEDESTAL_BLOCK_ENTITY, AnticPedestalBlock::tick);
    }

    private static void tick(World world, BlockPos pos, BlockState state, AnticPedestalBlockEntity anticPedestalBlockEntity) {
        if(world.isClient())
            return;

        AnticPedestalBlock anticPedestalBlock = (AnticPedestalBlock) state.getBlock();
        if(anticPedestalBlock.tickItemEntity()) {
            if(anticPedestalBlock.getInteract() == Interact.ADD_ITEM) {
                anticPedestalBlockEntity.createEntity(anticPedestalBlock.getDisplayItem(), world);
                anticPedestalBlock.tickItemEntity(false);
                return;
            }

            if(anticPedestalBlock.getInteract() == Interact.REMOVE_ITEM) {
                anticPedestalBlockEntity.removeEntity();
                anticPedestalBlock.tickItemEntity(false);
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        AnticPedestalBlock anticPedestalBlock = (AnticPedestalBlock) state.getBlock();

        Utils.log("isOnBreak on " + world.isClient() + "-side");

        if(anticPedestalBlock != null && !world.isClient())
            anticPedestalBlock.entityInteract(Interact.REMOVE_ITEM, null, world);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AnticPedestalBlockEntity(pos, state);
    }


    public boolean tickItemEntity() {
        return tickItemEntity;
    }

    public void tickItemEntity(boolean shouldTick) {
        this.tickItemEntity = shouldTick;
    }

    public Interact getInteract() {
        return interact;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void entityInteract(Interact interact, @Nullable ItemStack itemStack, @NotNull World serverWorld) {
        tickItemEntity(true);
        this.interact = interact;
        this.displayItem = itemStack;
        this.world = serverWorld;
    }

    public enum Interact {
        ADD_ITEM,
        REMOVE_ITEM
    }

}
