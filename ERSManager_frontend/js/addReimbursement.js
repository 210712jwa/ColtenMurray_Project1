let submitButton = document.getElementById('submit');
let amountInput = document.getElementById('amount');
let typeInput = document.getElementById('type');
let fileInput = document.getElementById("fileinput");
let descriptionInput = document.getElementById('description');



function submit(event) {
    event.preventDefault(); // this will prevent the default behavior of what happens when 
    // a button inside a form element is clicked

    const submissionInfo = {
        'reimbAmount': amountInput.value,
        'reimbDescription': descriptionInput.value,
        'type': typeInput.value
    };

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
            'method': 'POST', 
            'credentials': 'include',
            headers: {
                'Content-Type': 'application/json' // application/json is a MIME type
            },
            body: JSON.stringify(submissionInfo)
        });
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/viewships.html';
        } 
    })
};

submitButton.addEventListener('click', submit);