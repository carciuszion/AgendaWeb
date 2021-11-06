<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> listaContatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
//teste de recebimento da lista
//for(int i = 0; i<listaContatos.size();i++){
//	out.println(listaContatos.get(i).getNome() + listaContatos.get(i).getEmail()+"<br>");
//}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/fone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a class="Botao1" href="NovoContato.html">Novo contato</a>
	<a class="Botao3" href="report">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < listaContatos.size(); i++) {
			%>
			<tr>
				<td><%=listaContatos.get(i).getIdcon()%></td>
				<td><%=listaContatos.get(i).getNome()%></td>
				<td><%=listaContatos.get(i).getFone()%></td>
				<td><%=listaContatos.get(i).getEmail()%></td>
				<td><a class="Botao1"
					href="select?idcon=<%=listaContatos.get(i).getIdcon()%>">Editar</a>
					<a
					href="javascript:confirmar(<%=listaContatos.get(i).getIdcon()%>,
			    							   '<%=listaContatos.get(i).getNome()%>')"
					class="Botao2">Excluir</a></td>
			</tr>

			<%
			}
			%>
		</tbody>

	</table>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>