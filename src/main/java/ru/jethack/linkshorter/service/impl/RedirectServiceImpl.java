package ru.jethack.linkshorter.service.impl;

import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;
import ru.jethack.linkshorter.service.RedirectService;

@Service
public class RedirectServiceImpl implements RedirectService {

    final LinkRepository linkRepository;

    public RedirectServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getOriginalLink(String shortLink) {
        Link link = linkRepository.findByShortLink(shortLink);
        link.setRedirectCount(link.getRedirectCount() + 1);
        linkRepository.save(link);
        return link;
    }
}
