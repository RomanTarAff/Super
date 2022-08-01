package ui.page.app.sell;

import core.control.Button;
import core.control.TextLabel;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.page.NftDetailsPage;

public class ListingDialog extends BasePage {

    private static final Logger log = Logger.getLogger(ListingDialog.class);

    private final TextLabel title = new TextLabel(By.xpath("//div[@class='text-subhead']"));

    private final TextLabel firstStepTitle = new TextLabel(By.xpath("//div[@class='step-section__title']"), 0);
    private final TextLabel firstStepSubTitle = new TextLabel(By.xpath("//div[@class='step-section__subtitle']"), 0);
    private final TextLabel firstStepStatus = new TextLabel(By.xpath("//div[@class='step-status-mark step-status-mark__completed']"), 0);

    private final TextLabel secondStepTitle = new TextLabel(By.xpath("//div[@class='step-section__title']"), 1);
    private final TextLabel secondStepSubTitle = new TextLabel(By.xpath("//div[@class='step-section__subtitle']"), 1);
    private final TextLabel secondStepStatus = new TextLabel(By.xpath("//div[@class='step-status-mark step-status-mark__completed']"), 1);

    private final TextLabel thirdStepTitle = new TextLabel(By.xpath("//div[@class='step-section__title']"), 2);
    private final TextLabel thirdStepSubTitle = new TextLabel(By.xpath("//div[@class='step-section__subtitle']"), 2);
    private final TextLabel thirdStepStatus = new TextLabel(By.xpath("//div[@class='step-status-mark step-status-mark__completed']"), 2);

    private final TextLabel listingStatus = new TextLabel(By.xpath("//div[@class='step-modal__footer-finished']"));
    private final TextLabel listingStatusFailed = new TextLabel(By.xpath("//div[@class='step-status-mark step-status-mark__failed']"));
    private final TextLabel modalError = new TextLabel(By.xpath("//div[@class='step-modal__footer-error']"));

    private final Button viwYourItemBtn = new Button(By.xpath("//button[text()=' View your item ']"));
    private final Button closeModal = new Button(By.cssSelector("//button[contains(@class,'close')]"));

    public ListingDialog() {
        super(By.xpath("//div[text()='Complete your listing']"));
        log.info("Listing dialog is opened");
    }

    public String getTitle() {
        return title.getText();
    }

    //1st step
    public String getFirstStepTitle() {
        return firstStepTitle.getText();
    }

    public String getFirstStepSubTitle() {
        return firstStepSubTitle.getText();
    }

    public boolean isFirstStepNotReady() {
        return Boolean.parseBoolean(firstStepStatus.getElement().getAttribute("hidden"));
    }

    //2st step
    public String getSecondStepTitle() {
        return secondStepTitle.getText();
    }

    public String getSecondStepSubTitle() {
        return secondStepSubTitle.getText();
    }

    public boolean isSecondStepNotReady() {
        return Boolean.parseBoolean(secondStepStatus.getElement().getAttribute("hidden"));
    }

    //3st step
    public String getThirdStepTitle() {
        return thirdStepTitle.getText();
    }

    public String getThirdStepSubTitle() {
        return thirdStepSubTitle.getText();
    }

    public boolean isThirdStepNotReady() {
        return Boolean.parseBoolean(thirdStepStatus.getElement().getAttribute("hidden"));
    }

    //listing
    public boolean isListingNotReady() {
        return Boolean.parseBoolean(listingStatus.getElement().getAttribute("hidden"));
    }

    public String getListingFailedStatus() {
        return listingStatusFailed.getText();
    }

    public String getModalErrorMessage() {
        return modalError.getText();
    }

    public NftDetailsPage viewYourItem() {
        viwYourItemBtn.press();
        return new NftDetailsPage();
    }

    public void waitForListingCompleted() {
        DriverWait.waitElementPresent(By.xpath("//div[@class='step-modal__footer-finished'][text()=' Listing Created!']"));
    }

    public void closeDialog() {
        closeModal.press();
    }

}
