package addsynth.overpoweredmod.items.basic;

import addsynth.overpoweredmod.items.register.OverpoweredItem;
import net.minecraft.world.item.ItemStack;

public final class VoidCrystal extends OverpoweredItem {

  public VoidCrystal(){
  }

  @Override
  public boolean isFoil(ItemStack stack){
    return true;
  }

}
