package com.ssafy.teongbin.shortcut.controller;

import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.common.reseponse.MsgType;
import com.ssafy.teongbin.common.reseponse.ResponseEntityDto;
import com.ssafy.teongbin.common.reseponse.ResponseUtils;
import com.ssafy.teongbin.shortcut.dto.request.AddShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.request.DeleteShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.response.ShortcutResponseDto;
import com.ssafy.teongbin.shortcut.entity.Shortcut;
import com.ssafy.teongbin.shortcut.service.ShortcutService;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/shortcut")
public class ShortcutController {

    private final ShortcutService shortcutService;
    private final UserService userService;

    // 숏컷 등록
    @PostMapping("/new")
    public ResponseEntityDto<Void> addShortcut(@AuthenticationPrincipal PrincipalDetails user, @RequestBody AddShortcutRequestDto addShortcutRequestDto) {
        shortcutService.addShortcut(user, addShortcutRequestDto);
        return ResponseUtils.ok(MsgType.ADD_SHORTCUT_SUCCESSFULLY);
    }

    // 숏컷 삭제
    @PostMapping("/delete")
    public ResponseEntityDto<Void> deleteShortcut(@AuthenticationPrincipal PrincipalDetails user, @RequestBody DeleteShortcutRequestDto deleteShortcutRequestDto) {
        shortcutService.deleteShortcut(user, deleteShortcutRequestDto);
        return ResponseUtils.ok(MsgType.DELETE_SHORTCUT_SUCCESSFULLY);
    }

    // 숏컷 리스트
    @GetMapping("")
    public ResponseEntityDto<List> getShortcutList(@AuthenticationPrincipal PrincipalDetails user) {
        List<ShortcutResponseDto> shortcuts = shortcutService.getShortcutList(user);
        return ResponseUtils.ok(shortcuts, MsgType.SEARCH_SHORTCUT_LIST_SUCCESSFULLY);
    };


}