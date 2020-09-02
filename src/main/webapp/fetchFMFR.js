// JavaScript source code
allReimbursements();
let userID = sessionStorage.getItem("idUser");

var sel = document.getElementById('statusChoice');
async function allReimbursements() {
    console.log("in all reimbursements finance manager");
    //document.getElementById("bodyTable").innerText = "";
    // need to get id and display reimbursements by id
    let table = document.getElementById("bodyTable");
    

    let resp = await fetch(url + "reimbursements", {
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
            amount.innerHTML = "$ " + reimbursement.amount;
            //sTime.innerHTML = reimbursement.submitted;
            dateS = new Date(reimbursement.submitted);
            sTime.innerHTML = dateS.toLocaleString();
            // rTime.innerHTML = reimbursement.resolved;

            let reimbResolvedTime = reimbursement.resolved;
            if (reimbResolvedTime != null) {
                dateR = new Date(reimbursement.resolved);
                rTime.innerHTML = dateR.toLocaleString();
            } else {
                rTime.innerHTML = reimbursement.resolved;
            }

            desc.innerHTML = reimbursement.description;
            author.innerHTML = reimbursement.author.username;
            //insert if statement if resolver is not equal to null or null
            let reimbResolver = reimbursement.resolver;
            if (reimbResolver != null) {
                resolver.innerHTML = reimbursement.resolver.username;
            } else {
                resolver.innerHTML = reimbursement.resolver;
            }
            
           
            status.innerHTML = reimbursement.status.status;
            type.innerHTML = reimbursement.type.type;


        }
    }
}

async function updateStatus() {

    let reimbID = document.getElementById("idInput").value;
    console.log(reimbID);

    //check which radio is checked
    //if checked value= 0 update status to deny
    //else if checked value = 1 update status to approve
    const rbs = document.querySelectorAll('input[name="reimbStatus"]');
    let selectedValue;
    for (const rb of rbs) {
        if (rb.checked) {
            selectedValue = rb.value;
            break;
        }
    }
    console.log("choice:"+selectedValue);
    let status = {
        id: reimbID,
        authorId: userID,
        status: selectedValue
    }

    let resp = await fetch(url + "updateStatus", {
        method: 'POST',
        body: JSON.stringify(status),
        credentials: "include"
    });
    if (resp.status === 200) {
        console.log(resp);
        allReimbursements();
    }
}


async function filterStatus() {
    console.log("in filter status: on change works");
    getSelectedOption(sel);
   

}

async function getSelectedOption(sel) {
    document.getElementById("bodyTable").innerText = "";
    let opt;
    let option;
    for (var i = 0, len = sel.options.length; i < len; i++) {
        opt = sel.options[i];
        if (opt.selected === true) {
            option = opt.value;
            console.log(option);
            break;
        }
    }
    let selectChoice = option;
    console.log("choice match:" + selectChoice);
    console.log("after sel option");

    if (selectChoice == 4) {
        allReimbursements();
    } else {
        //document.getElementById("bodyTable").innerText = "";
        // need to get id and display reimbursements by id
        let table = document.getElementById("bodyTable");


        let resp = await fetch(url + "filter/" + selectChoice, {
            credentials: 'include',
        });

        if (resp.status === 200) {
            console.log(resp);
            let data = await resp.json();

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
                amount.innerHTML = "$ " + reimbursement.amount;
                dateS = new Date(reimbursement.submitted);
                sTime.innerHTML = dateS.toLocaleString();
               // rTime.innerHTML = reimbursement.resolved;

                let reimbResolvedTime = reimbursement.resolved;
                if (reimbResolvedTime != null) {
                    dateR = new Date(reimbursement.resolved);
                    rTime.innerHTML = dateR.toLocaleString();
                } else {
                    rTime.innerHTML = reimbursement.resolved;
                }


                desc.innerHTML = reimbursement.description;
                author.innerHTML = reimbursement.author.username;




                let reimbResolver = reimbursement.resolver;
                if (reimbResolver != null) {
                    resolver.innerHTML = reimbursement.resolver.username;
                } else {
                    resolver.innerHTML = reimbursement.resolver;
                }


                status.innerHTML = reimbursement.status.status;
                type.innerHTML = reimbursement.type.type;


            }
        }
    }
}

