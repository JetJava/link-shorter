package ru.jethack.linkshorter.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.jethack.linkshorter.service.LinkGeneratorService;
import ru.jethack.linkshorter.service.impl.LinkGeneratorServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("generate")
public class LinkGeneratorController {

    final LinkGeneratorService linkGeneratorService;

    public LinkGeneratorController(LinkGeneratorService linkGeneratorService) {
        this.linkGeneratorService = linkGeneratorService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> generate(@RequestBody Map<String, String> original){
        Map<String, String> link= new HashMap<>();
        String shortUrl = "/l/".concat(linkGeneratorService.createShortLink(original.get("original")).getShortLink());
        link.put("link", shortUrl);
        return link;
    }
}
