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
                            fetchComments();
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