package at.fhv.roomix.domain.guest.model;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoomCategoryDomain {
    /* Database */
    private int roomCategoryId;
    private String categoryDescription;

    private Collection<ReservationUnitDomain> reservationUnitsByRoomCategoryId;
    private Collection<RoomDomain> roomsByRoomCategoryId;
    private Collection<RoomCategoryPriceDomain> roomCategoryPricesByRoomCategoryId;

    private Proxy<Collection<RoomCategoryPriceDomain>, Integer> roomCategoryPriceProxy;
    private Proxy<Collection<RoomDomain>, Integer> roomProxy;
    private Proxy<Collection<ReservationUnitDomain>, Integer> reservationUnitProxy;

    private RoomCategoryMetaData metaData;

    public Collection<ReservationUnitDomain> getReservationUnitsByRoomCategoryId() {
        return (reservationUnitsByRoomCategoryId = reservationUnitProxy.get());
    }

    public void setReservationUnitsByRoomCategoryId(Collection<ReservationUnitDomain> reservationUnitsByRoomCategoryId) {
        this.reservationUnitsByRoomCategoryId = reservationUnitsByRoomCategoryId;
    }

    public Collection<RoomDomain> getRoomsByRoomCategoryId() {
        return (roomsByRoomCategoryId = roomProxy.get());
    }

    public void setRoomsByRoomCategoryId(Collection<RoomDomain> roomsByRoomCategoryId) {
        this.roomsByRoomCategoryId = roomsByRoomCategoryId;
    }

    public Collection<RoomCategoryPriceDomain> getRoomCategoryPricesByRoomCategoryId() {
        return (roomCategoryPricesByRoomCategoryId = roomCategoryPriceProxy.get());
    }

    public void setRoomCategoryPricesByRoomCategoryId(Collection<RoomCategoryPriceDomain> roomCategoryPricesByRoomCategoryId) {
        this.roomCategoryPricesByRoomCategoryId = roomCategoryPricesByRoomCategoryId;
    }

    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(int roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setRoomCategoryPriceProxy(Proxy<Collection<RoomCategoryPriceDomain>, Integer> roomCategoryPriceProxy) {
        this.roomCategoryPriceProxy = roomCategoryPriceProxy;
    }

    public void setRoomProxy(Proxy<Collection<RoomDomain>, Integer> roomProxy) {
        this.roomProxy = roomProxy;
    }

    public void setReservationUnitProxy(Proxy<Collection<ReservationUnitDomain>, Integer> reservationUnitProxy) {
        this.reservationUnitProxy = reservationUnitProxy;
    }

    public RoomCategoryMetaData getMetaData() {
        return metaData;
    }

    private LocalDate startDate;
    private LocalDate endDate;
    /* Determines the number of occupied rooms per day  */
    private HashMap<LocalDate, Integer> occupationMap;
    private HashMap<LocalDate, Integer> unconfirmedReservationMap;
    private HashMap<LocalDate, Integer> confirmedReservationMap;

    public void setCategoryMetaData(LocalDate start, LocalDate end, GuestDomain guest){
        startDate = start;
        endDate = end;
        metaData = new RoomCategoryMetaData();
        Collection<RoomCategoryPriceDomain> result = getRoomCategoryPricesByRoomCategoryId().stream()
                .filter(
                        roomCategoryPriceDomain -> {
                          SeasonDomain season = roomCategoryPriceDomain.getSeasonBySeason();
                          /* actually this shouldn't happen since database constraint is "not null" */
                          if(season != null){
                              if(season.getStartDate().toLocalDate().isBefore(startDate) &&
                                      season.getEndDate().toLocalDate().isAfter(startDate) &&
                                      season.getEndDate().toLocalDate().isBefore(endDate)){
                                    startDate = season.getEndDate().toLocalDate();
                                    return true;
                              }
                              return season.getStartDate().toLocalDate().isBefore(startDate) &&
                                season.getEndDate().toLocalDate().isAfter(endDate);
                          }
                          return false;
                        }).collect(Collectors.toCollection(LinkedHashSet::new));
        startDate = start;
        ContractingPartyDomain contractingParty = guest.getContractingPartiesByContactId().iterator().next();
        Collection<PartnerAgreementDomain> partnerAgreements = null;
        Optional<PartnerAgreementDomain> partnerAgreement = null;
        if(contractingParty.getPartnerAgreementsByContractingPartyId() != null){
            partnerAgreements =
                    contractingParty.getPartnerAgreementsByContractingPartyId().stream().
                    filter(partnerAgreementDomain ->
                            partnerAgreementDomain.getStartDate().toLocalDate().isBefore(start) &&
                                    partnerAgreementDomain.getExpiringDate().toLocalDate().isAfter(end)
                    ).collect(Collectors.toCollection(LinkedHashSet::new));

            partnerAgreement = partnerAgreements.stream().
                    filter(partnerAgreementDomain -> partnerAgreementDomain.getRoomCategory().equals(roomCategoryId)).
                    findFirst();
        }
        calculateOccupationMap();
        calculateReservationMap();
        if(result != null){
            AtomicInteger total = new AtomicInteger();
            AtomicInteger count = new AtomicInteger();
            result.forEach(roomCategoryPriceDomain -> {
                total.addAndGet(roomCategoryPriceDomain.getListPrice() +
                        roomCategoryPriceDomain.getSeasonBySeason().getAdditionalCharge());
                count.incrementAndGet();
            });
            metaData.setPricePerDay(total.get() / count.get());
            if(partnerAgreement != null){
                metaData.setAgreementDiscount(partnerAgreement.get().getDiscount());
                metaData.setContingent(partnerAgreement.get().getCountRoomCategory());
            }
            metaData.setTotalNumberOfRooms(getTotalNumberOfRooms());
            metaData.setNumberOfConfirmedReservations(getOccupationStatusByMap(confirmedReservationMap));
            metaData.setNumberOfUnconfirmedReservations(getOccupationStatusByMap(unconfirmedReservationMap));
            metaData.setNumberOfOccupiedRooms(getOccupationStatusByMap(occupationMap));
        }

    }

    private HashMap<LocalDate, Integer> initTimeMap(){
        HashMap<LocalDate, Integer> map = new HashMap<>();
        LocalDate currentDay = startDate;
        while(currentDay.isBefore(endDate) || currentDay.isEqual(endDate)){
            map.put(currentDay, 0);
            currentDay = currentDay.plusDays(1);
        }
        return map;
    }

    private void calculateOccupationMap(){
        occupationMap = initTimeMap();
        for(RoomDomain room: getRoomsByRoomCategoryId()){
            LinkedList<RoomAssignmentDomain> roomAssignments = room.getRoomAssignmentsByRoomId().stream().filter(
                    roomAssignmentDomain -> roomAssignmentDomain.getArrivalDate().toLocalDate().isAfter(startDate) ||
                            roomAssignmentDomain.getDepartureDate().toLocalDate().isBefore(endDate)
            ).collect(Collectors.toCollection(LinkedList::new));
            for (RoomAssignmentDomain roomAssignment : roomAssignments) {
                LocalDate current = roomAssignment.getArrivalDate().toLocalDate();
                LocalDate endDt = roomAssignment.getDepartureDate().toLocalDate();
                while(current.isBefore(endDt) || current.isEqual(endDt)){
                    if(occupationMap.containsKey(current)){
                        occupationMap.put(current, occupationMap.get(current) + 1);
                    }
                    current = current.plusDays(1);
                }
            }
        }
    }

    private void calculateReservationMap(){
        confirmedReservationMap = initTimeMap();
        unconfirmedReservationMap = initTimeMap();
        Stream<ReservationUnitDomain> reservationUnitDomainStream = getReservationUnitsByRoomCategoryId().stream().filter(reservationUnitDomain -> {
            LocalDate unitStartDate = reservationUnitDomain.getStartDate().toLocalDate();
            LocalDate unitEndDate = reservationUnitDomain.getEndDate().toLocalDate();
            return (unitStartDate.isAfter(startDate) && unitStartDate.isBefore(endDate)) ||
                    (unitEndDate.isBefore(endDate) && unitEndDate.isAfter(startDate)) ||
                    (unitEndDate.isAfter(endDate) && unitStartDate.isBefore(startDate));
        });
        List<ReservationUnitDomain> confirmedReservationUnits = reservationUnitDomainStream.filter(reservationUnitDomain ->
                reservationUnitDomain.getCancellationByCancellation() == null &&
                        reservationUnitDomain.getReservationByReservation().
                                getReservationStatus().equals(EReservationStatus.CONFIRMED.getStr())).collect(Collectors.toList());

        List<ReservationUnitDomain> unconfirmedReservationUnits = reservationUnitDomainStream.filter(reservationUnitDomain ->
                reservationUnitDomain.getCancellationByCancellation() == null &&
                        reservationUnitDomain.getReservationByReservation().
                                getReservationStatus().equals(EReservationStatus.UNCONFIRMED.getStr())).collect(Collectors.toList());

        for (ReservationUnitDomain unconfirmedReservationUnit : unconfirmedReservationUnits) {
            LocalDate current = unconfirmedReservationUnit.getStartDate().toLocalDate();
            LocalDate endDt = unconfirmedReservationUnit.getEndDate().toLocalDate();
            while(current.isBefore(endDt) || current.isEqual(endDt)){
                if(unconfirmedReservationMap.containsKey(current)){
                    unconfirmedReservationMap.put(current, unconfirmedReservationMap.get(current) + 1);
                }
                current = current.plusDays(1);
            }
        }
        for (ReservationUnitDomain confirmedReservationUnit : confirmedReservationUnits) {
            LocalDate current = confirmedReservationUnit.getStartDate().toLocalDate();
            LocalDate endDt = confirmedReservationUnit.getEndDate().toLocalDate();
            while(current.isBefore(endDt) || current.isEqual(endDt)){
                if(confirmedReservationMap.containsKey(current)){
                    confirmedReservationMap.put(current, confirmedReservationMap.get(current) + 1);
                }
                current = current.plusDays(1);
            }
        }

    }

    private int getOccupationStatusByMap(HashMap<LocalDate, Integer> map){
        Iterator<Map.Entry<LocalDate, Integer>> mapIterator = map.entrySet().iterator();
        int max = 0;
        while(mapIterator.hasNext()){
            int current = mapIterator.next().getValue();
            if(current > max){
                max = current;
            }
        }
        return max;
    }

    private int getTotalNumberOfRooms(){
        return getRoomsByRoomCategoryId().size();
    }

}
