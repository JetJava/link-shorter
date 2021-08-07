package ru.jethack.linkshorter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jethack.linkshorter.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByOriginalLink(String originalLink);
    Link findByShortLink(String shortLink);


}