package Blocks;

import Chunks.ChunkManager;
import Renderer.RenderTypeBlock;

/**
 * Created by penagwin on 4/22/14.
 */
public class Block {
    // public
    public Block getRelative(BlockFace face) {

        return ChunkManager.getBlockAt(x + face.getModX(), y + face.getModY(), z + face.getModZ());

    }

    public float x, y, z;
    public String name;

    public Block(float nx, float ny, float nz, String nname) {
        x = nx;
        y = ny;
        z = nz;
        name = nname;
    }

    public void render() {
        RenderTypeBlock.render(x, y, z, 1d, 1d, 1d);
    }
}
