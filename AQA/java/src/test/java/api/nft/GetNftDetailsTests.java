package api.nft;

import api.BaseApiTests;
import api.enums.*;
import api.model.request.nft.SearchNftRequest;
import api.model.response.CodeMessageResponse;
import api.model.response.nft.NftDetails;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.*;

@Epic("Get nft details")
public class GetNftDetailsTests extends BaseApiTests {

    @Test(description = "NFT details with listing")
    public void nftDetailsWithListing() {

        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.ON_SALE.getValue()))
                .limit(1)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        //search nft
        SearchNftResponse nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        NftDetails nftDetails = nftService.getNftDetails(getTestCollectionData().getContractAddress(),
                        nft.getTokenId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(NftDetails.class);

        assertEquals(nftDetails.getContractType(), ContractType.ERC721.getValue(), "Contract type is invalid");
        assertEquals(nftDetails.getNftStatus(), NftStatus.ACTIVE.getValue(), "NFT should be active");
        assertEquals(nftDetails.getName(), nft.getName(), "NFT name is not correct");
        assertEquals(nftDetails.getBlockchain().getName(), Blockchain.ETH.getName(), "Blockchain name is invalid");
        assertEquals(nftDetails.getBlockchain().getNetwork(), Blockchain.ETH.getNetwork(), "Blockchain name is invalid");
        assertNotNull(nftDetails.getTokenId(), "Token id should not be null");
        assertTrue(nftDetails.getLikesCount() >= 0, "Likes should be >= 0");
        assertTrue(nftDetails.getWatchers() >= 0, "Watchers should be >= 0");
        assertTrue(nftDetails.getViews() >= 0, "Views should be >= 0");
        assertEquals(nftDetails.getContractAddress(), getTestCollectionData().getContractAddress(),
                "Contract address is not correct");
        assertNotNull(nftDetails.getId(), "id should not be null");
        assertNotNull(nftDetails.getTokens(), "Tokens should not be null");

        assertTrue(nftDetails.getOwner().getAddress().contains(Account.MINT.getAddress().toLowerCase()),
                "NFT owner should be MINT user");
        assertTrue(nftDetails.getStatuses().contains(Status.ON_SALE.getValue()), "Statuses should contains on sale");

        assertEquals(nftDetails.getCollection().getId(), getTestCollectionData().getId(),
                "Collection id is not correct");
        assertEquals(nftDetails.getCollection().getContractAddress(), getTestCollectionData().getContractAddress(),
                "Collection contract address is not correct");

        assertNotNull(nftDetails.getListing(), "NFT details listing should not be null");
        assertFalse(nftDetails.getCollection().isVerified(), "Collection should not be verified");
        assertNotNull(nftDetails.getCollection().getName(), "Collection name should not be null");
    }

    @Test(description = "NFT details with offers")
    public void nftDetailsWithOffers() {

        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.HAS_OFFERS.getValue()))
                .limit(1)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        //search nft
        SearchNftResponse nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        NftDetails nftDetails = nftService.getNftDetails(getTestCollectionData().getContractAddress(),
                        nft.getTokenId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(NftDetails.class);

        assertEquals(nftDetails.getContractType(), ContractType.ERC721.getValue(), "Contract type is invalid");
        assertEquals(nftDetails.getNftStatus(), NftStatus.ACTIVE.getValue(), "NFT should be active");
        assertEquals(nftDetails.getName(), nft.getName(), "NFT name is not correct");
        assertEquals(nftDetails.getBlockchain().getName(), Blockchain.ETH.getName(), "Blockchain name is invalid");
        assertEquals(nftDetails.getBlockchain().getNetwork(), Blockchain.ETH.getNetwork(), "Blockchain name is invalid");
        assertNotNull(nftDetails.getTokenId(), "Token id should not be null");
        assertTrue(nftDetails.getLikesCount() >= 0, "Likes should be >= 0");
        assertTrue(nftDetails.getWatchers() >= 0, "Watchers should be >= 0");
        assertTrue(nftDetails.getViews() >= 0, "Views should be >= 0");
        assertEquals(nftDetails.getContractAddress(), getTestCollectionData().getContractAddress(),
                "Contract address is not correct");
        assertNotNull(nftDetails.getId(), "id should not be null");
        assertNotNull(nftDetails.getTokens(), "Tokens should not be null");

        assertTrue(nftDetails.getOwner().getAddress().contains(Account.MINT.getAddress().toLowerCase()),
                "NFT owner should be MINT user");
        assertTrue(nftDetails.getStatuses().contains(Status.HAS_OFFERS.getValue()), "Statuses should contains has_offers");

        assertEquals(nftDetails.getCollection().getId(), getTestCollectionData().getId(),
                "Collection id is not correct");
        assertEquals(nftDetails.getCollection().getContractAddress(), getTestCollectionData().getContractAddress(),
                "Collection contract address is not correct");

        assertNotNull(nftDetails.getHighestOffer(), "Highest offer should not be null");
        assertFalse(nftDetails.getCollection().isVerified(), "Collection should not be verified");
        assertNotNull(nftDetails.getCollection().getName(), "Collection name should not be null");
    }

    @Test(description = "NFT details. Invalid contract address")
    public void nftDetailsInvalidContractAddress() {

        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.ON_SALE.getValue()))
                .limit(1)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        //search nft
        SearchNftResponse nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        CodeMessageResponse codeMessageResponse = nftService.getNftDetails("0x902dfd8f9f",
                        nft.getTokenId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "NotFoundError");
        assertEquals(codeMessageResponse.getMessage(), "Collection not found");
    }

    @Test(description = "NFT details. Invalid token id")
    public void nftDetailsInvalidTokenId() {

        SearchNftRequest search = SearchNftRequest.builder()
                .group(Group.IN_WALLET.getValue())
                .statuses(List.of(Status.ON_SALE.getValue()))
                .limit(1)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        //search nft
        SearchNftResponse nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        CodeMessageResponse codeMessageResponse = nftService.getNftDetails(getTestCollectionData().getContractAddress(),
                        "0x902dfd8f9f", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        assertEquals(codeMessageResponse.getCode(), "NFTNotFound");
        assertEquals(codeMessageResponse.getMessage(), "NFT not found");
    }
}
