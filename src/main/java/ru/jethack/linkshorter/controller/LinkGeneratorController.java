package ru.jethack.linkshorter.controller;


import org.springframework.web.bind.annotation.*;
import ru.jethack.linkshorter.service.LinkService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("generate")
public class LinkGeneratorController {

    final LinkService linkService;

    public LinkGeneratorController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public Map<String, String> generate(@RequestBody Map<String, String> original){
        Map<String, String> link= new HashMap<>();
        link.put("link", linkService.createShortLink(original.get("original")).getShortLink());
        return link;
    }
}
