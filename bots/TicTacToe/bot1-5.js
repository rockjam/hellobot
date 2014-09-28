var EMPTY = '-';
var preferredMoves =
    [
        [1, 1], [0, 0], [0, 2], [2, 0], [2, 2],
        [0, 1], [1, 0], [1, 2], [2, 1]
    ];

function makeMove(field, my) {

    var i;
    for (i = 0; i < preferredMoves.length; ++i) {
        var move = preferredMoves[i];
        if (field[move[0]][move[1]] == EMPTY) {
            return move;
        }
    }
}