package Blocks;

import Renderer.RenderTypeBlock;

/**
 * Created by penagwin on 4/22/14.
 */
public class GrassBlock extends Block{
    double color1, color2, color3;
    public GrassBlock(float nx, float ny, float nz) {
        super(nx, ny, nz, "Grass");
        color1 = Math.random();
        color2 = Math.random();
        color3 = Math.random();

    }
    public void render(){
        RenderTypeBlock.render(x, y,z, color1, color2, color3);
    }
}
