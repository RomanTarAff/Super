package ui.page.app;

import com.epam.ta.reportportal.ws.annotations.In;
import core.control.Button;
import core.control.TextField;
import core.control.TextLabel;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.NftCards;

public class MyProfilePage extends BasePage {

    private static final Logger log = Logger.getLogger(MyProfilePage.class);
    public NftCards nftCards = new NftCards();

    private final TextLabel name = new TextLabel(By.cssSelector("h3[class='name']"));
    private final TextLabel address = new TextLabel(By.cssSelector("p[class='address']"));
    private final Button editProfile = new Button(By.xpath("div[class='profile-actions']"));
    private final TextLabel items = new TextLabel(By.xpath("//div[@class='stat-wrapper'][.//div[@class='stat-type'][text()=' items ']]/div[@class='stat']"));
    private final TextLabel followers = new TextLabel(By.xpath("//div[@class='stat-wrapper'][.//div[@class='stat-type'][text()=' followers ']]/div[@class='stat']"));
    private final TextLabel avgSale = new TextLabel(By.xpath("//div[@class='stat-wrapper'][.//div[@class='stat-type'][text()=' avg. sale ']]/div[@class='stat']"));
    private final TextLabel traded = new TextLabel(By.xpath("//div[@class='stat-wrapper'][.//div[@class='stat-type'][text()=' traded ']]/div[@class='stat']"));
    private final TextLabel searchResultsCount = new TextLabel(By.xpath("//div[@class='nfts-social-list-filter__search-result']"));
    private final TextField searchInput = new TextField(By.cssSelector("input[placeholder='Search NFTs']"));
    private final String tab = "//div[contains(@class,'tab-container')][text()='%s']";

    public MyProfilePage() {
        super(By.cssSelector("div[class='profile-layout']"));
        sleep(3);
        log.info("My Profile page is opened");
    }

    public String getName() {
        return name.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getItems() {
        return items.getText();
    }

    public String getFollowers() {
        return followers.getText();
    }

    public String getAvgSale() {
        return avgSale.getText();
    }

    public String getTrader() {
        return traded.getText();
    }

    public String getSearchResultCount() {
        return searchResultsCount.getText().split(" ")[0];
    }

    public void searchNft(String nft) {
        searchInput.setAndConfirmValue(nft);
        sleep(5);
    }

    public void openTab(String tabTitle) {
        Button b = new Button(By.xpath(String.format(tab, tabTitle)));
        b.press();
        sleep(5);
    }

    public void reloadPageUntilMintedNftPresent(int count) {
        int currentCount = Integer.parseInt(getSearchResultCount());
        log.info(String.format("Waiting count is %d", count));
        log.info(String.format("Current number of nft %d", currentCount));
        boolean isNewAccount = currentCount == count;
        if(!isNewAccount) {
            this.reload();
            sleep(4);
            reloadPageUntilMintedNftPresent(count);
        }
    }
}
