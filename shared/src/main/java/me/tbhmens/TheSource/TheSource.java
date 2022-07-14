package me.tbhmens.TheSource;

import me.tbhmens.TheSource.util.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TheSource {
    public static WeightedList<Block> ores;

    public static void init() {
        ores = new WeightedList<>() {{
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
