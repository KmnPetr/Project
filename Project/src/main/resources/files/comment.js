var getCommentObject={

    // Функция для получения данных из сети
        fetchComments:function() {
        fetch('/comment/get_all')
            .then(response => response.json())
            .then(comments => this.displayComments(comments))
            .catch(error => console.log(error));
    },

    // Функция для отображения комментариев в списке
    displayComments:function(comments) {
        const commentList = document.getElementById('comment-list');

        // Очистка списка перед добавлением новых элементов
        commentList.innerHTML = '';

        // Итерация по каждому комментарию и добавление его в список
        comments.forEach(comment => {
            const ownerAndDate=document.createElement('p');
            ownerAndDate.textContent=comment.owner_name+' '+comment.created_at;
            const commentText = document.createElement('p');
            commentText.textContent =comment.text;

            // Создание кнопки "like"
            const likeButton = document.createElement('button');
            likeButton.textContent = 'like';
            // Создание кнопки "dislike"
            const dislikeButton = document.createElement('button');
            dislikeButton.textContent = 'dislike';

            // Обработчик события при нажатии на кнопку "like"
            likeButton.addEventListener('click', () => {
                const request = new XMLHttpRequest();
                request.open('GET', `/comment/like?type=like&id=${comment.id}`);
                request.send();
            });
            // Обработчик события при нажатии на кнопку "dislike"
            dislikeButton.addEventListener('click', () => {
                const request = new XMLHttpRequest();
                request.open('GET', `/comment/like?type=dislike&id=${comment.id}`);
                request.send();
            });

            commentList.appendChild(ownerAndDate);
            commentList.appendChild(commentText);
            commentList.appendChild(likeButton);
            commentList.appendChild(dislikeButton);
            commentList.appendChild(document.createElement('hr'));
        });
    }
}

var sendCommentObject={
    init:function () {
        document.getElementById('commentForm')
            .addEventListener('submit',
                function(event) {
            event.preventDefault(); // Отменяем стандартное поведение формы

            // Получаем значение введенного текста
            var comment = document.getElementById('commentInput').value;

            // Создаем AJAX-запрос для отправки данных на сервер
            var request = new XMLHttpRequest();
                    request.open('POST', '/comment/create', true);
                    request.setRequestHeader('Content-Type', 'application/json');

                    request.onreadystatechange = function() {
                if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
                    // Обработка успешной отправки комментария
                    console.log('status 200');
                    console.log(request.responseText);
                    //очищаем поле
                    document.getElementById('commentInput').value='';
                    //обновляем список комментов
                    getCommentObject.fetchComments();
                } else {
                    // Обработка ошибки отправки комментария
                    console.log('Ошибка при отправке комментария');
                }
            };

            // Отправляем данные на сервер
                    request.send(JSON.stringify({text: comment}));
        });
    }

}