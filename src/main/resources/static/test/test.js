switch (d.type) {
    case "netease_songlist":
        (
            a = a.match(/(song\?id=(\d+)|\/song\/(\d+))/gi)
        )
        && 0 < a.length
        && (
            es =
                [],
                b.each(
                    a,
                    function (a, c) {
                        -1 === b.inArray(c, e) && es.push(c)
                    }
                ),
                d.array = es.join(",").replace(/(song\?id=|\/song\/)/gi, "")
        );
        break;
    case "netease_songs":
        (a = a.match(/song\?id=(\d+)/gi)) && 0 < a.length && (e = [], b.each(a,
            function (a, c) {
                -1 === b.inArray(c, e) && e.push(c)
            }), d.array = e.join(",").replace(/song\?id=/g, ""));
        break;
    case "netease_album":
        (a = a.match(
                /(album\?id=(\d+)|\/album\/(\d+))/i)
        ) &&
        0 < a.length &&
        (
            d.array = a[0].replace(/(album\?id=|\/album\/)/gi, "")
        );
        break;


    case "netease_playlist":
        (
            a = a.match(
                /(playlist\?id=(\d+)|\/playlist\/(\d+))/i
            )
        ) &&
        0 < a.length &&
        (
            d.array = a[0].replace(/(playlist\?id=|\/playlist\/)/gi, "")
        )


}


playlist;


if ("netease" == c) {
    switch (d.type) {


        case "netease_songlist":


            (a = a.match(/(song\?id=(\d+)|\/song\/(\d+))/gi))
            &&
            0 < a.length
            &&
            (es = [], b.each(a,
                    function (a, c) {
                        -1 === b.inArray(c, e) && es.push(c)
                    }), d.array = es.join(",").replace(/(song\?id=|\/song\/)/gi, ""
                )
            );
            break;


        case "netease_songs":
            (a = a.match(/song\?id=(\d+)/gi)) && 0 < a.length && (e = [], b.each(a,
                function (a, c) {
                    -1 === b.inArray(c, e) && e.push(c)
                }), d.array = e.join(",").replace(/song\?id=/g, ""));
            break;


        case "netease_album":
            (a = a.match(/(album\?id=(\d+)|\/album\/(\d+))/i)) && 0 < a.length && (d.array = a[0].replace(/(album\?id=|\/album\/)/gi, ""));
            break;
        case "netease_playlist":
            (a = a.match(/(playlist\?id=(\d+)|\/playlist\/(\d+))/i)) && 0 < a.length && (d.array = a[0].replace(/(playlist\?id=|\/playlist\/)/gi, ""))
    }
}






