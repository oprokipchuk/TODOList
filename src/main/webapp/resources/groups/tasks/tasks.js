let tasks;

let filteredTasks;
console.log('refresh');

refreshTasks().then(function() {showTasks();}).then(function() {
	let firstElem = document.body.querySelector('#TaskList > li');
	firstElem.dispatchEvent(new Event('click'));
});

filterInput.oninput = showTasks;
c1.onclick = showTasks;
c2.onclick = showTasks;
c3.onclick = showTasks;

async function refreshTasks() {
	let getUrl = 'get';

	let response = await fetch(getUrl, {
		method : 'GET',
	});

	if (response.ok) {
		console.log("successful get");
		let result = await response.json();
		tasks = result.tasks;

		TaskList.innerHTML = '';

		for (let i = 0; i < tasks.length; i++) {
			tasks[i] = JSON.parse(tasks[i]);
			tasks[i].taskNum = i + 1;
		}
		filteredTasks = tasks;
	}
}

function showTasks() {


	if (filterInput.value != '') {
		console.log(filterInput.value);
		filteredTasks = tasks.filter(item => item.name.startsWith(filterInput.value));
	}
	else filteredTasks = tasks;
	if (!c1.checked) {
		filteredTasks = filteredTasks.filter(item => item.status != 'not started');
	}
	if (!c2.checked) {
		filteredTasks = filteredTasks.filter(item => item.status != 'in process');
	}
	if (!c3.checked) {
		filteredTasks = filteredTasks.filter(item => item.status != 'done');
	}

	TaskList.innerHTML = '';

	for (let i = 0; i < filteredTasks.length; i++) {
			let elemLi = document.createElement('li');
			elemLi.setAttribute("data-id", filteredTasks[i].id)
			elemLi.setAttribute("data-num", filteredTasks[i].taskNum);
			let elemP = document.createElement('p');
			elemP.innerHTML = filteredTasks[i].name;
			elemLi.appendChild(elemP);

			elemSpan = document.createElement('span');
			elemSpan.innerHTML = `
				<p class="changeButton edit">&#9998;</p
				><p class="changeButton delete">&#128465;</p>
			`;
			elemSpan.firstElementChild.onclick = showEditForm;
			elemSpan.lastElementChild.onclick = doDelete;
			elemLi.appendChild(elemSpan);

			TaskList.appendChild(elemLi);
			elemLi.onclick = showInfo;
		}
		let elemLi = document.createElement('li');
		elemLi.classList.add("addElem");
		let elemP = document.createElement('p');
		elemP.innerHTML = '+'
		elemLi.appendChild(elemP);
		TaskList.appendChild(elemLi);
		elemLi.onclick = showCreationForm;
}


async function showInfo() {

	console.log('in showInfo');
	let elemId = this.getAttribute("data-id");
	let elemNum = this.getAttribute("data-num");
	let task = null;

	for (let i = 0; i < tasks.length; i++) {
		if (elemId == ''+tasks[i].id) {
			task= tasks[i];
			break;
		}
	}

	if (task != null) {
		TaskHeader.innerHTML = "<span>" + task.name + "</span>";

		Content.innerHTML = '';
		Content.setAttribute("data-num", elemNum);

		Content.innerHTML = 
		`
				<form id='infoForm' class='info-task-form'>
					<p>Your task details.</p>
					<div class='form-row'>
  						<label for='name'>Name</label>
  						<p id='infoName'>Task1</p>
					</div>
					<div class='form-row'>
  						<label for='status'>Status</label>
  						<p id='infoStatus'>not done</p>
					</div>
					<div class='form-row'>
  						<label for='description'>Desctiption</label>
  						<textarea id='infoDescription' disabled name='description' type='text'/>
  						</textarea>
					</div>
				</form>
		`;

		infoName.innerHTML = task.name;
		infoStatus.innerHTML = task.status;
		infoDescription.innerHTML = task.description;
	}
}

