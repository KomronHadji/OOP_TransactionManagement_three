package transactions;

import java.util.Arrays;

public class Region {
    private String regionName;
    private String[] placeNames;

    public Region(String regionName, String[] placeNames) {
        this.regionName = regionName;
        this.placeNames = placeNames;
    }

//    public String getRegionName() {
//        return regionName;
//    }


    public String getRegionName() {
        return regionName;
    }

    public String[] getPlaceNames() {
        return placeNames;
    }

    @Override
    public String toString() {
        return "Region[" + regionName + '\'' +
                ", " + Arrays.toString(placeNames) +
                ']';
    }
}
