package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.model.request.social.CollectionSettingsRequest;
import api.model.request.social.SocialLinks;
import api.model.response.CodeMessageResponse;
import api.model.response.social.CollectionSettingsResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.assertEquals;

@Epic("Get update collection settings")
public class GetUpdateCollectionSettingsTests extends BaseApiTests {

    @Test(description = "Get collection settings")
    public void getCollectionsSettings() {

        CollectionSettingsResponse collectionSettingsResponse = socialService
                .getCollectionSettings(getTestCollectionData().getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(CollectionSettingsResponse.class);
    }

    @Test(description = "Get collection settings. Not access")
    public void getCollectionsSettingsNotAccess() {

        CodeMessageResponse codeMessageResponse = socialService
                .getCollectionSettings(getTestCollectionData().getId(), System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_FORBIDDEN))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "AccessDenied");
        assertEquals(codeMessageResponse.getMessage(), "Access denied");
    }

    @Test(description = "Get collection settings. Invalid collection id")
    public void getCollectionsSettingsNotValidId() {

        CodeMessageResponse codeMessageResponse = socialService
                .getCollectionSettings("invalidCollectionId", System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/id must match format \"uuid\"");
    }

    @Test(description = "Edit collection settings - all fields")
    public void editCollectionSettingsAllFields() {

        String name = faker.lorem().characters(5);
        String description = faker.lorem().characters(5);
        String websiteUrl = faker.internet().url();

        String twitter = "https://" + faker.internet().url();
        String linkedin = "https://" + faker.internet().url();
        String instagram = "https://" + faker.internet().url();
        String youtube = "https://" + faker.internet().url();
        String twitch = "https://" + faker.internet().url();
        String telegram = "https://" + faker.internet().url();
        String snapchat = "https://" + faker.internet().url();
        String medium = "https://" + faker.internet().url();
        String facebook = "https://" + faker.internet().url();

        SocialLinks socialLinks = SocialLinks
                .builder()
                .twitter(List.of(twitter))
                .linkedin(List.of(linkedin))
                .instagram(List.of(instagram))
                .youtube(List.of(youtube))
                .twitch(List.of(twitch))
                .telegram(List.of(telegram))
                .snapchat(List.of(snapchat))
                .medium(List.of(medium))
                .facebook(List.of(facebook))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .name(name)
                .description(description)
                .websiteUrl(websiteUrl)
                .socialLinks(socialLinks)
                .build();

        socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        CollectionSettingsResponse collectionSettingsResponse = socialService
                .getCollectionSettings(getTestCollectionData().getId(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(CollectionSettingsResponse.class);

        assertEquals(collectionSettingsResponse.getName(), name);
        assertEquals(collectionSettingsResponse.getDescription(), description);
        assertEquals(collectionSettingsResponse.getWebsiteUrl(), "https://" + websiteUrl);
        assertEquals(collectionSettingsResponse.getSocialLinks(), socialLinks);
    }

    @Test(description = "Edit collection settings. Invalid description length")
    public void editCollectionSettingsDescriptionLength() {

        String description = faker.lorem().characters(1005);

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .description(description)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/description must NOT have more than 1000 characters");
    }

    @Test(description = "Edit collection settings. Invalid website url")

    public void editCollectionSettingsInvalidWebSiteUrl() {

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .websiteUrl("invalidWebsiteUrl")
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0),
                "data/data/websiteUrl must match pattern \"^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_а-яА-ЯёЁ0-9%#$&@\\/_-]+(\\.[a-zA-Z0-9_а-яА-ЯёЁ0-9%#$&\\/_-]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_а-яА-ЯёЁ0-9%#$&\\/_-]+=\\w+(&[a-zA-Z0-9_а-яА-ЯёЁ0-9%#$&\\/_-]+=\\w+)*)?\\b([-a-zA-Z0-9()!@:%_+.~#?&\\/\\\\=]*)\"");
    }

    @Test(description = "Edit collection settings. Invalid twitter")
    public void editCollectionSettingsInvalidTwitter() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .twitter(List.of("invalidTwitterLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidTwitterLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid linkedin")
    public void editCollectionSettingsInvalidLinkedin() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .linkedin(List.of("invalidLinkedInLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidLinkedInLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid instagram")
    public void editCollectionSettingsInvalidInstagram() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .instagram(List.of("invalidInstagramLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidInstagramLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid youtube")
    public void editCollectionSettingsInvalidYoutube() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .youtube(List.of("invalidYoutubeLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidYoutubeLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid twitch")
    public void editCollectionSettingsInvalidTwitch() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .twitch(List.of("invalidTwitchLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidTwitchLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid telegram")
    public void editCollectionSettingsInvalidTelegram() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .telegram(List.of("invalidTelegramLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidTelegramLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid snapchat")
    public void editCollectionSettingsInvalidSnapchat() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .snapchat(List.of("invalidSnapchatLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidSnapchatLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid medium")
    public void editCollectionSettingsInvalidMedium() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .medium(List.of("invalidMediumLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidMediumLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Invalid facebook")
    public void editCollectionSettingsInvalidFacebook() {

        SocialLinks socialLinks = SocialLinks
                .builder()
                .facebook(List.of("invalidFacebookLink"))
                .build();

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .socialLinks(socialLinks)
                .build();

        CodeMessageResponse codeMessageResponse = socialService.updateCollectionSettings(getTestCollectionData().getId(),
                        request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "invalidFacebookLink link doesn't have domain");
    }

    @Test(description = "Edit collection settings. Unauthorized")
    public void editCollectionSettingsUnauthorized() {

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .description("description")
                .build();

        CodeMessageResponse codeMessageResponse = socialService
                .updateCollectionSettings(getTestCollectionData().getId(), request, null)
                .shouldHave(Conditions.statusCode(HTTP_UNAUTHORIZED))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "AuthorizationError");
        assertEquals(codeMessageResponse.getMessage(), "Unauthorized");
    }

    @Test(description = "Edit collection settings. Not access")
    public void editCollectionSettingsNotAccess() {

        CollectionSettingsRequest request = CollectionSettingsRequest
                .builder()
                .description("description")
                .build();

        CodeMessageResponse codeMessageResponse = socialService
                .updateCollectionSettings(getTestCollectionData().getId(), request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_FORBIDDEN))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "AccessDenied");
        assertEquals(codeMessageResponse.getMessage(), "You are not allowed to perform this action");
    }
}
