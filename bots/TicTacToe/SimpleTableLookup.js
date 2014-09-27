//Simplest Strategy – Heuristic Preferences via Table Lookup

var EMPTY = '-';
var preferredMoves = [
[1, 1], [0, 0], [0, 2], [2, 0], [2, 2],
[0, 1], [1, 0], [1, 2], [2, 1]];

function makeMove(field, my) {

    for (move in preferredMoves) {
        if (field[move[0]][move[1]] == EMPTY) {
            return move;
        }
    }

    //The field is full. Should not reach here
    return [0,0];
}