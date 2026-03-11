package nl.tudelft.jpacman.level;

import java.util.HashSet;
import java.util.Set;

import nl.tudelft.jpacman.level.Level.LevelObserver;

/**
 * Managers observers and notifications for levels.
 */
public class LevelObserverNotifier {
    
    /**
     * The observers for a level.
     */
    private final Set<LevelObserver> observers;

    /**
     * Creates a new LevelObserverNotifier.
     */
    public LevelObserverNotifier() {
        this.observers = new HashSet<>();
    }

    /**
     * Adds an observer to notify on level loss/win. 
     * @param observer The observer that will be added.
     */
    public void addObserver(LevelObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer if it was listed.
     * @param observer The observer that will be removed
     */
    public void removeObserver(LevelObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers of level loss.
     */
    public void notifyLevelLoss() {
        for (LevelObserver observer : observers) {
            observer.levelWon();
        }
    }

    /**
     * Notify all observers of level loss.
     */
    public void notifyLevelWon() {
        for (LevelObserver observer : observers) {
            observer.levelWon();
        }
    }
}
