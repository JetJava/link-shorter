package ru.jethack.linkshorter.service.impl;

import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;
import ru.jethack.linkshorter.service.LinkGeneratorService;
import ru.jethack.linkshorter.util.RandomStringGenerator;


@Service
public class LinkGeneratorServiceImpl implements LinkGeneratorService {

    final RandomStringGenerator randomStringGenerator;
    final LinkRepository linkRepository;

    public LinkGeneratorServiceImpl(RandomStringGenerator randomStringGenerator, LinkRepository linkRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.linkRepository = linkRepository;
    }

    public Link createShortLink(String originalLink) {
        originalLink = originalLink.toLowerCase().trim();
        originalLink = originalLink.contains("://") ? originalLink : "http://".concat(originalLink);
        Link link = getLinkByOriginalLink(originalLink);
        if (link == null) {
            String randomString;
            do {
                randomString = randomStringGenerator.generate();
            } while (getLinkByShortLink(randomString) != null);

            link = new Link();
            link.setOriginalLink(originalLink);
            link.setShortLink(randomString);
            link.setRedirectCount(0);
            saveLink(link);
        }
        return link;
    }

    private void saveLink(Link link) {
        linkRepository.save(link);
    }

    private Link getLinkByOriginalLink(String originalLink) {
        return linkRepository.findByOriginalLink(originalLink);
    }

    private Link getLinkByShortLink(String shortLink) {
        return linkRepository.findByShortLink(shortLink);
    }
}
