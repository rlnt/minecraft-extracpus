package dev.rlnt.extracpus.compat;

import static dev.rlnt.extracpus.Constants.MOD_ID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@SuppressWarnings("unused")
public class UniversalRecipeFactory implements IRecipeFactory {

    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
        UniversalRecipe recipe = UniversalRecipe.factory(context, json);
        return new UniversalRecipe(
            new ResourceLocation(MOD_ID, "universal_recipe"),
            recipe.getIngredients(),
            recipe.getRecipeOutput()
        );
    }

    public static class UniversalRecipe extends ShapelessOreRecipe {

        @SuppressWarnings("WeakerAccess")
        public UniversalRecipe(@Nullable ResourceLocation group, NonNullList<Ingredient> input, ItemStack result) {
            super(group, input, result);
        }

        public static UniversalRecipe factory(JsonContext context, JsonObject json) {
            String group = JsonUtils.getString(json, "group", "");

            NonNullList<Ingredient> ings = NonNullList.create();
            for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients")) {
                JsonObject obj = ele.getAsJsonObject();
                String itemID = obj.get("item").getAsString();
                String namespace = itemID.substring(0, itemID.indexOf(":"));
                String item = itemID.substring(itemID.indexOf(":") + 1);

                if (namespace.equals("extracells") && !Loader.isModLoaded("extracells")) {
                    obj.remove("item");
                    obj.addProperty("item", "aeadditions:" + item);
                }

                ings.add(CraftingHelper.getIngredient(obj, context));
            }

            if (ings.isEmpty()) throw new JsonParseException("No ingredients for shapeless recipe");

            ItemStack itemstack = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
            return new UniversalRecipe(group.isEmpty() ? null : new ResourceLocation(group), ings, itemstack);
        }
    }
}
