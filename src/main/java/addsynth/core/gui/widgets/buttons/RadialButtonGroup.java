package addsynth.core.gui.widgets.buttons;

import java.util.function.Consumer;
import addsynth.core.gameplay.reference.GuiReference;
import addsynth.core.gui.util.GuiUtil;
import addsynth.core.gui.widgets.WidgetUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

public final class RadialButtonGroup extends AbstractButton {

  public static final int radial_gui_size = 12;
  public static final int line_spacing = 3;

  private static final int radial_texture_x = 216;
  private static final int radial_texture_y = 0;
  private static final int radial_selected_texture_y = 40;
  private static final int radial_texture_size = 40;
  
  private static final int line_height = radial_gui_size + line_spacing;

  private final Component[] options;
  private int buttons;
  public int option_selected = 0;
  private int i;
  private final int[] button_height;

  private Consumer<Integer> onSelectionChanged;

  public RadialButtonGroup(int x, int y, Component[] options){
    this(x, y, options, 0, null);
  }
  
  public RadialButtonGroup(int x, int y, Component[] options, int default_option){
    this(x, y, options, default_option, null);
  }
  
  public RadialButtonGroup(int x, int y, Component[] options, Consumer<Integer> responder){
    this(x, y, options, 0, responder);
  }
  public RadialButtonGroup(int x, int y, Component[] options, int default_option, Consumer<Integer> responder){
    super(x, y, radial_gui_size, line_height*options.length, Component.empty());
    this.options = options;
    this.option_selected = default_option;
    this.onSelectionChanged = responder;
    
    buttons = options.length;
    button_height = new int[buttons];
    for(i = 0; i < buttons; i++){
      button_height[i] = this.y + (line_height * i);
    }
  }

  @Override
  public void renderButton(PoseStack matrix, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_){
    @SuppressWarnings("resource")
    final Minecraft minecraft = Minecraft.getInstance();
    final Font font = minecraft.font;
    WidgetUtil.common_button_render_setup(GuiReference.widgets);
    for(i = 0; i < buttons; i++){
      blit(matrix, x, y + (line_height*i), radial_gui_size, radial_gui_size, radial_texture_x, i == option_selected ? radial_selected_texture_y : radial_texture_y, radial_texture_size, radial_texture_size, 256, 256);
    }
    for(i = 0; i < buttons; i++){
      font.draw(matrix, options[i], x + 16, y + (line_height*i) + 2, GuiUtil.text_color);
    }
  }

  @Override
  public void onClick(double mouse_x, double mouse_y){
    for(i = 0; i < buttons; i++){
      if(mouse_y >= button_height[i] && mouse_y <= button_height[i] + radial_gui_size){
        if(i != option_selected){
          option_selected = i;
          if(onSelectionChanged != null){
            onSelectionChanged.accept(i);
          }
        }
        break;
      }
    }
  }

  @Override
  public void onPress(){
  }

  // public final void setResponder(final Consumer<Integer> responder){
  //   this.onSelected = responder;
  // }

  public final int getSelectedOption(){
    return option_selected;
  }

  @Override
  public void playDownSound(SoundManager p_playDownSound_1_){
  }

  @Override
  public void updateNarration(NarrationElementOutput p_169152_){
  }

}
