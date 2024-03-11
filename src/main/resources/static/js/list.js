var count = 0;
var isLiked = false;
function addLike() {
    const pushHeartBtn = document.querySelector(".heartBtn");
    if (!isLiked) {
        pushHeartBtn.innerHTML = '<i class="fa-solid fa-heart" style="color: #ff0000;"></i>';
        countPlus();
    } else {
        pushHeartBtn.innerHTML = '<i class="fa-regular fa-heart"></i>';
        countMinus();
    }
    isLiked = !isLiked;
}
//detail.html이면 아래코드 시행
function countPlus() {
    count = count + 1;
    if (window.location.pathname.includes('detail.html')) {
        document.querySelector(".like_count").innerText = count;
    } else {
        document.querySelector(".like_count").innerText = count + "개";
    }
}

function countMinus() {
    count = count - 1;
    if (window.location.pathname.includes('detail.html')) {
        document.querySelector(".like_count").innerText = count;
    } else {
        document.querySelector(".like_count").innerText = count + "개";
    }
}
  	document.querySelector(".heartBtn").addEventListener('click', addLike);
	
	// notice-m-container를 클릭했을 때 이벤트 처리
	document.querySelector('.notice-m-container').addEventListener('click', function() {
	    window.location.href = 'https://www.youtube.com/'; // 이동할 링크를 원하는 주소로 변경하세요
	});
	
	// heart-info를 클릭했을 때 이벤트 처리
	document.querySelector('.fa-regular.fa-heart').addEventListener('click', function(event) {
	    event.stopPropagation(); // 상위 요소로 이벤트 전파를 막음
	    // 하트 클릭 시 수행할 동작을 여기에 작성하세요
	});

$(document).ready(function() {
    $(".like-button").click(function() { // '.like-button' 클래스를 가진 버튼을 클릭하면
        var runningNo = $(this).data("runningNo"); // 클릭한 버튼의 'data-running-no' 속성 값을 가져옵니다.
        
        // 좋아요 수를 증가시키는 요청을 보냅니다.
        $.ajax({
            type: "POST",
            url: "/runnings/" + runningNo + "/like",
            success: function() {
                // 요청이 성공하면, 해당 게시물의 최신 좋아요 수를 받아옵니다.
                $.ajax({
                    type: "GET",
                    url: "/runnings/" + runningNo,
                    success: function(data) {
                        // 최신 좋아요 수를 화면에 업데이트합니다.
                        $("#like-count-" + runningNo).text(data.countLike);
                    }
                });
            }
        });
    });
});


