package com.weather.api.emibeanatte.service;

import com.weather.api.emibeanatte.entity.QueryRecord;
import com.weather.api.emibeanatte.repository.QueryRecordRepository;
import com.weather.api.emibeanatte.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryRecordService {

    @Autowired
    private QueryRecordRepository queryRecordRepository;

    public void registerQuery(User user, String query, String response) {
        QueryRecord queryRecord = new QueryRecord();
        queryRecord.setUser(user);
        queryRecord.setQuery(query);
        queryRecord.setResponse(response);
        queryRecordRepository.save(queryRecord);
    }

}
