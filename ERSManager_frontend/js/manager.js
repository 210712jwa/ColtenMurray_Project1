let filterInput = document.getElementById('status');
let filterButton = document.getElementById('filterButton')

let removeButton = document.getElementById('removeFilter')

function onLoad(event) {
    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 401) {
            window.location.href = '/index.html'
        } else if (response.status === 200) {
            return response.json();
        }
    }).then((user) => {
        return fetch(`http://localhost:7000/getAllRequests`, {
            'method': 'GET', 
            'credentials': 'include'
        });
    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        clearTable();
        populateReimbursements(reimbursements);
    })
}

function filter(event) {
    event.preventDefault(); 

    fetch('http://localhost:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 401) {
            window.location.href = '/index.html'
        } else if (response.status === 200) {
            return response.json();
        }
    }).then((user) => {
        return fetch(`http://localhost:7000/filterRequestsByStatus/${filterInput.value}`, {
            'method': 'GET', 
            'credentials': 'include'
        });
    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        clearTable();
        populateReimbursements(reimbursements);
    })
}

function clearTable(){
    let tbody = document.querySelector('#reimbursements tbody');
    tbody.innerHTML = "";
}

function populateReimbursements(reimbursementsArray) {
    let tbody = document.querySelector('#reimbursements tbody');



    for (const ERSReimbursement of reimbursementsArray) {
        /*
        <th>Ship ID</th>
                    <th>Ship Name</th>
                    <th>Ship Age</th>
                    <th>Ship Owner First Name</th>
                    <th>Ship Owner Last Name</th>
                    <th>Ship Status</th>
        */

        let tr = document.createElement('tr');

        let reimbursementIdTd = document.createElement('td');
        reimbursementIdTd.innerHTML = ERSReimbursement.id;

        let reimbursementAmountTd = document.createElement('td');
        reimbursementAmountTd.innerHTML = "$"+ERSReimbursement.reimbAmount;

        let reimbursementSubmittedTd = document.createElement('td');
        reimbursementSubmittedTd.innerHTML = new Date(ERSReimbursement.reimbSubmitted).toISOString().slice(0, 10);

        let reimbursementResolvedTd = document.createElement('td');
        if( ERSReimbursement.reimbResolved == null){
            reimbursementResolvedTd.innerHTML = "Not Resolved"
        }
        else{
            reimbursementResolvedTd.innerHTML = new Date(ERSReimbursement.reimbResolved).toISOString().slice(0, 10);;
        }

        let reimbursementDescriptionTd = document.createElement('td');
        reimbursementDescriptionTd.innerHTML = ERSReimbursement.reimbDescription;

        let reimbursementStatusTd = document.createElement('td');
        reimbursementStatusTd.innerHTML = ERSReimbursement.status.reimbStatus;

        let reimbursementTypeTd = document.createElement('td');
        reimbursementTypeTd.innerHTML = ERSReimbursement.type.reimbType;

        let reimbursementAuthorTd = document.createElement('td');
        reimbursementAuthorTd.innerHTML = ERSReimbursement.author.firstName + " " + ERSReimbursement.author.lastName;

        let reimbursementResolverTd = document.createElement('td');
        if(ERSReimbursement.resolver == null){
            reimbursementResolverTd.innerHTML = "No Resolver"
        }
        else{
            reimbursementResolverTd.innerHTML = ERSReimbursement.resolver.firstName + " " + ERSReimbursement.resolver.lastName;
        }
//////////////////////// BUTTON ADDER ///////////////////////////////////////////////////////////////////
        var approveButton = null;
        var denyButton = null;
        if(reimbursementStatusTd.innerHTML == "Pending"){
            approveButton = document.createElement("button");
            approveButton.innerHTML = "Approve";
            approveButton.onclick = function (){
                const submissionInfo = {
                    'status': 2
                };

                fetch(`http://localhost:7000/processRequest/${ERSReimbursement.id}`, {
                    'credentials': 'include',
                    'method': 'PUT',
                    headers: {
                        'Content-Type': 'application/json' // application/json is a MIME type
                    },
                    body: JSON.stringify(submissionInfo)
                }).then((response) =>{
                    if (response.status === 200) {
                        window.location.href = '/manager.html'
                    }
                })
            };

            denyButton = document.createElement("button")
            denyButton.innerHTML = "Deny";
            denyButton.onclick = function (){
                const submissionInfo = {
                    'status': 3
                };

                fetch(`http://localhost:7000/processRequest/${ERSReimbursement.id}`, {
                    'credentials': 'include',
                    'method': 'PUT',
                    headers: {
                        'Content-Type': 'application/json' // application/json is a MIME type
                    },
                    body: JSON.stringify(submissionInfo)
                }).then((response) =>{
                    if (response.status === 200) {
                        window.location.href = '/manager.html'
                    }
                })
            };
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tr.appendChild(reimbursementIdTd);
        tr.appendChild(reimbursementAmountTd);
        tr.appendChild(reimbursementSubmittedTd);
        tr.appendChild(reimbursementResolvedTd);
        tr.appendChild(reimbursementDescriptionTd);
        tr.appendChild(reimbursementStatusTd);
        tr.appendChild(reimbursementTypeTd);
        tr.appendChild(reimbursementAuthorTd);
        tr.appendChild(reimbursementResolverTd);
        if(approveButton != null){
            tr.appendChild(approveButton);
        }
        if(denyButton != null){
            tr.appendChild(denyButton);
        }
        

        tbody.appendChild(tr);
    }
}

function logout(event){
  
    event.preventDefault();
    fetch('http://127.0.0.1:7000/logout', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application.json'
        },
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = 'index.html';
        }
    })
};


window.addEventListener('load', onLoad);
filterButton.addEventListener('click', filter);
removeButton.addEventListener('click', onLoad);