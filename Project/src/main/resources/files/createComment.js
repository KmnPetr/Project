var sendCommentObject={
    init:function () {
        document.getElementById('commentForm')
            .addEventListener('submit',
                function(event) {
                    event.preventDefault(); // Отменяем стандартное поведение формы

                    // Получаем значение введенного текста
                    var comment = document.getElementById('commentInput');

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
                            comment.value='';
                            comment.style.height='auto';
                            //обновляем список комментов
                            fetchComments();
                        } else {
                            // Обработка ошибки отправки комментария
                            console.log('Ошибка при отправке комментария');
                        }
                    };

                    // Отправляем данные на сервер
                    request.send(JSON.stringify({text: comment.value}));
                });
    }
}
function chengeSize() {
    const textarea = document.getElementById('commentInput');
    textarea.addEventListener('input', function() {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';});
}