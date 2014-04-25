package Renderer;

import Blocks.Block;
import Chunks.Chunk;
import Chunks.ChunkManager;
import Main.Main;
import org.lwjgl.opengl.GL11;
/**
 * Created by penagwin on 4/22/14.
 */
public class RenderTypeBlock extends RenderType {
    public static void render(float x, float y, float z, double color1, double color2, double color3) {
        Boolean top= true, down= true, back= true, front= true, left= true, right = true;
        /*for (Chunk chunk : ChunkManager.LoadedChunksList) {
            for (Block block : chunk.BlocksList) {
                if(block.x + 1 == x){
                    left = false;
                }
                if(block.x - 1 == x){
                    right = false;
                }
                if(block.z - 1 == z){
                    front = false;
                }
                if(block.z - 1 == z){
                    back = false;
                }
            }
        }*/
        GL11.glBegin(GL11.GL_QUADS);
        //HERE IS WHERE YOU BIND A TEXTURE

        // Front Face
        GL11.glColor3f((float)color1, (float)color2, (float)color3);
        //GL11.glColor3f(.5f, 1, 1);
        if(front) {
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(x - 1.0f, y - 1.0f, z + 0.0f);   // Bottom Left Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex3f(x + 0.0f, y - 1.0f, z + 0.0f);   // Bottom Right Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex3f(x + 0.0f, y + 0.0f, z + 0.0f);   // Top Right Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex3f(x - 1.0f, y + 0.0f, z + 0.0f);   // Top Left Of The Texture and Quad
        }
        // Back Face
        //GL11.glColor3f(1, .5f, 1);
        if(back) {
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex3f(x - 1.0f, y - 1.0f, z - 1.0f);   // Bottom Right Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex3f(x - 1.0f, y + 0.0f, z - 1.0f);   // Top Right Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex3f(x + 0.0f, y + 0.0f, z - 1.0f);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(x + 0.0f, y - 1.0f, z - 1.0f);   // Bottom Left Of The Texture and Quad
        }
        // Top Face
        //GL11.glColor3f(1, 1, .5f);
        if(top) {
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex3f(x - 1.0f, y + 0.0f, z - 1.0f);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(x - 1.0f, y + 0.0f, z + 0.0f);   // Bottom Left Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex3f(x + 0.0f, y + 0.0f, z + 0.0f);   // Bottom Right Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex3f(x + 0.0f, y + 0.0f, z - 1.0f);   // Top Right Of The Texture and Quad
        }
        // Bottom Face
        //GL11.glColor3f(.5f, .5f, 1);
        if(down) {
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex3f(x - 1.0f, y - 1.0f, z - 1.0f);   // Top Right Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex3f(x + 0.0f, y - 1.0f, z - 1.0f);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(x + 0.0f, y - 1.0f, z + 0.0f);   // Bottom Left Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex3f(x - 1.0f, y - 1.0f, z + 0.0f);   // Bottom Right Of The Texture and Quad
        }
        // Right face
        //GL11.glColor3f(.5f, 1, .5f);
        if(right) {
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex3f(x + 0.0f, y - 1.0f, z - 1.0f);   // Bottom Right Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex3f(x + 0.0f, y + 0.0f, z - 1.0f);   // Top Right Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex3f(x + 0.0f, y + 0.0f, z - 0.0f);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(x + 0.0f, y - 1.0f, z + 0.0f);   // Bottom Left Of The Texture and Quad
        }
        // Left Face
        //GL11.glColor3f(1, .5f, .5f);
        if(left) {
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(x - 1.0f, y - 1.0f, z - 1.0f);   // Bottom Left Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex3f(x - 1.0f, y - 1.0f, z + 0.0f);   // Bottom Right Of The Texture and Quad
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex3f(x - 1.0f, y + 0.0f, z + 0.0f);   // Top Right Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex3f(x - 1.0f, y + 0.0f, z - 1.0f);   // Top Left Of The Texture and Quad
        }
            GL11.glEnd();

    }
}
