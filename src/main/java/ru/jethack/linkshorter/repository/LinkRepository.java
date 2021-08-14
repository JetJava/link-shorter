package ru.jethack.linkshorter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.jethack.linkshorter.model.Link;

import java.util.List;
import java.util.Map;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByOriginalLink(String originalLink);
    Link findByShortLink(String shortLink);

    @Query(value = "select *, dense_rank() over(order by redirect_count desc) rank " +
            "from link offset :offset limit :count", nativeQuery = true)
    List<Map<String, Object>> getLinkRankList(Integer offset, Integer count);

    @Query(value = "select *, dense_rank() over(order by redirect_count desc) rank " +
            "from link where short_link = :shortLink", nativeQuery = true)
    Map<String, Object> getLinkRank(String shortLink);
}