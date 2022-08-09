package ui;

import api.enums.Account;
import core.selenium.DriverManager;
import listener.TestListener;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.testng.annotations.*;
import ui.page.app.MyProfilePage;
import ui.page.metamask.ActivityPage;
import ui.page.metamask.HomePage;

import java.util.List;

import static org.testng.Assert.assertTrue;

@Listeners(value = TestListener.class)
public class StatusCheckTests extends BaseUiTests {

    private static final Logger log = Logger.getLogger(StatusCheckTests.class);

    @Test(testName = "Check activity last events")
    public void statusCheckActivity() {
        initBrowserWithoutMetamask();
        List<String> events = List.of("Minted", "Transfer", "Sold", "Burned");
        List<String> time = List.of("1m ago", "2m ago", "3m ago", "4m ago", "5m ago", "6m ago", "7m ago");

        HomePage homePage = new HomePage();
        ActivityPage activityPage = homePage.openActivityPage();

        //check events
        if(events.contains(activityPage.table.getEventValue(1))) {
            assertTrue(events.contains(activityPage.table.getEventValue(1)),
                    String.format("Event from 1 row is %s", activityPage.table.getEventValue(1)));

            //check when
            assertTrue(time.contains(activityPage.table.getWhen(1)),
                    String.format("Time from 1 row is %s", activityPage.table.getWhen(1)));
        }
        if(events.contains(activityPage.table.getEventValue(2))) {
            assertTrue(events.contains(activityPage.table.getEventValue(2)),
                    String.format("Event from 2 row is %s", activityPage.table.getEventValue(2)));

            //check when
            assertTrue(time.contains(activityPage.table.getWhen(2)),
                    String.format("Time from 2 row is %s", activityPage.table.getWhen(2)));
        }
    }

    @Test(testName = "Mint nft and check activity table")
    public void mintStatus() {
        initMetamask(Account.MINT);
        HomePage homePage = new HomePage();
        homePage.openUsersMenu();
        MyProfilePage myProfilePage = homePage.header.openMyProfile();
        String numberOfNftBeforeMint = myProfilePage.getSearchResultCount();
        mint();
        myProfilePage.reloadPageUntilMintedNftPresent(Integer.parseInt(numberOfNftBeforeMint) + 1);
        myProfilePage.reloadPageUntilMintedNameIsPresent(MINT_NFT_NAME);

        soft.assertEquals(myProfilePage.nftCards.getNftName(MINT_NFT_NAME), MINT_NFT_NAME,
                String.format("Minted nft name should be %s", MINT_NFT_NAME));
        soft.assertEquals(myProfilePage.nftCards.getNftCollection(MINT_NFT_NAME), getTestCollectionData().getName(),
                String.format("Minted nft collection should be %s", getTestCollectionData().getName()));
        soft.assertNotNull(myProfilePage.nftCards.getNftImg(MINT_NFT_NAME), "Minted nft image should be present");
        soft.assertEquals(myProfilePage.nftCards.getNftLikes(MINT_NFT_NAME), "0", "Minted nft likes should be 0");

        ActivityPage activityPage = homePage.openActivityPage();
        activityPage.table.filterTags.closeFilter("Sold");
        activityPage.table.filtersPanel.openStatusTab();
        activityPage.table.filtersPanel.mints();

        soft.assertEquals(activityPage.table.getEventValue(MINT_NFT_NAME), "Minted",
                String.format("Event for nft %s should be minted", MINT_NFT_NAME));
    }

    @AfterMethod
    public void closeJob() {
        DriverManager.getInstance().closeBrowser();
    }
}
