package com.example.service.impl;

import com.example.dto.CheckinLogRequest;
import com.example.dto.CheckinLogResponse;
import com.example.entity.CheckInLog;
import com.example.exception.SystemException;
import com.example.repo.CheckinLogRepository;
import com.example.service.CheckinService;
import com.example.utils.Constants;
import com.example.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {

    private final CheckinLogRepository checkinLogRepo;

    @Autowired
    public CheckinServiceImpl(CheckinLogRepository checkinLogRepo) {
        this.checkinLogRepo = checkinLogRepo;
    }


    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void handleCheckin(String playerId) {
        DateFormat dateFormat = new SimpleDateFormat(DateUtil.FORMAT.FORMAT_DATE);
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        List<CheckInLog> checkInLogs = checkinLogRepo.getByPlayerIdAndTime(playerId, currentDate);
        if(checkInLogs != null && !checkInLogs.isEmpty()) {
            throw new SystemException(HttpStatus.BAD_REQUEST, String.format(Constants.ERROR_CODE.CHECKED_IN, playerId));
        }

        CheckInLog checkin = new CheckInLog();
        checkin.setCreateAt(date);
        checkin.setPlayerId(playerId);
        checkin.setPoint(Constants.POINT_DAYLY);

        checkinLogRepo.save(checkin);
    }

    @Override
    public CheckinLogResponse getCheckinLog(CheckinLogRequest request) {
        Integer page = (request.getPage() - 1)*request.getPage();
        Integer pageSize = request.getPageSize();

        List<CheckInLog> logs = checkinLogRepo.getByPlayerId(request.getPlayerId(), page, pageSize);
        Integer total = checkinLogRepo.getTotalByPlayerId(request.getPlayerId());

        return new CheckinLogResponse(total, logs);
    }
}
