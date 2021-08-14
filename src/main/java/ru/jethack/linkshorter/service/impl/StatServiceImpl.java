package ru.jethack.linkshorter.service.impl;

import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;
import ru.jethack.linkshorter.service.StatService;

import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {
    final LinkRepository linkRepository;

    public StatServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getLink(String shortLink) {
        return linkRepository.findByShortLink(shortLink);
    }

    public List<Map<String, Object>> getAllLinksRank(Integer page, Integer count) {
        return linkRepository.getLinkRankList(count * page, count);
    }

    public Map<String, Object> getLinkRank(String shortLink) {
        return linkRepository.getLinkRank(shortLink);
    }

}
