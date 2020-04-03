package cn.wode490390.nukkit.basicmazegen;

import cn.nukkit.block.Block;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.biome.EnumBiome;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

import java.util.Collections;
import java.util.Map;

public class DiagonalMazeGenerator extends Generator {

    protected static final boolean[] LEFT = {
            false, false, true, true, true, true, true, true,
            false, false, false, true, true, true, true, true,
            true, false, false, false, true, true, true, true,
            true, true, false, false, false, true, true, true,
            true, true, true, false, false, false, true, true,
            true, true, true, true, false, false, false, true,
            true, true, true, true, true, false, false, false,
            true, true, true, true, true, true, false, false
    };
    protected static final boolean[] RIGHT = {
            true, true, true, true, true, true, false, false,
            true, true, true, true, true, false, false, false,
            true, true, true, true, false, false, false, true,
            true, true, true, false, false, false, true, true,
            true, true, false, false, false, true, true, true,
            true, false, false, false, true, true, true, true,
            false, false, false, true, true, true, true, true,
            false, false, true, true, true, true, true, true
    };

    protected ChunkManager level;
    protected DiagonalMazeGeneratorSettings settings;

    public DiagonalMazeGenerator() {
        this(null);
    }

    public DiagonalMazeGenerator(Map<String, Object> options) {
        this.settings = DiagonalMazeGeneratorPlugin.getInstance().getSettings();
    }

    @Override
    public int getId() {
        return TYPE_INFINITE;
    }

    @Override
    public String getName() {
        return "normal";
    }

    @Override
    public ChunkManager getChunkManager() {
        return this.level;
    }

    @Override
    public Map<String, Object> getSettings() {
        return Collections.emptyMap();
    }

    @Override
    public void init(ChunkManager level, NukkitRandom random) {
        this.level = level;
    }

    @Override
    public void generateChunk(int chunkX, int chunkZ) {
        BaseFullChunk chunk = this.level.getChunk(chunkX, chunkZ);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 0, z, this.settings.getGroundId(), this.settings.getGroundMeta());
                chunk.setBiome(x, z, EnumBiome.JUNGLE.biome);
            }
        }

        NukkitRandom random = new NukkitRandom((chunkX << 16) + chunkZ);
        this.placeSlash(chunk, 0, 0, random.nextBoolean() ? LEFT : RIGHT);
        this.placeSlash(chunk, 0, 8, random.nextBoolean() ? LEFT : RIGHT);
        this.placeSlash(chunk, 8, 0, random.nextBoolean() ? LEFT : RIGHT);
        this.placeSlash(chunk, 8, 8, random.nextBoolean() ? LEFT : RIGHT);
    }

    protected void placeSlash(BaseFullChunk chunk, int baseX, int baseZ, boolean[] status) {
        for (int x = 0; x < 8; ++x) {
            for (int z = 0; z < 8; ++z) {
                boolean slash = status[(x << 3) + z];
                for (int y = 1; y < 8; ++y) {
                    if (slash) {
                        chunk.setBlock(x + baseX, y, z + baseZ, Block.AIR);
                    } else {
                        chunk.setBlock(x + baseX, y, z + baseZ, this.settings.getWallId(), this.settings.getWallMeta());
                    }
                }
            }
        }
    }

    @Override
    public void populateChunk(int chunkX, int chunkZ) {

    }

    @Override
    public Vector3 getSpawn() {
        return new Vector3(2.5, 1.5, 0.5);
    }
}
