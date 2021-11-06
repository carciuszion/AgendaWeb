<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" href="imagens/fone.png">
<link rel="stylesheet" href="style.css">
<title>Editar Contatos</title>
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input id="Caixa3" type="text" value="<%=request.getAttribute("idcon")%>" name="idcon" readonly="readonly"
				placeholder="Id"></td>
			</tr>

			<tr>
				<td><input class="Caixa1" type="text" value="<%=request.getAttribute("nome")%>" name="nome" 
				placeholder="Nome"></td>
			</tr>
			<tr>
				<td><input class="Caixa2" type="text" value="<%=request.getAttribute("fone")%>" name="fone"
					placeholder="Fone"></td>
			</tr>
			<tr>
				<td><input class="Caixa1" type="text" value="<%=request.getAttribute("email")%>" name="email"
					placeholder="E-mail"></td>
			</tr>
		</table>
		<input class="Botao1" type="button" value="Salvar"
			onclick="validar()">
	</form>
	<script type="text/javascript" src="scripts/validador.js"></script>
</body>
</html>