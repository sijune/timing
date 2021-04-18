$(document).ready(function (){

    /*이벤트 부여*/
    $('#menu-home').on('click', function (){
        window.location.href = '/';
    })
    $('#menu-stock').on('click', function (){
        window.location.href = '/stock';
    })
    $('#menu-coin').on('click', function (){
        window.location.href = '/coin';
    })
    $('#menu-posts').on('click', function (){
        window.location.href = '/posts';
    })

    /* 현재 상대경로를 가져온다. */
    var nowUrl = window.location.pathname;

    if(nowUrl == '/'){
        $('#menu-home').addClass('selected');
        $('#menu-stock').removeClass('selected');
        $('#menu-coin').removeClass('selected');
        $('#menu-posts').removeClass('selected');
    }else if(nowUrl == '/stock'){
        $('#menu-home').removeClass('selected');
        $('#menu-stock').addClass('selected');
        $('#menu-coin').removeClass('selected');
        $('#menu-posts').removeClass('selected');
    }else if(nowUrl == '/coin'){
        $('#menu-home').removeClass('selected');
        $('#menu-stock').removeClass('selected');
        $('#menu-coin').addClass('selected');
        $('#menu-posts').removeClass('selected');
    }else if(nowUrl == '/posts'){
        $('#menu-home').removeClass('selected');
        $('#menu-stock').removeClass('selected');
        $('#menu-coin').removeClass('selected');
        $('#menu-posts').addClass('selected');
    }else{
        $('#menu-home').removeClass('selected');
        $('#menu-stock').removeClass('selected');
        $('#menu-coin').removeClass('selected');
        $('#menu-posts').removeClass('selected');
    }
})
