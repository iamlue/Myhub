var name;
$(function () {
    $.getUrlParam = function (title) {
        var reg = new RegExp("(^|&)" + title + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
    var href = window.location.href;
    var title = $.getUrlParam("title");
    var pageIndex = $.getUrlParam("pageIndex");
    var pagetotal;
    if (pageIndex == null) {
        pageIndex = 0;
    }
    var pageCount = $.getUrlParam("pageCount");
    if (pageCount == null) {
        pageCount = $("#pageCount option:selected").text();
    }
    $("#pageCount option[value='" + pageCount + "']").attr("selected", "selected");
    if (title == null) {
        $("#span_title").append("首页");
    } else {
        $("#span_title").append(title);
    }

    $.ajax({
        type: "get",
        url: "selectAnime.do",
        async: true,
        data: {title: title, pageCount: pageCount, pageIndex: pageIndex},
        success: function (data) {
            var datajson = eval('(' + data + ')');
            console.info(datajson);
            var animes = datajson.data;
            pagetotal = datajson.pagetotal;
            for (var i = 0; i < animes.length; i++) {
                $("#ulId").append("<li style='display:inline-block;margin:10px;'><a onclick='show(this)'  ondblclick=\"animeEdit('" + animes[i].id + "')\"  name=" + animes[i].id +
                    "><img style='border:1px dashed #afa1a1' class='' src=" + animes[i].image +
                    " title='" + animes[i].brief + "'></h4><p>"
                    + animes[i].name + "</p></a></li>")
            }
            if (pagetotal > 1) {
                var lastindex = Number(pageIndex) - 1;
                console.info(lastindex)
                if (lastindex >= 0) {
                    $("#pageId").append("<a href='video_anime.html?pageIndex=" + lastindex + "&title=" + title + "&pageCount=" + pageCount + "'>上一页</a>&nbsp;");
                }

            }
            for (var i = 0; i < pagetotal; i++) {
                var index = Number(i) + 1;
                console.info(index)
                $("#pageId").append("<a id='a" + i + "' href='video_anime.html?pageIndex=" + i + "&title=" + title + "&pageCount=" + pageCount + "'>" + index + "</a>&nbsp;");
                if (i == pageIndex && i > 0) {
                    $("#pageId #a" + i).css("background-color", "#03a9f4");
                }
            }
            if (pagetotal > 1) {
                var nextindex = Number(pageIndex) + 1;
                if (nextindex < pagetotal) {
                    $("#pageId").append("<a href='video_anime.html?pageIndex=" + nextindex + "&title=" + title + "&pageCount=" + pageCount + "'>下一页</a>&nbsp;");
                }

            }
        },
        error: function (data) {
            console.info(data);
        },
        dataType: 'text'
    });
    $("#pageCount").change(function () {
        var pageCount = $("#pageCount option:selected").text();
        window.location.href = "video_anime.html?title=" + title + "&pageCount=" + pageCount
    })
    $("#anime_add").click(function () {
        $("#anime_add_dialog").dialog({
            title: '添加',
            width: 400,
            height: 250,
            closed: false,
            modal: true,
            top: 50,
            iconCls: 'icon-save',
            resizable: true,
            buttons: [{
                text: 'Save',
                handler: function () {
                    $("#anime_form").form('submit', {
                        url: 'saveAnime.do',
                        onSubmit: function (param) {
                            param.title = title;
                        },
                        success: function (data) {
                            console.info(data);
                            $("#anime_add_dialog").dialog("close");
                            var title = $("#span_title").html();
                            window.location.href = "video_anime.html?title=" + title;
                        }
                    })
                }
            }, {
                text: 'reset',
                handler: function () {
                    $("#anime_form").form("reset");
                }
            }]

        })
    });
    /*$("#ulId,li a p").click(function(this){
        console.info(this);
        var p = $(this).html();
        console.info(p)
        window.location.href="video_anime.html?title="+p
    })*/
    $("#anime_delete").click(function () {
        $.messager.confirm('提示', '是否删除' + name, function (r) {
            if (r) {
                $.ajax({
                    type: "get",
                    url: "delete",
                    async: true
                });
            }
        });
    })

})

function show(obj) {
    var p = $(obj).find('p')
    name = p.html();
    id = $(obj).attr('name');
    $("#VideoId").empty();
    $.ajax({
        type: "post",
        data: {id: id},
        url: "anime/showVideo.do",
        async: true,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var path = data[i].replace(/\\/g, '\\\\');
                var all = Number(path.length) - Number(path.lastIndexOf("\\"))
                var filename = path.substring(path.lastIndexOf("\\") + 1, path.length);
                $("#VideoId").append("<a href='javascript:'  onclick=\"run('" + path + "')\" title='" + filename + "'><p><img src='image/image_icon.png'/>" + filename + "</p> </a>")
                $("#VideoId").show();
            }
        }
    });
}

function run(path) {
    var newpath = path.replace(/\\/g, '\\\\')
    $.get({
        url: 'Anime/runVideo.do',
        data: {path: newpath},
        success: function (data) {

        }
    })
}

function animeEdit(id) {
    $("#anime_add_dialog").dialog({
        title: '添加',
        width: 400,
        height: 250,
        closed: false,
        modal: true,
        top: 50,
        iconCls: 'icon-save',
        resizable: true,
        buttons: [{
            text: 'Save',
            handler: function () {
                $("#anime_form").form('submit', {
                    url: 'editAnime.do',
                    onSubmit: function (param) {
                        param.id = id;
                    },
                    success: function (data) {
                        console.info(data);
                        $("#anime_add_dialog").dialog("close");
                        var title = $("#span_title").html();
                        window.location.href = "video_anime.html?title=" + title;
                    }
                })
            }
        }, {
            text: 'reset',
            handler: function () {
                $("#anime_form").form("reset");
            }
        }]

    })
    $.ajax({
        type: "post",
        url: "selectAnimeByid.do",
        data: {id: id},
        async: true,
        success: function (data) {
            if (data != null) {
                console.info(data);
                /*var jsondata = eval('(' +data + ')');*/
                var anime = data.data;
                console.info(anime.name);
                $("#animeName").textbox("setValue", anime.name);
                $("#animeLocal").textbox("setValue", anime.local);
            }

        }
    });


}
