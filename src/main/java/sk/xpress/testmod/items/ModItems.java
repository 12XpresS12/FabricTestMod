package sk.xpress.testmod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import sk.xpress.testmod.TestMod;
import sk.xpress.testmod.blocks.ModBlocks;

public class ModItems {

    public static Item STICK_HARVESTER = registerItem("stick_harvester", new StickHarvester());
    public static Item ANTIC_PEDESTAL_BLOCKITEM = registerItem("antic_pedestal", new BlockItem(ModBlocks.ANTIC_PEDESTAL, new FabricItemSettings().group(TestMod.ITEM_GROUP)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, TestMod.identifier(name), item);
    }
}
