
let groups;

refreshGroups(groups);

async function refreshGroups(groups) {
	let getUrl = base + '/groups/get';

	let response = await fetch(getUrl, {
		method : 'GET',
	});

	if (response.ok) {
		console.log("successful get");
		let result = response.json();
		groups = result.groups;
	}

	GroupList.innerHTML = '';

	for (let i = 0; i < groups.length; i++) {
		let elemLi = document.createElement('li');
		let elemP = document.createElement('p');
		elemP.innerHTML = groups[i].name;
		elemLi.appendChild(elemP);
		GroupList.appendChild(elemLi);
	}
	let elemLi = document.createElement('li');
	elemLi.classList.add("addElem");
	let elemP = document.createElement('p');
	elemP.innerHTML = '+'
	elemLi.appendChild(elemP);
	GroupList.appendChild(elemLi);
}