package com.lukmanhidayah.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.config.ApplicationProperties;
import com.lukmanhidayah.catalog.config.CloudProperties;
import com.lukmanhidayah.catalog.service.GreetingService;

import lombok.AllArgsConstructor;

import java.util.TimeZone;

@Service("greetingService")
@AllArgsConstructor
public class GreetingServiceImpl implements GreetingService {

  private ApplicationProperties applicationProperties;

  private CloudProperties cloudProperties;

  @Override
  public String sayGreeting() {
    TimeZone timeZone = TimeZone.getTimeZone(applicationProperties.getTimezone());

    return applicationProperties.getWelcomeText() + ", Our timezone is " + timeZone.getDisplayName()
        + " and our currency is " + applicationProperties.getCurrency() + " and our cloud api key is "
        + cloudProperties.getApiKey();
  }

}
