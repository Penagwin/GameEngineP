package Util;

/**
 * Created by penagwin on 4/25/14.
 */

import Blocks.Block;
import Blocks.BlockFace;
import Chunks.Chunk;
import Chunks.ChunkManager;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.floor;
import static java.lang.Math.round;

/**
 * This class performs ray tracing and iterates along blocks on a line
 */
public class BlockIterator implements Iterator<Block> {

    private final int maxDistance;

    private static final int gridSize = 1 << 24;
    private boolean end = false;

    private Block[] blockQueue = new Block[3];
    private int currentBlock = 0;
    private int currentDistance = 0;
    private int maxDistanceInt;

    private int secondError;
    private int thirdError;

    private int secondStep;
    private int thirdStep;

    private BlockFace mainFace;
    private BlockFace secondFace;
    private BlockFace thirdFace;

    /**
     * Constructs the BlockIterator
     *
     * @param start       A Vector3D giving the initial location for the trace
     * @param direction   A Vector3D pointing in the direction for the trace
     * @param yOffset     The trace begins vertically offset from the start Vector3D
     *                    by this value
     * @param maxDistance This is the maximum distance in blocks for the
     *                    trace. Setting this value above 140 may lead to problems with
     *                    unloaded chunks. A value of 0 indicates no limit
     */
    public BlockIterator( Vector3D start, Vector3D direction, double yOffset, int maxDistance) {
        this.maxDistance = maxDistance;

        Vector3D startClone = start;

        startClone.y = ((float)(startClone.y + yOffset));

        currentDistance = 0;

        double mainDirection = 0;
        double secondDirection = 0;
        double thirdDirection = 0;

        double mainPosition = 0;
        double secondPosition = 0;
        double thirdPosition = 0;

        Block startBlock = ChunkManager.getBlockAt(floor(startClone.x), floor(startClone.y), floor(startClone.z));

        if (getXLength(direction) > mainDirection) {
            mainFace = getXFace(direction);
            mainDirection = getXLength(direction);
            mainPosition = getXPosition(direction, startClone, startBlock);

            secondFace = getYFace(direction);
            secondDirection = getYLength(direction);
            secondPosition = getYPosition(direction, startClone, startBlock);

            thirdFace = getZFace(direction);
            thirdDirection = getZLength(direction);
            thirdPosition = getZPosition(direction, startClone, startBlock);
        }
        if (getYLength(direction) > mainDirection) {
            mainFace = getYFace(direction);
            mainDirection = getYLength(direction);
            mainPosition = getYPosition(direction, startClone, startBlock);

            secondFace = getZFace(direction);
            secondDirection = getZLength(direction);
            secondPosition = getZPosition(direction, startClone, startBlock);

            thirdFace = getXFace(direction);
            thirdDirection = getXLength(direction);
            thirdPosition = getXPosition(direction, startClone, startBlock);
        }
        if (getZLength(direction) > mainDirection) {
            mainFace = getZFace(direction);
            mainDirection = getZLength(direction);
            mainPosition = getZPosition(direction, startClone, startBlock);

            secondFace = getXFace(direction);
            secondDirection = getXLength(direction);
            secondPosition = getXPosition(direction, startClone, startBlock);

            thirdFace = getYFace(direction);
            thirdDirection = getYLength(direction);
            thirdPosition = getYPosition(direction, startClone, startBlock);
        }

        // trace line backwards to find intercept with plane perpendicular to the main axis

        double d = mainPosition / mainDirection; // how far to hit face behind
        double secondd = secondPosition - secondDirection * d;
        double thirdd = thirdPosition - thirdDirection * d;

        // Guarantee that the ray will pass though the start block.
        // It is possible that it would miss due to rounding
        // This should only move the ray by 1 grid position
        secondError = (int) floor(secondd * gridSize);
        secondStep = (int) round(secondDirection / mainDirection * gridSize);
        thirdError = (int) floor(thirdd * gridSize);
        thirdStep = (int) round(thirdDirection / mainDirection * gridSize);

        if (secondError + secondStep <= 0) {
            secondError = -secondStep + 1;
        }

        if (thirdError + thirdStep <= 0) {
            thirdError = -thirdStep + 1;
        }

        Block lastBlock;

        lastBlock = startBlock.getRelative(mainFace.getOppositeFace());

        if (secondError < 0) {
            secondError += gridSize;
            lastBlock = lastBlock.getRelative(secondFace.getOppositeFace());
        }

        if (thirdError < 0) {
            thirdError += gridSize;
            lastBlock = lastBlock.getRelative(thirdFace.getOppositeFace());
        }

        // This means that when the variables are positive, it means that the coord=1 boundary has been crossed
        secondError -= gridSize;
        thirdError -= gridSize;

        blockQueue[0] = lastBlock;
        currentBlock = -1;

        scan();

        boolean startBlockFound = false;

        for (int cnt = currentBlock; cnt >= 0; cnt--) {
            if (blockEquals(blockQueue[cnt], startBlock)) {
                currentBlock = cnt;
                startBlockFound = true;
                break;
            }
        }

        if (!startBlockFound) {
            throw new IllegalStateException("Start block missed in BlockIterator");
        }

        // Calculate the number of planes passed to give max distance
        maxDistanceInt = (int) round(maxDistance / (Math.sqrt(mainDirection * mainDirection + secondDirection * secondDirection + thirdDirection * thirdDirection) / mainDirection));

    }

