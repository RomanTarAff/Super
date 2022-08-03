package api.social.profile;

import api.BaseApiTests;
import api.enums.Account;
import api.model.request.social.EditProfileRequest;
import api.model.request.social.SocialLinks;
import api.model.response.CodeMessageResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class EditSocialLinksNegativeTests extends BaseApiTests {

    @Test(testName = "Edit profile invalid facebook", dataProvider = "urls")
    public void editProfileInvalidFacebook(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .facebook(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid instagram", dataProvider = "urls")
    public void editProfileInvalidInstagram(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .instagram(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid linkedin", dataProvider = "urls")
    public void editProfileInvalidLinkedin(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .linkedin(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid medium", dataProvider = "urls")
    public void editProfileInvalidMedium(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .medium(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid snapchat", dataProvider = "urls")
    public void editProfileInvalidSnapchat(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .snapchat(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid telegram", dataProvider = "urls")
    public void editProfileInvalidTelegram(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .telegram(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid twitch", dataProvider = "urls")
    public void editProfileInvalidTwitch(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitch(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid twitter", dataProvider = "urls")
    public void editProfileInvalidTwitter(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitter(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid youtube", dataProvider = "urls")
    public void editProfileInvalidYoutube(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .youtube(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile invalid discord", dataProvider = "urls")
    public void editProfileInvalidDiscord(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .discord(List.of(url))
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
        soft.assertAll();
    }

    @Test(testName = "Edit profile facebook length")
    public void editProfileFacebookLength() {
        List<String> facebook = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .facebook(facebook)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile instagram length")
    public void editProfileInstagramLength() {
        List<String> instagram = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .instagram(instagram)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile linkedin length")
    public void editProfileLinkedinLength() {
        List<String> linkedin = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .linkedin(linkedin)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile medium length")
    public void editProfileMediumLength() {
        List<String> medium = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .medium(medium)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile snapchat length")
    public void editProfileSnapchatLength() {
        List<String> snapchat = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .snapchat(snapchat)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile telegram length")
    public void editProfileTelegramLength() {
        List<String> telegram = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .telegram(telegram)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile twitch length")
    public void editProfileTwitchLength() {
        List<String> twitch = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitch(twitch)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile twitter length")
    public void editProfileTwitterLength() {
        List<String> twitter = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitter(twitter)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile youtube length")
    public void editProfileYoutubeLength() {
        List<String> youtube = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .youtube(youtube)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @Test(testName = "Edit profile discord length")
    public void editProfileDiscordLength() {
        List<String> discord = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .discord(discord)
                        .build())
                .build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        soft.assertEquals(codeMessageResponse.getCode(), "ValidationError");
        soft.assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        soft.assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "");
        soft.assertAll();
    }

    @DataProvider(name = "urls")
    public Object[][] getUrls() {
        return new Object[][]
                {{"http://ggf", "link doesn't have domain"},
                        {"gfgf", "link doesn't have domain"},
                };
    }

}
