package ui;

import api.enums.Account;
import base.BaseTests;
import core.selenium.DriverManager;
import core.selenium.TargetFactory;
import core.selenium.wait.DriverWait;
import org.openqa.selenium.Dimension;
import ui.enums.Page;
import ui.page.metamask.*;

import static core.config.ConfigurationManager.configuration;

public class BaseUiTests extends BaseTests {

    private final Dimension dimension = new Dimension(1920, 1080);

    protected void initMetamask(Account account) {
        DriverManager.getInstance().setDriver(new TargetFactory().createInstance("chrome"));
        DriverManager.getInstance().getDriver().get(configuration().url());
        DriverManager.getInstance().getDriver().manage().window().setSize(dimension);
        DriverWait.turnOnImplicitWaits();
        sleep(4);
        BasePage.switchTo(Page.METAMASK);
        WelcomePage welcomePage = new WelcomePage();
        ImportPage importPage = welcomePage.start();
        AgreePage agreePage = importPage.importAcc();
        SecretPage secretPage = agreePage.agree();
        secretPage.setSecret("smoke fit skull dilemma between often feel approve forum drink mind favorite");
        secretPage.setPassword("Qwaszx@1");
        secretPage.confirmPassword("Qwaszx@1");
        secretPage.apply();
        CongratsPage congratsPage = secretPage.importAcc();
        WhatsNewPage whatsNewPage = congratsPage.submit();
        WalletPage walletPage = whatsNewPage.close();
        walletPage.openNetworkDropdown();
        walletPage.showTestNetworks();
        walletPage.enableTestNetworks();
        walletPage.openNetworkDropdown();
        walletPage.selectRinkeby();
        MainPage mainPage = new MainPage();
        mainPage.logo();
        mainPage.openAccountMenu();
        ImportPrivateKeyPage importPrivateKeyPage = mainPage.importPrivateKey();
        importPrivateKeyPage.setPk(account.getPrivateKey());
        importPrivateKeyPage.importKey();
        BasePage.switchTo(Page.MAIN);
        HomePage homePage = new HomePage();
        homePage.header.connectWallet();
        homePage.header.selectMetamask();
        BasePage.switchTo(Page.METAMASK);
        walletPage.reload();
        ConnectWalletPage connectWalletPage = new ConnectWalletPage();
        connectWalletPage.approve();
        connectWalletPage.approve();
        connectWalletPage.reload();
        connectWalletPage.reload();
        SignPage signPage = new SignPage();
        signPage.sign();
        BasePage.switchTo(Page.MAIN);
    }

    protected void initBrowserWithoutMetamask() {
        DriverManager.getInstance().setDriver(new TargetFactory().createInstance("chrome"));
        DriverManager.getInstance().getDriver().get(configuration().url());
        DriverManager.getInstance().getDriver().manage().window().maximize();
        DriverWait.turnOnImplicitWaits();
        BasePage.switchTo(Page.METAMASK);
        BasePage.switchTo(Page.MAIN);
    }

}
