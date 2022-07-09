package me.tbhmens.TheSource;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("thesource")
public class ForgeMain {
    public ForgeMain() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
    }

    public void init(final FMLCommonSetupEvent event) {
        TheSource.init();
    }
}
