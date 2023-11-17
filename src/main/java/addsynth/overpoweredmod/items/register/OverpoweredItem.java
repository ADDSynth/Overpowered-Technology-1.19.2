package addsynth.overpoweredmod.items.register;

import addsynth.overpoweredmod.assets.CreativeTabs;
import net.minecraft.world.item.Item;

public class OverpoweredItem extends Item {

  public OverpoweredItem(){
    super(new Item.Properties().tab(CreativeTabs.creative_tab));
  }

  public OverpoweredItem(final Item.Properties properties){
    super(properties);
  }

}
