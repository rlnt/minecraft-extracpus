package rlnt.extracpus.util;

import net.minecraft.block.Block;
import rlnt.extracpus.Constants;

public class RegistryUtils {
    public static Block setBlockName(final Block block, final String name) {
        block.setRegistryName(Constants.MOD_ID, name).setTranslationKey(Constants.MOD_ID + "." + name);
        return block;
    }
}
