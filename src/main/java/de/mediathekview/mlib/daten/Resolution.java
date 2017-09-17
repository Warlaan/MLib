package de.mediathekview.mlib.daten;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    private final int qualityStrenght;
    private final String description;

    Resolution(final int aIndex, final String aDescription)
    {
        qualityStrenght = aIndex;
        description = aDescription;
    }

    public String getDescription()
    {
        return description;
    }

    private int getIndex()
    {
        return qualityStrenght;
    }

    public static List<Resolution> getFromBestToLowest()
    {
        return Arrays.asList(Resolution.values()).stream().sorted(Comparator.comparing(Resolution::getIndex).reversed())
                .collect(Collectors.toList());
    }
    
    
    
}
