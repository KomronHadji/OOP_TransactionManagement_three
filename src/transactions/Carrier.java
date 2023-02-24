package transactions;

import java.util.Arrays;

public class Carrier {
    private String carrierName;
    private String[] regionName;

    public Carrier(String carrierName, String[] regionName) {
        this.carrierName = carrierName;
        this.regionName = regionName;
    }

    public String[] getRegionName() {
        return regionName;
    }

    public String getCarrierName() {
        return carrierName;
    }



    @Override
    public String toString() {
        return "Carrier[" + carrierName + '\'' +
                ", " + Arrays.toString(regionName) +
                ']';
    }
}
