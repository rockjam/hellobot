var VirusWarMessageBox = function (s) {
    var u = '<img src="/pub/games/viruswar/img/virus4.png" alt="' + viruswar_text["img-alt"] + '">';
    return{show: function (v) {
        var k = s("#VirusWar_msg_box_id");
        0 == k.length && (s("body").append('<div class="modal hide fade" id="VirusWar_msg_box_id" style="width:30em;"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">x</button><h3>' + viruswar_text.title + '</h3></div><div class="modal-body">' + u + '<span id="spanMessageId" style="margin-left:1em;"></span></div><div class="modal-footer"><button type="button" class="btn btn-primary" data-dismiss="modal">' + viruswar_text["btn:close"] + "</a></div></div>"), k = s("#VirusWar_msg_box_id"));
        k.on("shown", function () {
            k.find("#buttonClose").focus()
        });
        fix_all();
        k.find("#spanMessageId").text(v);
        k.modal("show")
    }}
}(jQuery);
function VirusWar(s, u, v, k, F) {
    function I() {
        var b = "table-" + p;
        l.append(J(w, b));
        var a = "button-" + p, c = "indicator-" + p, e = "spanMessagePlaceholder-" + p;
        l.append("<p>" + ("<a class='btn no-decoration' id='" + a + "'>" + viruswar_text["btn:restart"] + "</a>") + ("<span style='margin-left:1em;'><img src='/img/ajax-loader-bert2.gif' id='" + c + "' alt='[thinking...]' style='display:none;'></span>") + ("<span id='" + e + "' style='margin-left:1em; color:red; font-style:bold; display:none;'>\u0412\u043a\u043b\u044e\u0447\u0435\u043d \u0440\u0435\u0437\u0435\u0440\u0432\u043d\u044b\u0439 \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a \u044d\u043d\u0435\u0440\u0433\u0438\u0438...</span>") + "</p>");
        B = l.find("#" + c);
        l.find("#" + e);
        x = document.getElementById(b);
        g = K(w);
        G(F);
        y = new AILibrary(w, z, g);
        C();
        l.find("#" + b + " td").click(function () {
            H(this.parentNode.rowIndex, this.cellIndex, A ? d : f)
        });
        l.find("#" + a).click(function () {
            f = d;
            m.length = 0;
            G(F);
            C()
        });
        L()
    }

    function H(b, a, c) {
        if (!M(b, a, c))return!1;
        c = g[b][a];
        if (n == c)g[b][a] = f; else if (d == c && h == f)g[b][a] = D; else if (h == c && d == f)g[b][a] = E; else return alert("incorrect move"), !1;
        m.push({row: b, col: a, turn: f});
        if (d == f || !A || z <= m.length)C(), N(m);
        if (z <= m.length) {
            m.length = 0;
            f = d == f ? h : d;
            if (!y.can_make_move(f)) {
                O(3 - f);
                return
            }
            h == f && 0 < A && (B.show(), setTimeout(function () {
                for (var a = h, b = y.make_computer_move(a, A), c = 0; c < b.length; c++)H(b[c][0], b[c][1], a);
                B.hide()
            }, 1E3))
        }
        return!0
    }

    function M(b, a, c) {
        if (c != f || z <= m.length || !y.is_step_correct(b, a, c))return!1;
        var e = g[b][a];
        if (d == c) {
            if (n != e && h != e)return!1;
            if (0 == b && 0 == a)return!0
        }
        return h == c && n != e && d != e ? !1 : !0
    }

    function N(b) {
        for (var a = 0; a < b.length; a++)x.rows[b[a].row].cells[b[a].col].className += " tdLastMove"
    }

    function J(b, a) {
        for (var c = '<table id="' + a + '" class="viruswar"><tbody>', e = 0; e < b; e++) {
            for (var c = c + "<tr>", d = 0; d < b; d++)c += "<td></td>";
            c += "</tr>"
        }
        return c + "</tbody></table>"
    }

    function K(b) {
        for (var a = Array(b), c = 0; c < a.length; c++)a[c] = Array(b);
        return a
    }

    function G(b) {
        for (var a = 0; a < g.length; a++)for (var c = 0; c < g[a].length; c++)g[a][c] = n;
        if (0 < b)for (var a = 2 + v, e = c = 0; c < b;) {
            var d = 0 + Math.floor(7 * Math.random()), f = 0, f = d >= a ? 0 + Math.floor(7 * Math.random()) : a + Math.floor(Math.random() * (6 - a + 1));
            n == g[d][f] && (g[d][f] = h, c++);
            e++;
            if (1E3 < e)break
        }
    }

    function C() {
        for (var b = 0; b < w; b++)for (var a = 0; a < P; a++) {
            var c = g[b][a], e = x.rows[b].cells[a];
            e.innerHTML = q[c];
            e.className = r[c]
        }
    }

    function O(b) {
        var a = "", a = d == b ? viruswar_text.victory : h == b ? viruswar_text.defeat : viruswar_text.draw;
        VirusWarMessageBox.show(a);
        d == b && void 0 != t.callback && "function" == typeof t.callback && t.callback()
    }

    function L() {
        var b, a = !1;
        if ("undefined" != typeof document.styleSheets) {
            var c = document.styleSheets, e = 0;
            a:for (; e < c.length; e++) {
                b = "undefined" != typeof c[e].cssRules ? c[e].cssRules : c[e].rules;
                for (var d = 0; d < b.length; d++)if (".viruswar" == b[d].selectorText) {
                    a = !0;
                    break a
                }
            }
        }
        a || (b = jQuery("<style> .viruswar{} table.viruswar {border: 1px solid #ccc; margin-top:1em; margin-bottom:0.5em; background-color:#fff; } table.viruswar > tbody > tr > td{ border : 1px solid #ccc; width : 2em; height : 2em; vertical-align : middle; text-align : center; } td.tdLastMove {font-weight : bold; text-shadow: 0.15em 0.15em #ccc;} td.mark_x{color:#00f;}td.mark_o{color:#f00;}td.mark_x_over_o{color:#faa; background-color:blue;}td.mark_o_over_x{color:#aaf; background-color:red;}</style>"), jQuery("html > head").append(b))
    }

    var p = s, w = u, P = u, z = v, A = k, y = null, t = {}, g = null, n = 0, d = 1, h = 2, E = 3, D = 4, x = null, q = [];
    q[n] = "";
    q[d] = "X";
    q[h] = "O";
    q[E] = "O";
    q[D] = "X";
    var r = [];
    r[n] = "";
    r[d] = "mark_x";
    r[h] = "mark_o";
    r[E] = "mark_x_over_o";
    r[D] = "mark_o_over_x";
    var f = d, m = [], B = null, l = jQuery("#" + p);
    if (0 == l.length)alert("not found #" + p); else return I(), t.destructor = function () {
        l.empty()
    }, t
};