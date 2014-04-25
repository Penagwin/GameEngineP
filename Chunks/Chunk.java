package Chunks;

import Blocks.Block;
import Blocks.GrassBlock;

import java.util.ArrayList;

/**
 * Created by penagwin on 4/22/14.
 */
public class Chunk {
    //ChunkNodeTree goes X, Y, Z
    public Block[][][] chunkTree = new Block[8][8][8];


    public void Generate() {
        for (float i = 0; i < 8; i += 1) {
            for (float k = 0; k < 8; k += 1) {
                for (float l = 0 ; l < 8; l += 1) {
                    ChunkManager.Blockcount++;
                    chunkTree[(int)i][(int)k][(int)l] = new GrassBlock(i-x, k-y, l-z);
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
