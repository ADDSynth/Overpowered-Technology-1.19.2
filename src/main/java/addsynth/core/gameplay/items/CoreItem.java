package addsynth.core.gameplay.items;

import addsynth.core.ADDSynthCore;
import net.minecraft.world.item.Item;

/** This should only be used to construct base items for {@link ADDSynthCore}! */
public class CoreItem extends Item {

  public CoreItem(){
    super(new Item.Properties().tab(ADDSynthCore.creative_tab));
  }

  public CoreItem(final Item.Properties properties){
    super(properties.tab(ADDSynthCore.creative_tab));
  }

}
