package ui.page.app.sell;

import core.control.Button;
import core.control.TextField;
import core.control.TextLabel;
import core.selenium.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.page.NftDetailsPage;

import java.util.ArrayList;
import java.util.List;

public class SellNftPage extends BasePage {

    private static final Logger log = Logger.getLogger(ListingDialog.class);

    //nft
    private final TextLabel nftImage = new TextLabel(By.xpath("//div[@class='nft-listing__left']//img"));
    private final TextLabel nftName = new TextLabel(By.xpath("//div[@class='nft-listing__left']//span[@class='text']"));
    private final TextLabel nftCollection =
            new TextLabel(By.xpath("//div[@class='nft-listing__left']//div[contains(@class,'collection-name')]"));
    private final TextLabel nftLikes =
            new TextLabel(By.xpath("//div[@class='nft-listing__left']//div[@class='icon-button-text like-action-wrapper']//div[@class='text-footnote']"));
    private final TextLabel nftPrice = new TextLabel(By.xpath("//div[@class='nft-listing__left']//span[@class='price-amount']"));
    private final TextLabel nftCurrency = new TextLabel(By.xpath("//div[@class='nft-listing__left']//div[@class='nft-price-wrapper']//img"));
    private final TextLabel nftTokenId = new TextLabel(By.xpath("//div[@class='nft-listing__left-token-id text-body']"));

    //center
    private final TextLabel mainTitle = new TextLabel(By.xpath("//div[@class='nft-listing']/h2"));
    private final Button currencyMenu =
            new Button(By.xpath("//div[@class='nft-listing__center']//div[@class='input-wrapper']//div[@class='dropdown__toggle']//button"));
    private final TextLabel listOfCurrencies = new TextLabel(By.xpath("//div[@class='dropdown__list']//p"));
    private String itemCurrency = "//div[@class='dropdown__list']//p[text()='%s']";
    private final TextField inputPrice = new TextField(By.xpath("//div[@class='nft-listing__center']//input[@type='number']"));
    private final TextField priceDiv = new TextField(By.xpath("//div[@class='field-wrapper error currency-price-bottom form-price-field']"));
    private final TextLabel errorMessage = new TextLabel(By.xpath("//span[@class='error-message']"));
    private final TextLabel scheduleTitle = new TextLabel(By.xpath("//div[@class='nft-listing-section']//h4"));
    private final TextLabel scheduledBody = new TextLabel(By.xpath("//div[@class='nft-listing-section']//p"));
    private final Button dateSwitch = new Button(By.cssSelector("input[class='switch-input']"));
    private final Button clickDateSwitch = new Button(By.xpath("//label[contains(@class,'nft-listing-section')]"));
    private final Button dateMenu = new Button(By.xpath("//div[@class='nft-listing-section__content']//div[@class='dropdown__toggle']"));
    private final TextLabel defaultSelectedDate = new TextLabel(By.xpath("//div[@class='nft-listing-section__content']//div[@class='dropdown__toggle']"));
    private final String dateItems = "//div[@class='nft-listing-section__content']//div[@class='dropdown__list']//div[contains(@class,'list-item')]";
    private String itemDate = "//div[@class='dropdown__list']//p[text()='%s']";
    private String allDates = "//div[@class='nft-listing-section__content']//div[@class='dropdown__list']//div[contains(@class,'list-item')]";

