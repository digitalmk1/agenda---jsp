<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Editar Contatos</title>

<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>

	<h1>Editar Contatos</h1>
	<form name="frmContato" action="select">
		<table>

			<tr>
				<td><input type="text" name="idcon" id="Caixa3" readonly
				 value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>


			<tr>
				<td><input type="text" name="nome" class="Caixa1 "
				value="<%out.print(request.getAttribute("nome"));%>" ></td>
			</tr>

			<tr>
				<td><input type="text" name="fone" class="Caixa2" 
				value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="email" class="Caixa1" 
				value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>

		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="Scripts/validador.js"></script>
</body>

</html>