package ru.jethack.linkshorter.service;

import ru.jethack.linkshorter.model.Link;

public interface LinkGeneratorService {
    Link createShortLink(String originalLink);
}
