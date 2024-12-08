package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:ios_config.properties")
public interface TestConfigAndroid extends Config {
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

