package com.projectif.ooslibrary.mapper;

import com.projectif.ooslibrary.admin.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdMamberMapper {

    List<MemberVO> findAllMember();
}
