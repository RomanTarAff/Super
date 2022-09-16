package api.social.profile;

import api.BaseApiTests;
import api.enums.Account;
import api.model.request.social.EditProfileRequest;
import api.model.response.social.ProfileResponse;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.testng.Assert.assertEquals;

@Epic("Edit profile")
public class EditProfileTests extends BaseApiTests {

    @Test(description = "Edit profile username")
    public void editProfileUsername() {
        String username = faker.name().firstName();

        EditProfileRequest request = EditProfileRequest.builder()
                .username(username).build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getUsername(), username,
                String.format("Username should be %s", username));
    }

    @Test(description = "Edit profile email")
    public void editProfileEmail() {
        String email = faker.internet().emailAddress();

        EditProfileRequest request = EditProfileRequest.builder()
                .email(email).build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getEmail(), email,
                String.format("Email should be %s", email));
    }

    @Test(description = "Edit profile description")
    public void editProfileDescription() {
        String description = faker.lorem().characters(20, true);

        EditProfileRequest request = EditProfileRequest.builder()
                .description(description).build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getDescription(), description,
                String.format("Description should be %s", description));
    }

    @Test(description = "Edit profile website url")
    public void editProfileWebsiteUrl() {
        String url = "https://" + faker.internet().url();

        EditProfileRequest request = EditProfileRequest.builder()
                .websiteUrl(url).build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getWebsiteUrl(), url,
                String.format("Website url should be %s", url));
    }

    @Test(description = "Edit profile avatar url")
    public void editProfileAvatarUrl() {
        String url = "https://" + faker.internet().url();

        EditProfileRequest request = EditProfileRequest.builder()
                .avatarUrl(url).build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getAvatarUrl(), url,
                String.format("Avatar url should be %s", url));
    }

    @Test(description = "Edit profile banner url")
    public void editProfileBannerUrl() {
        String url = "https://" + faker.internet().url();

        EditProfileRequest request = EditProfileRequest.builder()
                .bannerUrl(url).build();

        socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_NO_CONTENT));

        ProfileResponse profileResponse = socialService.getMyProfile(System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(ProfileResponse.class);

        assertEquals(profileResponse.getBannerUrl(), url,
                String.format("Banner url should be %s", url));
    }
}