    private boolean blockEquals(Block a, Block b) {
        return a.x == b.x && a.y == b.y && a.z == b.z;
    }

    private BlockFace getXFace(Vector3D direction) {
        return ((direction.x > 0) ? BlockFace.EAST : BlockFace.WEST);
    }

    private BlockFace getYFace(Vector3D direction) {
        return ((direction.y > 0) ? BlockFace.UP : BlockFace.DOWN);
    }

    private BlockFace getZFace(Vector3D direction) {
        return ((direction.z > 0) ? BlockFace.SOUTH : BlockFace.NORTH);
    }

    private double getXLength(Vector3D direction) {
        return Math.abs(direction.x);
    }

    private double getYLength(Vector3D direction) {
        return Math.abs(direction.y);
    }

    private double getZLength(Vector3D direction) {
        return Math.abs(direction.z);
    }

    private double getPosition(double direction, double position, int blockPosition) {
        return direction > 0 ? (position - blockPosition) : (blockPosition + 1 - position);
    }

    private double getXPosition(Vector3D direction, Vector3D position, Block block) {
        return getPosition(direction.x, position.x, (int) block.x);
    }

    private double getYPosition(Vector3D direction, Vector3D position, Block block) {
        return getPosition(direction.y, position.y, (int) block.y);
    }

    private double getZPosition(Vector3D direction, Vector3D position, Block block) {
        return getPosition(direction.z, position.z, (int) block.z);
    }

    /**
     * Constructs the BlockIterator
     *
     * @param loc         The location for the start of the ray trace
     * @param yOffset     The trace begins vertically offset from the start Vector3D
     *                    by this value
     * @param maxDistance This is the maximum distance in blocks for the
     *                    trace. Setting this value above 140 may lead to problems with
     *                    unloaded chunks. A value of 0 indicates no limit
     */


    /**
     * Returns true if the iteration has more elements
     */

    public boolean hasNext() {
        scan();
        return currentBlock != -1;
    }

    /**
     * Returns the next Block in the trace
     *
     * @return the next Block in the trace
     */

    public Block next() {
        scan();
        if (currentBlock <= -1) {
            throw new NoSuchElementException();
        } else {
            return blockQueue[currentBlock--];
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("[BlockIterator] doesn't support block removal");
    }

    private void scan() {
        if (currentBlock >= 0) {
            return;
        }
        if (maxDistance != 0 && currentDistance > maxDistanceInt) {
            end = true;
            return;
        }
        if (end) {
            return;
        }

        currentDistance++;

        secondError += secondStep;
        thirdError += thirdStep;

        if (secondError > 0 && thirdError > 0) {
            blockQueue[2] = blockQueue[0].getRelative(mainFace);
            if (((long) secondStep) * ((long) thirdError) < ((long) thirdStep) * ((long) secondError)) {
                blockQueue[1] = blockQueue[2].getRelative(secondFace);
                blockQueue[0] = blockQueue[1].getRelative(thirdFace);
            } else {
                blockQueue[1] = blockQueue[2].getRelative(thirdFace);
                blockQueue[0] = blockQueue[1].getRelative(secondFace);
            }
            thirdError -= gridSize;
            secondError -= gridSize;
            currentBlock = 2;
            return;
        } else if (secondError > 0) {
            blockQueue[1] = blockQueue[0].getRelative(mainFace);
            blockQueue[0] = blockQueue[1].getRelative(secondFace);
            secondError -= gridSize;
            currentBlock = 1;
            return;
        } else if (thirdError > 0) {
            blockQueue[1] = blockQueue[0].getRelative(mainFace);
            blockQueue[0] = blockQueue[1].getRelative(thirdFace);
            thirdError -= gridSize;
            currentBlock = 1;
            return;
        } else {
            blockQueue[0] = blockQueue[0].getRelative(mainFace);
            currentBlock = 0;
            return;
        }
    }
}