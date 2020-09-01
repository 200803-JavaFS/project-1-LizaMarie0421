allReimbursementsByAuthor();

async function allReimbursementsByAuthor() {
    console.log("in all reimbursements")
    document.getElementById("bodyTable").innerText = "";
    // need to get id and display reimbursements by id
    console.log(idUser);
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
        }
    }
}