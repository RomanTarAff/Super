package ui.page.metamask;

import core.control.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class WalletPage extends BasePage {

    private static final Logger log = Logger.getLogger(WalletPage.class);

    private final Button dropdownBtn = new Button(By.xpath("//div[contains(@class,'network-display network-display--clickable chip')]"));
    private final Button enableTestNetworksBtn = new Button(By.cssSelector("a[class='network-dropdown-content--link']"));
    private final Button enableTestNetworksSettingsBtn =
            new Button(By.xpath("//div[@data-testid='advanced-setting-show-testnet-conversion']//div[@class='toggle-button__status']"), 1);
    private final Button selectRinkeby = new Button(By.xpath("//li[.//span[contains(text(),'Rinkeby')]]"));

    public WalletPage() {
        super(By.xpath("//div[contains(@class,'network-display network-display--clickable chip')]"));
        log.info("Wallet page is opened");
    }

    public void openNetworkDropdown() {
        dropdownBtn.press();
        sleep(1);
        log.info("Dropdown is clicked");
    }

    public void showTestNetworks() {
        enableTestNetworksBtn.press();
        sleep(1);
        log.info("Show test networks is clicked");
    }

    public void enableTestNetworks() {
        enableTestNetworksSettingsBtn.press();
        sleep(1);
        log.info("Enable test networks is clicked");
    }

    public void selectRinkeby() {
        selectRinkeby.press();
        sleep(1);
        log.info("Rinkeby is clicked");
    }

}
