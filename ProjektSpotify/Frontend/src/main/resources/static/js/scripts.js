function myFunction() {
    let input, filter,searchTable, table, tr, td, i, txtValue, p, div;
    input = document.getElementById("input");
    filter = input.value.toUpperCase();
    searchTable = document.getElementById("search-table");
    tr = searchTable.getElementsByTagName("tr");
    td = tr[0].getElementsByTagName("td")

    for (i = 0; i < td.length; i++) {
        div = td[i].getElementsByTagName("div")[0];
        if (div) {
            p = div.getElementsByTagName("p")[0];
            if (p) {
                txtValue = p.textContent || p.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    div.style.display = "";
                } else {
                    div.style.display = "none";
                }
            }
        }
    }
}