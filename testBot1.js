var step = -1;
function makeMove(field, side) {
    step++;
    if (step == 0) {
        return [0, 0];
    }
    if (step == 1) {
        return [1, 0];
    }
    if (step == 2) {
        return [0, 1];
    }
    if (step == 3) {
        return [1, 2];
    }
    if (step == 4) {
        return [2, 2];
    }
}