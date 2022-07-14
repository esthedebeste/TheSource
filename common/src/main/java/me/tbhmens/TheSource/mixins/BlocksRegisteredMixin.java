package me.tbhmens.TheSource.mixins;

import me.tbhmens.TheSource.TheSource;
import me.tbhmens.TheSource.util.WeightedList;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Blocks.class)
public class BlocksRegisteredMixin {
    @Inject(at = @At("TAIL"), method = "<clinit>")
    private static void registered(CallbackInfo ci) {
        TheSource.ores = new WeightedList<>() {{
            add(Blocks.DIAMOND_ORE, 1.5);
            add(Blocks.EMERALD_ORE, 2);
            add(Blocks.LAPIS_ORE, 3);
            add(Blocks.REDSTONE_ORE, 4);
            add(Blocks.COPPER_ORE, 4);
            add(Blocks.IRON_ORE, 5);
            add(Blocks.COAL_ORE, 5);
            add(Blocks.COBBLESTONE, 30);
            add(Blocks.STONE, 30);
        }};
    }
}