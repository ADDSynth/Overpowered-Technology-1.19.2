package addsynth.overpoweredmod.items.tools;

import addsynth.core.game.item.constants.ToolConstants;
import addsynth.overpoweredmod.assets.CreativeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;

public final class OverpoweredShovel extends ShovelItem {

  public OverpoweredShovel(){
    super(OverpoweredTiers.CELESTIAL, ToolConstants.axe_damage, ToolConstants.axe_speed, new Item.Properties().tab(CreativeTabs.creative_tab));
  }

  @Override
  public boolean isEnchantable(ItemStack stack){
    return false;
  }

  @Override
  public Rarity getRarity(ItemStack stack){
    return Rarity.RARE;
  }
}
