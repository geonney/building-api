package com.geonlee.api.domin.member;

import com.geonlee.api.common.response.ItemResponse;
import com.geonlee.api.common.response.ItemsResponse;
import com.geonlee.api.config.locale.LocaleConfig;
import com.geonlee.api.config.message.MessageConfig;
import com.geonlee.api.domin.member.record.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

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
                        .status(messageConfig.getMessage("SUCCESS.SEARCH.CODE"))
                        .message(messageConfig.getMessage("SUCCESS.SEARCH.MESSAGE"))
                        .item(memberService.getMemberById(memberId))
                        .build());
    }

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemsResponse<MemberSearchResponse>> getMembers() {
        return ResponseEntity.ok()
                .body(ItemsResponse.<MemberSearchResponse>builder()
                        .status(messageConfig.getMessage("SUCCESS.SEARCH.CODE"))
                        .message(messageConfig.getMessage("SUCCESS.SEARCH.MESSAGE"))
                        .items(memberService.getMembers())
                        .build());
    }

    @PostMapping(value = "/member"
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberCreateResponse> createMember(@RequestBody MemberCreateResponse parameter) {
        return ResponseEntity.ok()
                .body(new MemberCreateResponse(null, null, null, null));
    }

    @PutMapping(value = "/member"
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberModifyResponse> modifyMember(@RequestBody MemberModifyRequest parameter) {
        return ResponseEntity.ok()
                .body(new MemberModifyResponse(null, null, null));
    }

    @DeleteMapping(value = "/member"
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteMember(@RequestBody MemberDeleteRequest parameter) {
        return ResponseEntity.ok()
                .body(1L);
    }
}
