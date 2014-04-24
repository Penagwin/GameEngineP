package Chunks;

import Blocks.Block;
import Blocks.GrassBlock;

import java.util.ArrayList;

/**
 * Created by penagwin on 4/23/14.
 * <p/>
 * <p/>
 * Chunk Octrees will need at least 3 nodes to contain 8 blocks per chunk.
 */


public class SubSubChunk {
    public ArrayList<Block> Layer3 = new ArrayList<Block>(); //Layer3, Bottom Layer; contains all blocks

    public SubSubChunk(double x, double y, double z, Chunk chunk) {
        ChunkManager.BlocksSubTree.add(this);
        for (double i = x; i < 2 + x; i ++) {
            for (double k = z; k < 2 + z; k ++) {
                for (double l = y - 1; l < y; l++) {
                    Layer3.add(new GrassBlock((float) i, (float) l, (float) k));
                }
            }
        }
    }

}
