package ru.jethack.linkshorter.service;

import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;

@Service
public class StatService {
    final LinkRepository linkRepository;

    public StatService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getLink(String shortLink){
        return linkRepository.findByShortLink(shortLink);
    }

    public Integer getLinkRank(String shortLink){
        return 1;
    }
}
