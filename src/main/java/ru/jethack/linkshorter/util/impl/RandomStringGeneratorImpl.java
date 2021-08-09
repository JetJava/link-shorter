package ru.jethack.linkshorter.util.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.util.RandomStringGenerator;

@Service
public class RandomStringGeneratorImpl implements RandomStringGenerator {
    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(6, 8);
    }
}
