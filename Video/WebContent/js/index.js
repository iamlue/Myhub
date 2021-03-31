$(function () {
    $('#sm').sidemenu({
        data: [{
            text: '经典动漫',
            iconCls: 'icon-sum',
            state: 'open',
            children: [{
                text: 'Option1'
            }, {
                text: 'Option2'
            }, {
                text: 'Option3',
                children: [{
                    text: 'Option31'
                }, {
                    text: 'Option32'
                }]
            }]
        }, {
            text: '剧情动漫',
            iconCls: 'icon-sum',
            children: [{
                text: 'Option4'
            }, {
                text: 'Option5'
            }, {
                text: 'Option6'
            }]
        }],
        onSelect: onSideMenuSelect
    });
    /*$("#anime_add").click(function(){
    	alert("123");
    })*/
})

function onSideMenuSelect(item) {
    $.get({
        url: "1.json",
        data: {title: item.text},
        success: function (data) {
            var datajson = eval('(' + data + ')');

            /*$(#anime_table).innerHTML=*/
            console.info(datajson.length);
            var group = 6;
            var sum = 1;
            var seq = 0;
            if (datajson.length % group != 0) {
                sum = parseInt(datajson.length / group) + 1;
            } else {
                sum = parseInt(datajson.length / group);
            }
            $("#anime_table").empty();
            for (var i = 0; i < sum; i++) {
                console.info(datajson[i]);
                $("#anime_table").append("<tr></tr>");
                for (var j = 0; j < 6; j++) {
                    if (seq < datajson.length) {
                        var tr = $("#anime_table tr:eq(" + i + ")")
                        console.info(tr);
                        tr.append("<td style='width: 160px;'><a href=" + datajson[seq].href +
                            " target='_blank'><img class='' src=" + datajson[seq].image +
                            " ></a></a> <h4 class='anime_text_center'><a href='javascript:' >" + datajson[seq].num +
                            "</a></h4><p  style='overflow: hiddren;word-break: keep-all;white-space: nowrap;text-overflow: ellipsis;'>"
                            + datajson[seq].title + "</p></td>");
                        seq++;
                    }
                }

            }
        }, error: function (data) {
            console.info(data);
        },
        dataType: 'text'
    });

    console.info(item.text)
}