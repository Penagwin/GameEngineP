package Renderer;

import Blocks.Block;
import Chunks.ChunkManager;
import Chunks.SubSubChunk;

/**
 * Created by penagwin on 4/22/14.
 */
public class Renderer {
    public static void render() {
        for (SubSubChunk chunk : ChunkManager.BlocksSubTree) {
            for (Block block : chunk.Layer3) {
                block.render();
            }
        }
    }
}
