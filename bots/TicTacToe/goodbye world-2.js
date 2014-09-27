var step = -1;
function makeMove(field, side) {
    step++;
    if (step == 0) {
        return [2, 0];
    }
    if (step == 1) {
        return [1, 1];
    }
    if (step == 2) {
        return [2, 1];
    }
    if (step == 3) {
        return [0, 2];
    }
}