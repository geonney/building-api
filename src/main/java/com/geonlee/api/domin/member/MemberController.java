package com.geonlee.api.domin.member;

import com.geonlee.api.common.code.NormalCode;
import com.geonlee.api.common.response.ItemResponse;
import com.geonlee.api.common.response.ItemsResponse;
import com.geonlee.api.config.message.MessageConfig;
import com.geonlee.api.domin.member.record.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author GEONLEE
 * @since 2024-07-25
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MessageConfig messageConfig;

    @GetMapping(value = "/members/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponse<MemberSearchResponse>> getMemberById(@PathVariable("memberId") String memberId) {
        return ResponseEntity.ok()
                .body(ItemResponse.<MemberSearchResponse>builder()
                        .status(messageConfig.getCode(NormalCode.SEARCH_SUCCESS))
                        .message(messageConfig.getMessage(NormalCode.SEARCH_SUCCESS))
                        .item(memberService.getMemberById(memberId))
                        .build());
    }

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemsResponse<MemberSearchResponse>> getMembers() {
        return ResponseEntity.ok()
                .body(ItemsResponse.<MemberSearchResponse>builder()
                        .status(messageConfig.getCode(NormalCode.SEARCH_SUCCESS))
                        .message(messageConfig.getMessage(NormalCode.SEARCH_SUCCESS))
                        .items(memberService.getMembers())
                        .build());
    }

    @PostMapping(value = "/member"
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponse<MemberCreateResponse>> createMember(@RequestBody @Valid MemberCreateRequest parameter) {
        return ResponseEntity.ok()
                .body(ItemResponse.<MemberCreateResponse>builder()
                        .status(messageConfig.getCode(NormalCode.CREATE_SUCCESS))
                        .message(messageConfig.getMessage(NormalCode.CREATE_SUCCESS))
                        .item(memberService.createMember(parameter))
                        .build());
    }

    @PutMapping(value = "/member"
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponse<MemberModifyResponse>> modifyMember(@RequestBody MemberModifyRequest parameter) {
        return ResponseEntity.ok()
                .body(ItemResponse.<MemberModifyResponse>builder()
                        .status(messageConfig.getCode(NormalCode.MODIFY_SUCCESS))
                        .message(messageConfig.getMessage(NormalCode.MODIFY_SUCCESS))
                        .item(memberService.modifyMember(parameter))
                        .build());
    }

    @DeleteMapping(value = "/member/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponse<Long>> deleteMember(@PathVariable("memberId") String memberId) {
        return ResponseEntity.ok()
                .body(ItemResponse.<Long>builder()
                        .status(messageConfig.getCode(NormalCode.MODIFY_SUCCESS))
                        .message(messageConfig.getMessage(NormalCode.MODIFY_SUCCESS))
                        .item(memberService.deleteMember(memberId))
                        .build());
    }
}
