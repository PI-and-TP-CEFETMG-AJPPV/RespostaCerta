
<%@page import="br.cefetmg.respostaCerta.model.domain.User"%>
<%@page import="br.cefetmg.respostaCerta.model.service.UserManagementImpl"%>
<%@page import="br.cefetmg.respostaCerta.model.service.UserManagement"%>
<%@page import="br.cefetmg.respostaCerta.model.dao.UserDAOImpl"%>
<%@page import="br.cefetmg.respostaCerta.model.dao.UserDAOImpl"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/Menu.css" rel="stylesheet">
<% Boolean falhouLogin = (Boolean) request.getAttribute("falha");
   if(falhouLogin==null){
       falhouLogin=false;
   }
%>
<div class="container-fluid">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <%
            User usuario = null;
                if (request.getSession().getAttribute("usuario") != null) {
                    UserManagement management = new UserManagementImpl(new UserDAOImpl());
                    usuario = management.getUserById((Long)request.getSession().getAttribute("usuario"));
                    
            %>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#menu-toggle" id="menu-toggle"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/RespostaCerta/ControllerServlet?control=Logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Sair</a></li>
                </ul>
                <form class="navbar-form navbar-right" action="/RespostaCerta/ControllerServlet?control=Busca" method="POST">
                    <div class="input-group">
                        <select class="form-control" name="opcao">
                            <option>Quest�o</option>
                            <option>M�dulo</option>
                            <option>Dom�nio</option>
                        </select>
                        <div class="input-group-btn">
                            <input type="text" class="form-control" placeholder="Pesquisar..." id="query" name="query" value="">
                        </div>
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                </form>
            </div>
            <% } else {
            %>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a id="logar" href="#modalLogin" data-toggle="modal">Logar</a></li>
                    <li><a href="/RespostaCerta/ControllerServlet?control=PagCadastrar">Cadastrar</a></li>
                </ul>
                <form class="navbar-form navbar-right" action="/RespostaCerta/ControllerServlet?control=Busca" method="POST">
                    <div class="input-group">
                        <select class="form-control" name="opcao">
                            <option>Quest�o</option>
                            <option>M�dulo</option>
                            <option>Disciplina</option>
                        </select>
                        <div class="input-group-btn">
                            <input type="text" class="form-control" placeholder="Pesquisar..." id="query" name="query" value="">
                        </div>
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                </form>
            </div>
            <% } %>
        </div>
    </nav>
    <% if (request.getSession().getAttribute("usuario") != null) { %>
    <div id="wrapper" class="toggled">
        <div class="container-fluid">
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <br><br><br>
                    <img src="/RespostaCerta/ImageServlet?tipo=user&id=<%=usuario.getIdUsuario()%>" alt="..." width="200" height="200">`
                    <li class="sidebar-brand"><a href="#" class="navbar-brand" style="color:white"><%=usuario.getNomeUsuario()%></a></li>
                    <% if(usuario.getIdtUsuario()!='E'){%>
                    <li><a href="/RespostaCerta/">P�gina Inicial</a></li>
                    <li><a href="/RespostaCerta/ControllerServlet?control=Desempenho">Desempenho</a></li>
                    <%}else{%>
                    <p>Cadastro de Professor em An�lise</p>
                    <%}%>
                    <% if(usuario.getIdtUsuario()=='P' || usuario.getIdtUsuario()=='G'){%>
                    <li><a href="/RespostaCerta/ControllerServlet?control=PagCadastrarQuestao">Cadastrar Quest�o</a></li>
                    <li><a href="/RespostaCerta/ControllerServlet?control=PagMinhasQuestoes">Minhas Quest�es</a></li>
                    <% } %>
                    <% if(usuario.getIdtUsuario()=='G'){%>
                    <li><a href="/RespostaCerta/ControllerServlet?control=PagGerenciarCadastro">Gerenciamento de Cadastros</a></li>
                    <%}%>
                </ul>
            </div>
        </div>
        <%} else {%>
        <div id="modalLogin" class="modal fade role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Logar</h4>
                    </div>
                    <div class="modal-body">
                        <form id="fazerLogin" action="/RespostaCerta/ControllerServlet?control=Login" method="POST">
                            <div class="form-group">
                                <label for="emailUsuario">E-mail</label>
                                <input class="form-control" placeholder="Digite o seu e-mail" required type="email" name="emailUsuario" id="emailUsuario">
                            </div>
                            <div class="form-group">
                                <label for="senhaUsuario">Senha</label>
                                <input class="form-control" type="password" name="senhaUsuario" required id="senhaUsuario">
                            </div>
                            <%if(falhouLogin){%>
                            <div class="form-group" style="color:red;">
                                <p>E-mail ou senha incorreto</p>
                            </div>
                            <%}%>
                            <button class="btn btn-default" type="submit">Login</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
        <% }%>
        <br>
        <br>
        <br>
        <!--Aqui vem a parte interna-->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/Menu.js"></script>
        <%if(falhouLogin){%>
        <script>$('#logar').click();</script>
        <%}%>
