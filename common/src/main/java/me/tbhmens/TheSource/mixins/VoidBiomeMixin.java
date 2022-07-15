package me.tbhmens.TheSource.mixins;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.world.level.biome.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.data.worldgen.placement.MiscOverworldPlacements.VOID_START_PLATFORM;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.TOP_LAYER_MODIFICATION;

@Mixin(OverworldBiomes.class)
public class VoidBiomeMixin {
    // Adds rain and mobs to the "minecraft:the_void" biome.
    @Inject(at = @At("HEAD"), method = "theVoid", cancellable = true)
    private static void theVoid(CallbackInfoReturnable<Biome> cir) {
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobs);
        Biome returning = new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.5f).downfall(0.5f)
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(8104703).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null).build())
                .mobSpawnSettings(mobs.build())
                .generationSettings(new BiomeGenerationSettings.Builder().addFeature(TOP_LAYER_MODIFICATION, VOID_START_PLATFORM).build())
                .build();
        cir.setReturnValue(returning);
    }
}