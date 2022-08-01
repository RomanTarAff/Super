package ui.page.app.collection;

import core.control.Button;
import core.control.TextField;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.FiltersPanel;
import ui.form.NftCards;
import ui.form.Table;

public class CollectionDetailsPage extends BasePage {

    private static final Logger log = Logger.getLogger(CollectionDetailsPage.class);

    private final String tab = "//div[contains(@class,'tab-container')][text()='%s']";
    private final TextField search = new TextField(By.cssSelector("input[placeholder='Search NFTs']"));

    public Table table = new Table();
    public FiltersPanel filterPanel = new FiltersPanel();
    public NftCards nftCards = new NftCards();

    public CollectionDetailsPage() {
        super(By.xpath("//div[@class='collection-layout__banner']"));
        log.info("Collection details page is opened");
    }

    public void openTab(String tabTitle) {
        new Button(By.xpath(String.format(tab, tabTitle))).press();
    }

    public void searchNft(String nft) {
        search.setAndConfirmValue(nft);
    }
}
