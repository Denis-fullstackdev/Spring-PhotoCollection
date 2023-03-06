console.log("---- JS - OK");

const URLParams = new URLSearchParams(window.location.search);
const keyword = URLParams.get("keyword");
const fieldFilter = URLParams.get("fieldFilter");



if (keyword == null)
    elencoFotos();
else {
    axios.get(`http://localhost:8080/api?keyword=${keyword}&fieldFilter=${fieldFilter}`).then((result) => {
        console.log("test axios.get OK", result);
        const statusCode = result.status;
        if(statusCode != 204) {
	        document.querySelector("#table_elenco_fotos").innerHTML = '';
	        result.data.forEach(foto => {
	            console.log("vedi foto: ", foto);
	            document.querySelector("#table_elenco_fotos").innerHTML +=
	            `
	            <tr>
	            	<td>${foto.id}</td>
	                <td>
	                	<a href="show.html?id=${foto.id}">${foto.titolo}</a>
	                </td>
	                <td>${foto.tag}</td>
	                <td>${foto.url} €</td>
	            </tr>
	            `
	    	});
    	} else {
			document.querySelector("#table_elenco_fotos").innerHTML = '<tr><td><h2>NESSUN CONTENUTO TROVATO!</h2></td></tr>';
		}
    }).catch((result) => {
        console.log("Errore row 11 show.js", result);
        alert("Errore");
    })
}

function elencoFotos() {
    axios.get('http://localhost:8080/api').then((result) => {
        console.log("test axios.get OK", result);
        document.querySelector("#table_elenco_fotos").innerHTML = '';
        result.data.forEach(foto => {
            console.log("vedi foto: ", foto);
            document.querySelector("#table_elenco_fotos").innerHTML +=
                `
            <tr>
            	<td>${foto.id}</td>
                <td>
                	<a href="frontend/show?id=${foto.id}">${foto.titolo}</a>
                </td>
                <td>${foto.tag}</td>
                <td>${foto.url}</td>
                <td><img src=${foto.url} alt=${foto.titolo} class="card-img-top"></td>
            </tr>
            `
        });
    }).catch((result) => {
        console.log("test axios.get ERROR", result);
        alert("Errore: row 23 index.js!");
    })
}