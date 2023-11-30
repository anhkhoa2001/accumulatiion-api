package com.example.repo;

import com.example.entity.CheckInLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckinLogRepository extends JpaRepository<CheckInLog, Long> {

    @Query(value = "SELECT * FROM checkin_log WHERE PLAYER_ID = :playerId AND DATE_FORMAT(CREATE_AT, '%d/%m/%Y') = :time", nativeQuery = true)
    List<CheckInLog> getByPlayerIdAndTime(@Param("playerId") String playerId, @Param("time") String time);


    @Query(value = "SELECT * FROM checkin_log WHERE PLAYER_ID = :playerId LIMIT :pageSize OFFSET :page", nativeQuery = true)
    List<CheckInLog> getByPlayerId(@Param("playerId") String playerId,
                                   @Param("page") Integer page,
                                   @Param("pageSize") Integer pageSize);

    @Query(value = "SELECT COUNT(ID) FROM checkin_log  WHERE PLAYER_ID = :playerId", nativeQuery = true)
    Integer getTotalByPlayerId(@Param("playerId") String playerId);
}