async function showCreationForm() {

	TaskHeader.innerHTML = "<span>New task</span>";

	Content.innerHTML = 
		`
				<form id='createForm' class='create-task-form'>
					<p>Input data to create new task.</p>
					<div class='form-row'>
  						<label for='name'>Name</label>
  						<input name='name' type='text'/>
					</div>
					<div class='form-row'>
  						<label for='status'>Status</label>
  						<select name='status' type='text'/>
  							<option value="not started">
  								Task not started
  							</option>
							<option value="in process">
								Task in process
							</option>
							<option value="done">
								Task is done
							</option>
  						</select>
					</div>
					<div class='form-row'>
  						<label for='description'>Desctiption</label>
  						<textarea name='description' type='text'/>
  						</textarea>
					</div>
					<div class='form-row'>
  						<button onclick="doCreate()">Submit</button>
					</div>
				</form>
		`;

	createForm.onsubmit = function(event) {
			event.preventDefault();
			return false;
		};
}

async function showEditForm(event) {

	console.log('in edit');
	event.stopPropagation();
	let elemId = this.parentElement.parentElement.getAttribute("data-id");
	let elemNum = this.parentElement.parentElement.getAttribute("data-num");
	let task = null;

	for (let i = 0; i < tasks.length; i++) {
		if (elemId == ''+tasks[i].id) {
			task= tasks[i];
			break;
		}
	}

	if (task != null) {
		TaskHeader.innerHTML = "<span>" + task.name + "</span>";

		Content.innerHTML = '';
		Content.setAttribute("data-num", elemNum);

		Content.innerHTML = 
		`
				<form id='editForm' class='create-task-form'>
					<p>Input data to edit your task.</p>
					<div class='form-row'>
  						<label for='name'>Name</label>
  						<input name='name' type='text'/>
					</div>
					<div class='form-row'>
  						<label for='status'>Status</label>
  						<select name='status' type='text'/>
  							<option value="not started">
  								Task not started
  							</option>
							<option value="in process">
								Task in process
							</option>
							<option value="done">
								Task is done
							</option>
  						</select>
					</div>
					<div class='form-row'>
  						<label for='description'>Desctiption</label>
  						<textarea name='description' type='text'/>
  						</textarea>
					</div>
					<div class='form-row'>
  						<button onclick="doEdit()">Submit</button>
					</div>
				</form>
		`;
		editForm.name.value = task.name;
		editForm.status.value = task.status;
		editForm.description.innerHTML = task.description;
		editForm.onsubmit = function(event) {
			event.preventDefault();
			return false;
		};
	}	
}

async function doEdit() {
	let editUrl = 'put';

	let data = {
		'name' : editForm.name.value,
		'status' : editForm.status.value,
		'description' : editForm.description.value,
		'taskNum' : editForm.parentElement.getAttribute('data-num'),
	}

	let response = await fetch(editUrl, {
		method : 'PUT',
		body : JSON.stringify(data),
	});

	if (response.ok) {
		console.log("successful edit");
		refreshTasks().then(function() {showTasks();});
	}
	return false;
}

async function doCreate() {

	let createUrl = 'post';

	let data = {
		'taskName' : createForm.name.value,
		'taskStatus' : createForm.status.value,
		'taskDescription' : createForm.description.value,
	}

	let response = await fetch(createUrl, {
		method : 'POST',
		body : JSON.stringify(data),
	});

	if (response.ok) {
		console.log("successful create");
		refreshTasks().then(function() {showTasks();}).then(function () {
			TaskList.lastElementChild.previousElementSibling.dispatchEvent(new Event('click'));
		});
	}
}

async function doDelete(event) {
	console.log('in delete');
	event.stopPropagation();
	let deleteUrl = 'delete?taskNum='

	let tNum = this.parentNode.parentNode.getAttribute("data-num");

	let response = await fetch(deleteUrl + tNum, {
		method : 'DELETE',
	});

	if (response.ok) {
		console.log("successful delete");
		refreshTasks().then(function() {showTasks();});
	}
}

function htmlToElement(html) {
    var template = document.createElement('template');
    html = html.trim();
    template.innerHTML = html;
    return template.content.firstChild;
}

