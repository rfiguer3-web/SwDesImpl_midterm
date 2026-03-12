package nl.tudelft.jpacman.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;

/**
 * Immutable value object grouping level data, ghosts, and starting squares.
 */
public class LevelData {
    
    /**
     * The board of the level.
     */
    private final Board board;

    /**
     * The ghosts for the level.
     */
    private final List<Ghost> ghosts;

    /**
     * The starting squares for players.
     */
    private final List<Square> startPositions;

    /**
     * Constructor for LevelData
     */
    public LevelData(Board board, List<Ghost> ghosts, List<Square> startPositions) {
        assert board != null;
        assert ghosts != null;
        assert startPositions != null;

        this.board = board;
        this.ghosts = Collections.unmodifiableList(new ArrayList<>(ghosts));
        this.startPositions = Collections.unmodifiableList(new ArrayList<>(startPositions));
    }

    public Board getBoard() {
        return this.board;
    }

    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

    public List<Square> getStartPositions() {
        return this.startPositions;
    }
}
