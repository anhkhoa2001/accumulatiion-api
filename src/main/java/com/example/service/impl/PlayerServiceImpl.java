package com.example.service.impl;

import com.example.entity.Player;
import com.example.exception.SystemException;
import com.example.repo.PlayerRepository;
import com.example.service.PlayerService;
import com.example.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void create(Player request) {
        Player playerExist = playerRepo.getPlayerByPlayerName(request.getPlayerName());

        if(playerExist != null) {
            throw new SystemException(HttpStatus.BAD_REQUEST, String.format(Constants.ERROR_CODE.CODE_EXIST, "Username"));
        }

        request.setPlayerId(UUID.randomUUID().toString());
        playerRepo.save(request);
    }
}
