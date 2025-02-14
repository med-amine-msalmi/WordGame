const gridContainer = document.querySelector('.grid-container');
const scoreDisplay = document.getElementById('score');
const restartButton = document.getElementById('restart');

let grid = [];
let score = 0;

function initializeGrid() {
    grid = [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0],
    ];
    addRandomTile();
    addRandomTile();
    updateGrid();
}

function addRandomTile() {
    let emptyTiles = [];
    grid.forEach((row, rowIndex) => {
        row.forEach((value, colIndex) => {
            if (value === 0) {
                emptyTiles.push({ rowIndex, colIndex });
            }
        });
    });
    if (emptyTiles.length > 0) {
        const { rowIndex, colIndex } = emptyTiles[Math.floor(Math.random() * emptyTiles.length)];
        grid[rowIndex][colIndex] = Math.random() < 0.9 ? 2 : 4;
    }
}

function updateGrid() {
    gridContainer.innerHTML = '';
    grid.forEach(row => {
        row.forEach(value => {
            const cell = document.createElement('div');
            cell.classList.add('grid-cell');
            cell.setAttribute('data-value', value);
            cell.textContent = value !== 0 ? value : '';
            gridContainer.appendChild(cell);
        });
    });
    scoreDisplay.textContent = score;
}

function restartGame() {
    score = 0;
    initializeGrid();
}

restartButton.addEventListener('click', restartGame);
initializeGrid();
