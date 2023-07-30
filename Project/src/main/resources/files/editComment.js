function editComment(comment){
    let editableComment = document.getElementById('commentsId'+comment.id);
    editableComment.innerText='';
    let textArea = document.createElement('textarea');

    textArea.innerHTML=comment.text;
    textArea.style.width='100%';

    //функция слежения за размером поля
    textArea.addEventListener('input', function() {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';});

    //кнопка edit
    let buttonEdit = document.createElement('button');
    buttonEdit.innerText = 'Редактировать';
    buttonEdit.style.marginRight = '20px';
    buttonEdit.addEventListener('click',function (event) {
        event.preventDefault();
        let request = new XMLHttpRequest();
        request.open('POST', '/comment/update', true);
        request.setRequestHeader('Content-Type', 'application/json');

        request.onreadystatechange = function () {
            fetchComments();
        }
        request.send(JSON.stringify({id: comment.id, text: textArea.value}));
    });

    //кнопка cancel
    let buttonCancel = document.createElement('button');
    buttonCancel.innerText = 'Отмена';
    buttonCancel.addEventListener('click',function (event) {
        event.preventDefault();
        let editableComment = document.getElementById('commentsId'+comment.id);
        editableComment.innerText='';
        collectDiv(editableComment,comment);
    })

    editableComment.appendChild(document.createElement('br'));
    editableComment.appendChild(textArea);
    editableComment.appendChild(document.createElement('br'));
    editableComment.appendChild(buttonEdit);
    editableComment.appendChild(buttonCancel);
    editableComment.appendChild(document.createElement('br'));
    editableComment.appendChild(document.createElement('hr'));

    textArea.style.height = 'auto';
    textArea.style.height = textArea.scrollHeight + 'px';
}