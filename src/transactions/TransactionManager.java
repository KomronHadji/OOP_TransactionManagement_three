package transactions;

import java.util.*;


public class TransactionManager {
    private Map<Region, String[]> regionMap = new HashMap<>();
    private Map<Carrier, String[]> carrierMap = new HashMap<>();
    private List<Request> requestList = new ArrayList<>();
    private List<Offer> offerList = new ArrayList<>();
    private List<Transaction> transactionList = new ArrayList<>();
    private Map<EvaluatedTransaction, Integer> evaluatedTransactionMap = new HashMap();

    //R1
    public List<String> addRegion(String regionName, String... placeNames) {
        Region region = new Region(regionName, placeNames);
        regionMap.put(region, placeNames);
        List<String> places = new ArrayList<>();
        for (String placeName : placeNames) {
            if (!places.contains(placeName))
                places.add(placeName);
        }
        Collections.sort(places);
        return places;
    }

    public List<String> addCarrier(String carrierName, String... regionNames) {
        Carrier carrier1 = new Carrier(carrierName, regionNames);
        carrierMap.put(carrier1, regionNames);
        List<String> regionNames1 = new ArrayList<>();
        for (String regionName : regionNames) {
            if (!regionNames1.contains(regionName))
                regionNames1.add(regionName);
        }
        Collections.sort(regionNames1);
        return regionNames1;
    }

    public List<String> getCarriersForRegion(String regionName) {
        List<String> carriersOfRegion = new ArrayList<>();
        for (Carrier carrier : carrierMap.keySet()) {
            if (Arrays.asList(carrier.getRegionName()).contains(regionName)) {
                carriersOfRegion.add(carrier.getCarrierName());
            }
        }
        Collections.sort(carriersOfRegion);

        return carriersOfRegion;
    }

    //R2
    public void addRequest(String requestId, String placeName, String productId)
            throws TMException {
        if (placeName == null) {
            throw new TMException();
        }
        for (Request request : requestList) {
            request.getRequestId().equals(requestId);
            {
                throw new TMException();
            }
        }
        Request request = new Request(requestId, placeName, productId);
        requestList.add(request);
    }

    public void addOffer(String offerId, String placeName, String productId)
            throws TMException {
        if (placeName == null) {
            throw new TMException();
        }
        for (Offer offer : offerList) {
            if (offer.getOfferId().equals(offerId)) {
                throw new TMException();
            }
        }
        Offer offer = new Offer(offerId, placeName, productId);
        offerList.add(offer);
    }


    //R3
    public void addTransaction(String transactionId, String carrierName, String requestId, String offerId)
            throws TMException {
        //Offer ID and Requestion ID Bound Exception
        for (Transaction transaction : transactionList) {
            if ((transaction.getOfferId().equals(offerId) || (transaction.getRequestId().equals(requestId)))) {
                throw new TMException();
            }
        }
        if (requestId == null || offerId == null) {
            throw new TMException();
        }

        //Product ID Exception
        Offer neededOffer = null;
        Request neededRequest = null;
        if (neededOffer == null || neededRequest == null) {
            throw new TMException();
        }


        for (Offer offer : offerList) {
            if (offer.getOfferId().equals(offerId)) {
                neededOffer = offer;
            }
        }
        for (Request request : requestList) {
            if (request.getRequestId().equals(requestId)) {
                neededRequest = request;
            }
        }
        if (!neededOffer.getProductId().equals(neededRequest.getProductId())) {
            throw new TMException();
        }

        // Exception to PlaceName

        List<String[]> neededRegion = new ArrayList<>();
        List<String[]> needPlaceNameList = new ArrayList<>();

        for (Carrier carrier : carrierMap.keySet()) {
            if (carrier.getCarrierName().equals(carrierName)) {
                Carrier neededCarrier = carrier;
                neededRegion.add(neededCarrier.getRegionName());
            }
        }
        for (Region region : regionMap.keySet()) {
            if (neededRegion.contains(region.getRegionName())) {
                needPlaceNameList.add(region.getPlaceNames());
            }
        }
        if (!needPlaceNameList.contains(neededOffer.getPlaceName()) || !needPlaceNameList.contains(neededRequest.getPlaceName())) {
            throw new TMException();
        }


        Transaction transaction = new Transaction(transactionId, carrierName, requestId, offerId);
        transactionList.add(transaction);
    }

    public boolean evaluateTransaction(String transactionId, int score) {
        if (score >= 0 && score <= 10) {
            EvaluatedTransaction evaluatedTransaction = new EvaluatedTransaction(transactionId, score);
            evaluatedTransactionMap.put(evaluatedTransaction, score);
            return true;
        }
        else return false;
    }

    //R4
    public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
        return new TreeMap<Long, List<String>>();
    }

    public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
        return new TreeMap<String, Integer>();
    }

    public SortedMap<String, Long> nTPerProduct() {
        return new TreeMap<String, Long>();
    }


}

