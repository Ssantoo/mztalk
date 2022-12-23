package com.mztalk.main.domain.follow.service.impl;


import com.mztalk.main.domain.follow.dto.ProfileDto;
import com.mztalk.main.domain.follow.dto.ProfileResponseDto;
import com.mztalk.main.domain.follow.entity.Profile;
import com.mztalk.main.domain.follow.repository.ProfileCustomRepository;
import com.mztalk.main.domain.follow.repository.ProfileRepository;
import com.mztalk.main.domain.follow.service.ProfileService;
import com.mztalk.main.domain.board.repository.BoardRepository;
import com.mztalk.main.domain.follow.vo.ProfileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static com.mztalk.main.status.ProfileImageStatus.NO;
import static com.mztalk.main.status.ProfileImageStatus.YES;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    private final ProfileCustomRepository profileCustomRepository;

    private final BoardRepository boardRepository;


//
//        HttpHeaders headersImg = new HttpHeaders();
//        headersImg.add("Content-type", "text/html");
//
//
//        //사진 한번도 바꾸지 않았던 사람이라면 기본 프로필 이미지를 set해주기 위해
//        Profile profile = profileCustomRepository.findByUserStatus(own);
//
//        System.out.println(profile.getProfileImageStatus());
//
//        if(profile.getProfileImageStatus()==NO) {
//        //프로필 기본 사진
//             ProfileDto.builder()
//                .profileUrl("https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png")
//                .build();
//
//        }else{
//        //바꾼 프로필 사진
//        ResponseEntity<String> responseImg = new RestTemplate().exchange(
//                "http://localhost:8000/resource/main-image?bNo="+own+"&serviceName=story",    //첫번째: url
//                HttpMethod.GET,
//                new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
//                String.class
//        );
//
//        JSONObject jsonObject = new JSONObject(responseImg.getBody());
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        JSONObject obj = jsonArray.getJSONObject(0);
//        String imageUrl = obj.getString("imageUrl");
//
//            ProfileDto.builder()
//                    .profileUrl(imageUrl)
//                    .build();
//        }
//
//        //페이지주인이름
//        HttpHeaders headersName = new HttpHeaders();
//        headersName.add("Content-type", "text/html");
//
//        ResponseEntity<String> responseName = new RestTemplate().exchange(
//                "http://localhost:8000/login/user-info/" + own,
//                HttpMethod.GET,
//                new HttpEntity<String>(headersImg),
//                String.class
//        );
//
////        System.out.println("response2 : " + response2.getBody());
//        JSONObject ownName = new JSONObject(responseName.getBody());
//        String nickname = ownName.getString("nickname");
//
////        System.out.println("nickname : " + nickname);
//
//
//        //유저 게시물
//        long count = boardRepository.countByOwn(own);
//
//        return ProfileVo.builder()
//                .nickname(nickname)
//                .profileUrl(profile.getPostImageUrl())
//                .boardCount(count)
//                .followerCount(1L)   //임시 더미데이터
//                .followingCount(2L)  //임시 더미데이터
//                .build();
//    }

    //개인 프로필 사진
    @Override
    @Transactional(readOnly = true)
    public Profile ProfileImg(long own) {

        Profile profile = profileCustomRepository.findByUserStatus(own);

        System.out.println(profile.getProfileImageStatus());

        HttpHeaders headersImg = new HttpHeaders();
        headersImg.add("Content-type", "text/html");

        if(profile.getProfileImageStatus()==YES){
            ResponseEntity<String> responseImg = new RestTemplate().exchange(
                    "http://localhost:8000/resource/main-image?bNo=" + own + "&serviceName=story",    //첫번째: url
                    HttpMethod.GET,
                    new HttpEntity<String>(headersImg),     //바디, 헤더 다 담기 가능/엔티티
                    String.class
            );

            JSONObject profileImage = new JSONObject(responseImg.getBody());
            JSONObject profileData = profileImage.getJSONObject("data");
            String imageUrl = profileData.getString("imageUrl");
            String imageName = profileData.getString("objectKey");

            ProfileDto.builder()
                    .profileUrl(imageUrl)
                    .profileImageName(imageName)
                    .build();

             Profile.builder()
                    .postImageUrl(profile.getPostImageUrl())
                    .build();

            System.out.println(profile);

            return profile;
        }else {
            return Profile.builder()
                    .postImageUrl("https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png")
                    .profileImageName("기본이미지")
                    .build();
        }

    }

    //이미지바꾸기
    @Override
    @Transactional
    public ProfileResponseDto changeProfile(long own, ProfileDto profileDto) {

        HttpHeaders headerImage = new HttpHeaders();
        headerImage.add("Content-type", "text/html");

        ResponseEntity<String> responseproImg = new RestTemplate().exchange(
                "http://localhost:8000/resource/main-image?bNo="+own+"&serviceName=story",    //첫번째: url
                HttpMethod.GET,
                new HttpEntity<String>(headerImage),     //바디, 헤더 다 담기 가능/엔티티
                String.class
        );

        JSONObject profileImg = new JSONObject(responseproImg.getBody());
            //String objectKey = profileImg.getString("objectKey");
         System.out.println("프로필이미지 관련 : "+ profileImg);
         // System.out.println(objectKey);
        //profileImg.put("imageUrl", profileImg.getString("imageUrl"));

        JSONObject profileData = profileImg.getJSONObject("data");

        String imageUrl = profileData.getString("imageUrl");
        String imageName = profileData.getString("objectKey");

        System.out.println(imageUrl);
        System.out.println(imageName);




        //유저의이름
        HttpHeaders headersNames = new HttpHeaders();
        headersNames.add("Content-type", "text/html");

        ResponseEntity<String> responseName = new RestTemplate().exchange(
                "http://localhost:8000/login/user-info/" + own,
                HttpMethod.GET,
                new HttpEntity<String>(headerImage),
                String.class
        );

        JSONObject ownName = new JSONObject(responseName.getBody());
        String nickname = ownName.getString("nickname");

        Profile profile = profileRepository.save(profileDto.toEntity(profileData, ownName));

        return new ProfileResponseDto(profile);

    }




    //String profileUrl = profileRepository.save(imageUrl);

