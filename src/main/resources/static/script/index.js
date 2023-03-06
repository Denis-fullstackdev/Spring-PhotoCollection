console.log("---- JS - OK");

const URLParams = new URLSearchParams(window.location.search);
const filtro = URLParams.get("filtro");



if (filtro == null)
    elencoFotos();
else {

    axios.get(`http://localhost:8080/api?filtro=${filtro}`).then((result) => {
        console.log("test axios.get OK", result);
        document.querySelector("#table_elenco_fotos").innerHTML = '';
        result.data.forEach(foto => {
            console.log("vedi foto: ", foto);
            document.querySelector("#table_elenco_fotos").innerHTML +=
                `
            <tr>
            	<td>${foto.id}</td>
                <td>${foto.titolo}</td>
                <td>${foto.tag}</td>
                <td>${foto.url} €</td>
            </tr>
            `
        });
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
                <td>${foto.titolo}</td>
                <td>${foto.tag}</td>
                <td>${foto.url} €</td>
            </tr>
            `
        });
    }).catch((result) => {
        console.log("test axios.get ERROR", result);
        alert("Errore: row 23 index.js!");
    })
}