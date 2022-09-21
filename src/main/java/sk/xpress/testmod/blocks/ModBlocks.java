package sk.xpress.testmod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import sk.xpress.testmod.TestMod;
import sk.xpress.testmod.blocks.anticpedestal.AnticPedestalBlock;
import sk.xpress.testmod.blocks.anticpedestal.AnticPedestalBlockEntity;

public class ModBlocks {

    // BLOCK
    public static Block ANTIC_PEDESTAL = registerBlock("antic_pedestal", new AnticPedestalBlock());

    // BLOCK ENTITY

    public static final BlockEntityType<AnticPedestalBlockEntity> ANTIC_PEDESTAL_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            TestMod.identifier("antic_pedestal_block_entity"),
            FabricBlockEntityTypeBuilder.create(AnticPedestalBlockEntity::new, ANTIC_PEDESTAL).build()
    );
    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, TestMod.identifier(name), block);
    }

}
