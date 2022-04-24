/*
 * 
 * search.js - provide backend functions to seach for airports
 * 
 * reference: https://codepen.io/imdunn/pen/wqNbqV?editors=0010
 * 
 */
const airport_input = function (id, data, options) {
    var listOfResults = [];

    var fuse = new Fuse(data, options.fuse_options);

    var ac = $(id).on('click', function (e) {
        e.stopPropagation();
    })
            .on('focus keyup', search)
            .on('keydown', onKeyDown);

    var wrap = $('<div>')
            .addClass('autocomplete-wrapper')
            .insertBefore(ac)
            .append(ac);

    var list = $('<div>')
            .addClass('autocomplete-results')
            .on('click', '.autocomplete-result', function (e) {
                e.preventDefault();
                e.stopPropagation();
                selectIndex($(this).data('index'));
            })
            .appendTo(wrap);

    $(document)
            .on('mouseover', '.autocomplete-result', function (e) {
                var index = parseInt($(this).data('index'), 10);
                if (!isNaN(index)) {
                    list.attr('data-highlight', index);
                }
            })
            .on('click', clearResults);

    function clearResults() {
        results = [];
        numResults = 0;
        list.empty();
    }

    function selectIndex(index) {
        if (results.length >= index + 1) {
            ac.val(results[index].item.IATA);
            clearResults();
        }
    }

    let results = [];
    let numResults = 0;
    let selectedIndex = -1;

    function search(e) {
        // 38 code = up Arrow
        // 13 code = enter
        // 40 code = down arrow

        // Add global event listener for click
        // If the click is not a result it will clear all the suggestions
        document.addEventListener('click', function globalClickListener(evnt) {
            clearResults();
            document.removeEventListener('click', globalClickListener);
        });

        if (e.which === 38 || e.which === 13 || e.which === 40) {
            return;
        }

        // Check if user have written anything
        if (ac.val().length > 0) {
            // Splice the results and
            results = fuse.search(ac.val()).slice(0, options.max_returned_results);

            numResults = results.length;

            const divs = results.map((r, i) => {
                const result = options.formatting
                        .replace(
                                new RegExp('\\$\\(unique-result\\)', 'g'),
                                'autocomplete_result'
                                )
                        .replace(new RegExp('\\$\\(i\\)', 'g'), i)
                        .replace(new RegExp('\\$\\(name\\)', 'g'), r.item.name)
                        .replace(new RegExp('\\$\\(IATA\\)', 'g'), r.item.IATA)
                        .replace(new RegExp('\\$\\(city\\)', 'g'), r.item.city)
                        .replace(new RegExp('\\$\\(country\\)', 'g'), r.item.country);

                return result;
            });

            selectedIndex = -1;
            
            list.html(divs.join(''))
                    .attr('data-highlight', selectedIndex);

        } else {
            numResults = 0;
            list.empty();
        }
    }

    function onKeyDown(e) {
        switch (e.which) {
            case 38: // up
                selectedIndex--;
                if (selectedIndex <= -1) {
                    selectedIndex = -1;
                }
                list.attr('data-highlight', selectedIndex);
                break;
            case 13: // enter
                selectIndex(selectedIndex);
                break;
            case 9: // enter
                selectIndex(selectedIndex);
                e.stopPropagation();
                return;
            case 40: // down
                selectedIndex++;
                if (selectedIndex >= numResults) {
                    selectedIndex = numResults - 1;
                }

                list.attr('data-highlight', selectedIndex);
                break;

            default:
                return; // exit this handler for other keys
        }
        e.stopPropagation();
        e.preventDefault(); // prevent the default action (scroll / move caret)
    }
};