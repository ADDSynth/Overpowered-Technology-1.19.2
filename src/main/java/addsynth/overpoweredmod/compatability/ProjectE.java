package addsynth.overpoweredmod.compatability;

import addsynth.core.compat.Compatibility;
import addsynth.overpoweredmod.OverpoweredTechnology;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import addsynth.overpoweredmod.game.reference.OverpoweredItems;
import moze_intel.projecte.api.imc.CustomEMCRegistration;
import moze_intel.projecte.api.imc.IMCMethods;
import moze_intel.projecte.api.nss.NSSItem;
import net.minecraftforge.fml.InterModComms;

public final class ProjectE {

  public static final void register_emc_values(){

    final String sender = OverpoweredTechnology.MOD_ID;
    final String mod = Compatibility.PROJECT_E.modid;
    final String message = IMCMethods.REGISTER_CUSTOM_EMC;

    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredItems.energy_crystal_shards.get()), 7282L));
    // MAYBE: I feel as though people are going to get back at me for not calculating this correctly.
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredItems.celestial_gem.get()),        65536L));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredBlocks.light_block.get()),        589824L));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredItems.void_crystal.get()),        131072L));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredBlocks.null_block.get()),         524288L));
    
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredItems.plasma.get()),               32768L));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(OverpoweredItems.unimatter.get()),           524288L));
  }

}
