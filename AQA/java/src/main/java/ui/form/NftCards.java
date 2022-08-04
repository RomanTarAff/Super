package ui.form;

import core.control.Button;
import core.control.TextLabel;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class NftCards {

    private static final Logger log = Logger.getLogger(NftCards.class);

    private final Button threeDotMenu = new Button(By.cssSelector("div[class='three-dots-trigger']"));
    private String menuItem ="//div[@class='dropdown-content three-dots-content']//div[text()=' %s']";
    private String nftItem ="//*[contains(@class,'nft-card')]//span[text()='%s']";
    private String nftCollection ="//*[contains(@class,'nft-card')][.//div[text()='%s']]//div[contains(@class,'collection-info-tooltip')]";
    private String nftImg ="//*[contains(@class,'nft-card')][.//div[text()='%s']]//img";
    private String nftLikes ="//*[contains(@class,'nft-card')][.//div[text()='%s']]//div[contains(@class,'like-action')]/div[@class='text-footnote']";
    private Button firstNft = new Button(By.cssSelector("div[class='nft-card cursor-pointer']"));

    public void openThreeDotMenu() {
        threeDotMenu.press();
    }

    public void sellNFT() {
        Button b = new Button(By.xpath(String.format(menuItem, "Sell NFT")));
        b.press();
    }

    public void makeOffer() {
        Button b = new Button(By.xpath(String.format(menuItem, "Make offer")));
        b.press();
    }

    public void cancelOffer() {
        Button b = new Button(By.xpath(String.format(menuItem, "Cancel offer")));
        b.press();
    }

    public void cancelListing() {
        Button b = new Button(By.xpath(String.format(menuItem, "Cancel listing")));
        b.press();
    }

    public String getNftName(String nft) {
        return new TextLabel(By.xpath(String.format(nftItem, nft))).getText();
    }

    public boolean isNftNameDisplayed(String nft) {
        TextLabel name = new TextLabel(By.xpath(String.format(nftItem, nft)));
        return name.isPresent();
    }

    public String getNftCollection(String nft) {
        return new TextLabel(By.xpath(String.format(nftCollection, nft))).getText();
    }

    public String getNftImg(String nft) {
        return new TextLabel(By.xpath(String.format(nftImg, nft))).getElement().getAttribute("src");
    }

    public String getNftLikes(String nft) {
        return new TextLabel(By.xpath(String.format(nftLikes, nft))).getText();
    }

}
