package nl.tudelft.jpacman.level;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.npc.Ghost;

/**
 * Magages NPC (ghost) scheduling
 */
public class NPCScheduler {
    
    /**
     * NPCs and their associated values
     */
    private final Map<Ghost, ScheduledExecutorService> npcs;

    /**
     * The level that NPCs move on
     */
    private final Level level;

    /**
     * A constructor for NPCScheduler.
     */
    public NPCScheduler(Iterable<Ghost> ghosts, Level level) {
        this.level = level;
        this.npcs = new HashMap<>();
        for (Ghost ghost : ghosts) {
            npcs.put(ghost, null);
        }
    }

    /**
     * Starts movement for NPCs
     */
    public void startNPCs() {
        for (final Ghost npc : npcs.keySet()) {
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.schedule(
                new NPCMoveTask(service, npc),
                npc.getInterval() / 2,
                TimeUnit.MILLISECONDS
            );
            npcs.put(npc, service);
        }
    }

    /**
     * Stops all NPC scheduling, interrupting in-progress moves.
     */
    public void stopNPCs() {
        for (ScheduledExecutorService service : npcs.values()) {
            assert service != null;
            service.shutdownNow();
        }
    }

    /**
     * A task that moves an NPC and reschuedles it after.
     */
    private final class NPCMoveTask implements Runnable {

        private final ScheduledExecutorService service;
        private final Ghost npc;

        public NPCMoveTask(ScheduledExecutorService service, Ghost npc) {
            this.service = service;
            this.npc = npc;
        }

        @Override
        public void run() {
            Direction nextMove = npc.nextMove();
            if (nextMove != null) {
                level.move(npc, nextMove);
            }
            long interval = npc.getInterval();
            service.schedule(this, interval, TimeUnit.MILLISECONDS);
        }
        
    }
}
