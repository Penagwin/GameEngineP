package Chunks;

import Blocks.AirBlock;
import Blocks.Block;
import Blocks.GrassBlock;

import java.util.ArrayList;

import static Util.Log.print;
import static Util.Log.println;

/**
 * Created by penagwin on 4/22/14.
 */
public class ChunkManager {
    public static ArrayList<SubSubChunk> BlocksSubTree = new ArrayList<SubSubChunk>();
    public static ArrayList<Chunk> Keeploaded = new ArrayList<Chunk>();
    public static ArrayList<Chunk> LoadedChunksList = new ArrayList<Chunk>();
    public static double Blockcount = 0;


    public static void unloadChunk(Chunk chunk) {
        LoadedChunksList.remove(chunk);
        //for (SubChunk sub : chunk.Layer1) {
        //    ChunkManager.BlocksSubTree.remove(sub);
        //}

    }

    public static void addLoaded(Chunk chunk) {
        LoadedChunksList.add(chunk);
    }

    public static void Keeploaded(Chunk chunk) {
        Keeploaded.add(chunk);
    }

    public static void flushLoaded() {
        LoadedChunksList.clear();
        for (Chunk chunk : Keeploaded) {
            addLoaded(chunk);
        }
        Keeploaded.clear();
    }

    public static Boolean setBlockAt(float x, float y, float z) {

        for (Chunk chunk : ChunkManager.LoadedChunksList) {
            if (chunk.x == x * 6 && chunk.z == z * 6) {
                for (float i = 0; i < 8; i += 1) {
                    for (float k = 0; k < 8; k += 1) {
                        for (float l = 0 ; l < 8; l += 1) {
                            chunk.chunkTree[(int)i][(int)k][(int)l] = null;
                            return true;
                        }
                    }
                }
                break;
            }
        }

        return false;
    }
    public static Block getBlockAt(double x, double y, double z) {

        for (Chunk chunk : ChunkManager.LoadedChunksList) {
            if (chunk.x == x * 6 && chunk.z == z * 6) {
                for (float i = 0; i < 8; i += 1) {
                    for (float k = 0; k < 8; k += 1) {
                        for (float l = 0 ; l < 8; l += 1) {
                            return chunk.chunkTree[(int)i][(int)k][(int)l];
                        }
                    }
                }
                break;
            }
        }
        return null;
    }
    public static Block getBlockAt(float x, float y, float z) {

        return getBlockAt((double)x, (double) y, (double) z);
    }

    public static Chunk generateChunk(float x, float y, float z) {
        Chunk chunk = new Chunk(x * 8, y, z * 8);
        chunk.Generate();
        return chunk;

    }

    public static double blockcount() {

        return Blockcount;

    }

}
