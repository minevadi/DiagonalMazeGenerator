package cn.wode490390.nukkit.basicmazegen;

import cn.nukkit.block.Block;
import cn.nukkit.level.GlobalBlockPalette;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.DyeColor;
import cn.wode490390.nukkit.basicmazegen.util.MetricsLite;

import java.util.NoSuchElementException;

public class DiagonalMazeGeneratorPlugin extends PluginBase {

    private static DiagonalMazeGeneratorPlugin INSTANCE;

    private DiagonalMazeGeneratorSettings settings;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this, 6988);
        } catch (Throwable ignore) {

        }

        this.saveDefaultConfig();
        Config config = this.getConfig();

        String node = "wall.material";
        int wallMaterial = Block.CONCRETE;
        try {
            wallMaterial = config.getInt(node, wallMaterial);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        int wallMeta = DyeColor.LIGHT_BLUE.getWoolData();
        node = "wall.meta";
        try {
            wallMeta = config.getInt(node, wallMeta);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        node = "ground.material";
        int groundMaterial = Block.CONCRETE;
        try {
            groundMaterial = config.getInt(node, groundMaterial);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        int groundMeta = DyeColor.BLUE.getWoolData();
        node = "ground.meta";
        try {
            groundMeta = config.getInt(node, groundMeta);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }

        try {
            GlobalBlockPalette.getOrCreateRuntimeId(wallMaterial, 0);
            try {
                GlobalBlockPalette.getOrCreateRuntimeId(wallMaterial, wallMeta);
            } catch (NoSuchElementException e) {
                wallMeta = 0;
                this.getLogger().warning("Invalid block meta. Use the default value.");
            }
        } catch (NoSuchElementException e) {
            wallMaterial = Block.CONCRETE;
            wallMeta = DyeColor.LIGHT_BLUE.getWoolData();
            this.getLogger().warning("Invalid block ID. Use the default value.");
        }
        try {
            GlobalBlockPalette.getOrCreateRuntimeId(groundMaterial, 0);
            try {
                GlobalBlockPalette.getOrCreateRuntimeId(groundMaterial, groundMeta);
            } catch (NoSuchElementException e) {
                groundMeta = 0;
                this.getLogger().warning("Invalid block meta. Use the default value.");
            }
        } catch (NoSuchElementException e) {
            groundMaterial = Block.CONCRETE;
            groundMeta = DyeColor.BLUE.getWoolData();
            this.getLogger().warning("Invalid block ID. Use the default value.");
        }

        this.settings = new DiagonalMazeGeneratorSettings(wallMaterial, wallMeta, groundMaterial, groundMeta);

        Generator.addGenerator(DiagonalMazeGenerator.class, "default", Generator.TYPE_INFINITE);
        Generator.addGenerator(DiagonalMazeGenerator.class, "normal", Generator.TYPE_INFINITE);
    }

    public DiagonalMazeGeneratorSettings getSettings() {
        return this.settings;
    }

    private void logConfigException(String node, Throwable t) {
        this.getLogger().alert("An error occurred while reading the configuration '" + node + "'. Use the default value.", t);
    }

    public static DiagonalMazeGeneratorPlugin getInstance() {
        return INSTANCE;
    }
}
