function getKafkaMessages() {

    const host = window.location.host;
    const socket = new WebSocket('wss://'+host+'/websocket');

    let table = document.getElementById('kafkaMessages');

    socket.onopen = () => {
        console.log("WebSocket соединение установлено.");

    };

    //обработка приходящих сообщений с сервера с инфой из кафки
    socket.onmessage = (event) => {

        console.log("Получено сообщение от сервера: " + event.data);

        let json = JSON.parse(event.data);

        let tr = document.createElement('tr');

        let td_message = document.createElement('td');
        let p_message = document.createElement('p');
        p_message.textContent = json.message;
        p_message.style.margin = '0px';
        td_message.style.paddingRight = '20px';
        td_message.appendChild(p_message);

        let td_partition = document.createElement('td');
        let p_partition = document.createElement('p');
        p_partition.textContent = 'partition = ' + json.partition;
        p_partition.style.margin = '0px';
        td_partition.style.paddingRight = '20px';
        td_partition.appendChild(p_partition);

        let td_offset = document.createElement('td');
        let p_offset = document.createElement('p');
        p_offset.textContent = 'offset = ' + json.offset;
        p_offset.style.margin = '0px';
        td_offset.style.paddingRight = '20px';
        td_offset.appendChild(p_offset);

        let td_createdAt = document.createElement('td');
        let p_createdAt = document.createElement('p');
        p_createdAt.textContent = 'createdAt = ' + json.createdAt;
        p_createdAt.style.margin = '0px';
        td_createdAt.style.paddingRight = '20px';
        td_createdAt.appendChild(p_createdAt);

        tr.appendChild(td_message);
        tr.appendChild(td_partition);
        tr.appendChild(td_offset);
        tr.appendChild(td_createdAt);


        if (tr.firstChild) {
            table.insertBefore(tr, table.firstChild);
        } else {
            table.appendChild(tr);
        }
    };

    // Закрытие WebSocket соединения
    socket.onclose = () => {
        console.log("WebSocket соединение закрыто.");
    };
}