package api.nft;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.CodeMessageResponse;
import api.model.response.nft.SearchNftResponse;
import api.model.response.nft.SearchNftResponseList;
import api.util.conditions.Conditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;

public class PinUnpinNftTests extends BaseApiTests {

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

    @Test(testName = "Pin and unpin nft", enabled = false)
    public void pinAndUnpinNft() {

        //pin nft
        Object like = nftService.pinNft(nftResult.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .getResponse().asString();

        sleep(3);

        //check isPinned
        SearchNftRequest search = SearchNftRequest.builder()
                .search(nftResult.getName())
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();
        SearchNftResponse nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        soft.assertTrue(nft.isPinned(), "NFT should be pinned");

        //unpin nft
        Object unlike = nftService.unpinNft(nft.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .getResponse().asString();

        sleep(3);

        SearchNftResponse nftAfterUnpin = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts().get(0);

        soft.assertFalse(nftAfterUnpin.isPinned(), "NFT should be unPinned");
        soft.assertAll();
    }

    @Test(testName = "Pin unauthorized")
    public void pinUnauthorized() {
        CodeMessageResponse codeMessageResponse = nftService.pinNft(nftResult.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(testName = "Unpin unauthorized")
    public void unpinUnauthorized() {
        CodeMessageResponse codeMessageResponse = nftService.unpinNft(nftResult.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(testName = "Pin invalid nft id")
    public void pinInvalidNftId() {
        CodeMessageResponse codeMessageResponse = nftService.pinNft("575226ae-00bf-5f4a-8gb9c-3d4c9c4b644f", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertAll();
    }

    @Test(testName = "Unpin invalid nft id")
    public void unpinInvalidNftId() {
        CodeMessageResponse codeMessageResponse = nftService.unpinNft("575226ae-00bf-5f4a-8gb9c-3d4c9c4b644f", System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertAll();
    }

    @Test(testName = "Pin not existing nft id")
    public void pinNotExistingNftId() {
        CodeMessageResponse codeMessageResponse = nftService.pinNft(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getMessage(), "NFT not found");
        soft.assertEquals(codeMessageResponse.getCode(), "NFTNotFound");
        soft.assertAll();
    }

    @Test(testName = "Unpin not existing nft id")
    public void unpinNotExistingNftId() {
        CodeMessageResponse codeMessageResponse = nftService.unpinNft(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getMessage(), "NFT not found");
        soft.assertEquals(codeMessageResponse.getCode(), "NFTNotFound");
        soft.assertAll();
    }
}
