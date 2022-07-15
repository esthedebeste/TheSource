package me.tbhmens.TheSource.mixins;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class VoidDamageMixin {
    @Inject(at = @At("HEAD"), method = "outOfWorld", cancellable = true)
    private void outOfWorld(CallbackInfo ci) { // outOfWorld() isn't called for /kill
        LivingEntity le = (LivingEntity) (Object) this;
        if (!(le instanceof Player player)) return;
        // when a player takes void damage, teleport them to max build height instead.
        player.setPos(player.position().x, player.level.getMaxBuildHeight(), player.position().z);
        ci.cancel();
    }
}
