package ru.jethack.linkshorter.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;
import ru.jethack.linkshorter.service.StatService;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    final LinkRepository linkRepository;

    public StatServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getLink(String shortLink) {
        return linkRepository.findByShortLink(shortLink);
    }

    public List<Link> getAllLinksRank(Integer page, Integer count) {
        Pageable sortedByRedirectCountDesk =
                PageRequest.of(page, count, Sort.by("redirectCount").descending().and(Sort.by("originalLink")));
        Page<Link> links = linkRepository.findAll(sortedByRedirectCountDesk);
        return links.toList();
    }

    public Integer getLinkRank(String shortLink) {
        return linkRepository.getLinkRank(shortLink);
    }
}
