# BooksApp 토이프로젝트

## 기간
2022.11.18 ~ 진행중
이후에도 신기술이 있으면 공부해서 추가할 예정입니다.

## 기술스택 
이후에도 계속 추가될 예정이나, 현재 기술스택입니다.

언어 : kotlin, 아키텍처 : MVVM, DI : koin

Coroutine, flow, paging3, room, navigation(바텀 네비게이션으로 사용), retrofit2, glide


## 내용
인터파크 오픈 API를 이용하여 만든 토이프로젝트입니다.

현재는 master, book-mvvm 브랜치만 있으나, 이전에 MovieIntroduce 프로젝트처럼 브랜치를 나눠갈 계획입니다.
ex) 클린아키텍처를 적용하려는 경우 master브랜치에서 book-cleanArchitecture로 따로 브랜치를 만들어 클린아키텍처 적용
master가 가장 최근의 업데이트(?)를 반영하는 브랜치입니다.

각 화면의 설명은 mp4의 화면과 같이 설명을 기재 해놓았습니다. (gif를 사용하려했으나, 10mb 용량 제한으로 mp4인 점 양해 부탁드립니다.)

MovieIntroduce라는 토이프로젝트가 있으나, API가 국내영화 데이터가 얼마 없기도 하고, 
UI가 단순하다보니, 기능 추가가 좀 힘들 것같다고 판단하여 새로 만들게 되었습니다.

## 오픈 API 출처
http://book.interpark.com/bookPark/html/bookpinion/api_main.html

## 메인, 더보기, 상세
국내도서, 외국도서, 국내 도서 추천 리스트를 보여줍니다.
리스트는 6개로 제한, 더보기를 통해 전부를 보이도록 구성했습니다.
영상에서 회색이미지는 책의 이미지가 없는경우 error image로 처리했습니다.

리스트에서 클릭시, 상세페이지로 이동하며, 상세 내용을 보여줍니다.
영상에서 북마크 클릭시 해당 데이터를  room에 저장, 나의 책장에서 볼 수 있습니다.

외국도서 추천리스트가 데이터가 너무 없다보니, 국내추천도서 리스트만 추가해줬습니다.


https://user-images.githubusercontent.com/36175704/204427938-c75ee5d2-8969-491b-94f2-706129fd032b.mp4
