package api.nft;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserPinnedNftTests extends BaseApiTests {

    private SearchNftResponse nftResult;

    @BeforeClass
    public void getNft() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        nftResult = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts().stream()
                .filter(nft -> !nft.isPinned()).findFirst().get();
    }

    @Test(testName = "Get pinned nfts")
    public void getPinnedNfts() {

        //get pinned nfts
        SearchNftResponseList nfts = nftService.getPinnedNft(userMint.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        nfts.getNfts().forEach(nft -> {
            assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            assertTrue(nft.isPinned(), "NFT should be pinned in group");
        });

        //pin nft
        Object pin = nftService.pinNft(nftResult.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .getResponse().asString();

        sleep(3);

        //get pinned nfts again
        SearchNftResponseList nftsAgain = nftService.getPinnedNft(userMint.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        nftsAgain.getNfts().forEach(nft -> {
            assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            assertTrue(nft.isPinned(), "NFT should be pinned in group");
        });
        assertEquals(nfts.getNfts().size(), nftsAgain.getNfts().size() + 1,
                "Size of pinned nfts list is not the same after pin NFT");
        
        //unpin nft
        Object unpin = nftService.unpinNft(nftResult.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .getResponse().asString();

        sleep(3);

        //get pinned nfts again
        SearchNftResponseList nftsAgainUnpin = nftService.getPinnedNft(userMint.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(SearchNftResponseList.class);

        nftsAgainUnpin.getNfts().forEach(nft -> {
            assertEquals(nft.getOwnerAddress(), userMint.getEthAddress());
            assertTrue(nft.isPinned(), "NFT should be pinned in group");
        });
        assertEquals(nfts.getNfts().size(), nftsAgainUnpin.getNfts().size(),
                "Size of pinned nfts list is not the same after pin NFT");
    }
}