    //summary
    private final TextLabel listingEndsPhrase = new TextLabel(By.xpath("//div[@class='nft-listing__right']//div[@class='ending-content__heading text-body']"));
    private final TextLabel listingEndsDate = new TextLabel(By.xpath("//div[@class='nft-listing__right']//div[@class='ending-content__date text-body']"));
    private final TextLabel summaryPrice = new TextLabel(By.xpath("//div[@class='nft-listing__right']//div[@class='price-content__value']//h3"));
    private final TextLabel summaryCurrency = new TextLabel(By.xpath("//div[@class='nft-listing__right']//img"));
    private final TextLabel percent = new TextLabel(By.xpath("//div[contains(@class,'price-percent__value price-percent')]//div[@class='content']"));
    private final TextLabel feesTitle = new TextLabel(By.xpath("//div[@class='nft-listing__right']//p[@class='text-body']"), 0);
    private final TextLabel feesBody = new TextLabel(By.xpath("//div[@class='nft-listing__right']//p[@class='text-body']"), 1);
    private final TextLabel feesValue = new TextLabel(By.xpath("//div[contains(@class,'fees')]//p[@class='fees__platform']"));
    private final TextLabel royalties = new TextLabel(By.xpath("//div[contains(@class,'fees')]//p[@class='fees__royalty']"));
    private final TextLabel youWillReceive = new TextLabel(By.xpath("//div[contains(@class,'fees')]//div[@class='text-body']"));
    private final Button listItBtn = new Button(By.xpath("//button[.//*[text()=' LIST IT ']]"));

    public SellNftPage() {
        super(By.xpath("//div[@class='nft-listing']"));
        log.info("Sell nft page is opened");
    }

    public NftDetailsPage clickOnNft() {
        nftName.getElement().click();
        return new NftDetailsPage();
    }

    public String getNftImageSrc() {
        return nftImage.getElement().getAttribute("src");
    }

    public String getNftName() {
        return nftName.getText();
    }

    public String getNftCollection() {
        return nftCollection.getText();
    }

    public String getNftPrice() {
        return nftPrice.getText();
    }

    public String getNftCurrency() {
        return nftCurrency.getElement().getAttribute("src");
    }

    public String getNftTokenId() {
        return nftTokenId.getText();
    }

    //center
    public String getNftLikes() {
        return nftLikes.getText();
    }

    public String getMainTitle() {
        return mainTitle.getText();
    }

    public void openCurrencyMenu() {
        currencyMenu.press();
    }

    public void selectCurrency(String currency) {
        new Button(By.xpath(String.format(itemCurrency, currency))).press();
    }

    public void setPrice(String price) {
        inputPrice.setValue(price);
    }

    public boolean isValidPrice() {
        return Boolean.parseBoolean(priceDiv.getElement().getAttribute("hidden"));
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getScheduledTitle() {
        return scheduleTitle.getText();
    }

    public String getScheduledBody() {
        return scheduledBody.getText();
    }

    public void switchDate() {
        clickDateSwitch.press();
    }

    public void openDateMenu() {
        dateMenu.press();
    }

    public String getDefaultDate() {
        return defaultSelectedDate.getText();
    }

    public List<String> getAllDates() {
        List<String> dates = new ArrayList<>();
        DriverManager.getInstance().getDriver().findElements(By.xpath(dateItems)).forEach(elem ->
                dates.add(elem.getText()));
        return dates;
    }

    public void selectDate(String date) {
        new Button(By.xpath(String.format(itemDate, date))).press();
    }

    //summary
    public String getListingEndsPhrase() {
        return listingEndsPhrase.getText();
    }

    public String getListingEndsDate() {
        return listingEndsDate.getText();
    }

    public String getSummaryPrice() {
        return summaryPrice.getText();
    }

    public String getPercent() {
        return percent.getText();
    }

    public boolean isPercentHidden() {
        return Boolean.parseBoolean(percent.getElement().getAttribute("hidden"));
    }

    public String getFeesTitle() {
        return feesTitle.getText();
    }

    public String getFeesBody() {
        return feesBody.getText();
    }

    public String getFeesValue() {
        return feesValue.getText();
    }

    public String getRoyalties() {
        return royalties.getText();
    }

    public String getYouWillReceivePhrase() {
        return youWillReceive.getText().replaceAll("\n", " ");
    }

    public ListingDialog listIt() {
        listItBtn.press();
        return new ListingDialog();
    }

}
