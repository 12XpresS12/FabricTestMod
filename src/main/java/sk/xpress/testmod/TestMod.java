package sk.xpress.testmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.xpress.testmod.blocks.ModBlocks;
import sk.xpress.testmod.items.ModItems;
import sk.xpress.testmod.items.StickHarvester;
import sk.xpress.testmod.listeners.PedestalInteract;

public class TestMod implements ModInitializer {

	private static final String MOD_ID = "mymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder
			.create(new Identifier(MOD_ID, "my_items"))
			.icon(() -> new ItemStack(Items.GRASS_BLOCK))
			.build();

	@Override
	public void onInitialize() {
		new PedestalInteract();
		new ModItems();
		new ModBlocks();
	}


	public static @NotNull Identifier identifier(String name) {
		return new Identifier(MOD_ID, name);
	}

}
