package rlnt.extracpus.client.render;

import appeng.block.crafting.BlockCraftingUnit;
import appeng.core.AppEng;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import rlnt.extracpus.Constants;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

class CraftingStorageModel implements IModel {

    private final static ResourceLocation RING_CORNER = AEtexture("ring_corner");
    private final static ResourceLocation RING_SIDE_HOR = AEtexture("ring_side_hor");
    private final static ResourceLocation RING_SIDE_VER = AEtexture("ring_side_ver");
    private final static ResourceLocation UNIT_BASE = AEtexture("unit_base");
    private final static ResourceLocation LIGHT_BASE = AEtexture("light_base");
    private final static ResourceLocation ACCELERATOR_LIGHT = AEtexture("accelerator_light");
    private final static ResourceLocation STORAGE_1K_LIGHT = texture("256k_light");
    private final static ResourceLocation STORAGE_4K_LIGHT = texture("1024k_light");
    private final static ResourceLocation STORAGE_16K_LIGHT = texture("4096k_light");
    private final static ResourceLocation STORAGE_64K_LIGHT = texture("16384k_light");
    private final static ResourceLocation MONITOR_BASE = AEtexture("monitor_base");
    private final static ResourceLocation MONITOR_LIGHT_DARK = AEtexture("monitor_light_dark");
    private final static ResourceLocation MONITOR_LIGHT_MEDIUM = AEtexture("monitor_light_medium");
    private final static ResourceLocation MONITOR_LIGHT_BRIGHT = AEtexture("monitor_light_bright");

    private final BlockCraftingUnit.CraftingUnitType type;

    CraftingStorageModel(BlockCraftingUnit.CraftingUnitType type) {
        this.type = type;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return ImmutableList.of(RING_CORNER, RING_SIDE_HOR, RING_SIDE_VER, UNIT_BASE, LIGHT_BASE, ACCELERATOR_LIGHT, STORAGE_1K_LIGHT, STORAGE_4K_LIGHT, STORAGE_16K_LIGHT, STORAGE_64K_LIGHT, MONITOR_BASE, MONITOR_LIGHT_DARK, MONITOR_LIGHT_MEDIUM, MONITOR_LIGHT_BRIGHT);
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        // Retrieve our textures and pass them on to the baked model
        TextureAtlasSprite ringCorner = bakedTextureGetter.apply(RING_CORNER);
        TextureAtlasSprite ringSideHor = bakedTextureGetter.apply(RING_SIDE_HOR);
        TextureAtlasSprite ringSideVer = bakedTextureGetter.apply(RING_SIDE_VER);

        switch (this.type) {
            case ACCELERATOR:
            case STORAGE_1K:
            case STORAGE_4K:
            case STORAGE_16K:
            case STORAGE_64K:
                return new CraftingStorageModelLightBaked(format, ringCorner, ringSideHor, ringSideVer, bakedTextureGetter.apply(LIGHT_BASE), getLightTexture(bakedTextureGetter, this.type));
            default:
                throw new IllegalArgumentException("Unsupported crafting unit type: " + this.type);
        }
    }

    private static TextureAtlasSprite getLightTexture(Function<ResourceLocation, TextureAtlasSprite> textureGetter, BlockCraftingUnit.CraftingUnitType type) {
        switch (type) {
            case ACCELERATOR:
                return textureGetter.apply(ACCELERATOR_LIGHT);
            case STORAGE_1K:
                return textureGetter.apply(STORAGE_1K_LIGHT);
            case STORAGE_4K:
                return textureGetter.apply(STORAGE_4K_LIGHT);
            case STORAGE_16K:
                return textureGetter.apply(STORAGE_16K_LIGHT);
            case STORAGE_64K:
                return textureGetter.apply(STORAGE_64K_LIGHT);
            default:
                throw new IllegalArgumentException("Crafting unit type " + type + " does not use a light texture.");
        }
    }

    @Override
    public IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }

    private static ResourceLocation texture(String name) {
        return new ResourceLocation(Constants.MOD_ID, "block/" + name);
    }

    private static ResourceLocation AEtexture(String name) {
        return new ResourceLocation(AppEng.MOD_ID, "blocks/crafting/" + name);
    }
}
