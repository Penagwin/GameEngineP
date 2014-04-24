package Controls;

import Chunks.Chunk;
import Chunks.ChunkManager;

/**
 * Created by penagwin on 4/23/14.
 */
public class PlayerCollisions {

    public static Boolean checkPlayer(Camera camera){
        int x = (int) -(camera.position.getX() / 8 + .5);
        int z = (int) -(camera.position.getZ() / 8);
        Chunk chunk;
        for (Chunk chunk1 : ChunkManager.LoadedChunksList) {
            if (chunk1.x == -x * 6 && chunk1.z == -z * 6) {
                chunk = chunk1;
                break;
            }
        }
        //if(){

     //   }
        return true;
    }

}
