
let groups;
console.log('refresh');

refreshGroups().then(function() {
	let firstElem = document.body.querySelector('#GroupList > li');
	firstElem.dispatchEvent(new Event('click'));
});

async function refreshGroups() {
	let getUrl = base + '/groups/get';

	let response = await fetch(getUrl, {
		method : 'GET',
	});

	if (response.ok) {
		console.log("successful get");
		let result = await response.json();
		groups = result.groups;

		GroupList.innerHTML = '';

		for (let i = 0; i < groups.length; i++) {
			let elemLi = document.createElement('li');
			//elemLi.setAttribute("onclick", "showInfo(this)");
			elemLi.setAttribute("data-id", JSON.parse(groups[i]).id)
			elemLi.setAttribute("data-num", i + 1);
			let elemP = document.createElement('p');
			elemP.innerHTML = JSON.parse(groups[i]).name;
			elemLi.appendChild(elemP);
			GroupList.appendChild(elemLi);
			elemLi.onclick = showInfo;
		}
		let elemLi = document.createElement('li');
		elemLi.classList.add("addElem");
		let elemP = document.createElement('p');
		elemP.innerHTML = '+'
		elemLi.appendChild(elemP);
		GroupList.appendChild(elemLi);
		elemLi.onclick = showCreationForm;
	}
}

async function doDelete() {

	let deleteUrl = base + '/groups/delete?groupNum=';
	let gNum = infoForm.parentElement.getAttribute("data-num");

	let response = await fetch(deleteUrl + gNum, {
		method : 'DELETE',
	});

	if (response.ok) {
		console.log("successful delete");
		refreshGroups().then(function() {
			let firstElem = document.body.querySelector('#GroupList > li');
			firstElem.dispatchEvent(new Event('click'));
		});
	}

}

async function doCreate() {

	let createUrl = base + '/groups/post';

	let data = {
		'groupName' : createForm.name.value,
	}

	let response = await fetch(createUrl, {
		method : 'POST',
		body : JSON.stringify(data),
	});

	if (response.ok) {
		console.log("successful create");
		refreshGroups().then(function () {
			GroupList.lastElementChild.previousElementSibling.dispatchEvent(new Event('click'));
		});
	}
	return false;
}

async function showInfo() {

	let elemId = this.getAttribute("data-id");
	let elemNum = this.getAttribute("data-num");
	let group = null;

	for (let i = 0; i < groups.length; i++) {
		if (elemId == ''+JSON.parse(groups[i]).id) {
			group = JSON.parse(groups[i]);
			break;
		}
	}

	if (group != null) {
		GroupHeader.innerHTML = "<p>" + group.name + "</p>";

		Content.innerHTML = '';
		Content.setAttribute("data-num", elemNum);

		Content.innerHTML = 
		`
				<form id='infoForm' class='info-task-form'>
					<p>Your group details.</p>
					<div class='form-row'>
  						<label for='name'>Name</label>
  						<p id='infoName'></p>
					</div>
					<div class='form-row'>
  						<label>Owners</label>
						<ul id='infoOwners'></ul>
					</div>
					<div class='form-row'>
  						<button type="button" id='openBtn'>Open group</button>
  						<a href=""></a>
					</div>
					<div class='form-row'>
  						<button type="button" id='deleteBtn'>Delete group</button>
					</div>
				</form>
		`;

		infoName.innerHTML = group.name;
		infoOwners.innerHTML = '<li>' + userLogin + '</li>';

		///openBtn.nextElementSibling.href = base + "/groups/group/" + elemNum + "/tasks/";
		//openBtn.onclick = function() {this.nextElementSibling.dispatchEvent(new Event('click'));}

		openBtn.onclick = function() {
			let openLink = base + "/groups/group/" + infoForm.parentElement.getAttribute('data-num') + "/tasks/";
			window.location = openLink;
		}

		deleteBtn.onclick = doDelete;

		infoForm.onsubmit = function(event) {
			event.preventDefault();
			return false;
		};


		/*let elemP = document.createElement("p");
		elemP.innerHTML = "Owners";

		let elemUList = document.createElement("ul");

		let elemLi = document.createElement("li");
		elemLi.innerHTML = userLogin;

		let elemOpenA = document.createElement("a");
		elemOpenA.innerHTML = "open group";
		elemOpenA.href = base + "/groups/group/" + elemNum + "/tasks/";

		let elemBr = document.createElement("br");

		let elemDeleteA = document.createElement("a");
		elemDeleteA.innerHTML = "delete group";
		elemDeleteA.href = "#";
		elemDeleteA.onclick = doDelete;

		Content.appendChild(elemP);
		elemUList.appendChild(elemLi);
		Content.appendChild(elemUList);
		Content.appendChild(elemOpenA);
		Content.appendChild(elemBr);
		Content.appendChild(elemDeleteA);*/
	}
}

async function showCreationForm() {

	GroupHeader.innerHTML = "<p>New group</p>";

	Content.innerHTML = '';

	Content.innerHTML = 
		`
				<form id='createForm' class='create-task-form'>
					<p>Input data to create new group.</p>
					<div class='form-row'>
  						<label for='name'>Name</label>
  						<input name='name' type='text'/>
					</div>
					<div class='form-row'>
  						<button type="button" onclick="doCreate()">Submit</button>
					</div>
				</form>
		`;

	/*let elemP = document.createElement("p");
	elemP.innerHTML = "Name:";

	let elemBr = document.createElement("br");

	let elemInput = document.createElement("input");
	elemInput.setAttribute("id", "newGroupInput");
	elemInput.setAttribute("type", "text");

	let elemCreateA = document.createElement("a");
	elemCreateA.innerHTML = "create group";
	elemCreateA.href = "#";
	elemCreateA.onclick = doCreate;

	Content.appendChild(elemP);
	Content.appendChild(elemBr);
	Content.appendChild(elemInput);
	Content.appendChild(elemCreateA);*/
}