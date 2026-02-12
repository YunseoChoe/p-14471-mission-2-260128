package org.example.global;

import org.example.domain.wiseSaying.controller.SystemController;
import org.example.domain.wiseSaying.controller.WiseSayingController;
import org.example.domain.wiseSaying.repository.WiseSayingRepository;
import org.example.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class AppContext {
    // 프로그램 전체에서 하나만 있으면 된다. (공유 목적 -> static)
    public static Scanner sc = new Scanner(System.in);
    public static WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
    public static WiseSayingService wiseSayingService = new WiseSayingService();
    public static WiseSayingController wiseSayingController = new WiseSayingController(sc);
    public static SystemController systemController = new SystemController();
}
