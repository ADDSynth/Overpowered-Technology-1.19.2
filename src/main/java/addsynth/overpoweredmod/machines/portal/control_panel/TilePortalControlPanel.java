package addsynth.overpoweredmod.machines.portal.control_panel;

import java.util.ArrayList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import addsynth.core.util.game.MinecraftUtility;
import addsynth.core.util.math.block.BlockArea;
import addsynth.core.util.network.NetworkUtil;
import addsynth.core.util.world.WorldUtil;
import addsynth.energy.lib.tiles.machines.IAutoShutoff;
import addsynth.energy.lib.tiles.machines.MachineState;
import addsynth.energy.lib.tiles.machines.TileManualMachine;
import addsynth.overpoweredmod.config.Config;
import addsynth.overpoweredmod.config.MachineValues;
import addsynth.overpoweredmod.game.NetworkHandler;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import addsynth.overpoweredmod.game.reference.OverpoweredItems;
import addsynth.overpoweredmod.machines.portal.frame.TilePortalFrame;
import addsynth.overpoweredmod.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class TilePortalControlPanel extends TileManualMachine implements IAutoShutoff, MenuProvider {

  private final Block data_cable;
  private final Block portal_frame;
  private final Block iron_frame;

  private static final int containers = 8;
  private boolean[] portal_items = new boolean[containers];
  private boolean valid_portal = false;
  @Nonnull
  private PortalMessage message = PortalMessage.NO_DATA_CABLE;
  private ArrayList<BlockPos> portal_frames = new ArrayList<>(containers);
  private Direction.Axis axis;
  private BlockPos lowest_portal_frame;
  private boolean auto_shutoff = true;

  // Gui methods:
  public final Component getMessage(){ return message.getMessage(); }
  public final boolean getPortalItem(final int index){ return portal_items[index]; }
  public final boolean isValid(){ return valid_portal; }
  @Override
  public final boolean getAutoShutoff(){ return auto_shutoff; }

  public final void setData(final boolean[] items, final PortalMessage message, final boolean valid_portal){
    this.portal_items = items;
    this.message = message;
    this.valid_portal = valid_portal;
  }

  public TilePortalControlPanel(BlockPos position, BlockState blockstate){
    super(Tiles.PORTAL_CONTROL_PANEL.get(), position, blockstate, MachineValues.portal);
    int i;
    for(i = 0; i < containers; i++){
      portal_items[i] = false;
    }
    data_cable   = OverpoweredBlocks.data_cable.get();
    portal_frame = OverpoweredBlocks.portal_frame.get();
    iron_frame   = OverpoweredBlocks.iron_frame_block.get();
  }

  @Override
  public final void load(final CompoundTag nbt){
    super.load(nbt);
    auto_shutoff = nbt.getBoolean("Auto Shutoff");
  }

  @Override
  protected final void saveAdditional(final CompoundTag nbt){
    super.saveAdditional(nbt);
    nbt.putBoolean("Auto Shutoff", auto_shutoff);
  }

  @Override
  public final void toggle_auto_shutoff(){
    auto_shutoff = !auto_shutoff;
    changed = true;
  }

  /**
   *  Call this function when the gui opens to run the portal check algorithm, which will check proper construction
   *  and check if the portal frames have the proper items in them.
   */
  public final void check_portal(final boolean creative_mode){
    portal_frames.clear();
    valid_portal = false;
    portal_items[0] = false;
    portal_items[1] = false;
    portal_items[2] = false;
    portal_items[3] = false;
    portal_items[4] = false;
    portal_items[5] = false;
    portal_items[6] = false;
    portal_items[7] = false;
    axis = null;
    
    evaluate_portal_construction(creative_mode);
    
    final SyncPortalDataMessage message = new SyncPortalDataMessage(worldPosition, portal_items, this.message, valid_portal);
    NetworkUtil.send_to_clients_in_world(NetworkHandler.INSTANCE, level, message);
  }

  private final void evaluate_portal_construction(final boolean player_is_in_creative_mode){
    if(portal_search_algorithm(this.worldPosition, new ArrayList<BlockPos>(30)) == false){
      message = PortalMessage.NO_DATA_CABLE;
      return;
    }
    if(portal_frames.size() < containers){
      message = PortalMessage.REQUIRE_PORTAL_FRAMES;
      return;
    }
    if(portal_frames.size() > containers){
      message = PortalMessage.TOO_MANY_PORTAL_FRAMES;
      return;
    }
    if(check_portal_construction() == false){
      message = PortalMessage.PORTAL_NOT_CONSTRUCTED;
      return;
    }
    if(check_for_obstruction_inside_portal_frame() == false){
      message = PortalMessage.OBSTRUCTED;
      return;
    }
    if(state != MachineState.RUNNING){
      message = PortalMessage.OFF;
      return;
    }
    if(player_is_in_creative_mode){
      valid_portal = true;
      message = PortalMessage.CREATIVE_MODE;
      return;
    }
    if( ( portal_items[0] && portal_items[1] && portal_items[2] && portal_items[3] &&
          portal_items[4] && portal_items[5] && portal_items[6] && portal_items[7] ) == false){
      message = PortalMessage.INCORRECT_ITEMS;
      return;
    }
    if(energy.needsEnergy()){
      message = PortalMessage.NEEDS_ENERGY;
      return;
    }
    valid_portal = true;
    message = PortalMessage.PORTAL_READY;
  }

  private final boolean portal_search_algorithm(BlockPos from, ArrayList<BlockPos> searched){
    boolean found = false;
    BlockPos position;
    Block block;
    for(final Direction side : Direction.values()){
      position = from.relative(side);
      if(searched.contains(position) == false){
        searched.add(position);
        block = level.getBlockState(position).getBlock();
        if(block == data_cable || block == portal_frame || block == iron_frame){
          found = true;
          if(block == portal_frame){
            final TilePortalFrame portal_frame = (TilePortalFrame)level.getBlockEntity(position); // we already know this is a portal frame block, its okay to do this.
            if(portal_frame != null){
              portal_frames.add(position);
              final int item = portal_frame.check_item();
              if(item >= 0){
                portal_items[item] = true;
              }
            }
          }
          portal_search_algorithm(position, searched);
        }
      }
    }
    return found;
  }

  private final boolean check_portal_construction(){
    int i;
    boolean portal_frames_valid = true;
    for(i = 0; i < containers; i++){
      if(portal_frames.get(i) == null){
        portal_frames_valid = false;
        break;
      }
    }
    
    if(portal_frames_valid){
      // get lowest portal frame
      lowest_portal_frame = BlockArea.getMinimumPosition(portal_frames);
      // the portal could be constructed either along the X axis or Z axis.
      final int x = lowest_portal_frame.getX();
      final int y = lowest_portal_frame.getY();
      final int z = lowest_portal_frame.getZ();
      if(check_portal_frame(x,  y,  z,level) && check_portal_frame(x+2,y,  z,level) &&
         check_portal_frame(x+4,y,  z,level) && check_portal_frame(x,  y+2,z,level) &&
         check_portal_frame(x+4,y+2,z,level) && check_portal_frame(x  ,y+4,z,level) &&
         check_portal_frame(x+2,y+4,z,level) && check_portal_frame(x+4,y+4,z,level)){
           axis = Direction.Axis.X;
           return true;
      }
      if(check_portal_frame(x,y,  z,  level) && check_portal_frame(x,y,  z+2,level) &&
         check_portal_frame(x,y,  z+4,level) && check_portal_frame(x,y+2,z,  level) &&
         check_portal_frame(x,y+2,z+4,level) && check_portal_frame(x,y+4,z,  level) &&
         check_portal_frame(x,y+4,z+2,level) && check_portal_frame(x,y+4,z+4,level)){
           axis = Direction.Axis.Z;
           return true;
      }
    }
    
    return false;
  }

  private static final boolean check_portal_frame(int x, int y, int z, Level world){
    return world.getBlockState(new BlockPos(x,y,z)).getBlock() == OverpoweredBlocks.portal_frame.get();
  }

  @SuppressWarnings("incomplete-switch")
  private final boolean check_for_obstruction_inside_portal_frame(){
    if(axis == null){
      return false;
    }
    boolean pass = true;
    final int min_x = lowest_portal_frame.getX() + 1;
    final int min_y = lowest_portal_frame.getY() + 1;
    final int min_z = lowest_portal_frame.getZ() + 1;
    int x;
    int y;
    int z;
    BlockPos position;
    switch(axis){
    case X:
      z = lowest_portal_frame.getZ();
      for(y = min_y; (y <= min_y + 2 && pass); y++){
        for(x = min_x; (x <= min_x + 2 && pass); x++){
          position = new BlockPos(x, y, z);
          if(level.getBlockState(position).getBlock() != Blocks.AIR){ // TODO: Check if material is AIR, not the specific Air Block.
            pass = false;
          }
        }
      }
      break;
    case Z:
      x = lowest_portal_frame.getX();
      for(y = min_y; (y <= min_y + 2 && pass); y++){
        for(z = min_z; (z <= min_z + 2 && pass); z++){
          position = new BlockPos(x, y, z);
          if(level.getBlockState(position).getBlock() != Blocks.AIR){
            pass = false;
          }
        }
      }
      break;
    }
    return pass;
  }

  @SuppressWarnings("incomplete-switch")
  public final void generate_portal(){
    if(valid_portal){
    
      for(BlockPos position : portal_frames){
        final TilePortalFrame tile = MinecraftUtility.getTileEntity(position, level, TilePortalFrame.class);
        if(tile != null){
          tile.getInputInventory().setStackInSlot(0, ItemStack.EMPTY);
        }
      }
      
      energy.setEmpty();
      
      int start_x, start_y, start_z;
      int x;
      int y;
      int z;
      int center;
      final BlockState portal = OverpoweredBlocks.portal.get().defaultBlockState();
      switch(axis){
      case X:
        start_x = lowest_portal_frame.getX()+1;
        start_y = lowest_portal_frame.getY()+1;
        start_z = lowest_portal_frame.getZ();
        center = lowest_portal_frame.getX()+2;
        z = start_z;
        for(y = start_y; y < start_y + 3; y++){
          for(x = start_x; x < start_x + 3; x++){
            level.setBlockAndUpdate(new BlockPos(x,y,z), portal); // .withProperty(PortalEnergyBlock.AXIS, EnumFacing.Axis.X));
          }
        }
        if(Config.teleport_to_unknown_dimension.get() == false){
        }
        WorldUtil.spawnItemStack(level, center, start_y, start_z, new ItemStack(OverpoweredItems.dimensional_flux.get(), 1), false);
        break;
      case Z:
        start_x = lowest_portal_frame.getX();
        start_y = lowest_portal_frame.getY()+1;
        start_z = lowest_portal_frame.getZ()+1;
        center = lowest_portal_frame.getZ()+2;
        x = start_x;
        for(y = start_y; y < start_y + 3; y++){
          for(z = start_z; z < start_z + 3; z++){
            level.setBlockAndUpdate(new BlockPos(x,y,z), portal); // .withProperty(PortalEnergyBlock.AXIS, EnumFacing.Axis.Z));
          }
        }
        if(Config.teleport_to_unknown_dimension.get() == false){
        }
        WorldUtil.spawnItemStack(level, start_x, start_y, center, new ItemStack(OverpoweredItems.dimensional_flux.get(), 1), false);
        break;
      }

      portal_items[0] = false;
      portal_items[1] = false;
      portal_items[2] = false;
      portal_items[3] = false;
      portal_items[4] = false;
      portal_items[5] = false;
      portal_items[6] = false;
      portal_items[7] = false;
      message = PortalMessage.OBSTRUCTED;
      valid_portal = false;

      if(auto_shutoff){
        power_switch = false;
        turn_off();
      }
      
      final SyncPortalDataMessage message = new SyncPortalDataMessage(worldPosition, portal_items, this.message, valid_portal);
      NetworkUtil.send_to_clients_in_world(NetworkHandler.INSTANCE, level, message);
      
    }
  }

  @Override
  @Nullable
  public AbstractContainerMenu createMenu(int id, Inventory player_inventory, Player player){
    return new ContainerPortalControlPanel(id, player_inventory, this);
  }

  @Override
  public Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
