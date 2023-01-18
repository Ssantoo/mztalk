package com.mztalk.main.domain.profile.service;


import com.mztalk.main.domain.profile.dto.ProfileDto;
import com.mztalk.main.domain.profile.dto.ProfileImageResponseDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.profile.entity.Profile;

import java.util.Optional;

public interface ProfileService {


    ProfileResponseDto changeProfile(long own, ProfileDto profileDto);

    ProfileImageResponseDto profileImg(long own);


    ProfileResponseDto profileName(long own);

    Long boardCount(long own);

    Long followerCount(long own);

    Long followingCount(long own);
}
