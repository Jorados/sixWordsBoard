$(document).ready(function() {
    var iconId;

    $('i').click(function() {
        var likeClass = $(this).attr( 'class' );
        var boardId = $(this).attr( 'name' );
        var likeCheck;
        iconId = $(this).attr( 'id' ); // 클릭한 아이콘 아이디 값 저장

        if (likeClass === 'fa-solid fa-heart') { // 하트가 눌러져 있다면 -> 취소 로직
            likeCheck = 1;
        } else if (likeClass === 'fa-regular fa-heart') { // 하트가 안 눌러져 있다면 -> 누르는 로직
            likeCheck = 0;
        }

        $.ajax({
            url: "/like",
            data: {
                likeCheck: likeCheck,
                boardId: boardId,
            },
            type: "POST",
            success: function(response) {
                if (isNaN(response)) { // 반환값이 숫자가 아니면 경고창 출력
                    alert(response); // "로그인이 필요한 서비스입니다." 출력
                } else {

                    if (likeCheck === 1) {
                        $('#' + iconId).attr('class', 'fa-regular fa-heart');
                    } else if (likeCheck === 0) {
                        $('#' + iconId).attr('class', 'fa-solid fa-heart');
                    }

                    $('#' + iconId).text(response); // 좋아요 개수 업데이트
                }
            },
            error: function() {
                alert("요청 처리 중 오류가 발생했습니다.");
            }
        });
    })
});