window.addEventListener("load", solve);

function solve() {

    let expenseInput = document.getElementById("expense");
    let amountInput = document.getElementById("amount");
    let dateInput = document.getElementById("date");
    let previewList = document.getElementById("preview-list");
    let expensesList = document.getElementById("expenses-list");

    let addBtn = document.getElementById("add-btn");
    let deleteBtn = document.querySelector(".btn.delete");
    addBtn.addEventListener('click', onAdd);
    deleteBtn.addEventListener('click', onDelete);

    function clearAllInputFields() {
        expenseInput.value = "";
        amountInput.value = "";
        dateInput.value = "";
    }

    function onAdd(event) {
        event.preventDefault();
        let li = document.createElement("li");
        li.classList.add("expense-item");
        li.innerHTML = `
          <article>
            <p>Type: ${expenseInput.value}</p>
            <p>Amount: ${amountInput.value}$</p>
            <p>Date: ${dateInput.value}</p>
          </article> 
          <div class="buttons">
            <button class="btn edit">edit</button>
            <button class="btn ok">ok</button>
          </div>
        `;

        let editBtn = li.querySelector(".btn.edit");
        let okBtn = li.querySelector(".btn.ok");
        editBtn.addEventListener('click', onEdit);
        okBtn.addEventListener('click', onOk);

        clearAllInputFields();
        addBtn.disabled = true;
        previewList.appendChild(li);
    }

    function onEdit(event) {
        event.preventDefault();
        let li = event.currentTarget.parentNode.parentNode;
        previewList.removeChild(li);
        let type = li.querySelectorAll("article p")[0].textContent.split(": ")[1];
        let amount = li.querySelectorAll("article p")[1].textContent.split(": ")[1].slice(0, -1);
        let date = li.querySelectorAll("article p")[2].textContent.split(": ")[1];

        expenseInput.value = type;
        amountInput.value = amount;
        dateInput.value = date;
        addBtn.disabled = false;
    }

    function onOk(event) {
        event.preventDefault();
        let li = event.currentTarget.parentNode.parentNode;
        let buttonsDiv = event.currentTarget.parentNode;
        li.removeChild(buttonsDiv);
        previewList.removeChild(li);

        expensesList.appendChild(li);
        addBtn.disabled = false;
    }



    function onDelete(event) {
        event.preventDefault();
        location.reload();
    }
}