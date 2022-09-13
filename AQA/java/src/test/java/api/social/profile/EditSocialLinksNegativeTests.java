package api.social.profile;

import api.BaseApiTests;
import api.enums.Account;
import api.model.request.social.EditProfileRequest;
import api.model.request.social.SocialLinks;
import api.model.response.CodeMessageResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Edit profile social links negative")
public class EditSocialLinksNegativeTests extends BaseApiTests {

    @Test(description = "Edit profile. Invalid facebook", dataProvider = "urls")
    public void editProfileInvalidFacebook(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .facebook(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid instagram", dataProvider = "urls")
    public void editProfileInvalidInstagram(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .instagram(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid linkedin", dataProvider = "urls")
    public void editProfileInvalidLinkedin(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .linkedin(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid medium", dataProvider = "urls")
    public void editProfileInvalidMedium(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .medium(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid snapchat", dataProvider = "urls")
    public void editProfileInvalidSnapchat(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .snapchat(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid telegram", dataProvider = "urls")
    public void editProfileInvalidTelegram(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .telegram(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid twitch", dataProvider = "urls")
    public void editProfileInvalidTwitch(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitch(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid twitter", dataProvider = "urls")
    public void editProfileInvalidTwitter(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitter(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid youtube", dataProvider = "urls")
    public void editProfileInvalidYoutube(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .youtube(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid discord", dataProvider = "urls")
    public void editProfileInvalidDiscord(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .discord(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(description = "Edit profile. Invalid facebook length")
    public void editProfileFacebookLength() {
        List<String> facebook = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .facebook(facebook)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/facebook must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid instagram length")
    public void editProfileInstagramLength() {
        List<String> instagram = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .instagram(instagram)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/instagram must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid linkedin length")
    public void editProfileLinkedinLength() {
        List<String> linkedin = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .linkedin(linkedin)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/linkedin must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid medium length")
    public void editProfileMediumLength() {
        List<String> medium = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .medium(medium)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/medium must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid snapchat length")
    public void editProfileSnapchatLength() {
        List<String> snapchat = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .snapchat(snapchat)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/snapchat must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid telegram length")
    public void editProfileTelegramLength() {
        List<String> telegram = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .telegram(telegram)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/telegram must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid twitch length")
    public void editProfileTwitchLength() {
        List<String> twitch = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitch(twitch)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/twitch must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid twitter length")
    public void editProfileTwitterLength() {
        List<String> twitter = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitter(twitter)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/twitter must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid youtube length")
    public void editProfileYoutubeLength() {
        List<String> youtube = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .youtube(youtube)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/youtube must NOT have more than 2 items");
    }

    @Test(description = "Edit profile. Invalid discord length")
    public void editProfileDiscordLength() {
        List<String> discord = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .discord(discord)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.BUY.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/socialLinks/discord must NOT have more than 2 items");
    }

    @DataProvider(name = "urls")
    public Object[][] getUrls() {
        return new Object[][]
                {{"http://ggf", "link doesn't have domain"},
                        {"gfgf", "link doesn't have domain"},
                };
    }

}
