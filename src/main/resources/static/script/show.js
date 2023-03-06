console.log("SHOW OK");
const URLParams = new URLSearchParams(window.location.search);
const fotoId = URLParams.get("id");
console.log("foto ID: ", fotoId);

axios.get(`http://localhost:8080/api/${fotoId}`).then((result) => {
    console.log("test axios.get OK", result);
    document.querySelector("#id").innerHTML = result.data.id;
    document.querySelector("#titolo").innerHTML = result.data.titolo;
    document.querySelector("#tag").innerHTML = result.data.tag;
    document.querySelector("#url").innerHTML = result.data.url;
}).catch((result) => {
    console.log("Errore row 11 show.js", result);
    alert("Errore");
})