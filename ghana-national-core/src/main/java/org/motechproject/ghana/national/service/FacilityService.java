package org.motechproject.ghana.national.service;

import org.apache.commons.lang.StringUtils;
import org.motechproject.ghana.national.domain.Facility;
import org.motechproject.ghana.national.exception.FacilityAlreadyFoundException;
import org.motechproject.ghana.national.exception.FacilityNotFoundException;
import org.motechproject.ghana.national.repository.AllFacilities;
import org.motechproject.ghana.national.tools.StartsWithMatcher;
import org.motechproject.ghana.national.tools.Utility;
import org.motechproject.mrs.model.MRSFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;

@Service
public class FacilityService {

    private AllFacilities allFacilities;
    private IdentifierGenerationService identifierGenerationService;

    @Autowired
    public FacilityService(AllFacilities allFacilities, IdentifierGenerationService identifierGenerationService) {
        this.allFacilities = allFacilities;
        this.identifierGenerationService = identifierGenerationService;
    }

    public String create(String name, String country, String region, String district, String province, String phoneNumber, String additionalPhoneNumber1, String additionalPhoneNumber2, String additionalPhoneNumber3) throws FacilityAlreadyFoundException {
        final List<Facility> facilities = facilities();
        final MRSFacility mrsFacility = new MRSFacility(name, country, Utility.emptyToNull(region), Utility.emptyToNull(district), Utility.emptyToNull(province));
        if (isDuplicate(facilities, mrsFacility, phoneNumber)) {
            throw new FacilityAlreadyFoundException();
        }
        final Facility facility = new Facility(mrsFacility).phoneNumber(phoneNumber).additionalPhoneNumber1(additionalPhoneNumber1).
                additionalPhoneNumber2(additionalPhoneNumber2).additionalPhoneNumber3(additionalPhoneNumber3).motechId(identifierGenerationService.newFacilityId());
        return save(facility);
    }

    private String save(Facility facility) {
        if(facility.mrsFacilityId() == null)
            allFacilities.add(facility);
        else
            allFacilities.update(facility);
        return facility.mrsFacilityId();
    }

    public List<Facility> facilities() {
        return allFacilities.facilities();
    }

    public List<Facility> searchFacilities(String motechId, String name, String country, String region, String district, String province) {
        List<Facility> filteredFacilities = facilities();
        filteredFacilities = filterFacilities(on(Facility.class).mrsFacility().getCountry(), country, filteredFacilities);
        filteredFacilities = filterFacilities(on(Facility.class).mrsFacility().getRegion(), region, filteredFacilities);
        filteredFacilities = filterFacilities(on(Facility.class).mrsFacility().getCountyDistrict(), district, filteredFacilities);
        filteredFacilities = filterFacilities(on(Facility.class).mrsFacility().getStateProvince(), province, filteredFacilities);
        filteredFacilities = filterFacilities(on(Facility.class).mrsFacility().getName(), name, filteredFacilities);
        filteredFacilities = filterFacilities(on(Facility.class).motechId(), motechId, filteredFacilities);
        return filteredFacilities;
    }

    private boolean isDuplicate(List<Facility> facilities, MRSFacility mrsFacility, String phoneNumber) {
        for (Facility facility : facilities) {
            MRSFacility thatMrsFacility = facility.mrsFacility();
            boolean isFacilityNotUnique = false;
            if ((thatMrsFacility != null
                    && StringUtils.equals(thatMrsFacility.getName(), mrsFacility.getName())
                    && StringUtils.equals(thatMrsFacility.getStateProvince(), mrsFacility.getStateProvince())
                    && StringUtils.equals(thatMrsFacility.getCountyDistrict(), mrsFacility.getCountyDistrict())
                    && StringUtils.equals(thatMrsFacility.getRegion(), mrsFacility.getRegion())
                    && StringUtils.equals(thatMrsFacility.getCountry(), mrsFacility.getCountry()))) {
                isFacilityNotUnique = true;
            }
            if (isFacilityNotUnique || StringUtils.equals(facility.phoneNumber(), phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private List<Facility> filterFacilities(String field, String matcher, List<Facility> filteredFacilities) {
        return (StringUtils.isNotEmpty(matcher)) ? filter(having(field, StartsWithMatcher.ignoreCaseStartsWith(matcher)), filteredFacilities) : filteredFacilities;
    }

    public Facility getFacility(String facilityId) {
        return allFacilities.getFacility(facilityId);
    }

    public void update(Facility facilityToBeSaved) throws FacilityNotFoundException {

        Facility facility = allFacilities.getFacility(facilityToBeSaved.mrsFacility().getId());
        if (facility == null) {
            throw new FacilityNotFoundException();
        }
        save(facilityToBeSaved);
    }

    public Facility getFacilityByMotechId(String motechFacilityId) {
        return allFacilities.getFacilityByMotechId(motechFacilityId);
    }
}