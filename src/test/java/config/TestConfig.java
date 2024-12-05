package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {
    @Key("platform")
    String platform();

    @Key("bs_user")
    String browserstackUser();

    @Key("bs_key")
    String browserstackKey();

    @Key("bs_url")
    String browserstackUrl();

    @Key("app_url")
    String appUrl();
}

