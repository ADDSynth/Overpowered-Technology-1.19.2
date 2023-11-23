package addsynth.energy.compat.jei;

import addsynth.energy.gameplay.EnergyBlocks;
import addsynth.energy.gameplay.machines.circuit_fabricator.recipe.CircuitFabricatorRecipe;
import addsynth.energy.gameplay.reference.GuiReference;
import addsynth.energy.gameplay.reference.Names;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;

public final class CircuitRecipeCategory implements IRecipeCategory<CircuitFabricatorRecipe> {

  public static final RecipeType<CircuitFabricatorRecipe> type = new RecipeType<>(Names.CIRCUIT_FABRICATOR, CircuitFabricatorRecipe.class);
  private final IDrawable background;
  private final IDrawable icon;

  public CircuitRecipeCategory(final IGuiHelper gui_helper){
    final IDrawableBuilder drawable_builder = gui_helper.drawableBuilder(GuiReference.circuit_fabricator, 153, 67, 140, 54);
    drawable_builder.setTextureSize(384, 256);
    background = drawable_builder.build();
    icon = gui_helper.createDrawableItemStack(new ItemStack(EnergyBlocks.circuit_fabricator.get()));
  }

  @Override
  public final RecipeType<CircuitFabricatorRecipe> getRecipeType(){
    return type;
  }

  @Override
  public Component getTitle(){
    return Component.translatable(EnergyBlocks.circuit_fabricator.get().getDescriptionId());
  }

  @Override
  public IDrawable getBackground(){
    return background;
  }

  @Override
  public IDrawable getIcon(){
    return icon;
  }

  @Override
  public void setRecipe(IRecipeLayoutBuilder builder, CircuitFabricatorRecipe recipe, IFocusGroup focuses){
    final NonNullList<Ingredient> ingredients = recipe.getIngredients();
    builder.addSlot(RecipeIngredientRole.INPUT,    8,  8).addIngredients(ingredients.get(0));
    builder.addSlot(RecipeIngredientRole.INPUT,   26,  8).addIngredients(ingredients.get(1));
    builder.addSlot(RecipeIngredientRole.INPUT,   44,  8).addIngredients(ingredients.get(2));
    builder.addSlot(RecipeIngredientRole.INPUT,   62,  8).addIngredients(ingredients.get(3));
    builder.addSlot(RecipeIngredientRole.INPUT,    8, 26).addIngredients(ingredients.get(4));
    builder.addSlot(RecipeIngredientRole.INPUT,   26, 26).addIngredients(ingredients.get(5));
    builder.addSlot(RecipeIngredientRole.INPUT,   44, 26).addIngredients(ingredients.get(6));
    builder.addSlot(RecipeIngredientRole.INPUT,   62, 26).addIngredients(ingredients.get(7));
    builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 17).addItemStack(recipe.getResultItem());
  }

  @Override
  @Nullable
  public final ResourceLocation getRegistryName(final CircuitFabricatorRecipe recipe){
    return recipe.getId();
  }

}
