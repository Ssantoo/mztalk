package com.mztalk.main.domain.subscribe.service;

import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;

import java.util.concurrent.ConcurrentHashMap;

public interface SubscribeService {
    Subscribe save(SubscribeRequestDto subscribeRequestDto);
}
