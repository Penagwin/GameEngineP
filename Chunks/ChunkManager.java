package Chunks;

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


    public static void unloadChunk(Chunk chunk) {
        LoadedChunksList.remove(chunk);
        for (SubChunk sub : chunk.Layer1) {
            ChunkManager.BlocksSubTree.remove(sub);
        }

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

    public static Chunk generateChunk(float x, float y, float z) {
        Chunk chunk = new Chunk(x * 6, y, z * 6);
        chunk.Generate();
        return chunk;

    }

    public static double blockcount() {
        double count = 0;
        for (SubSubChunk chunk : BlocksSubTree) {
            count += chunk.Layer3.toArray().length;
        }
        return count;

    }

}
