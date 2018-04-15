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

        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                int currentHeight = (int) (
                        generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.5D, 0.5D)
                                * landCurveFactor + minimumHeight
                );

                chunk.setBlock(X, currentHeight, Z, Material.GRASS);
                chunk.setBlock(X, currentHeight - 1, Z, Material.DIRT);

                for (int i = currentHeight - 2; i > 0; i--) {
                    chunk.setBlock(X, i, Z, Material.STONE);
                }

                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        }

        return chunk;
    }
}
