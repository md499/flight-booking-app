/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function sortCol(table, col) {
    const bod = table.tBodies[0];
    const r = Array.from(bod.querySelectorAll("tr"));
    
    const newR = r.sort((a,b) => {
        const aT = a.querySelector('td:nth-child(${ column + 1})').textContent.trim();
        const bT = b.querySelector('td:nth-child(${ column + 1})').textContent.trim();
        
        if (aT > bT) {
            return 1;
        } else {
            return -1;
        }
        
    });
    
    while(bod.firstChild) {
        bod.removeChild(bod.firstChild);
    }
    
    bod.append(...newR);
    
    table.querySelectorAll("td").forEach(th => th.ClassList.remove("td-sort-asc", "td-sort-desc"));
    table.querySelectorAll('td:nth-child(${ column + 1})').classList.toggle("td-sort-asc", 1);
    table.querySelectorAll('td:nth-child(${ column + 1})').classList.toggle("td-sort-desc", 0);
}

document.querySelectorAll(".table-sortable td").forEach(header => {
    header.addEventListener("click", () => {
        const element = header.parentElement.parentElement.parentElement;
        const hIndx = Array.prototype.indexOf.call(header.parentElement.children, header);
        const isAsc = header.classList.contains("td-sort-asc");
        
        sortCol(element, hIndx, !isAsc);
    });
});