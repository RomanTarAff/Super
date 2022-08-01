package ui.buy;

import api.enums.*;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import core.selenium.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.BasePage;
import ui.BaseUiTests;
import ui.enums.Page;
import ui.page.NftDetailsPage;
import ui.page.app.UnreviewedCollectionDialog;
import ui.page.app.buy.CompletePurchaseDialog;
import ui.page.metamask.ConfirmTransactionPage;
import ui.page.metamask.HomePage;

import java.util.List;

public class BuyNowTests extends BaseUiTests {

    private SearchNftResponse nft4KB;
    private SearchNftResponse nftReject;
    private SearchNftResponse nftETH;

    @BeforeClass
    public void getNftOnSale() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.ON_SALE.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(30)
                .sort(Sort.MOST_RECENT.getValue())
                .build();
        List<SearchNftResponse> nfts = nftService.searchNftItems(search, MINT_TOKEN)
                .asClass(SearchNftResponseList.class).getNfts();
        nft4KB = nfts.stream().filter(nft -> nft.getListing().getPrice().getSymbol().equals(Currency.FOUR_KB.getValue())).findFirst().get();
        nftETH = nfts.stream().filter(nft -> nft.getListing().getPrice().getSymbol().equals(Currency.ETH.getValue())).findFirst().get();
        nftReject = nfts.get(nfts.size() - 1);
    }

    @BeforeMethod
    public void initBrowser() {
        initMetamask(Account.BUY);
    }

    @AfterMethod
    public void closeJob() {
        DriverManager.getInstance().closeBrowser();
    }

    @Test(testName = "Buy now reject")
    public void buyNowReject() {
        HomePage homePage = new HomePage();
        homePage.header.search(nftReject.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.buyNow();
        CompletePurchaseDialog completePurchaseDialog = unreviewedCollectionDialog.agree();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.reject();
        BasePage.switchTo(Page.MAIN);
        soft.assertEquals(completePurchaseDialog.getPurchaseStatusText(), "Purchase Failed!");
        soft.assertEquals(completePurchaseDialog.getPurchaseError(), "MetaMask Tx Signature: User denied transaction signature.");
        soft.assertAll();
    }

    @Test(testName = "Buy now 4KB")
    public void buyNow4KB() {

        double totalSaleAmountValue = Double.parseDouble(getTestCollectionData().getTotalSaleAmount()) * Math.pow(10, -18);

        HomePage homePage = new HomePage();
        homePage.header.search(nft4KB.getName());
        NftDetailsPage nftDetailsPage = homePage.header.openFirstNftFromSearch();
        UnreviewedCollectionDialog unreviewedCollectionDialog = nftDetailsPage.buyNow();
        soft.assertEquals(unreviewedCollectionDialog.getTitle(), "This is an unreviewed collection");
        soft.assertEquals(unreviewedCollectionDialog.getItemName(), "Name");
        soft.assertEquals(unreviewedCollectionDialog.getItemTotalSales(), "Total Sales");
        soft.assertEquals(unreviewedCollectionDialog.getItemTotalVolume(), "Total Volume");
        soft.assertEquals(unreviewedCollectionDialog.getItemContractAddress(), "Contract Address");
        soft.assertEquals(unreviewedCollectionDialog.getItemTotalItems(), "Total Items");

        soft.assertEquals(unreviewedCollectionDialog.getCollectionName(), getTestCollectionData().getName());
        soft.assertEquals(unreviewedCollectionDialog.getTotalSales(), getTestCollectionData().getTotalSaleCount());
        if (totalSaleAmountValue < 0.01) {
            soft.assertTrue(unreviewedCollectionDialog.getPrice().contains("< 0.01"));
        } else {
            soft.assertTrue(unreviewedCollectionDialog.getPrice().contains(String.valueOf(totalSaleAmountValue).substring(0, 6)));
        }
//       soft.assertEquals(unreviewedCollectionDialog.getPriceUsd()).not.toBeNull();
        soft.assertTrue(unreviewedCollectionDialog.getContractAddress().contains(getTestCollectionData().getContractAddress()));
        soft.assertEquals(unreviewedCollectionDialog.getTotalItems(), getTestCollectionData().getTotalNFTItems());
        soft.assertEquals(unreviewedCollectionDialog.getAgreement(),
                "I have reviewed this information and verified that this is the correct collection. I also understand that blockchain transactions are irreversible");

        //purchase dialog
        CompletePurchaseDialog completePurchaseDialog = unreviewedCollectionDialog.agree();
        BasePage.switchTo(Page.METAMASK, true);
        ConfirmTransactionPage confirmTransactionPage = new ConfirmTransactionPage();
        confirmTransactionPage.confirm();
        BasePage.switchTo(Page.MAIN);
        completePurchaseDialog.waitForPurchaseCompleted();

        soft.assertEquals(completePurchaseDialog.getTitle(), "Complete Purchase");
        soft.assertEquals(completePurchaseDialog.getNftName(), nft4KB);
        soft.assertEquals(completePurchaseDialog.getNftCollection(), getTestCollectionData().getName());
        soft.assertNotNull(completePurchaseDialog.getNftImageSrc(), "Image should not be null");
        soft.assertEquals(completePurchaseDialog.getNftTokenId(), String.format("Token ID %s...", nft4KB.getTokenId().substring(0, 6)));
        //soft.assertEquals(completePurchaseDialog.getOwner(), userMint.getUsername());
        soft.assertEquals(completePurchaseDialog.getOwnerId(), nft4KB.getOwnerAddress());
        soft.assertEquals(completePurchaseDialog.getRoyalties(), "Creator Royalties: 0%");
        soft.assertEquals(completePurchaseDialog.getFeesValue(), "GigaMart fee: 2.5% 0%");

        soft.assertEquals(completePurchaseDialog.getFirstStepTitle(), "Confirm your transaction. Waiting for signature...");
        soft.assertFalse(completePurchaseDialog.isFirstStepNotReady(), "First step should be ready");
        soft.assertEquals(completePurchaseDialog.getFirstStepSubTitle(), "Accept the signature request in your wallet extension and wait for your transaction to process.");

        soft.assertFalse(completePurchaseDialog.isPurchaseNotReady(), "Purchase should be ready");
        soft.assertEquals(completePurchaseDialog.getPurchaseStatusText(), "Purchase Complete!");
        NftDetailsPage nftDetailsPage1 = completePurchaseDialog.viewYourItem();

        //nft details
        soft.assertFalse(nftDetailsPage1.isBuyNowDisplayed(), "Buy now btn should be not displayed");
        soft.assertTrue(nftDetailsPage1.isSellNftDisplayed(), "Sell NFT btn should be displayed");
        soft.assertEquals(nftDetailsPage1.getNftOwner(), "Owned by  you");
        soft.assertEquals(nftDetailsPage1.getNftOwnerId(), Account.BUY.getAddress());

        //item history
        soft.assertEquals(nftDetailsPage1.getItemHistoryEvent(1), "Sold");
        soft.assertEquals(nftDetailsPage1.getItemHistoryFrom(0), userMint.getUsername());
        soft.assertEquals(nftDetailsPage1.getItemHistoryTo(0), userBuy.getUsername());
        soft.assertEquals(nftDetailsPage1.getItemHistoryWhen(0), "1m ago");
        soft.assertAll();
    }


}
