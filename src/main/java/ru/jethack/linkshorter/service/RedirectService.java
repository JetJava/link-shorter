package ru.jethack.linkshorter.service;

import ru.jethack.linkshorter.model.Link;

public interface RedirectService {
    Link getOriginalLink(String shortLink);
}
