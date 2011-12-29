package org.motechproject.functional.pages.facility;

import org.motechproject.functional.base.WebDriverProvider;
import org.motechproject.functional.pages.BasePage;
import org.motechproject.functional.pages.home.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

@Component
public class SearchFacilityPage extends BasePage {


    @Autowired
    public SearchFacilityPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider.getWebDriver());
    }

    @Autowired
    private HomePage homePage;

    @Autowired
    private FacilityPage facilityPage;

    @FindBy(id = "facilityId")
    @CacheLookup
    WebElement id;

    @FindBy(id = "name")
    @CacheLookup
    WebElement name;

    @FindBy(id = "countries")
    @CacheLookup
    WebElement country;

    @FindBy(id = "regions")
    @CacheLookup
    WebElement region;

    @FindBy(id = "districts")
    @CacheLookup
    WebElement district;

    @FindBy(id = "sub-districts")
    @CacheLookup
    WebElement subDistrict;

    @FindBy(id = "searchFacility")
    @CacheLookup
    WebElement search;

    private String searchResultElementID = "facilitiesReturnedBySearch";
    private String searchResultTableId = "searchResultTable";
    private String searchFacilityElementId = "searchFacility";


    public SearchFacilityPage withName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
        return this;
    }

    public SearchFacilityPage withCountry(String country) {
        Select selectCountry = new Select(this.country);
        selectCountry.selectByValue(country);
        return this;
    }

    public SearchFacilityPage withRegion(String regionName) {
        Select selectRegion = new Select(region);
        selectRegion.selectByValue(regionName);
        return this;
    }

    public SearchFacilityPage withDistrict(String districtName) {
        Select selectDistrict = new Select(district);
        selectDistrict.selectByValue(districtName);
        return this;
    }

    public SearchFacilityPage withSubDistrict(String subDistName) {
        Select selectSubDistrict = new Select(subDistrict);
        selectSubDistrict.selectByValue(subDistName);
        return this;
    }

    public void waitForPageToLoad(){
        elementPoller.waitForElementID(searchFacilityElementId, driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForSearchResultsToLoad(){
        elementPoller.waitForElementID(searchResultElementID, driver);
    }

    public void open() {
        homePage.openSearchFaclityPage();
        waitForPageToLoad();
    }

    public void search() {
        search.click();
        waitForSearchResultsToLoad();
    }

    public void clickEditLink(final String name, final String country, final String region, final String district, final String subDistrict, final String phoneNumber) {
        htmlTableParser.clickEditLink(driver, searchResultTableId, mapTableRowDataWithColumns(name, country, region, district, subDistrict, phoneNumber));
        facilityPage.waitForPageToLoad();
    }

    public void assertIfSearchReturned(String name, String country, String region, String district, String subDistrict, String phoneNumber) {
        assertTrue(htmlTableParser.hasRow(driver, searchResultTableId, mapTableRowDataWithColumns(name, country, region, district, subDistrict, phoneNumber)));
    }

    private HashMap<String, String> mapTableRowDataWithColumns(final String name, final String country, final String region, final String district, final String subDistrict, final String phoneNumber) {
        return new HashMap<String, String>(){{put("Name", name); put("Country", country); put("Region", region); put("District", district); put("Sub-District", subDistrict); put("Phone number", phoneNumber);}};
    }
}