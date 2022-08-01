package ui.form;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;;

import java.util.concurrent.TimeUnit;

public class FiltersPanel {

    private static final Logger log = Logger.getLogger(FiltersPanel.class);

    private final Button statusTabBtn = new Button(By.xpath("//button[@class='new-filters__button']//div[text()='Status ']"));
    private final Button statusListings = new Button(By.xpath("//div[@class='statuses']//button[text()=' Listings']"));
    private final Button statusSales = new Button(By.xpath("//div[@class='statuses']//button[text()=' Sales']"));
    private final Button statusOfferMade = new Button(By.xpath("//div[@class='statuses']//button[text()=' Offer Made']"));
    private final Button priceTab = new Button(By.xpath("//button[@class='new-filters__button']//div[text()='Price']"));
    private final Button collectionsTab = new Button(By.xpath("//button[@class='new-filters__button']//div[text()='Collections']"));

    public void openStatusTab() {
        statusTabBtn.press();
        log.info("Status tab is clicked");
    }

    public void statusOfferMade() {
        statusOfferMade.press();
        log.info("Status offer made is clicked");
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
