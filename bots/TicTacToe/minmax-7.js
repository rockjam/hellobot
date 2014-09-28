//Strong bot using Minimax algorithm

var EMPTY = '-';
var FIELD_SIZE = 3;
var MY_SIDE;
var OPPONENT_SIDE;

var Field;

function makeMove(field, my) {
   MY_SIDE = my;
   OPPONENT_SIDE = (my == 'x')? 'o': 'x';
   Field = field;
   print("Field.length=" + Field.length);
   print("Field[0].length=" + Field[0].length);

   var result = minimax(FIELD_SIZE - 1, my, Number.NEGATIVE_INFINITY, Number.POSITIVE_INFINITY);

   return [result[1],result[2]];
}

/** Minimax (recursive) at level of depth for maximizing or minimizing player
 with alpha-beta cut-off. Return int[3] of [score, row, col]  */
function minimax(depth, side, alpha, beta)
{
    // Generate possible next moves in an array of int[2] of {row, col}.
    var possibleMoves = generateMoves(Field);

    // mySeed is maximizing; while oppSeed is minimizing
    var    score;
    var    bestRow = -1;
    var    bestCol = -1;

    if (possibleMoves.length == 0 || depth == 0) {
        // Gameover or depth reached, evaluate score
        score = evaluate(Field);
        printf("Gameover or depth reached, score "+score);
        return [score, bestRow, bestCol];
    } else {
        var i;
        for (i = 0; i < possibleMoves.length; ++i)
        {
            var move = possibleMoves[i];

            // try this move for the current player
            Field[move[0]][move[1]] = side;
            if (side == MY_SIDE) {  // mySeed (computer) is maximizing player
                score = minimax(depth - 1, OPPONENT_SIDE, alpha, beta)[0];
                print(JSON.stringify(move)+" - score " + score + "alpha = "+alpha);
                if (score > alpha) {
                    alpha = score;
                    bestRow = move[0];
                    bestCol = move[1];
                }
            } else {  // oppSeed is minimizing player
                score = minimax(depth - 1, MY_SIDE, alpha, beta)[0];
                if (score < beta) {
                    beta = score;
                    bestRow = move[0];
                    bestCol = move[1];
                }
            }
            // undo move
            Field[move[0]][move[1]] = EMPTY;
            // cut-off
            if (alpha >= beta) {
                print(JSON.stringify(move)+" - cutted off");
                break;
            }
        }
        return [ (side == MY_SIDE) ? alpha : beta, bestRow, bestCol ];
    }
    throw new Error("should not reach here");
}

function generateMoves(field)
{
    var moves = [];
    // If gameover, i.e., no next move
    //if (hasWon(mySeed) || hasWon(oppSeed)) {
    //    return nextMoves;   // return empty list
    //}

    //print("-----Possible moves:");
    // Search for empty cells and add to the List
    for (var row = 0; row < FIELD_SIZE; ++row) {
        for (var col = 0; col < FIELD_SIZE; ++col) {
            if (field[row][col] == EMPTY) {
                moves.push([row, col]);
                //print("possibleMove="+[row, col]+", size="+moves.length);
            }
        }
    }
    return moves;
}

function evaluate(field)
{
    var score = 0;

    score += evaluateLine(field, 0, 0, 0, 1, 0, 2);  // row 0
    score += evaluateLine(field, 1, 0, 1, 1, 1, 2);  // row 1
    score += evaluateLine(field, 2, 0, 2, 1, 2, 2);  // row 2
    score += evaluateLine(field, 0, 0, 1, 0, 2, 0);  // col 0
    score += evaluateLine(field, 0, 1, 1, 1, 2, 1);  // col 1
    score += evaluateLine(field, 0, 2, 1, 2, 2, 2);  // col 2
    score += evaluateLine(field, 0, 0, 1, 1, 2, 2);  // diagonal
    score += evaluateLine(field, 0, 2, 1, 1, 2, 0);  // alternate diagonal

    return score;
}

function evaluateLine(field, row1, col1, row2, col2, row3, col3)
{
    var score = 0;

    // First cell
    if (field[row1][col1] == MY_SIDE) {
        score = 1;
    } else if (field[row1][col1] == OPPONENT_SIDE) {
        score = -1;
    }

    // Second cell
    if (field[row2][col2] == MY_SIDE) {
        if (score == 1) {   // cell1 is mySeed
            score = 10;
        } else if (score == -1) {  // cell1 is oppSeed
            return 0;
        } else {  // cell1 is empty
            score = 1;
        }
    } else if (field[row2][col2] == OPPONENT_SIDE) {
        if (score == -1) { // cell1 is oppSeed
            score = -10;
        } else if (score == 1) { // cell1 is mySeed
            return 0;
        } else {  // cell1 is empty
            score = -1;
        }
    }

    // Third cell
    if (field[row3][col3] == MY_SIDE) {
        if (score > 0) {  // cell1 and/or cell2 is mySeed
            score *= 10;
        } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
            return 0;
        } else {  // cell1 and cell2 are empty
            score = 1;
        }
    } else if (field[row3][col3] == OPPONENT_SIDE) {
        if (score < 0) {  // cell1 and/or cell2 is oppSeed
            score *= 10;
        } else if (score > 1) {  // cell1 and/or cell2 is mySeed
            return 0;
        } else {  // cell1 and cell2 are empty
            score = -1;
        }
    }
    return score;

}