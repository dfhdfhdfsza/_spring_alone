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

async function spreadCommentListFromServer(bno)
{
    try {
        const resp=await fetch('/comment/'+bno);
        const result=await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno)
{
    spreadCommentListFromServer(bno).then(result=>{
        let tbody=document.getElementById('cmtArea');
        console.log(result);
        if(result.length>0)
        {
            for(let i=0;i<result.length;i++)
            {
                let str=`<tr>`;
                str+=`<td>${result[i].cno}</td>`;
                str+=`<td>${result[i].writer}</td>`;
                str+=`<td>${result[i].content}</td>`;
                str+=`</tr>`;
                tbody.innerHTML+=str;
            }
        }
        else{
            let str=`<tr><td>no comment</td></tr>`;
            tbody.innerHTML+=str;
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
        if(result==1)
            alert('등록 성공!');
    })

})