$(document).ready(function () {
    //지도 api
        var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        var options = { //지도를 생성할 때 필요한 기본 옵션
            center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
            level: 3 //지도의 레벨(확대, 축소 정도)
        };
    
        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    
        //서머노트 그려주는 함수
        $(document).ready(function () {
            $('#summernote').summernote({
                lang: 'ko-KR',
                tabsize: 2,
                width: 1100,
                height: 270,
                toolbar: [
                    ['style', ['style']],
                    ['font', ['bold', 'underline', 'clear']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['table', ['table']],
                    ['insert', ['link', 'picture', 'video']],
                    ['view', ['fullscreen', 'codeview', 'help']]
                ]
            });
        });
    
        //서머노트에 있는 name값 데이터 가져와서 넘겨주는 함수
        function goWrite(frm) {
            var title = frm.title.value;
            var content = frm.content.value;
    
            if (title.trim() == '') {
                alert("제목을 입력해주세요");
                return false;
            }
            if (content.trim() == '') {
                alert("내용을 입력해주세요");
                return false;
            }
            frm.submit();
        }

    // 별점주기
    let $ratings = $('.ui-grade');


    $ratings.each(function () {
        if ($(this).data('init')) return;
        $(this).data('init', true);

        let $star = $(this).find('button');

        //default
        let $checkedStar = $(this).find('[aria-checked="true"]');
        if ($checkedStar) {
            $checkedStar.addClass('checked');
            $checkedStar.prevAll().addClass('checked');
        }

        //mouseover
        $star.on('mouseover', function () {
            $star.not($(this).addClass('hover')).removeClass('hover');
            $(this).prevAll().addClass('hover');
        });
        $star.on('mouseleave', function () {
            $star.removeClass('hover');
        });

        //click
        $star.on('click', function () {
            let $this = $(this);
            let txt = $this.text();
            let $result = $this.parent().find('.result-text');

            $star.not($this.addClass('checked')).removeClass('checked');
            $this.prevAll().addClass('checked');
            $star.not($this.attr('aria-checked', true)).attr('aria-checked', false);

            if (txt) {
                $result.text(txt);
            }
        });
    });









});