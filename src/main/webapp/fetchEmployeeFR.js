allReimbursementsByAuthor();
let userID = sessionStorage.getItem("idUser");
async function allReimbursementsByAuthor() {
    console.log("in all reimbursements")
    document.getElementById("bodyTable").innerText = "";
    // need to get id and display reimbursements by id
    let table = document.getElementById("bodyTable");
    let userID = sessionStorage.getItem("idUser");
    console.log("user: " + userID);


    let resp = await fetch(url + "reimbursements/" + userID, {
        credentials: 'include',
    });

    if (resp.status === 200) {
        console.log(resp);
        let data = await resp.json();
        //console.log(data);
        //put into the table instead of just console
        for (let reimbursement of data) {
            console.log(reimbursement);
            
            let row = table.insertRow(0);
            let id = row.insertCell(0);
            let amount = row.insertCell(1);
            let sTime = row.insertCell(2);
            let rTime = row.insertCell(3);
            let desc = row.insertCell(4);
            let author = row.insertCell(5);
            let resolver = row.insertCell(6);
            let status = row.insertCell(7);
            let type = row.insertCell(8);
            id.innerHTML = reimbursement.id;
            amount.innerHTML = "$ "+ reimbursement.amount;
            sTime.innerHTML = reimbursement.submitted;
            rTime.innerHTML = reimbursement.resolved;
            desc.innerHTML = reimbursement.description;
            author.innerHTML = reimbursement.author.username;
            //insert if statement if resolver is not equal to null or null

            let reimbResolver = reimbursement.resolver;
            if (reimbResolver != null) {
                resolver.innerHTML = reimbursement.resolver.username;
            } else {
                resolver.innerHTML = reimbursement.resolver;
            }
            resolver.innerHTML = reimbursement.resolver;
            status.innerHTML = reimbursement.status.status;
            type.innerHTML = reimbursement.type.type;


        }
    }
}

async function addReimb() {
    let reimbAmount = document.getElementById("rAmount").value;
    let reimbDesc = document.getElementById("rDesc").value;

    const rbsType = document.querySelectorAll('input[name="reimbType"]');
    let typeChoice;
    for (const rb of rbsType) {
        if (rb.checked) {
            typeChoice = rb.value;
            break;
        }
    }
    console.log(typeChoice);


    let reimbursement = {
        amount: reimbAmount,
        description: reimbDesc,
        authorId: userID,
        type: typeChoice
    }
    console.log(reimbursement);

    let resp = await fetch(url + "addReimbursement", {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    });

    if (resp.status == 200) {
        //call my find all by author func to refill table
        allReimbursementsByAuthor();
    }
}