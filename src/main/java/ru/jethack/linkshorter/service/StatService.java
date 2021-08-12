package ru.jethack.linkshorter.service;

import ru.jethack.linkshorter.model.Link;

import java.util.List;

public interface StatService {
    Link getLink(String shortLink);
    Integer getLinkRank(String shortLink);
    List<Link> getAllLinksRank(Integer page, Integer count);

}
