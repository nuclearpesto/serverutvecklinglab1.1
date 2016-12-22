// angular.module('chatModule',[])
angular.module('chatModule', ['ngMaterial','ngCookies'])
.config(function($mdIconProvider) {
  $mdIconProvider
    .iconSet('communication', 'img/icons/sets/communication-icons.svg', 24);
})
.controller('AppCtrl', ['$scope','$cookies', '$window', function($scope,$cookies,$window) {

    // INIT-stuff. Starta upp visuellt.
    $scope.chatUIs = [
      {name:"UNDEFINED1",id:"null",contents:[]},
      {name:"UNDEFINED2",id:"null",contents:[]},
      {name:"UNDEFINED3",id:"null",contents:[]},
    ];

    currentUser={token:"null",id:"me@me.me"};
    if (typeof($cookies.get('FacecrapLogin'))=='undefined') {
        console.log("VAFNNINGE JVLA KOOKKIE FANFANFAN");
        $window.location.href ="login.xhtml";
    }
    else {
        var kaka = JSON.parse(JSON.parse($cookies.get('FacecrapLogin')));
        console.log(kaka);
        currentUser={token:kaka.token,id:kaka.username};
    }

    // Kontakta bak{ndan och h{lsa
    var baseURL = "ws://localhost:3000";
    var socket = new WebSocket(baseURL);

    function error(err) {
        console.log("Fick konstigheter: "+err);
    }

    socket.onopen = function() {
        console.log("Uppkopplad")
        var obj = {type:'message',data:'fr}n oss alla till er alla, en riktigt God Jul.'};
        socket.send(JSON.stringify(obj));
    }






    // Ta emot meddelanden bak{ndan.
    socket.onmessage = function(event) {
        console.log(event.data);
        var msgfrombackend;
        try {
            msgfrombackend = JSON.parse(event.data);
        }
        catch(e) {
            error(event.data);
            return;
        }
        switch (msgfrombackend.type) {
            case 'message': $scope.$apply(addMessageToChat(msgfrombackend.data));
                break;
            case 'error': serverProtests(msgfrombackend.data);
                break;
            case 'unauthorized': $scope.$apply(unauthorized(msgfrombackend.data));
                break;
            case 'init': $scope.$apply(intialize(msgfrombackend.data));
                break;
            case 'newroom': $scope.$apply(roomAdded(msgfrombackend.data));
                break;
        }
    };

    function chatroomReceivedFromBackend(chatroom) {
        $scope.chatrooms.push({
            name:chatroom.name,
            id:chatroom.id,
            members:chatroom.users,
            messages:chatroom.posts
        })
    };





    // Stuff som ska påverka UIt
    function addMessageToChat(message) {
        var chat = findChatById(message.chatroomID);
        chat.messages.push(message);
        console.log("Chat: "+chat.id);
    }

    function findChatById(id) {
        for (i=0; i<$scope.chatrooms.length; i++) {
            if ($scope.chatrooms[i].id===id) return $scope.chatrooms[i];
        }
        return null;
    };





    // Stuff som är reaktioner på användarens input från HTMLen.
    $scope.sendMessage = function(message,chatId) {
        addMessageToChat({
        text: message,
        poster: currentUser,
        date: new Date().getDate(),
        chatroomID: chatId
      });
        // todo: Send to server
      console.log("Adding message: "+message,chatId);
    };

    $scope.buttonClick = function() {
      $scope.chatrooms[0].messages.push(
        {
              text: "Nytt meddelande",
              poster: "o@o.o",
              date: "2016-12-16:16-30",
              chatroomID: "lol"
        }
      )
    };

    $scope.chatClicked = function(id) {
      var clickedChat=findChatById(id);
      if (clickedChat==null)return;
      $scope.chatUIs[2]=$scope.chatUIs[1];
      $scope.chatUIs[1]=$scope.chatUIs[0];
      $scope.chatUIs[0] = {name:clickedChat.name,id:clickedChat.id, contents:clickedChat.messages};
    };

    $scope.getUserNameById = function(id) {
      for (i=0; i<$scope.friends.length; i++) {
        if ($scope.friends[i].email===id) return $scope.friends[i].name;
      }
      return null;
    };

    $scope.createNewChat = function(friend) {
      console.log("Chatting with"+friend);
      chatroomReceivedFromBackend({
        name:"You+"+$scope.getUserNameById(friend),
        id:"You"+friend,
        users: [],
        posts: []
      })
    };







    // Skit som vi bara har som testdata.
    $scope.friends = [
      {
        email: "p@p.p",
        name: "Pelle"
      }, {
        email: "k@k.k",
        name:"Kalle"
      }, {
        email: "a@a.a",
        name:"Anna"
      }, {
        email: "l@l.l",
        name:"Lisa"
      }, {
        email: "m@m.m",
        name:"Maja"
      }
    ];

    $scope.messages = [
      {
        text: "Hejsan",
        poster: "p@p.p",
        date: "2016-12-16:14-30",
        chatroomID: "sdfadfa"
      },{
        text: "HSvejsan",
        poster: "l@l.l",
        date: "2016-12-16:14-31",
        chatroomID: "sdfadfa"
      },{
        text: "Tjosan",
        poster: "m@m.m",
        date: "2016-12-16:14-32",
        chatroomID: "sdfadfa"
      },{
        text: "Flosan",
        poster: "k@k.k",
        date: "2016-12-16:14-33",
        chatroomID: "sdfadfa"
      }, {
        text: "Men oj då",
        poster: "p@p.p",
        date: "2016-12-16:14-34",
        chatroomID: "sdfadfa"
      }
    ];
    $scope.chatrooms = [
      {
        name: "General",
        id: "sdfadfa",
        members: $scope.friends,
        messages: $scope.messages
      }, {
        name: "Private",
        id: "test",
        members: $scope.friends,
        messages: $scope.messages
      }
    ];

}]);
