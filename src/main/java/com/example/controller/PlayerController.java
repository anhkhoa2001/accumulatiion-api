package com.example.controller;

import com.example.dto.CheckinLogRequest;
import com.example.entity.Player;
import com.example.service.CheckinService;
import com.example.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final CheckinService checkinService;

    @Autowired
    public PlayerController(PlayerService playerService, CheckinService checkinService) {
        this.playerService = playerService;
        this.checkinService = checkinService;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody Player request) {
        playerService.create(request);

        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/check-in")
    public ResponseEntity checkin(@RequestBody String playerId) {
        checkinService.handleCheckin(playerId);

        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/checkin-log")
    public ResponseEntity getCheckinLog(@RequestBody CheckinLogRequest request) {
        return ResponseEntity.ok(checkinService.getCheckinLog(request));
    }

}
