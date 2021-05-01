var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        })
        $("#btn-update").on('click', function () {
            _this.update();
        });
        $("#btn-delete").on('click', function () {
            _this.delete();
        });
        $("#btn-notify-ok").on('click', function () {
            _this.notify_ok();
        });
        $("#btn-notify-cncl").on('click', function () {
            _this.notify_cncl();
        });
    },
    save : function () {
        var data = {
            title : $("#title").val(),
            author : $("#author").val(),
            content : $("#content").val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (returns) {
            alert('글이 등록되었습니다.');
            console.log("%%%%%%%%%%%"+returns)
            window.location.href = '/posts';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    update : function () {
        var data = {
            title : $("#title").val(),
            content : $("#content").val()
        };

        var id = $("#id").val();

        $.ajax({
            type : 'PUT',
            url : '/api/v1/posts/'+ id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    delete : function () {
        var id = $("#id").val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/'+ id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/posts';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    notify_ok : function () {
        var data = {
            pushYn : 'Y',
            userEmail : $("#userEmail").val()
        };

        $.ajax({
            type : 'PUT',
            url : '/api/v1/notify/check',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('수신에 동의하셨습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            console.log(JSON.stringify(error));
            alert(JSON.stringify(error));
        });
    },
    notify_cncl : function () {
        var data = {
            pushYn : 'N',
            userEmail : $("#userEmail").val()
        };

        $.ajax({
            type : 'PUT',
            url : '/api/v1/notify/check',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('수신을 거부하셨습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
console.log("#######");
main.init();


$(document).ready(function (){

    var pushYn = $('#pushYn').val();

    //아직 알림설정을 하지 않았다면
    if (pushYn == 'A') {
        $('#myModal').modal();
    }
})

