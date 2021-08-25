let reimbursementButton = document.getElementById('requestreimbursement');

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
        return fetch(`http://localhost:7000/user/${user.id}/reimbursement`, {
            'method': 'GET', 
            'credentials': 'include'
        });
    }).then((response) => {
        return response.json()
    }).then((reimbursements) => {
        populateReimbursements(reimbursements);
    })
}

function addReimbursement(event){
    event.preventDefault();

    window.location.href = '/addReimbursement.html';
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
        let submitted = new Date(ERSReimbursement.reimbSubmitted)
        reimbursementSubmittedTd.innerHTML = submitted.getMonth()+1+"/"+submitted.getDate() +"/"+submitted.getFullYear();

        let reimbursementResolvedTd = document.createElement('td');
        if( ERSReimbursement.reimbResolved == null){
            reimbursementResolvedTd.innerHTML = "Not Resolved"
        }
        else{
            reimbursementResolvedTd.innerHTML = new Date(ERSReimbursement.reimbResolved);
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
        


        tr.appendChild(reimbursementIdTd);
        tr.appendChild(reimbursementAmountTd);
        tr.appendChild(reimbursementSubmittedTd);
        tr.appendChild(reimbursementResolvedTd);
        tr.appendChild(reimbursementDescriptionTd);
        tr.appendChild(reimbursementStatusTd);
        tr.appendChild(reimbursementTypeTd);
        tr.appendChild(reimbursementAuthorTd);
        tr.appendChild(reimbursementResolverTd);

        tbody.appendChild(tr);
    }
}

reimbursementButton.addEventListener('click', addReimbursement);
window.addEventListener('load', onLoad);
