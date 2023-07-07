    // Функция для получения данных из сети
    function fetchComments() {
        fetch('/comment/get_all')
            .then(response => response.json())
            .then(comments => this.displayComments(comments))
            .catch(error => console.log(error));
    }

    // Функция для отображения списка комментариев
    function displayComments(comments) {
        const commentList = document.getElementById('comment-list');
        commentList.innerHTML = '';

        comments.sort((a, b)=>{
            if (a.owner_name==='Ваш комментарий:'&&b.owner_name==='Ваш комментарий:')return b.count_likes-a.count_likes;
            else if (a.owner_name==='Ваш комментарий:')return -1;
            else if (b.owner_name==='Ваш комментарий:')return 1;
            else return b.count_likes-a.count_likes;
        });

        // Итерация по каждому комментарию и добавление его в список
        comments.forEach(comment => {
            let div=document.createElement('div');
            div.setAttribute('id','commentsId'+comment.id);

            collectDiv(div,comment);

            commentList.appendChild(div);
        });
    }
    function collectDiv(div,comment) {
        let ownerAndDate=document.createElement('p');ownerAndDate.textContent=comment.owner_name+' '+comment.created_at;ownerAndDate.classList.add('comment_info');
        let commentText = document.createElement('p');commentText.textContent =comment.text;commentText.classList.add('comment_text');
        //создание кнопок лайков
        let count_likes = document.createElement('a');count_likes.innerText = ' '+comment.count_likes+' ';count_likes.classList.add('comment_like_number');
        let likeImage = document.createElement('img');
        likeImage.classList.add('likeImage');
        let count_dislikes = document.createElement('a');count_dislikes.innerText = ' '+comment.count_dislikes+' ';count_dislikes.classList.add('comment_like_number');
        let dislikeImage = document.createElement('img');
        dislikeImage.classList.add('likeImage');
        if(comment.like_status==='like'){
            likeImage.src="/files/image/finger_up_green.png";
            dislikeImage.src="/files/image/finger_down_white.png";
        }else if(comment.like_status==='dislike'){
            likeImage.src="/files/image/finger_up_white.png";
            dislikeImage.src="/files/image/finger_down_red.png";
        }else {
            likeImage.src="/files/image/finger_up_white.png";
            dislikeImage.src="/files/image/finger_down_white.png";
        }


        // Обработчик события при нажатии на кнопку "like"
        likeImage.addEventListener('click', () => {
            const request = new XMLHttpRequest();
            request.open('GET', `/comment/like?type=like&id=${comment.id}`);

            request.onload = function() {
                if (request.status === 200) {
                    console.log('Успешный запрос');
                    //обновляем значение коментария
                    let comment = JSON.parse(request.responseText);
                    const div=document.getElementById('commentsId'+comment.id)
                    div.innerHTML='';
                    collectDiv(div,comment);
                } else {
                    console.log('Не удалось выполнить запрос');
                    console.log(request.responseText);
                }
            };
            request.onerror = function() {
                console.log('Произошла ошибка сети');
            };

            request.send();
        });
        // Обработчик события при нажатии на кнопку "dislike"
        dislikeImage.addEventListener('click', () => {
            const request = new XMLHttpRequest();
            request.open('GET', `/comment/like?type=dislike&id=${comment.id}`);
            request.onload = function() {
                if (request.status === 200) {
                    console.log('Успешный запрос');
                    //обновляем значение коментария
                    let comment = JSON.parse(request.responseText);
                    const div=document.getElementById('commentsId'+comment.id)
                    div.innerHTML='';
                    collectDiv(div,comment);
                } else {
                    console.log('Не удалось выполнить запрос');
                    console.log(request.responseText);
                }
            };
            request.onerror = function() {
                console.log('Произошла ошибка сети');
            };
            request.send();
        });

        div.appendChild(ownerAndDate);
        div.appendChild(commentText);
        div.appendChild(count_likes);
        div.appendChild(likeImage);
        div.appendChild(count_dislikes);
        div.appendChild(dislikeImage);
        div.appendChild(document.createElement('hr'));
    }