function setupSearch(input, resultsList) {
    input.addEventListener('input', function() {
        const query = this.value;
        if (this.readOnly || query.length < 2) {
            resultsList.innerHTML = ''; return;
        }

        fetch(`/movies/search?term=${query}`)
            .then(res => res.json())
            .then(data => {
                resultsList.innerHTML = '';
                data.forEach(title => {
                    const div = document.createElement('div');
                    div.innerHTML = title;
                    div.onclick = () => {
                        input.value = title;
                        resultsList.innerHTML = '';
                        input.readOnly = true;
                        input.classList.add('lockedInput');
                    };
                    resultsList.appendChild(div);
                });
            });
    });
}

const searchInput = document.getElementById('movieSearch');
const resultsList = document.getElementById('reviewResultsList');
setupSearch(searchInput, resultsList);