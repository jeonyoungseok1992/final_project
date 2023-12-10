$(document).ready(function() 
{
    // $("#input_file").on("change", handleImgFileSelect);

    var fontSizes = [ '8', '9', '10', '11', '12', '14','16', '18', '20', '22', '24', '28', '30', '36', '50', '72','100' ];
    var fontNames = [ 'Arial', 'Arial Black','Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '바탕체' ];
    var toolbar = [[ 'fontname', [ 'fontname' ] ],
    [ 'fontsize', [ 'fontsize' ] ],
    [ 'style',    [ 'bold', 'italic', 'underline','strikethrough', 'clear' ] ],
    [ 'color', [ 'forecolor', 'color' ] ],
    ['insert',['picture']],
    [ 'table', [ 'table' ] ],
    [ 'para', [ 'ul', 'ol', 'paragraph' ] ],
    [ 'height', [ 'height' ] ],
    [ 'view', [ 'codeview' ] ]
    ];
    
  var setting = {
      height : 500,
      minHeight : null,
      maxHeight : null,
      lang : 'ko-KR',
      toolbar : toolbar,
      fontSizes : fontSizes,
      fontNames : fontNames,
      disableResizeEditor: true,
      callbacks : { //여기 부분이 이미지를 첨부하는 부분
          onImageUpload : function(files, editor,	welEditable) 
          {
              for (var i = files.length - 1; i >= 0; i--) 
              {
                console.log(files[i],this);
                sendFile(files[i],this);
              }
          },
          onMediaDelete : function($target, editor, $editable) 
            {
                var deletedImageUrl = $target
                .attr('src')
                .split('/')
                .pop()
                // ajax 함수 호출
                deleteFile(deletedImageUrl);
                
            }
          }
      };
        
      // value 를 먼저 넣고 초기화 한다.  
    //   $('#summernote').val('${fn:replace(productDetail.product_content,"\'","\\'")}');
      $('#summernote').summernote(setting);
    });

     /* summernote에서 이미지 업로드시 실행할 함수 */
	 	function sendFile(file, editor) {
            // 파일 전송을 위한 폼생성
	 		data = new FormData();
	 	    data.append("file", file);
	 	    $.ajax({ // ajax를 통해 파일 업로드 처리
	 	        data : data,
	 	        type : "POST",
	 	        url : "uploadSummernoteImageFile",
                enctype : 'multipart/form-data',
	 	        cache : false,
	 	        contentType : false,
	 	        processData : false,
	 	        success : function(data) { // 처리가 성공할 경우
                    // 에디터에 이미지 출력
	 	        	$(editor).summernote('editor.insertImage', data);
	 	        }
	 	    });
	 	}
    /* summernote에서 이미지 삭제시 실행할 함수 */
         function deleteFile(file) {
            data = new FormData()
            data.append('file', file)
            $.ajax({
                data: data,
                type: 'POST',
                url: 'deleteSummernoteImageFile',
                contentType: false,
                enctype: 'multipart/form-data',
                processData: false,
            })
        }

        function sumReset(){
          // 서머노트 리셋
          $('#summernote').summernote('reset');
          document.getElementById('titlebox').value='';
        }
