/**
 * 
 */
 
 !function(e) {
    var n = function(n) {
        e(n).show();
        var t = {
            lines: 9,
            length: 8,
            width: 4,
            radius: 10,
            scale: 1,
            corners: 1,
            color: "#FFFFFF",
            opacity: .25,
            rotate: 0,
            direction: 1,
            speed: 1,
            trail: 60,
            fps: 20,
            zIndex: 2e9,
            className: "spinner",
            top: "50%",
            left: "50%",
            shadow: !1,
            hwaccel: !1,
            position: "absolute"
        }
          , o = document.getElementById("spin")
          , i = new Spinner(t).spin(o);
        o.appendChild(i.el)
    }
      , t = function(n, t, i, a) {
        e.ajax({
            url: "/v1/" + n + "/cancel",
            type: "GET",
            cache: !1
        }).done(function(e) {
            userInfo && userInfo.statusCheck.stopCheckStatus(),
            i(),
            o(t, e.cancel_url, a)
        }).fail(function(e, n, t) {})
    }
      , o = function(n, t, o) {
        n ? o && e(o).attr("action", t).submit() : window.location.replace(t)
    };
    window.teslaCommonPayment = {
        showSpin: n,
        paymentStop: t,
        redirectToUrlWithMethod: o
    }
}(jQuery);
