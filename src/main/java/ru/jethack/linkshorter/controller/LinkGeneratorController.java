package ru.jethack.linkshorter.controller;


import org.springframework.web.bind.annotation.*;
import ru.jethack.linkshorter.service.LinkGeneratorService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("generate")
public class LinkGeneratorController {

    final LinkGeneratorService linkGeneratorService;

    public LinkGeneratorController(LinkGeneratorService linkGeneratorService) {
        this.linkGeneratorService = linkGeneratorService;
    }

    @PostMapping
    public Map<String, String> generate(@RequestBody Map<String, String> original){
        Map<String, String> link= new HashMap<>();
        link.put("link", linkGeneratorService.createShortLink(original.get("original")).getShortLink());
        return link;
    }
}
