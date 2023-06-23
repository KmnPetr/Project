function fetchAndDisplayData() {
    // Осуществляем запрос в сеть с помощью функции fetch
    fetch('/comment/get_list')
        .then(response => response.json()) // Преобразуем ответ в JSON формат
        .then(data => {
            // Получаем список объектов и выводим их на страницу
            let list = document.getElementById('list'); // Получаем элемент списка в HTML
            data.forEach(item => { // Итерируемся по объектам из списка
                let listItem = document.createElement('li'); // Создаем элемент <li>
                listItem.textContent = item.name; // Выводим наименование объекта в элемент списка
                list.appendChild(listItem); // Добавляем элемент списка в <ul>
            });
        })
        .catch(error => console.log(error)); // Обрабатываем ошибки, если они возникнут
}

// Вызываем функцию fetchAndDisplayData при загрузке страницы
window.addEventListener('load', fetchAndDisplayData);