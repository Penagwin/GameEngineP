package Chunks;

import Blocks.Block;

import java.util.ArrayList;

/**
 * Created by penagwin on 4/22/14.
 */
public class Chunk {
    public ArrayList<SubChunk> Layer1 = new ArrayList<SubChunk>(); //Layer1, Top layer; contains 2 and 3 and all blocks
    public ArrayList<Block> BlocksList = new ArrayList<Block>(); //Layer1, Top layer; contains 2 and 3 and all blocks

    public void Generate() {
        for (double i = x; i < 8 + x; i += 2) {
            for (double k = z; k < 8 + z; k += 2) {
                for (double l = y - 1; l < y; l += 2) {
                    Layer1.add(new SubChunk(i, l, k, this));
                }
            }
        }
    }

    public float x, y, z;
    public Boolean loaded = false;

    public Chunk(float nx, float ny, float nz) {
        x = nx;
        y = ny;
        z = nz;
    }
}
