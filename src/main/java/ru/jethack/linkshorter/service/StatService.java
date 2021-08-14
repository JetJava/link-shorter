package ru.jethack.linkshorter.service;

import ru.jethack.linkshorter.model.Link;

import java.util.List;
import java.util.Map;

public interface StatService {
    Link getLink(String shortLink);
    Map<String, Object> getLinkRank(String shortLink);
    List<Map<String, Object>> getAllLinksRank(Integer page, Integer count);
}
