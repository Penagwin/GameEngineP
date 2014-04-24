package Chunks;

import java.util.ArrayList;

/**
 * Created by penagwin on 4/23/14.
 * <p/>
 * <p/>
 * Chunk Octrees will need at least 3 nodes to contain 8 blocks per chunk.
 */


public class SubChunk {
    public ArrayList<SubSubChunk> Layer2 = new ArrayList<SubSubChunk>(); //Layer2, Middle Layer; contains 1 and all blocks

    public SubChunk(double x, double y, double z, Chunk chunk) {
        for (double i = x; i < 2 + x; i += 2) {
            for (double k = z; k < 2 + z; k += 2) {
                for (double l = y - 2; l < y; l += 2) {
                    Layer2.add(new SubSubChunk(i, y, k, chunk));
                }
            }
        }
    }
}
