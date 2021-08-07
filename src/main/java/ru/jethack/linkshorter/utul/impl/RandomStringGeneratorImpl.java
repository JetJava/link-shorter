package ru.jethack.linkshorter.utul.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.jethack.linkshorter.utul.RandomStringGenerator;

@Service
public class RandomStringGeneratorImpl implements RandomStringGenerator {
    @Override
    public String generate() {
        return RandomStringUtils.randomAscii(6,8);
    }
}
