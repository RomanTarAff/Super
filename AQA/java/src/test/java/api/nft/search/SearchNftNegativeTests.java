package api.nft.search;

import api.BaseApiTests;
import api.enums.Group;
import api.enums.Sort;
import api.enums.Status;
import api.model.request.nft.SearchNftRequest;
import api.model.response.CodeMessageResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;

public class SearchNftNegativeTests extends BaseApiTests {

    @Test(testName = "Search nft without required fields")
    public void searchWithoutRequiredFields() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("")
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data must have required property 'sort'");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(1), "data must have required property 'limit'");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(2), "data/search must NOT have fewer than 1 characters");
        soft.assertAll();
    }

    @Test(testName = "Search nft more 100 chars")
    public void searchMore100Chars() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search(faker.lorem().characters(101))
                .statuses(List.of(Status.NOT_LISTED.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/search must NOT have more than 100 characters");
        soft.assertAll();
    }

    @Test(testName = "Search nft invalid status")
    public void searchInvalidStatus() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of("invalid"))
                .group(Group.IN_WALLET.getValue())
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/statuses/0 must be equal to one of the allowed values");
        soft.assertAll();
    }

    @Test(testName = "Search nft invalid group")
    public void searchInvalidGroup() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.ON_SALE.getValue()))
                .group("invalid")
                .limit(100)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/group must be equal to one of the allowed values");
        soft.assertAll();
    }

    @Test(testName = "Search nft invalid sort")
    public void searchInvalidSort() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.ON_SALE.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(100)
                .sort("invalid")
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/sort must be equal to one of the allowed values");
        soft.assertAll();
    }

    @Test(testName = "Search nft invalid limit")
    public void searchInvalidLimit() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.ON_SALE.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(101)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/limit must be <= 100");
        soft.assertAll();
    }

    @Test(testName = "Search nft empty search")
    public void searchNftsEmptySearch() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("")
                .statuses(List.of(Status.ON_SALE.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, MINT_TOKEN)
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/search must NOT have fewer than 1 characters");
        soft.assertAll();
    }

    @Test(testName = "Search nft unauthorized")
    public void searchNftsUnauthorized() {
        SearchNftRequest search = SearchNftRequest.builder()
                .search("Lesley")
                .statuses(List.of(Status.ON_SALE.getValue()))
                .group(Group.IN_WALLET.getValue())
                .limit(5)
                .sort(Sort.MOST_RECENT.getValue())
                .build();

        CodeMessageResponse codeMessageResponse = nftService.searchNftItems(search, null)
                .shouldHave(Conditions.statusCode(HTTP_FORBIDDEN))
                .asClass(CodeMessageResponse.class);
        soft.assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
        soft.assertAll();
    }
}
