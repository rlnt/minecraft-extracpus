package rlnt.extracpus.handler;

import appeng.block.crafting.BlockCraftingUnit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rlnt.extracpus.Constants;
import rlnt.extracpus.ExtraCPUs;
import rlnt.extracpus.block.BlockCraftingStorageEx;
import rlnt.extracpus.init.ModBlocks;
import rlnt.extracpus.util.RegistryUtils;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class RegistrationHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final Item[] itemBlocks = {
                new ItemBlock(ModBlocks.CRAFTING_STORAGE_256k).setRegistryName(ModBlocks.CRAFTING_STORAGE_256k.getRegistryName()),
                new ItemBlock(ModBlocks.CRAFTING_STORAGE_1024k).setRegistryName(ModBlocks.CRAFTING_STORAGE_1024k.getRegistryName()),
                new ItemBlock(ModBlocks.CRAFTING_STORAGE_4096k).setRegistryName(ModBlocks.CRAFTING_STORAGE_4096k.getRegistryName()),
                new ItemBlock(ModBlocks.CRAFTING_STORAGE_16384k).setRegistryName(ModBlocks.CRAFTING_STORAGE_16384k.getRegistryName())
        };

        event.getRegistry().registerAll(itemBlocks);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final Block[] blocks = {
                RegistryUtils.setBlockName(new BlockCraftingStorageEx(BlockCraftingUnit.CraftingUnitType.STORAGE_1K), "crafting_storage_256k").setCreativeTab(ExtraCPUs.MOD_TAB),
                RegistryUtils.setBlockName(new BlockCraftingStorageEx(BlockCraftingUnit.CraftingUnitType.STORAGE_4K), "crafting_storage_1024k").setCreativeTab(ExtraCPUs.MOD_TAB),
                RegistryUtils.setBlockName(new BlockCraftingStorageEx(BlockCraftingUnit.CraftingUnitType.STORAGE_16K), "crafting_storage_4096k").setCreativeTab(ExtraCPUs.MOD_TAB),
                RegistryUtils.setBlockName(new BlockCraftingStorageEx(BlockCraftingUnit.CraftingUnitType.STORAGE_64K), "crafting_storage_16384k").setCreativeTab(ExtraCPUs.MOD_TAB)
        };

        event.getRegistry().registerAll(blocks);
    }
}
