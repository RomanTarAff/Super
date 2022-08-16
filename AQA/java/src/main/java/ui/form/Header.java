package ui.form;

import core.control.Button;
import core.control.TextField;
import org.openqa.selenium.By;
import ui.page.NftDetailsPage;
import ui.page.app.MyProfilePage;

import java.util.concurrent.TimeUnit;

public class Header {

    private final Button logoBtn = new Button(By.xpath("//a[contains(@class,'logo')]"));
    private final Button connectWalletBtn = new Button(By.xpath("//div[text()=' Connect Wallet ']"));
    private final Button selectMetamaskBtn = new Button(By.xpath("//h3[@class='provider-item__title'][text()='MetaMask']"));
    private final TextField search = new TextField(By.cssSelector("input[placeholder='Search NFTs, collections, and users']"));
    private final Button profileBtn = new Button(By.xpath("//div[contains(@class,'short-profile')]//button"));
    private final Button userInfo = new Button(By.xpath("button[class='action-item secondary header-short-profile__action']"));
    private final Button myProfileBtn = new Button(By.xpath("//div[@class='icon-text-item__text'][text()=' My Profile ']"));
    private final Button firstNftFromSearch
            = new Button(By
            .xpath("//section[contains(@class,'result-section')][.//h3[contains(text(),'NFT')]]//div[@class='result-section__list']//a[@class='result-item']"), 0);

    public void connectWallet() {
        connectWalletBtn.press();
    }

    public void selectMetamask() {
        selectMetamaskBtn.press();
    }

    public NftDetailsPage openFirstNftFromSearch() {
        firstNftFromSearch.press();
        return new NftDetailsPage();
    }

    public void logo() {
        logoBtn.press();
    }

    public void openUserMenu() {
        userInfo.press();
    }

    public MyProfilePage openMyProfile() {
        myProfileBtn.press();
        return new MyProfilePage();
    }

    public void search(String item) {
        search.setValue(item);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