//        return ProfileDto.builder()
//                .profileUrl(imageUrl)
//                .profileImageName(imageName)
//                .nickname(nickname)
//                .build();




//    //프로필 이미지 변경
//    @Override
//    public ProfileVo getProfileImage(long own) {
//        HttpHeaders headersProfileImg = new HttpHeaders();
//        headersProfileImg.add("Content-type", "text/html");
//
//
//        ResponseEntity<String> responseProfileImg = new RestTemplate().exchange(
//                "http://localhost:8000/resource/main-image",
//                HttpMethod.GET,
//                new HttpEntity<String>(headersProfileImg),
//                String.class
//        );
//
//        JSONObject jsonObject = new JSONObject(responseProfileImg.getBody());
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        JSONObject obj = jsonArray.getJSONObject(0);
//        String imageUrl = obj.getString("imageUrl");
//
//
//        System.out.println(imageUrl);
//
//
//
//
//
//        return ProfileVo.builder()
//                .profileUrl(imageUrl)
//                .nickname(nickname)
//                .build();
//    }
























//    private String asString(String data,String dataname) {
//        try{
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(data);
//            return element.getAsJsonObject().get(dataname).getAsString();
//        } catch(Exception e) {
//            log.error("not JsonObject");
//        }
//        return "not JsonObject";
//    }
    }


// for(int i = 0 ; i < jsonArray.length(); i++){
//        JSONObject obj = jsonArray.getJSONObject(0);
//        String imageName = obj.getString("imageName");
//        String imageUrl = obj.getString("imageUrl");
//
//        System.out.println("imageName : " + imageName);
//        System.out.println("imageUrl : " + imageUrl);
//        }
