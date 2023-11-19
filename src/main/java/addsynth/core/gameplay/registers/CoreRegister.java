package addsynth.core.gameplay.registers;

import addsynth.core.ADDSynthCore;
import addsynth.core.gameplay.Core;
import addsynth.core.gameplay.Trophy;
import addsynth.core.gameplay.blocks.CautionBlock;
import addsynth.core.gameplay.blocks.TrophyBlock;
import addsynth.core.gameplay.items.CoreItem;
import addsynth.core.gameplay.music_box.MusicBox;
import addsynth.core.gameplay.music_box.MusicSheet;
import addsynth.core.gameplay.reference.Names;
import addsynth.core.gameplay.team_manager.TeamManagerBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = ADDSynthCore.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class CoreRegister {

  @SubscribeEvent
  public static final void register_blocks(final RegisterEvent event){
    final ResourceKey key = event.getRegistryKey();
    if(key.equals(ForgeRegistries.Keys.BLOCKS)){
      final IForgeRegistry<Block> registry = event.getForgeRegistry();
      registry.register(Names.CAUTION_BLOCK,   new CautionBlock());
      registry.register(Names.MUSIC_BOX,       new MusicBox());
      registry.register(Names.TEAM_MANAGER,    new TeamManagerBlock());
      registry.register(Names.BRONZE_TROPHY,   new TrophyBlock());
      registry.register(Names.SILVER_TROPHY,   new TrophyBlock());
      registry.register(Names.GOLD_TROPHY,     new TrophyBlock());
      registry.register(Names.PLATINUM_TROPHY, new TrophyBlock());
      // registry.register(Names.TEST_BLOCK, new TestBlock());
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
      registry.register(Names.CAUTION_BLOCK,   newBlockItem(Core.caution_block));
      registry.register(Names.MUSIC_BOX,       newBlockItem(Core.music_box));
      registry.register(Names.MUSIC_SHEET,     new MusicSheet());
      registry.register(Names.TEAM_MANAGER,    newBlockItem(Core.team_manager));
      registry.register(Names.TROPHY_BASE,     new CoreItem());
      registry.register(Names.BRONZE_TROPHY,   newBlockItem(Trophy.bronze));
      registry.register(Names.SILVER_TROPHY,   newBlockItem(Trophy.silver));
      registry.register(Names.GOLD_TROPHY,     newBlockItem(Trophy.gold));
      registry.register(Names.PLATINUM_TROPHY, newBlockItem(Trophy.platinum));
      // registry.register(Names.TEST_BLOCK, newBlockItem(Core.test_block));
    }
    if(key.equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)){
      final IForgeRegistry<BlockEntityType> registry = event.getForgeRegistry();
      Tiles.MUSIC_BOX.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.MENU_TYPES)){
      final IForgeRegistry<MenuType> registry = event.getForgeRegistry();
    }
  }

  private static final BlockItem newBlockItem(final RegistryObject<Block> block){
    return new BlockItem(block.get(), new Item.Properties().tab(ADDSynthCore.creative_tab));
  }

}
