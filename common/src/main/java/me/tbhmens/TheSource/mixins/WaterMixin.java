package me.tbhmens.TheSource.mixins;

import me.tbhmens.TheSource.TheSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowingFluid.class)
public abstract class WaterMixin {
    @Shadow
    public abstract Fluid getSource();

    @Inject(at = @At("HEAD"), method = "spreadTo", cancellable = true)
    private void flow(LevelAccessor world, BlockPos pos, BlockState state, Direction dir, FluidState fluidState, CallbackInfo ci) {
        if (!state.isAir()) return;
        BlockState second = world.getBlockState(pos.offset(dir.getNormal()));
        if ((this.getSource() == Fluids.WATER && second.getFluidState().isSourceOfType(Fluids.LAVA)) || (this.getSource() == Fluids.LAVA && second.getFluidState().isSourceOfType(Fluids.WATER))) {
            ci.cancel();
            world.setBlock(pos, TheSource.ores.getRandom().defaultBlockState(), 3);
        }
    }
}