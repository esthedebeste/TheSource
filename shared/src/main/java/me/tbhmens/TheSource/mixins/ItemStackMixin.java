package me.tbhmens.TheSource.mixins;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static java.lang.Math.min;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract void setDamageValue(int damage);

    @Shadow
    public abstract int getMaxDamage();

    @Inject(at = @At(value = "RETURN"), method = "hurt", cancellable = true)
    private void mine(int i, RandomSource randomSource, ServerPlayer player, CallbackInfoReturnable<Boolean> cir) {
        boolean breaking = cir.getReturnValue();
        if (!breaking) return;
        if (player == null)
            return;
        if (player.totalExperience > 0) {
            player.giveExperiencePoints(-min(10, player.totalExperience));
            player.playNotifySound(SoundEvents.ANVIL_LAND, SoundSource.MASTER, 1, 1);
            setDamageValue(getMaxDamage() / 2);
            cir.setReturnValue(false);
        }
    }
}
