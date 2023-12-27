Kakao.init('46465c1e54053b370d7b004cf8ed3df6');

let inputTag = document.querySelector(".copy-value");
                
function copyText(){ 

    navigator.clipboard.writeText(window.location.href).then(res=>{
        alert("url이 복사되었습니다.");
    })
}

function shareMessage(num) {
    console.log(num)
    Kakao.Share.sendDefault({
      objectType: 'feed',
      content: {
        title: '공유된 여행 계획이에요 :)',
        description: '#함께하면 #더좋은여행 #같이가요우리',
        imageUrl:
          'https://img.freepik.com/premium-photo/bukchon-hanok-village-with-seoul-city-skyline-cityscape-of-south-korea_255553-4703.jpg',
        link: {
          // [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
          mobileWebUrl: 'http://localhost:8009',
          webUrl: 'http://localhost:8009',
        },
      },
      buttons: [
        {
          title: '웹으로 보기',
          link: {
            mobileWebUrl: 'http://localhost:8009/mapping/finalPlan.bo?tripPlanNo='+num,
            webUrl: 'http://localhost:8009/mapping/finalPlan.bo?tripPlanNo='+num,
          },
        },
      ],
    });
  }

  function shareFacebook(){
    const url = document.URL;
    window.open('https://www.facebook.com/sharer.php?u=' + url,'facebook','toolbar=0,status=0,width=655,height=520');
  }
  function shareNaverblog() {
    var url = encodeURI(encodeURIComponent(myform.url.value));
    var title = encodeURI(myform.title.value);
    var shareURL = "https://share.naver.com/web/shareView?url=" + url + "&title=" + title;
    document.location = shareURL;
  }