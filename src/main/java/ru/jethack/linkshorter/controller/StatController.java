package ru.jethack.linkshorter.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.service.StatService;
import ru.jethack.linkshorter.service.impl.StatServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stat")
public class StatController {

    final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getAllLinkStat(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "100") Integer count) {
        if (count > 100) count = 100;
        List<Link> links = statService.getAllLinksRank(page, count);
        List<Map<String, Object>> result = new ArrayList<>();
        //long m1 = System.currentTimeMillis();
        for (Link link : links) {
            result.add(new HashMap<>() {{
                put("link", "/l/".concat(link.getShortLink()));
                put("original", link.getOriginalLink());
                put("rank", statService.getLinkRank(link.getShortLink()));
                put("count", link.getRedirectCount());
            }});
        }
        //long m2 = System.currentTimeMillis();
        //System.out.println(m2 - m1);
        return result;
    }

    @GetMapping(value = "{shortLink}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getLinkStat(@PathVariable String shortLink) {
        Link link = statService.getLink(shortLink);

        return new HashMap<>() {{
            put("link", "/l/".concat(link.getShortLink()));
            put("original", link.getOriginalLink());
            put("rank", statService.getLinkRank(shortLink));
            put("count", link.getRedirectCount());
        }};
    }
}
