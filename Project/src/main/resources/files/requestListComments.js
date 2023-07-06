    // Функция для получения данных из сети
    function fetchComments() {
        fetch('/comment/get_all')
            .then(response => response.json())
            .then(comments => this.displayComments(comments))
            .catch(error => console.log(error));
    }

    // Функция для отображения комментариев в списке
    function displayComments(comments) {
        const commentList = document.getElementById('comment-list');

        // Очистка списка перед добавлением новых элементов
        commentList.innerHTML = '';

        // Итерация по каждому комментарию и добавление его в список
        comments.forEach(comment => {
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
                        fetchComments();//обновляем список комментов
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
                        fetchComments();//обновляем список комментов
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

            commentList.appendChild(ownerAndDate);
            commentList.appendChild(commentText);
            commentList.appendChild(count_likes);
            commentList.appendChild(likeImage);
            commentList.appendChild(count_dislikes);
            commentList.appendChild(dislikeImage);
            commentList.appendChild(document.createElement('hr'));
        });
    }
