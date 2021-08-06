package ru.jethack.linkshorter.service;

import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.repository.LinkRepository;
import ru.jethack.linkshorter.utul.RandomStringGenerator;

@Service
public class LinkService {

    final RandomStringGenerator randomStringGenerator;
    final LinkRepository linkRepository;

    public LinkService(RandomStringGenerator randomStringGenerator, LinkRepository linkRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.linkRepository = linkRepository;
    }

    public Link createShortLink(String originalLink) {
        Link link = getLinkByOriginalLink(originalLink);
        if (link == null) {
            String randomString = "";
            do {
                randomString = randomStringGenerator.generate();
            } while (getLinkByShortLink(randomString) != null);

            link = new Link();
            link.setOriginalLink(originalLink);
            link.setShortLink("/l/".concat(randomString));
            link.setRequestCount(0);
            saveLink(link);
        }
        return link;
    }

    private void saveLink(Link link) {
        linkRepository.save(link);
    }

    public Link getLinkByOriginalLink(String originalLink) {
        return linkRepository.findByOriginalLink(originalLink);
    }

    public Link getLinkByShortLink(String shortLink) {
        return linkRepository.findByShortLink(shortLink);
    }
}
