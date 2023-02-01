package com.exchangeinformant.repository;

import com.exchangeinformant.util.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NameRepositoryRedis {
    public static final String HASH_KEY = "Name";

    @Autowired
    private RedisTemplate redisTemplate;

    public Name save(Name name) {
        redisTemplate.opsForHash().put(HASH_KEY, name.getSecureCode(), name);
        return name;
    }

    public List<Name> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }
}
