package api.social.profile;

import api.BaseApiTests;
import api.enums.Account;
import api.model.request.social.EditProfileRequest;
import api.model.request.social.SocialLinks;
import api.model.response.social.ProfileResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.testng.Assert.assertEquals;

@Epic("Edit profile social links")
public class EditProfileSocialLinksTests extends BaseApiTests {

    @Test(description = "Edit profile facebook")
    public void editProfileFacebook() {
        List<String> facebook = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .facebook(facebook)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getFacebook(), facebook);
    }

    @Test(description = "Edit profile instagram")
    public void editProfileInstagram() {
        List<String> instagram = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .instagram(instagram)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getInstagram(), instagram);
    }

    @Test(description = "Edit profile linkedin")
    public void editProfileLinkedin() {
        List<String> linkedin = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .linkedin(linkedin)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getLinkedin(), linkedin);
    }

    @Test(description = "Edit profile medium")
    public void editProfileMedium() {
        List<String> medium = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .medium(medium)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getMedium(), medium);
    }

    @Test(description = "Edit profile snapchat")
    public void editProfileSnapchat() {
        List<String> snapchat = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .snapchat(snapchat)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getSnapchat(), snapchat);
    }

    @Test(description = "Edit profile telegram")
    public void editProfileTelegram() {
        List<String> telegram = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .telegram(telegram)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getTelegram(), telegram);
    }

    @Test(description = "Edit profile twitch")
    public void editProfileTwitch() {
        List<String> twitch = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitch(twitch)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getTwitch(), twitch);
    }

    @Test(description = "Edit profile twitter")
    public void editProfileTwitter() {
        List<String> twitter = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .twitter(twitter)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getTwitter(), twitter);
    }

    @Test(description = "Edit profile youtube")
    public void editProfileYoutube() {
        List<String> youtube = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .youtube(youtube)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getYoutube(), youtube);
    }

    @Test(description = "Edit profile discord")
    public void editProfileDiscord() {
        List<String> discord = List.of("https://" + faker.internet().url(), "https://" + faker.internet().url());
        EditProfileRequest request = EditProfileRequest.builder()
                .socialLinks(SocialLinks.builder()
                        .discord(discord)
                        .build())
                .build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getSocialLinks().getDiscord(), discord);
    }
}
