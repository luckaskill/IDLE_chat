jQuery(document).ready(function ($) {
    let userName = document.getElementById('userName').value;

    let userFroms = document.getElementsByClassName('userForm');
    let usersIDs = document.getElementsByName('userToTalk');
    let userToTalk;
    let newMsgsIntervalID;
    $.each(userFroms, function (i, item) {
        item.onsubmit = function (event) {
            clearInterval(newMsgsIntervalID);
            event.preventDefault();
            userToTalk = usersIDs[i].value;
            viewMsgsAjax($, userName, userToTalk);
            newMsgsIntervalID = setInterval(loadNewMsgsAjax, 4000, $, userName, userToTalk);
        }
    });

    let text = document.getElementById("messageText");
    let sendMsg = document.getElementById("sendMsg");
    sendMsg.onclick = function (event) {
        event.preventDefault();
        sendCycleRun($, text, userName, userToTalk);
    };

    $("#messageText").keypress(function (e) {
        if (e.which === 13) {
            event.preventDefault();
            sendCycleRun($, text, userName, userToTalk);
        }
    });

    msgWindow.scroll(function () {
        if (msgWindow.scrollTop() < 70) {
            loadMsgsAjax($, userName, userToTalk);
        }
    });
});
let msgWindow = $("#msgsWindow");

function sendCycleRun($, text, userName, userToTalk) {
    sendMsgAjax($, text, userToTalk);
    setTimeout(loadNewMsgsAjax, 150, $, userName, userToTalk);
    text.value = "";
}

function viewMsgsAjax($, userName, userToTalk) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/IDLE_chat_war_exploded/controller?command=viewCorrespondence&userToTalk=' + userToTalk,
        data: 'json',
        dataType: 'json',
        success: [function (data) {
            viewMsgs(data, userName);
        }],
        error: [function () {
            msgWindow.empty();
        }]
    });
}

function loadNewMsgsAjax($, userName, userToTalk) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/IDLE_chat_war_exploded/controller?command=loadNewMessages&userToTalk=' + userToTalk,
        data: 'json',
        dataType: 'json',
        success: [function (data) {
            loadNewMsgs(data, userName);
        }]
    });
}

function loadMsgsAjax($, userName, userToTalk) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/IDLE_chat_war_exploded/controller?command=loadMessages&userToTalk=' + userToTalk,
        data: 'json',
        dataType: 'json',
        success: [function (data) {
            loadMsgs(data, userName);
        }]
    });
}

function loadMsgs(data, userName) {
    let items = getCorrespondencePart(data, userName);
    msgWindow.prepend(items);
}

function sendMsgAjax($, text, userToTalk) {
    $.ajax({
        type: 'GET',
        url: "http://localhost:8081/IDLE_chat_war_exploded/controller?command=sendMessage&messageText=" + text.value + "&userToTalk=" + userToTalk
    });
}


function viewMsgs(data, userName) {
    console.log(data);
    let items = getCorrespondencePart(data, userName);
    msgWindow.empty();
    msgWindow.append(items);
    msgWindow.scrollTop(9999);
}

function loadNewMsgs(data, userName) {
    console.log(data);
    if (data !== null) {
        let items = getCorrespondencePart(data, userName);
        msgWindow.append(items);
        msgWindow.scrollTop(9999);
    }
}

function getCorrespondencePart(data, userName) {
    let items = [];
    let className;
    $.each(data, function (i, item) {
        console.log(item);
        if (userName === item.userFrom.login) {
            className = "sentMsg";
        } else {
            className = "receivedMsg";
        }
        items.push('<div class=' + className + '>' +
            '<div>' + item.time + '</div>' +
            '<div>' + item.userFrom.login + '</div>' +
            '<div style="margin-bottom: 2%">' + item.text + '</div>' +
            '</div>');
    });
    return items;
}
