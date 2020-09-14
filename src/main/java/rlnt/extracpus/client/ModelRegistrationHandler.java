package rlnt.extracpus.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import rlnt.extracpus.Constants;
import rlnt.extracpus.init.ModBlocks;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Constants.MOD_ID)
public class ModelRegistrationHandler {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(Item.getItemFromBlock(ModBlocks.CRAFTING_STORAGE_256k));
        registerModel(Item.getItemFromBlock(ModBlocks.CRAFTING_STORAGE_1024k));
        registerModel(Item.getItemFromBlock(ModBlocks.CRAFTING_STORAGE_4096k));
        registerModel(Item.getItemFromBlock(ModBlocks.CRAFTING_STORAGE_16384k));
    }

    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
