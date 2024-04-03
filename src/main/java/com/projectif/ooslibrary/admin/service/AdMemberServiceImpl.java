package com.projectif.ooslibrary.admin.service;

import com.projectif.ooslibrary.admin.domain.MemberVO;
import com.projectif.ooslibrary.admin.mapper.AdMamberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdMemberServiceImpl implements AdMemberService {

    private final AdMamberMapper adMamberMapper;

    @Override
    public List<MemberVO> findAllMember() {
        return adMamberMapper.findAllMember();
    }
}
