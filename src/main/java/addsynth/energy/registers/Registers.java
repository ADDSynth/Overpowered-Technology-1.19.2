package addsynth.energy.registers;

import addsynth.energy.ADDSynthEnergy;
import addsynth.energy.gameplay.EnergyBlocks;
import addsynth.energy.gameplay.machines.circuit_fabricator.CircuitFabricatorBlock;
import addsynth.energy.gameplay.machines.circuit_fabricator.CircuitFabricatorContainer;
import addsynth.energy.gameplay.machines.circuit_fabricator.recipe.CircuitFabricatorRecipeSerializer;
import addsynth.energy.gameplay.machines.compressor.CompressorBlock;
import addsynth.energy.gameplay.machines.compressor.ContainerCompressor;
import addsynth.energy.gameplay.machines.compressor.recipe.CompressorRecipeSerializer;
import addsynth.energy.gameplay.machines.electric_furnace.ContainerElectricFurnace;
import addsynth.energy.gameplay.machines.electric_furnace.ElectricFurnaceBlock;
import addsynth.energy.gameplay.machines.energy_diagnostics.EnergyDiagnosticsBlock;
import addsynth.energy.gameplay.machines.energy_storage.ContainerEnergyStorage;
import addsynth.energy.gameplay.machines.energy_storage.EnergyStorageBlock;
import addsynth.energy.gameplay.machines.energy_wire.EnergyWire;
import addsynth.energy.gameplay.machines.generator.ContainerGenerator;
import addsynth.energy.gameplay.machines.generator.GeneratorBlock;
import addsynth.energy.gameplay.machines.universal_energy_interface.ContainerUniversalEnergyInterface;
import addsynth.energy.gameplay.machines.universal_energy_interface.UniversalEnergyInterfaceBlock;
import addsynth.energy.gameplay.reference.Names;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = ADDSynthEnergy.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class Registers {

  @SubscribeEvent
  public static final void register(final RegisterEvent event){
    final ResourceKey key = event.getRegistryKey();
    if(key.equals(ForgeRegistries.Keys.BLOCKS)){
      final IForgeRegistry<Block> registry = event.getForgeRegistry();
      registry.register(Names.ENERGY_WIRE,                new EnergyWire());
      registry.register(Names.GENERATOR,                  new GeneratorBlock());
      registry.register(Names.ENERGY_STORAGE,             new EnergyStorageBlock());
      registry.register(Names.COMPRESSOR,                 new CompressorBlock());
      registry.register(Names.ELECTRIC_FURNACE,           new ElectricFurnaceBlock());
      registry.register(Names.CIRCUIT_FABRICATOR,         new CircuitFabricatorBlock());
      registry.register(Names.UNIVERSAL_ENERGY_INTERFACE, new UniversalEnergyInterfaceBlock());
      registry.register(Names.ENERGY_DIAGNOSTICS_BLOCK,   new EnergyDiagnosticsBlock());
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
      registry.register(Names.ENERGY_WIRE,                newBlockItem(EnergyBlocks.wire));
      registry.register(Names.GENERATOR,                  newBlockItem(EnergyBlocks.generator));
      registry.register(Names.ENERGY_STORAGE,             newBlockItem(EnergyBlocks.energy_storage));
      registry.register(Names.COMPRESSOR,                 newBlockItem(EnergyBlocks.compressor));
      registry.register(Names.ELECTRIC_FURNACE,           newBlockItem(EnergyBlocks.electric_furnace));
      registry.register(Names.CIRCUIT_FABRICATOR,         newBlockItem(EnergyBlocks.circuit_fabricator));
      registry.register(Names.UNIVERSAL_ENERGY_INTERFACE, newBlockItem(EnergyBlocks.universal_energy_machine));
      registry.register(Names.ENERGY_DIAGNOSTICS_BLOCK,   newBlockItem(EnergyBlocks.energy_diagnostics_block));
      
      registry.register(Names.POWER_CORE,          newItem());
      registry.register(Names.ADVANCED_POWER_CORE, newItem());
      registry.register(Names.POWER_REGULATOR,     newItem());
      registry.register(Names.CIRCUIT_TIER_1,      newItem());
      registry.register(Names.CIRCUIT_TIER_2,      newItem());
      registry.register(Names.CIRCUIT_TIER_3,      newItem());
      registry.register(Names.CIRCUIT_TIER_4,      newItem());
      registry.register(Names.CIRCUIT_TIER_5,      newItem());
      registry.register(Names.CIRCUIT_TIER_6,      newItem());
      registry.register(Names.CIRCUIT_TIER_7,      newItem());
      registry.register(Names.CIRCUIT_TIER_8,      newItem());
      registry.register(Names.CIRCUIT_TIER_9,      newItem());
    }
    if(key.equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)){
      final IForgeRegistry<BlockEntityType> registry = event.getForgeRegistry();
      Tiles.ENERGY_WIRE.register(registry);
      Tiles.GENERATOR.register(registry);
      Tiles.ENERGY_CONTAINER.register(registry);
      Tiles.COMPRESSOR.register(registry);
      Tiles.ELECTRIC_FURNACE.register(registry);
      Tiles.CIRCUIT_FABRICATOR.register(registry);
      Tiles.UNIVERSAL_ENERGY_INTERFACE.register(registry);
      Tiles.ENERGY_DIAGNOSTICS_BLOCK.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.MENU_TYPES)){
      final IForgeRegistry<MenuType> registry = event.getForgeRegistry();
      registry.register(Names.GENERATOR,                  IForgeMenuType.create(ContainerGenerator::new));
      registry.register(Names.ENERGY_STORAGE,             IForgeMenuType.create(ContainerEnergyStorage::new));
      registry.register(Names.COMPRESSOR,                 IForgeMenuType.create(ContainerCompressor::new));
      registry.register(Names.ELECTRIC_FURNACE,           IForgeMenuType.create(ContainerElectricFurnace::new));
      registry.register(Names.CIRCUIT_FABRICATOR,         IForgeMenuType.create(CircuitFabricatorContainer::new));
      registry.register(Names.UNIVERSAL_ENERGY_INTERFACE, IForgeMenuType.create(ContainerUniversalEnergyInterface::new));
    }
    if(key.equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS)){
      final IForgeRegistry<RecipeSerializer> registry = event.getForgeRegistry();
      registry.register(Names.COMPRESSOR,         new CompressorRecipeSerializer());
      registry.register(Names.CIRCUIT_FABRICATOR, new CircuitFabricatorRecipeSerializer());
    }
  }

  private static final Item newItem(){
    return new Item(new Item.Properties().tab(ADDSynthEnergy.creative_tab));
  }

  private static final BlockItem newBlockItem(final RegistryObject<Block> block){
    return new BlockItem(block.get(), new Item.Properties().tab(ADDSynthEnergy.creative_tab));
  }

}
