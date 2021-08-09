package ru.jethack.linkshorter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "link")
@Entity
public class Link {

    @Id
    private String shortLink;

    private String originalLink;

    private Integer redirectCount;

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public Integer getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(Integer redirectCount) {
        this.redirectCount = redirectCount;
    }
}