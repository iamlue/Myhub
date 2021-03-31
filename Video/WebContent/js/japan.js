$(function () {
    var name;
    $('#sm').sidemenu({
        data: [{
            text: '单体',
            iconCls: 'icon-sum',
            state: 'open',
            children: [{
                text: '谷原希美'
            }, {
                text: 'だ飛鳥'
            }, {
                text: '佐山愛'
            }, {
                text: '宇都宫紫苑'
            }, {
                text: '市来美保',
                children: [{
                    text: '市来美保-有码'
                }, {
                    text: '市来美保-无码'
                }]
            }, {
                text: '长泽梓'
            }, {
                text: '三上悠亜'
            }, {
                text: '西條るり'
            }, {
                text: '篠田ゆう'
            }, {
                text: '系列',
                children: [{
                    text: '成熟',
                    children: [{
                        text: '人妻'
                    },
                        {
                            text: '時田こずえ'
                        }, {
                            text: '牧村彩香'
                        }]
                }, {
                    text: '巨乳'
                }, {
                    text: '剧情'
                }, {
                    text: '清新'
                }]
            }]
        }, {
            text: '多人',
            iconCls: 'icon-sum',
            children: [{
                text: '一夫多妻'
            }, {
                text: '其他'
            }]
        }],
        onSelect: onSideMenuSelect
    });

    function onSideMenuSelect(item) {
        var num = $("#videoNum").val();
        name = item.text;
        $.get({
            url: "selectJapan.do",
            data: {name: item.text, num: num},
            success: function (data) {
                var datajson = eval('(' + data + ')');
                $("#japan_ul_id").empty();
                for (var i = 0; i < datajson.length; i++) {
                    console.info(datajson[i])
                    $("#japan_ul_id").append("<li style='display:inline-block;margin:5px'><a href=" + datajson[i].href +
                        " target='_blank'><img class='' src=" + datajson[i].image +
                        " title='" + datajson[i].brief + "'></a></a> <h4 class='japan_text_center'><a href='javascript:' onclick='run(" + datajson[i].id + ")'>" + datajson[i].num +
                        "</a></h4><p>"
                        + datajson[i].title + "</p></li>");
                }

            }, error: function (data) {
                console.info(data);
            },
            dataType: 'text'
        });

        console.info(item.text)
    }

    $("#japan_add").click(function () {
        $("#japan_dialog").dialog({
            title: '添加',

            width: 500,

            height: 300,

            closed: false,
            modal: true,
            top: 50,
            iconCls: 'icon-save',
            resizable: true,
            buttons: [{
                text: 'Save',
                handler: function () {
                    $("#japan_form").form('submit', {
                        url: 'saveJapan.do',
                        onSubmit: function (param) {
                            param.title = name;
                        },
                        success: function (data) {
                            $("#japan_dialog").dialog("close");
                        }
                    })
                }
            }, {
                text: 'reset',
                handler: function () {
                    $("#japan_form").form("reset");
                }
            }]

        })
    });
    $("#japan_delete").click(function () {
        var num = $("#videoNum").val()
        $.get({
            url: 'deleteJapan.do',
            data: {num: num},
            success: function (data) {
                alert(data.local);
                $("#videoNum").attr("value", "");
            }
        })
    });

})

function run(id) {
    $.get({
        url: 'Japan/runVideo.do',
        data: {id: id},
        success: function (data) {

        }
    })
}