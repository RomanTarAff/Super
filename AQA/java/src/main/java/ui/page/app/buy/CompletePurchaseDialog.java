package ui.page.app.buy;

import core.control.Button;
import core.control.TextLabel;
import core.selenium.wait.DriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.page.NftDetailsPage;

public class CompletePurchaseDialog extends BasePage {

    private static final Logger log = Logger.getLogger(CompletePurchaseDialog.class);

    private final TextLabel title = new TextLabel(By.xpath("//div[@class='text-subhead']"));
    private final TextLabel nftImg = new TextLabel(By.xpath("//div[@class='nft-summary']//img"));
    private final TextLabel nftName = new TextLabel(By.xpath("//div[@class='nft-summary']//div[@class='title']//a[contains(@class,'nft-summary__link')]"));
    private final TextLabel nftCollection = new TextLabel(By.xpath("//div[@class='nft-summary']//div[contains(@class,'ui-collection')]//div[@class='text-overflow']"), 1);
    private final TextLabel nftTokenId = new TextLabel(By.xpath("//p[@class='nft-summary__token-address']"));
    private final TextLabel nftOwner = new TextLabel(By.xpath("//div[@class='text-overflow nft-summary__owner']"));
    private final TextLabel nftOwnerId = new TextLabel(By.xpath("//div[contains(@class,'ui-user-name nft-summary__owner-name')]/a"));
    private final TextLabel nftCreatedDate = new TextLabel(By.xpath("//p[@class='nft-summary__created']"));

    private final TextLabel currencyIcon = new TextLabel(By.xpath("//div[@class='nft-summary__price-container']//img"));
    private final TextLabel price = new TextLabel(By.xpath("//div[@class='nft-summary__price-container']//span[@class='currency-price']"));
    private final TextLabel priceUsd = new TextLabel(By.xpath("//div[@class='nft-summary__price-container']//span[@class='currency-price']"));
    private final TextLabel fees = new TextLabel(By.xpath("//p[@class='fees__platform']"));
    private final TextLabel royalties = new TextLabel(By.xpath("//p[@class='fees__royalty']"));
    private final TextLabel firstStepTitle = new TextLabel(By.xpath("//div[@class='step-section__title']"), 0);
    private final TextLabel firstStepSubTitle = new TextLabel(By.xpath("//div[@class='step-section__subtitle']"), 0);
    private final TextLabel firstStepStatus = new TextLabel(By.xpath("//div[@class='step-status-mark step-status-mark__completed']"), 0);
    private final TextLabel purchaseStatus = new TextLabel(By.xpath("//div[@class='step-status-mark step-status-mark__completed']"), 1);
    private final TextLabel purchaseStatusText = new TextLabel(By.xpath("//div[@class='step-modal__footer-finished']"));
    private final TextLabel purchaseErrorText = new TextLabel(By.xpath("//div[@class='step-modal__footer-error']"));
    private final Button viwYourItemBtn = new Button(By.xpath("//button[text()=' View your item ']"));

    public CompletePurchaseDialog() {
        super(By.cssSelector("div[class='modal-wrapper__content step-modal buy-now-modal']"));
        log.info("Complete Purchase Dialog is opened");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getNftImageSrc() {
        return nftImg.getElement().getAttribute("src");
    }

    public String getNftName() {
        return nftName.getText();
    }

    public String getNftCollection() {
        return nftCollection.getText();
    }

    public String getOwner() {
        return nftOwner.getText();
    }

    public String getOwnerId() {
        return nftOwnerId.getElement().getAttribute("href");
    }

    public String getNftTokenId() {
        return nftTokenId.getText();
    }

    //summary
    public String getSummaryCurrency() {
        return currencyIcon.getElement().getAttribute("src");
    }

    public String getPrice() {
        return price.getText();
    }

    public String getPriceUsd() {
        return priceUsd.getText();
    }

    public String getFeesValue() {
        return fees.getText();
    }

    public String getRoyalties() {
        return royalties.getText();
    }

    public String getFirstStepTitle() {
        return firstStepTitle.getText();
    }

    public String getFirstStepSubTitle() {
        return firstStepSubTitle.getText();
    }

    public boolean isFirstStepNotReady() {
        return Boolean.parseBoolean(firstStepStatus.getElement().getAttribute("hidden"));
    }

    public String getPurchaseStatusText() {
        return purchaseStatusText.getText();
    }

    public String getPurchaseError() {
        return purchaseErrorText.getText();
    }

    public boolean isPurchaseNotReady() {
        return Boolean.parseBoolean(purchaseStatus.getElement().getAttribute("hidden"));
    }

    public NftDetailsPage viewYourItem() {
        viwYourItemBtn.press();
        return new NftDetailsPage();
    }

    public void waitForPurchaseCompleted() {
        DriverWait.waitElementPresent(By.xpath("//div[@class='step-modal__footer-finished'][text()=' Purchase Complete!']"));
    }


}
