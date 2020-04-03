# Diagonal Maze Generator for Nukkit
[![Build](https://img.shields.io/circleci/build/github/wode490390/DiagonalMazeGenerator/master)](https://circleci.com/gh/wode490390/DiagonalMazeGenerator/tree/master)
[![Release](https://img.shields.io/github/v/release/wode490390/DiagonalMazeGenerator)](https://github.com/wode490390/DiagonalMazeGenerator/releases)
[![Release date](https://img.shields.io/github/release-date/wode490390/DiagonalMazeGenerator)](https://github.com/wode490390/DiagonalMazeGenerator/releases)
<!--[![Servers](https://img.shields.io/bstats/servers/6988)](https://bstats.org/plugin/bukkit/DiagonalMazeGenerator/6988)
[![Players](https://img.shields.io/bstats/players/6988)](https://bstats.org/plugin/bukkit/DiagonalMazeGenerator/6988)-->

This is an easter egg generator ported from [20w14infinite](https://minecraft.gamepedia.com/Java_Edition_20w14infinite).

![](https://i.loli.net/2020/04/03/4M68rP7JpFWtSix.png)

If you found any bugs or have any suggestions, please open an issue on [GitHub Issues](https://github.com/wode490390/DiagonalMazeGenerator/issues).

If you love this plugin, please star it on [GitHub](https://github.com/wode490390/DiagonalMazeGenerator).

## Download
- [Releases](https://github.com/wode490390/DiagonalMazeGenerator/releases)
- [Snapshots](https://circleci.com/gh/wode490390/DiagonalMazeGenerator)

## Configurations

### config.yml
```yaml
wall:
  # Block ID used to generate the wall
  material: 236
  meta: 3
ground:
  # Block ID used to generate the ground
  material: 236
  meta: 11
```

## Compiling
1. Install [Maven](https://maven.apache.org/).
2. Run `mvn clean package`. The compiled JAR can be found in the `target/` directory.

## Metrics Collection

This plugin uses [bStats](https://github.com/wode490390/bStats-Nukkit) - you can opt out using the global bStats config, see the [official website](https://bstats.org/getting-started) for more details.

<!--[![Metrics](https://bstats.org/signatures/bukkit/DiagonalMazeGenerator.svg)](https://bstats.org/plugin/bukkit/DiagonalMazeGenerator/6988)-->

###### If I have any grammar and terms error, please correct my wrong :)
