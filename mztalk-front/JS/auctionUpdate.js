
//디테일값 그대로 받아오기
window.onload = () => {
    const imageInfo = JSON.parse(localStorage.getItem("imageInfo"));
    console.log("update imageInfo: " + imageInfo);
    document.getElementById("title").value = localStorage.getItem("title");
    for(let i = 0; i < imageInfo.length; i++) {
        console.log("hidden-objectKey 넣을 값: " + imageInfo[i].objectKey);
        document.getElementById("hidden-objectKey").value = imageInfo[i].objectKey;
        document.getElementById("fileList").innerHTML = `<span class = "imageName">${imageInfo[i].imageName}</span><span id = "fileDelete" style="color: gray;margin-left: 5px; cursor: pointer;" onclick="deleteFile();">X<br></span>`;

    }
    document.getElementById("content").value = localStorage.getItem("content");
    document.getElementById("startPrice").value = localStorage.getItem("startPrice");
    document.getElementById("timeLimit").value = localStorage.getItem("timeLimit");
    document.getElementById("hidden-bId").value = localStorage.getItem("bId");


    localStorage.removeItem("title");
    localStorage.removeItem("content");
    localStorage.removeItem("startPrice");
    localStorage.removeItem("timeLimit");

    document.getElementById("startPrice").addEventListener('click', function() {
        alert("시작가는 변경할 수 없습니다.");
    });
    document.getElementById("timeLimit").addEventListener('click', function() {
        alert("남은 시간은 변경할 수 없습니다.");
    });
}

//파일 추가 버튼
document.getElementById("addFile").addEventListener('click', function() {
    const fileArea = document.getElementById("fileArea");
    
    if(Number(document.getElementsByName("image").length) + Number(document.getElementsByClassName("imageName").length) < 3) {
        const newDiv = document.createElement('div');
        newDiv.classList.add('col-10');
        newDiv.innerHTML = '<input type = "file" class = "form-control" name = "image" accept="image/*"">';
        fileArea.append(newDiv);
    } else {
        alert("파일은 3개까지 첨부 가능합니다.");
    }
});

//수정하기
function updateBoard() {
    fetch("http://localhost:8000/auction/" + document.getElementById('hidden-bId'), {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "title" : document.getElementById('title').value,
            "writer": localStorage.getItem('userNickname'),
            "content":document.getElementById('content').value,
            "bId": document.getElementById('hidden-bId').value
        }),
    })
    .then(res => {
        location.href="auctionDetail.html";
    })
}

//파일 삭제
function deleteFile() {
    console.log("파일삭제 시 hidden-objectKey: " + document.getElementById("hidden-objectKey").value);
    if(confirm('정말 삭제하시겠습니까?')) {
        fetch('http://localhost:8000/resource/image-detail?imageName=' + document.getElementById("hidden-objectKey").value, {
            method: 'DELETE',
            headers: {
                "Content-Type": "text/html",
                Authorization: localStorage.getItem('authorization'),
                RefreshToken: localStorage.getItem('refreshToken'),
            }
        })
        .then(res=>{
            if(res.status == 200) {
                console.log("이미지 삭제 완료");
                document.getElementById("fileList").innerHTML += "";
            }
        })
    }

}

//목록으로
function backToMain() {
    location.href="auction.href";
}
