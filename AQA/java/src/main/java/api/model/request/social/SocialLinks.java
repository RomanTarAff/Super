package api.model.request.social;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialLinks {

    private List<String> email;
    private List<String> facebook;
    private List<String> instagram;
    private List<String> linkedin;
    private List<String> medium;
    private List<String> snapchat;
    private List<String> telegram;
    private List<String> twitch;
    private List<String> twitter;
    private List<String> youtube;
    private List<String> discord;
}
