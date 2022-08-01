package core.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${host}.properties"})
public interface Configuration extends Config {

    @Key("execution")
    String execution();

    @Key("headless")
    Boolean headless();

    @Key("url")
    String url();

    @Key("authBaseUrl")
    String authBaseUrl();

    @Key("socialBaseUrl")
    String socialBaseUrl();

    @Key("adminBaseUrl")
    String adminBaseUrl();

    @Key("nftBaseUrl")
    String nftBaseUrl();

    @Key("dropsBaseUrl")
    String dropsBaseUrl();

    @Key("mediahubBaseUrl")
    String mediahubBaseUrl();

    @Key("rinkeby")
    String rinkeby();
}
