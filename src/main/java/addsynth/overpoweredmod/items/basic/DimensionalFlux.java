package addsynth.overpoweredmod.items.basic;

import addsynth.overpoweredmod.items.register.OverpoweredItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

public final class DimensionalFlux extends OverpoweredItem {

  public DimensionalFlux(){
  }
  
  @Override
  public Component getName(ItemStack stack){
    return ((MutableComponent)super.getName(stack)).withStyle(ChatFormatting.LIGHT_PURPLE);
  }

}
