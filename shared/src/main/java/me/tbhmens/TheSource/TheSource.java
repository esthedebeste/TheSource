package me.tbhmens.TheSource;

import me.tbhmens.TheSource.util.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TheSource {
    public static WeightedList<Block> ores;

    public static void init() {
        ores = new WeightedList<>() {{
            add(Blocks.DIAMOND_ORE, 5);
            add(Blocks.EMERALD_ORE, 5);
            add(Blocks.LAPIS_ORE, 5);
            add(Blocks.REDSTONE_ORE, 10);
            add(Blocks.COPPER_ORE, 10);
            add(Blocks.IRON_ORE, 30);
            add(Blocks.COAL_ORE, 40);
        }};
    }
}
