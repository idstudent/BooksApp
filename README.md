# BooksApp 토이프로젝트 

## 기간
2022.11.18 ~ 진행중
이후에도 신기술이 있으면 공부해서 추가할 예정입니다.

## 기술스택 
이후에도 계속 추가될 예정이나, 현재 기술스택입니다.

언어 : kotlin, DI : koin


아키텍처 :

          master 브랜치 - MVVM, Clean Architecture

          book-mvvm-clean-architecture 브랜치 - MVVM, Clean Architecture 
          
          book-mvvm 브랜치 - MVVM
          

Coroutine, flow, paging3, room, navigation(바텀 네비게이션으로 사용), retrofit2, glide


## 내용
인터파크 오픈 API를 이용하여 만든 토이프로젝트입니다.

현재는 master, book-mvvm 브랜치만 있으나, 이전에 MovieIntroduce 프로젝트처럼 브랜치를 나눠갈 계획입니다.
ex) 클린아키텍처를 적용하려는 경우 master브랜치에서 book-cleanArchitecture로 따로 브랜치를 만들어 클린아키텍처 적용
master가 가장 최근의 업데이트(?)를 반영하는 브랜치입니다. **최신 업데이트 날짜 2022.12.03**

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

## 검색
검색한 제목을 토대로 리스트를 보여줍니다.
기본은 국내 도서이며, 외국도서 검색을 하려는 경우 외국도서보기를 체크하여, 검색을 합니다.

https://user-images.githubusercontent.com/36175704/204428899-9efabc17-2469-4189-98fe-2ff7907f9a42.mp4

## 베스트셀러
국내도서, 외국도서 베스트 샐러 리스틀 보여주며,
리스트 클릭시 상세, 더보기 클릭시 국내 또는 외국 베스트 셀러의 전체 리스트를 보여줍니다.

ui는 뷰페이저를 이용해 캐러셀을 구현했습니다.

https://user-images.githubusercontent.com/36175704/204429263-8f5ad515-a551-4090-ad4f-72bc94f781eb.mp4

## 나의 책장
room을 이용하여 북마크한 책을 보여줍니다. 
북마크 해제시, 리스트에 반영합니다.

https://user-images.githubusercontent.com/36175704/204429586-3d2a1360-cb4e-41fd-8267-6efa9be1edf8.mp4


## 독후감
room을 이용하여 독후감을 쓸 수 있습니다.
상세페이지에서 리뷰쓰기버튼을 통해 리뷰쓰는 페이지로 이동
쓰기, 수정이 가능합니다.

https://user-images.githubusercontent.com/36175704/204429935-2838919a-8a03-40ec-a102-42a7aed63e58.mp4
