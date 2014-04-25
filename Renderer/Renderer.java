package Renderer;

import Blocks.AirBlock;
import Blocks.Block;
import Blocks.GrassBlock;
import Chunks.Chunk;
import Chunks.ChunkManager;
import Chunks.SubSubChunk;

/**
 * Created by penagwin on 4/22/14.
 */
public class Renderer {
    public static void render() {
        for (Chunk chunk : ChunkManager.LoadedChunksList) {
            for (float i = 0; i < 8; i += 1) {
                for (float k = 0; k < 8; k += 1) {
                    for (float l = 0 ; l < 8; l += 1) {
                        Block block = chunk.chunkTree[(int)i][(int)k][(int)l];
                        if(!(block instanceof AirBlock)) {
                            block.render();
                        }
                    }
                }
            }
        }
    }
}
