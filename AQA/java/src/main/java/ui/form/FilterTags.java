package ui.form;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class FilterTags {

    private static final Logger log = Logger.getLogger(FilterTags.class);

    private final Button clearFiltersBtn =
            new Button(By.xpath("//div[@class='new-filters__tags activity-filtered-list-tags']//span[text()='Clear Filters']"));

    private String closeFiltersBtn = "//button[@class='new-filters__tag tag'][.//div[text()='%s']]/*[@class='close']";
    private String myOffersStatusBtn = "//div[@class='switch-item__label'][text()='%s']";

    public Button getCloseFilterBtn(String btn) {
        return new Button(By.xpath(String.format(closeFiltersBtn, btn)));
    }

    public Button getMyOffersStatusBtn(String btn) {
        return new Button(By.xpath(String.format(myOffersStatusBtn, btn)));
    }

    public void clearAllFilters() {
        clearFiltersBtn.pressWithout();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Clear all filters");
    }

    public void closeFilter(String filter) {
        getCloseFilterBtn(filter).pressWithout();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.format("Filter %s was cleared", filter));
    }

    public void closeFilterWithScrollUp(String filter) {
        getCloseFilterBtn(filter).press();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.format("Filter %s was cleared", filter));
    }

    public void closeFilterWithScrollDown(String filter) {
        getCloseFilterBtn(filter).pressWithScrollDown();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.format("Filter %s was cleared", filter));
    }




}
