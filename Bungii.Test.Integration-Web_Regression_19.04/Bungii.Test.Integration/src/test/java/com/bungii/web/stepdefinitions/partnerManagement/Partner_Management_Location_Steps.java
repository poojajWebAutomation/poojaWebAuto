package com.bungii.web.stepdefinitions.partnerManagement;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partnerManagement.PartnerManagement_LocationPage;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.error;

public class Partner_Management_Location_Steps extends DriverBase {
    ActionManager action = new ActionManager();
    PartnerManagement_LocationPage Page_PartnerManagement_Location = new PartnerManagement_LocationPage();
    private static LogUtility logger = new LogUtility(Partner_Management_Location_Steps.class);

    @Then("^Only \"([^\"]*)\" should be displayed$")
    public void only_something_should_be_displayed(String partner) throws Throwable {
        try{
      switch (partner){
          case "One partner location":
              List<WebElement> onlyOnePartnersDisplayed = Page_PartnerManagement_Location.Text_PartnerLength();
              testStepAssert.isTrue(onlyOnePartnersDisplayed.size()==1,"Only one partner should be displayed","Only one partner is displayed","More Than one partner is displayed");
              break;
          case "Multiple partners locations":
              Thread.sleep(4000);
              List<WebElement> multiplePartnersDisplayed = Page_PartnerManagement_Location.Text_PartnerLength();
              testStepAssert.isTrue(multiplePartnersDisplayed.size() >1,"Multiple partners should be displayed","Multiple partners are displayed","Multiple partners are not displayed");
              break;
      }
      log("I should be able to see "+partner,"I could see "+partner,false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^All the partners are in alphabetical order$")
    public void all_the_partners_are_in_alphabetical_order() throws Throwable {
        try{
        List<WebElement> allPartners = Page_PartnerManagement_Location.List_AllPartners();
        ArrayList<String> tabs = new ArrayList<String>();
        for (WebElement partnerName :allPartners) {
            tabs.add(partnerName.getText());
        }
        boolean arePartnersOrderedAlphabetically = Ordering.natural().isOrdered(tabs);;
        testStepAssert.isTrue(arePartnersOrderedAlphabetically,"Partners should be alphabetically ordered","Partners are alphabetically ordered","Partners are not alphabetically ordered");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I should see the text \"([^\"]*)\" highlighted$")
    public void i_should_see_the_text_something_highlighted(String text) throws Throwable {
        try{
        String yellowHighlight = PropertyUtility.getDataProperties("text.yellow.highlight");
        String expectedHighlightForTextInSearchBar = action.getCssBackgroundColor(Page_PartnerManagement_Location.Text_HighlightedTextInSearchBar());
        testStepAssert.isEquals(expectedHighlightForTextInSearchBar,yellowHighlight,"The text in the searchbar should be highlighed by yellow color",
                "The text in the searchbar is highlighed by yellow color","The text in the searchbar is not highlighed by yellow color");
        String searchedText = action.getText(Page_PartnerManagement_Location.Text_HighlightedTextInSearchBar());
        testStepAssert.isEquals(searchedText,text,text+" should be present in the search bar",text+" is present in the search bar",text+" is not present in the search bar");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The earliest schedule time for solo should be \"([^\"]*)\" and for duo \"([^\"]*)\"$")
    public void the_earliest_schedule_time_for_solo_should_be_something_and_for_duo_something(String soloTime, String duoTime) throws Throwable {
      try{
        String forSolo = action.getText(Page_PartnerManagement_Location.Text_SoloEarliestScheduleTime());
        String forDuo= action.getText(Page_PartnerManagement_Location.Text_DuoEarliestScheduleTime());
        testStepAssert.isEquals(forSolo,soloTime,"The earliest schedule time for solo delivery should be " +soloTime,
                "The earliest schedule time for solo delivery is " +soloTime,"The earliest schedule time for solo delivery is not" +soloTime);
        testStepAssert.isEquals(forDuo,duoTime,"The earliest schedule time for duo delivery should be " +duoTime,
                "The earliest schedule time for duo delivery is " +duoTime,"The earliest schedule time for duo delivery is not" +duoTime);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
