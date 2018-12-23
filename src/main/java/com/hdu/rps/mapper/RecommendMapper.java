package com.hdu.rps.mapper;

import com.hdu.rps.model.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer rcdno);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer rcdno);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);
    
    int selectUserNoByRecommendedPersonID(int RcdNo);

    Recommend selectByRecommendedNoAndPosNo(@Param("repno") int repno, @Param("posno") int posno);

    ArrayList<Integer> selectRecommendedPersonIDByPosno(int posno);

    ArrayList<Integer> selectRecommendedPersonIDByPosnoAndState(@Param("posno") int posno,@Param("state") int state);

    ArrayList<Integer> selectRecommendedPersonIDByUserno(int userno);

    ArrayList<Recommend> selectRecommendByUserno(int userno);

    void deleteByRepNo(int repno);

    Recommend selectByRepNo(int repno);
}