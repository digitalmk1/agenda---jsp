/**
 * @Autor Márcio Silva de Oliveira
 */

function validar(){


let nome = frmContato.nome.value
let fone = frmContato.fone.value

if (nome === "") {
	alert('O campo NOME preenchimento obrigatorio')
	frmContato.nome.focus()
	return false;
	
} else if (fone ==="") {
	alert('O campo FONE preenchimento obrigatorio')
	 frmContato.fone.focus()
	 return false;
}else{
	document.forms["frmContato"].submit();
	}
}