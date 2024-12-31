package com.yang.finance.biz.mapper;

import com.yang.finance.biz.domain.Member;
import com.yang.mybatis.help.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends CommonMapper<Member> {
}