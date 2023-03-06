console.log("CREATE JS OK");

receiveFoto();

function createCommento(event) {
	event.preventDefault();
	
	const commento = {
		fotoId : document.querySelector('#foto_id').value,
		content : document.querySelector('#content').value,
		user : document.querySelector('#user').value
	};
	
	axios.post(`http://localhost:8080/api/4/create`, commento).then((res) => {
		console.log("OK CREATE ROW 14 CREATE.JS");
		location.href = "/frontend";
	}).catch((res) => {
		console.log("ERRORE ROW 17 CREATE JS", res);
	})
};

function receiveFoto() {
	axios.get('http://localhost:8080/api/4').then((res) => {
		console.log("RECEIVE FOTO console log");
		const foto = res.data;
		document.querySelector('#foto_id').innerHTML+=
		`
			<input type="number" class="d-none" id="foto_id" value="${foto.id}">
		`
	}).catch((res) => {
		console.error("ERRORE ROW 29 CREATE.JS", res);
	})
}