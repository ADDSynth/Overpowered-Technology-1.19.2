package addsynth.core.game.registry;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class BlockHolder {

  private final ResourceLocation name;
  private final RegistryObject<Block> block;
  private final Supplier<Block> constructor;
  private final RegistryObject<Item> item;
  private final Item.Properties item_properties;
  
  public BlockHolder(ResourceLocation name, Supplier<Block> constructor, Item.Properties item_properties){
    this.name = name;
    block = RegistryObject.create(name, ForgeRegistries.BLOCKS);
    this.constructor = constructor;
    item = RegistryObject.create(name, ForgeRegistries.ITEMS);
    this.item_properties = item_properties;
  }

  public final Block getBlock(){
    return block.get();
  }
  
  public final BlockItem getItem(){
    return (BlockItem)item.get();
  }

  public final void registerBlock(final IForgeRegistry<Block> registry){
    registry.register(name, constructor.get());
  }
  
  public final void registerItem(final IForgeRegistry<Item> registry){
    registry.register(name, new BlockItem(block.get(), item_properties));
  }

}
