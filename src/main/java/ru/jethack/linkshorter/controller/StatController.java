package ru.jethack.linkshorter.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.jethack.linkshorter.service.StatService;

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
        return statService.getAllLinksRank(page, count);
    }

    @GetMapping(value = "{shortLink}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getLinkStat(@PathVariable String shortLink) {
        return statService.getLinkRank(shortLink);
    }
}
