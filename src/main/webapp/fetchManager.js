// JavaScript source code
const url = "http://localhost:8080/project1/"

//not using function passing name of function
document.getElementById("loginButton").addEventListener("click", loginFunc);

async function loginFunc() {
    console.log("in login func");
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username: usern,
        password: userp
    }
    console.log(user);
    //so it goes to login as method post with user as a body (object)
    //credentials ensures that the cookie is saved on the browser
    let resp = await fetch(url + "login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: "include"
    });
    console.log(status);

    if (resp.status === 200) {
        console.log(resp);
        document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN.";
        redirectPage();
        //redirect to employee pages 
        //redirectEmployee();
        //redirectManager();
        //add avenger button show

    } else {
        document.getElementById("login-row").innerText = "Login failed!";
    }
}
async function redirectPage() {

    //indow.location.href = ".html";
    let resp = await fetch(url + "success", {
        method: 'GET',
        credentials: "include"
    });
    console.log(resp.status);
    if (resp.status === 200) {
        console.log(resp);
        let data = await resp.json();
        console.log(data);
        console.log(data.id);
        let idUser = data.id;
        sessionStorage.setItem("idUser", idUser);
        console.log(data.userRole.role);
        let role = data.userRole.role;
        //let userID = data.id;

        if (role == "Employee") {
            window.location.href = "employeeSuccess.html";
        } else if (role== "Manager") {
            window.location.href = "managerSuccess.html";
        }
        
    } else {
        document.getElementById("login-row").innerText = "Login failed!";
    }
}
