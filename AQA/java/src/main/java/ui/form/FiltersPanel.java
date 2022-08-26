package ui.form;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;;

import java.util.concurrent.TimeUnit;

public class FiltersPanel {

    private static final Logger log = Logger.getLogger(FiltersPanel.class);

    private final Button statusTabBtn = new Button(By.xpath("//button[@class='new-filters__button']//div[text()='Status ']"));
    private final Button statusListings = new Button(By.xpath("//div[@class='statuses']//button[text()=' Listings']"));
    private final Button mintsBtn = new Button(By.xpath("//div[@class='statuses']//button[text()=' Mints']"));
    private final Button statusSales = new Button(By.xpath("//div[@class='statuses']//button[text()=' Sales']"));
    private final Button statusOfferMade = new Button(By.xpath("//div[@class='statuses']//button[text()=' Offer Made']"));
    private final Button priceTab = new Button(By.xpath("//button[@class='new-filters__button']//div[text()='Price']"));
    private final Button collectionsTab = new Button(By.xpath("//button[@class='new-filters__button']//div[text()='Collections']"));

    public void openStatusTab() {
        statusTabBtn.scrollToElementAndPress();
        log.info("Status tab is clicked");
    }

    public void statusOfferMade() {
        statusOfferMade.scrollToElementAndPress();
        log.info("Status offer made is clicked");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void mints() {
        mintsBtn.scrollToElementAndPress();
        log.info("Status Mints is clicked");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
