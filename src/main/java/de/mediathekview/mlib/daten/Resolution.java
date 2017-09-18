package de.mediathekview.mlib.daten;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Resolution
{
    HD        (3, "HD"), 
    NORMAL    (2, "Normal"), 
    SMALL     (1, "Klein"), 
    VERY_SMALL(0, "Sehr klein")
    ;

    /**
     * The bigger the index the better the quality.
     */
    private final int resolutionSize;
    private final String description;

    private Resolution(final int resolutionSize, final String description)
    {
        this.resolutionSize = resolutionSize;
        this.description    = description;
    }

    public String getDescription()
    {
        return description;
    }

    public static Resolution getHighestResolution() {
        return Resolution.HD;
    }
    
    public static Resolution getLowestResolution() {
        return Resolution.VERY_SMALL;
    }

    public static List<Resolution> getFromBestToLowest()
    {
        return Arrays.asList(Resolution.values()).stream().sorted(Comparator.comparing(Resolution::getResolutionSize).reversed())
                .collect(Collectors.toList());
    }

    public static Resolution getNextHigherResolution(Resolution startingResolution) {
        return getNextResolutionByDirection(startingResolution, CountingDirection.HIGHER);
    }
    
    public static Resolution getNextLowerResolution(Resolution startingResolution) {
        return getNextResolutionByDirection(startingResolution, CountingDirection.LOWER);
    }
    
    int getResolutionSize() {
        return resolutionSize;
    }

    static Resolution getResoultionByResolutionSize(int searchedResolutionSize) throws NoSuchElementException {
        for (Resolution currentResolution : Resolution.values()) {
            if(searchedResolutionSize == currentResolution.getResolutionSize()) {
                return currentResolution;
            }
        }
        
        throw new NoSuchElementException(String.format("Resolution with ResolutionIndex %d not found", searchedResolutionSize));
    }

    private enum CountingDirection {
        HIGHER(+1),
        LOWER(-1);
        
        int direction;
        
        private CountingDirection(int direction) {
            this.direction = direction;
        }
        
        public int getDirectionValue() {
            return direction;
        }
    }
    
    
    static Resolution getNextResolutionByDirection(Resolution startingResolution, CountingDirection direction) {
        try {
            return getResoultionByResolutionSize((startingResolution.getResolutionSize() + direction.getDirectionValue()));
        } catch (NoSuchElementException nsee) {
            return startingResolution;
        }
    }
    
    
}
