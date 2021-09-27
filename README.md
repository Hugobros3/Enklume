# Enklume (Forked)
## Java library for parsing Minecraft save files

Enklume is a small Java library that is able to read and parse Minecraft Worlds in the "anvil" format. It includes a NBT parser based on the [Official Wiki](https://minecraft.gamepedia.com/NBT_format) informations, and is mostly thread-safe. You are given an entire region when you open one, and it is garbage collected only by Java itself, no fancy book-keeping in this library.

### Installation & Building

I use Gradle. I can't be bothered right now to provide artifacts let alone uploading them, so you'll have to make your own, luckily it's really easy. Clone the repo then type in `./gradlew install`, you'll have it installed to your local maven repo, and you'll be able to find the files in `build/libs`.

### Usage

It's straightforward enough to use. You can make a new world by creating a `MinecraftWorld(File worldFolder)`, then call `getRegion(x, z)` to load a `MinecraftRegion`, then use `getChunk()` and you'll be free to peek arround.

For loading lone .nbt files, use `NBTFile(File, CompressionScheme)`.

For a better example on how to use this library, it is used in the (yet unreleased, but upcomming) `io.xol.chunkstories.core.converter` package of the core content of Chunk Stories, to map complex block types ( signs, chests, etc ) to chunkstories's equivalents.

### Additions by me (SinTh0r4s)

I require slightly more functionality that sill fits this library. This fork is used in [VisualProspecting](https://github.com/SinTh0r4s/VisualProspecting) to analyze played world and recover lost meta information about chunks.

### Background

Enklume was born out of the need for making the [Chunk Stories](https://chunkstories.xyz) Minecraft map importer, it used to live in it's source tree, but the need for me to expose it in the mod API and general unfitness to have an entire unrelated lib in my main source tree meant it was worth the trouble to spin it off as a seperate project.

### License

It's LGPL. Have your fun, I don't really care, maybe [send me (Hugobros3)](https://github.com/Hugobros3) a screenshot if you do cool shit with it :)