console.log(bnoVal);
async function postCommentToServer(cmtData)
{
    try {
        const url='/comment/post';
        const config={
            method:"post",
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        }
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function spreadCommentListFromServer(bnoVal,page)
{
    try {
        const resp=await fetch('/comment/'+bnoVal+'/'+page);
        const result=await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bnoVal,page=1)
{
    
    spreadCommentListFromServer(bnoVal,page).then(result=>{
        
        let tbody=document.getElementById('cmtArea');
        if(page==1){
            //다시 댓글을 뿌릴때 기존 값 삭제 1page일 경우만
            tbody.innerHTML="";
        }
            console.log(result);
            if(result.cmtList.length>0)
            {
                for(let i=0;i<result.cmtList.length;i++)
                {
                    let str=`<tr data-cno='${result.cmtList[i].cno}'>`;
                    str+=`<td>${result.cmtList[i].cno}</td>`;
                    str+=`<td>${result.cmtList[i].writer}</td>`;
                    str+=`<td><input type="text" value="${result.cmtList[i].content}" id="cmtModText"></td>`;
                    str+=`<td>${result.cmtList[i].regAt}</td>`
                    str+=`<td>${result.cmtList[i].modAt}</td>`
                    str+=`<td><button type="button" class="delBtn">삭제</button></td>`;
                    str+=`<td><button type="button" class="modBtn">수정</button></td>`;
                    str+=`</tr>`;
                    tbody.innerHTML+=str;
                }
            }
            else{
                let str=`<tr><td>no comment</td></tr>`;
                tbody.innerHTML+=str;
            }
            //댓글 페이징 코드
            // let moreBtn=document.getElementById('moreBtn');
            //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음
            if(result.pgvo.pageNo<result.endPage||result.next){
                moreBtn.style.visibility='visible';
            }
            else{
                moreBtn.style.visibility='hidden';//버튼 숨김
            }
    
        })
    }



document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    let cmtWriter=document.getElementById('cmtWriter').innerText;
    let cmtText=document.getElementById('cmtText').value;
    let cmtData={
        bno:bnoVal,
        writer:cmtWriter,
        content:cmtText
    };  
    console.log(cmtData);
    postCommentToServer(cmtData).then(result=>{
        cmtText.value="";
        if(result==1){
            alert('등록 성공!');
        }
        getCommentList(bnoVal);
    })

})

document.addEventListener('click',(e)=>{
    let tr=e.target.closest('tr');
    if(e.target.classList.contains('delBtn'))
    {
        let cno=tr.dataset.cno;
        removeCommentToServer(cno).then(result=>{
            if(result==1){
                alert('삭제성공');
                getCommentList(bnoVal);
            }
            else{
                alert('삭제 실패!');
            }
        })
    }
    else if(e.target.classList.contains('modBtn'))
    {
        let cmtData={
            cno:tr.dataset.cno,
            content:document.getElementById('cmtModText').value
        };
        console.log(cmtData);
        editCommentToServer(cmtData).then(result=>{
            if(result==1){
                alert('수정 성공!');
                getCommentList(bnoVal);
            }
            else{
                alert('수정 실패!');
            }
        })
    }
    else if(e.target.id=='moreBtn')
    {
        let moreBtn=document.getElementById('moreBtn');
        let page=moreBtn.dataset.page;
        moreBtn.dataset.page=parseInt(page)+1;
        getCommentList(bnoVal,parseInt(e.target.dataset.page));
    }
    
})

async function removeCommentToServer(cno)
{
    try {
        const url='/comment/'+cno;
        const config={
            method:'delete'
        };
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function editCommentToServer(cmtData)
{
    
    try {
        const url='/comment/'+cmtData.cno;
        const config={
            method: 'put',
            headers:{
                'Content-Type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;  
    } catch (error) {
        console.log(error);
    }
}