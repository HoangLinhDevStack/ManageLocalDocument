package com.manager.doc.properties.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:admin-properties/created-account.properties")
public class AdminCreateAccountUserProperties {

    @Value("${created.account.title}")
    private String title;
    @Value("${created.account.rule.two-character}")
    private String twoCharacter;
    @Value("${created.account.rule.middle-character}")
    private String middleCharacter;
    @Value("${created.account.rule.system-character}")
    private String systemCharacter;
    @Value("${created.account.rule.two-character-number}")
    private int characterNumberFirst;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTwoCharacter() {
        return twoCharacter;
    }

    public void setTwoCharacter(String twoCharacter) {
        this.twoCharacter = twoCharacter;
    }

    public String getMiddleCharacter() {
        return middleCharacter;
    }

    public void setMiddleCharacter(String middleCharacter) {
        this.middleCharacter = middleCharacter;
    }

    public String getSystemCharacter() {
        return systemCharacter;
    }

    public void setSystemCharacter(String systemCharacter) {
        this.systemCharacter = systemCharacter;
    }

    public int getCharacterNumberFirst() {
        return characterNumberFirst;
    }

    public void setCharacterNumberFirst(int characterNumberFirst) {
        this.characterNumberFirst = characterNumberFirst;
    }
}
