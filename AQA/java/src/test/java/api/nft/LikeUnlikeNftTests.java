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
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;

@Epic("Like unlike NFT")
public class LikeUnlikeNftTests extends BaseApiTests {

    private SearchNftResponse nft_1;

    @BeforeClass
    public void getNft() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        List<SearchNftResponse> nfts = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts();
        nft_1 = nfts.get(0);
    }

    @Test(description = "Like and unlike nft", enabled = false)
    public void likeAndUnlikeNft() {
        //check count of likes

        SearchNftRequest search = SearchNftRequest.builder()
                .search(nft_1.getName())
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        List<SearchNftResponse> nfts = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts();
        int likesBefore = nfts.get(0).getLikesCount();

        //like nft
        Object like = nftService.likeNft(nft_1.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .getResponse().asString();

        sleep(5);

        //check count of likes
        List<SearchNftResponse> nft = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts();

        soft.assertEquals(nft.get(0).getLikesCount(), likesBefore + 1, "Likes should be increment after like");

        //unlike nft
        Object unlike = nftService.unlikeNft(nft_1.getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT))
                .getResponse().asString();

        //check count of likes after unlike
        List<SearchNftResponse> nftUnlike = nftService.searchNftItems(search, System.getProperty(Account.MINT.getENV()))
                .asClass(SearchNftResponseList.class).getNfts();

        sleep(5);

        soft.assertEquals(nftUnlike.get(0).getLikesCount(), likesBefore, "Likes should be decrement after unlike");
        soft.assertAll();
    }

    @Test(description = "Like. Unauthorized")
    public void likeUnauthorized() {
        CodeMessageResponse codeMessageResponse = nftService.likeNft(nft_1.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(description = "Unlike. Unauthorized")
    public void unlikeUnauthorized() {
        CodeMessageResponse codeMessageResponse = nftService.unlikeNft(nft_1.getId(), null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }

    @Test(description = "Like NFT. Invalid nft id")
    public void likeInvalidNftId() {
        CodeMessageResponse codeMessageResponse = nftService.likeNft(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        System.out.println(codeMessageResponse.toString());
        soft.assertEquals(codeMessageResponse.getCode(), "NFTNotFound");
        soft.assertEquals(codeMessageResponse.getMessage(), "NFT not found");
        soft.assertAll();
    }

    @Test(description = "Unlike NFT. Invalid nft id")
    public void unlikeInvalidNftId() {
        CodeMessageResponse codeMessageResponse = nftService.unlikeNft(faker.internet().uuid(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NOT_FOUND))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "NFTNotFound");
        soft.assertEquals(codeMessageResponse.getMessage(), "NFT not found");
        soft.assertAll();
    }
}
