console.log("SHOW OK");
const URLParams = new URLSearchParams(window.location.search);
const fotoId = URLParams.get("id");
console.log("foto ID: ", fotoId);

fotoReload(fotoId);

function fotoReload(fotoId) {
	
	axios.get(`http://localhost:8080/api/${fotoId}`).then((result) => {
	    console.log("test axios.get OK", result);
	    
	    const foto = result.data;
	    
	    document.querySelector("#id").innerHTML = foto.id;
	    document.querySelector("#titolo").innerHTML = foto.titolo;
	    document.querySelector("#tag").innerHTML = foto.tag;
	    document.querySelector("#url").innerHTML = foto.url;
	    
	    if(foto.commentos.length >= 1) {
			document.querySelector('#commentos').innerHTML = '';
			foto.commentos.forEach( commento => {
				document.querySelector('#commentos').innerHTML +=
				`
					<div class="m-2 border">
						<h3>Scritto da: ${commento.user}</h3>
						<p>${commento.content}</p>
					</div>
				`;
			});
		} else document.querySelector('#commentos').innerHTML = "<p>Nessun commento su questa foto</p>"
	    
	}).catch((result) => {
	    console.log("Errore row 11 show.js", result);
	    alert("Errore");
	})
	
}