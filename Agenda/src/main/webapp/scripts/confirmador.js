/**
 * No coments...
 */

function confirmar(idcon, nome){
	let resposta = confirm("Confirma a exclusão do contato " + nome + "?")
	if (resposta===true){
		window.location.href="delete?idcon=" + idcon
	} 
}	