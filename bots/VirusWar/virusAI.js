function AILibrary(W, D, X) {
    function E(a, e) {
        return 0 <= a && a < l && 0 <= e && e < l
    }

    function z(a) {
        for (var e = Array(a), c = 0; c < a; c++) {
            e[c] = Array(a);
            for (var b = 0; b < a; b++)e[c][b] = 0
        }
        return e
    }

    function S(a, e) {
        return e[0] - a[0]
    }

    function M(a, e) {
        for (var c = 0; c < l; c++)for (var b = 0; b < l; b++)a[c][b] = 0;
        c = [];
        1 == e && 0 == r[0][0] ? a[0][0] = 1 : 2 == e && 0 == r[9][9] && (a[9][9] = 1);
        for (b = 0; b < l; b++)for (var d = 0; d < l; d++)r[b][d] == e && (c.push([b, d]), a[b][d] = 2);
        for (; 0 < c.length;) {
            b = c[0][0];
            d = c[0][1];
            c.shift();
            for (var f = 0; 8 > f; f++) {
                var g = b + F[f], h = d + G[f];
                E(g, h) && 0 == a[g][h] && (0 < r[g][h] && 0 == ((r[g][h] ^ e) & 1) && (c.push([g, h]), a[g][h] = 2), 0 == r[g][h] || r[g][h] == 3 - e) && (a[g][h] = 1)
            }
        }
        return a
    }

    function N(a, e, c) {
        H.push([a, e, r[a][e]]);
        r[a][e] = 0 == r[a][e] ? c : c + 2
    }

    function O(a, e) {
        for (var c = 0; 3 > c; c++)N(a[c][0], a[c][1], e)
    }

    function J() {
        if (0 != H.length) {
            var a = H.pop();
            r[a[0]][a[1]] = a[2]
        }
    }

    function P() {
        for (var a = 0; 3 > a; a++)J()
    }

    function A(a) {
        for (var e = z(l), c = [], b = 0; b < l; b++)for (var d = 0; d < l; d++)r[b][d] == a ? (e[b][d] = 0, c.push([b, d])) : e[b][d] = K;
        for (; 0 < c.length;) {
            b = c[0][0];
            d = c[0][1];
            c.shift();
            var f = 1;
            r[b][d] == a + 2 && (f = 0);
            for (var g = 0; 8 > g; g++) {
                var h = b + F[g], t = d + G[g];
                !E(h, t) || (r[h][t] == 5 - a || e[h][t] <= e[b][d] + f) || (e[h][t] = e[b][d] + f, 0 == f ? c.unshift([h, t]) : c.push([h, t]))
            }
        }
        return e
    }

    function Q(a) {
        for (var e = z(l), c = [], b = 0, d = 0; d < l; d++)for (var f = 0; f < l; f++)if (r[d][f] == a + 2 && 0 == e[d][f]) {
            var b = b + 1, g = [
                [d, f]
            ];
            e[d][f] = b;
            for (var h = [], t = [], q = 0; 0 < g.length;) {
                var u = g[0][1], v = g[0][1];
                g.shift();
                for (var q = q + 1, m = 0; 8 > m; m++) {
                    var s = u + F[m], n = v + G[m];
                    E(s, n) && e[s][n] != b && (e[s][n] = b, r[s][n] == a + 2 && g.push([s, n]), 0 != r[s][n] && r[s][n] != 3 - a || t.push([s, n]), r[s][n] == a && h.push([s, n]))
                }
            }
            c.push([q, h, t])
        }
        return c
    }

    function T(a) {
        for (var e = A(3 - a), c = 0, b = 0; b < l; b++)for (var d = 0; d < l; d++)if (r[b][d] == a && 3 >= e[b][d]) {
            for (var f = 1, g = 0; 8 > g; g++) {
                var h = b + F[g], t = d + G[g];
                E(h, t) && ((r[h][t] == a || r[h][t] == a + 2) && e[h][t] <= e[b][d]) && (f += 1)
            }
            1 == e[b][d] && (f = 0);
            4 >= f + e[b][d] && (c -= 5 - f - e[b][d])
        }
        return c
    }

    function I(a) {
        for (var e = z(l), c = [], b = [], d = 0, f = 0; f < l; f++)for (var g = 0; g < l; g++)if (r[f][g] == a + 2 && 0 == e[f][g]) {
            ++d;
            var h = 0, t = [], q = [
                [f, g]
            ];
            for (e[f][g] = d; 0 < q.length;) {
                var u = q[0][0], v = q[0][1];
                q.shift();
                for (var h = h + 1, m = 0; 8 > m; m++) {
                    var s = u + F[m], n = v + G[m];
                    E(s, n) && e[s][n] != d && (r[s][n] == a && (e[s][n] = d, t.push([s, n])), r[s][n] == a + 2 && (e[s][n] = d, q.push([s, n])))
                }
            }
            b.push(h);
            c.push(t)
        }
        e = A(3 - a);
        for (m = g = 0; m < d; m++)if (0 != c[m].length) {
            3 < c[m].length && (g += b[m]);
            for (f = h = 0; f < c[m].length; f++)u = c[m][f][0], v = c[m][f][1], h += e[u][v];
            4 < h ? g += b[m] : 4 == h ? g += 0.5 * b[m] : 3 == h ? g += 0.2 * b[m] : 2 == h && (g += 0.1 * b[m])
        }
        c = 1E10 * g;
        b = Q(3 - a);
        d = A(a);
        for (u = f = 0; u < b.length; u++)if (0 != b[u][1].length) {
            v = 1;
            for (m = 0; m < b[u][1].length; m++)v += d[b[u][1][m][0]][b[u][1][m][1]];
            f -= (1 + Math.log(v)) * b[u][0]
        }
        c = c + 1E10 * f + 1E5 * T(a) - 1E3 * T(3 - a);
        b = A(a);
        a = A(3 - a);
        for (f = d = 0; f < l; f++)for (u = 0; u < l; u++)b[f][u] < a[f][u] && (d += 1), b[f][u] > a[f][u] && (d -= 1);
        return c + d
    }

    function R(a) {
        function e(u, t) {
            if (0 == u)f.push([I(a), t.slice(0)]); else {
                for (var m = [], s = A(a), n = A(3 - a), q = z(l), w = K, x = z(l), k = 0; k < l; k++)for (var p = 0; p < l; p++)w = w < s[k][p] + n[k][p] ? w : s[k][p] + n[k][p];
                for (k = 0; k < l; k++)for (p = 0; p < l; p++) {
                    var y = s[k][p] + n[k][p];
                    y == w && (x[k][p] = 1);
                    y == w + 1 && (x[k][p] = 0.5)
                }
                s = z(l);
                w = Q(a);
                y = Q(3 - a);
                for (k = 0; k < w.length; k++)if (3 >= w[k][1].length)for (p = 0; p < w[k][2].length; p++) {
                    var B = w[k][2][p][0], C = w[k][2][p][1];
                    s[B][C] += w[k][0] / (w[k][1].length + 1) * n[B][C]
                }
                for (k = 0; k < y.length; k++)for (p = 0; p < y[k][1].length; p++)B = y[k][1][p][0], C = y[k][1][p][1], s[B][C] += y[k][0] / y[k][1].length;
                for (k = 0; k < l; k++)for (p = 0; p < l; p++)r[k][p] == 3 - a && (q[k][p] += b), q[k][p] += c * x[k][p], q[k][p] += d * s[k][p];
                M(g, a);
                for (n = 0; n < l; n++)for (x = 0; x < l; x++)1 == g[n][x] && m.push([q[n][x], n, x]);
                m.sort(S);
                for (n = 0; n < (h < m.length ? h : m.length); n++)q = m[n][1], x = m[n][2], N(q, x, a), t.push([q, x]), e(u - 1, t), t.pop(), J()
            }
        }

        var c = 1, b = 1, d = 1, f = [], g = z(l), h = 10;
        e(3, []);
        f.sort(S);
        for (var t = [], q = 0; q < (U < f.length ? U : f.length); q++)t.push(f[q][1]);
        return t
    }

    function V(a, e, c, b) {
        if (0 == a)return 1 == e ? I(2) : -I(1);
        for (var d = R(e), f = 0; f < d.length; f++) {
            O(d[f], e);
            var g = b, h;
            h = a - 1;
            var l = 3 - e, q = c;
            if (0 == h)h = 1 == l ? I(2) : -I(1); else {
                for (var r = R(l), v = 0; v < r.length; v++) {
                    O(r[v], l);
                    var m = V(h - 1, 3 - l, q, b), q = q > m ? q : m;
                    P();
                    if (q >= b)break
                }
                h = q
            }
            b = g < h ? g : h;
            P();
            if (c >= b)break
        }
        return b
    }

    D = {};
    var l = W, r = X, F = [1, 1, 1, 0, -1, -1, -1, 0], G = [-1, 0, 1, 1, 1, 0, -1, -1], K = 1E100, U = 10, L = z(l);
    D.is_step_correct = function (a, e, c) {
        M(L, c);
        return 1 == L[a][e]
    };
    D.can_make_move = function (a) {
        for (var e = 0; 3 > e; e++) {
            var c = !1;
            M(L, a);
            for (var b = 0; b < l && !c; b++)for (var d = 0; d < l && !c; d++)1 == L[b][d] && (N(b, d, a), c = !0);
            if (!c) {
                for (; 0 != H.length;)J();
                return!1
            }
        }
        for (; 0 != H.length;)J();
        return!0
    };
    var H = [];
    D.make_computer_move = function (a, e) {
        for (var c = R(a), b = c[0], d = -K, f = 0; f < c.length; f++) {
            O(c[f], a);
            var g = V(1, 3 - a, d, K);
            g > d && (d = g, b = c[f]);
            P()
        }
        return b
    };
    return D
};