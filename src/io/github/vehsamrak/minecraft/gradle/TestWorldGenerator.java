package io.github.vehsamrak.minecraft.gradle;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class TestWorldGenerator extends ChunkGenerator {
    private static final double minimumHeight = 50D;
    // amount of difference between the highest and lowest possible heights of the world
    private static final double landCurveFactor = 30D;
    // larger the scale is, the steeper the terrain
    private static final double generatorScale = 0.05D;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(generatorScale);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int currentHeight = (int) (
                        generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D)
                                * landCurveFactor + minimumHeight
                );

                chunk.setBlock(x, currentHeight, z, Material.GRASS);
                chunk.setBlock(x, currentHeight - 1, z, Material.DIRT);

                for (int i = currentHeight - 2; i > 0; i--) {
                    chunk.setBlock(x, i, z, Material.STONE);
                }

                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        }

        return chunk;
    }
}
