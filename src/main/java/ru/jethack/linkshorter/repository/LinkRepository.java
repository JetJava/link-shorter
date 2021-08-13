package ru.jethack.linkshorter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.jethack.linkshorter.model.Link;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByOriginalLink(String originalLink);
    Link findByShortLink(String shortLink);

    @Query(value = "select rnk from (select *, dense_rank() over(order by redirect_count desc) rnk from link) as x " +
            "where x.short_link = :shortLink", nativeQuery = true)
    Integer getLinkRank(String shortLink);
}