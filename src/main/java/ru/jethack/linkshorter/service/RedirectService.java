package ru.jethack.linkshorter.service;

import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;

@Service
public class RedirectService {

    final LinkRepository linkRepository;

    public RedirectService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getOriginalLink(String shortLink){
        return linkRepository.findByShortLink(shortLink);
    }
}
