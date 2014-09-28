var EMPTY = '-';
var FIELD_SIZE = 3;

function makeMove(field, my) {

    var moves = [];
    for (i = 0; i < FIELD_SIZE; ++i) {
        for (j = 0; j < FIELD_SIZE; ++j) {
            if (field[i][j] == EMPTY) {
                moves.push([i,j]);
            }
        }
    }

    if(moves.length == 0)
        throw new Error("The field is full. I can't move!");

    var choise = Math.floor((Math.random() * (moves.length-1)) + 1);
    return moves[choise];
}