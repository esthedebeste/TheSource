package me.tbhmens.TheSource.mixins;

import me.tbhmens.TheSource.TheSource;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowableFluid.class)
public abstract class WaterMixin {
    @Shadow
    public abstract Fluid getStill();

    @Inject(at = @At("HEAD"), method = "flow", cancellable = true)
    private void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (!state.isAir()) return;
        BlockState second = world.getBlockState(pos.add(direction.getVector()));
        if ((this.getStill() == Fluids.WATER && second.getFluidState().isEqualAndStill(Fluids.LAVA)) || (this.getStill() == Fluids.LAVA && second.getFluidState().isEqualAndStill(Fluids.WATER))) {
            ci.cancel();
            world.setBlockState(pos, TheSource.ores.getRandom().getDefaultState(), 3);
        }
    }
}