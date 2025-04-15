package addsynth.core.util.command;

import java.util.Optional;
import java.util.function.Predicate;
import com.mojang.datafixers.util.Either;
import net.minecraft.commands.arguments.ResourceOrTagLocationArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagKey;

public final class TagArgumentTester<T> implements Predicate<T> {

  private final T value;
  private final Optional<HolderSet.Named<T>> set;
  public final boolean isTag;
  public final String name;

  public TagArgumentTester(MinecraftServer server, ResourceKey<Registry<T>> registry_key, ResourceOrTagLocationArgument.Result<T> argument_result){
    this(server.registryAccess().registryOrThrow(registry_key), argument_result);
  }

  public TagArgumentTester(Registry<T> registry, ResourceOrTagLocationArgument.Result<T> argument_result){
    final Either<ResourceKey<T>, TagKey<T>> result = argument_result.unwrap();
    final Optional<ResourceKey<T>> key = result.left();
    final Optional<TagKey<T>>      tag = result.right();
    value = key.isPresent() ? registry.get(key.get()) : null;
    set   = tag.isPresent() ? registry.getTag(tag.get()) : Optional.empty();
    isTag = set.isPresent();
    name = argument_result.asPrintable();
  }

  @Override
  public final boolean test(T input){
    if(value != null){
      return value == input;
    }
    if(isTag){
      for(final Holder<T> h : set.get()){
        if(h.value() == input){
          return true;
        }
      }
    }
    return false;
  }

}
