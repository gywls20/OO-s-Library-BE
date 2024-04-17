package com.projectif.ooslibrary.admin.service;

import com.projectif.ooslibrary.admin.domain.MemberVO;
import com.projectif.ooslibrary.mapper.AdMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdMemberServiceImpl implements AdMemberService {

    private final AdMemberMapper adMemberMapper;

    @Override
    public List<MemberVO> findAllMember() {
        return adMemberMapper.findAllMember();
    }
}
