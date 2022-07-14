package me.tbhmens.TheSource.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static java.lang.Math.*;

@Mixin(DiggerItem.class)
public abstract class MineMixin {

    @Shadow
    @Final
    private TagKey<Block> blocks;

    @Shadow
    @Final
    protected float speed;

    private static final String mainKey = "TheSource";
    private static final String XP = "XP";

    private static CompoundTag getSourceTag(ItemStack is) {
        return is.getOrCreateTagElement(mainKey);
    }

    @Inject(at = @At("HEAD"), method = "mineBlock")
    private void mine(ItemStack is, Level level, BlockState blockState, BlockPos blockPos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        if (!blockState.is(this.blocks))
            return; // not a block that this tool can mine
        var tag = getSourceTag(is);
        int giving;
        // always give 1 xp for mining something, give more if the block drops XP (probably a valuable block)
        if (blockState.getBlock() instanceof XPBlockAccessor xpBlock)
            giving = xpBlock.getXpRange().getMaxValue();
        else
            giving = 1;
        tag.putInt(XP, tag.getInt(XP) + giving);
    }

    @Inject(at = @At("HEAD"), method = "getDestroySpeed", cancellable = true)
    private void destroySpeed(ItemStack is, BlockState blockState, CallbackInfoReturnable<Float> cir) {
        if (!blockState.is(this.blocks))
            return;
        var tag = getSourceTag(is);
        var xp = tag.getInt(XP);
        var level = (int) log10(xp); // 10 => 1, 100 => 2, 1000 => 3, etc
        if (level > 0) {
            var addedMultiplier = 1 + level * level; // same formula as efficiency
            cir.setReturnValue(this.speed + addedMultiplier);
            cir.cancel();
        }
    }
}