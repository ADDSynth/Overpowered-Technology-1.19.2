package addsynth.material.items;

import addsynth.material.ADDSynthMaterials;
import net.minecraft.world.item.Item;

public final class MaterialItem extends Item {

  public MaterialItem(){
    super(new Item.Properties().tab(ADDSynthMaterials.creative_tab));
  }

  public MaterialItem(final Item.Properties properties){
    super(properties.tab(ADDSynthMaterials.creative_tab));
  }

}
