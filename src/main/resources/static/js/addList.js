function addMovieInput() {
    const container = document.getElementById('listMoviesContainer');

    const newGroup = document.createElement('div');
    newGroup.className = 'movieInputGroup';

    newGroup.innerHTML = `
        <div class="autocomplete">
            <input type="text" name="movieTitles" class="movieSearchInput"
                   placeholder="Search movie..." required autocomplete="off">
            <div class="resultsList autocompleteItems"></div>
        </div>
        <button type="button" class="btnRemove" onclick="removeMovieInput(this)">
            &times;
        </button>
    `;

    container.appendChild(newGroup);

    const input = newGroup.querySelector('.movieSearchInput');
    const results = newGroup.querySelector('.resultsList');
    setupSearch(input, results);
}

function removeMovieInput(button) {
    button.parentElement.remove();
}
