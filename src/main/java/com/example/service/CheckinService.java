package com.example.service;

import com.example.dto.CheckinLogRequest;
import com.example.dto.CheckinLogResponse;

public interface CheckinService {

    void handleCheckin(String playerId);

    CheckinLogResponse getCheckinLog(CheckinLogRequest request);
}
