<%--Author:Pedro Almeida--%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<%List<Subject> disciplinas = (List<Subject>) request.getAttribute("disciplinas");%>
<%List<Module> modulos = (List<Module>) request.getAttribute("modulos");%>
<%List<Question> questoesNov = (List<Question>) request.getAttribute("questoesNovas");%>
<%List<Question> questoesVis = (List<Question>) request.getAttribute("questoesVis");%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Resposta Certa</title>
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <img src="imgs/home.jpg" style="max-width: 100%; height: auto">    
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-5 col-md-offset-1">
                    <div class="panel panel-default" style="background-color: #7F7F7F">
                        <div class="panel-heading text-center" style="background-color: black">
                             <h4 style="color: white">Questões Recentes</h4>
                        </div>
                        <div class="panel-body">
                            <% for (Question ques : questoesNov) {%>
                                <p></p>
                                <a href="/RespostaCerta/ControllerServlet?control=Questao&id=<%=ques.getIdQuestao()%>">    
                                    <div class="panel panel-default">    
                                        <div class="panel-body">
                                            <%=ques.getTituloQuestao()%>
                                        </div>
                                    </div>
                                </a>
                            <%}%>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="panel panel-default" style="background-color: #7F7F7F">
                        <div class="panel-heading text-center" style="background-color: black">
                             <h4 style="color: white">Questões mais visualizadas</h4>
                        </div>
                        <div class="panel-body">
                            <% for (Question ques : questoesVis) {%>
                                <p></p>
                                <a href="/RespostaCerta/ControllerServlet?control=Questao&id=<%=ques.getIdQuestao()%>">    
                                    <div class="panel panel-default">    
                                        <div class="panel-body">
                                            <%=ques.getTituloQuestao()%>
                                        </div>
                                    </div>
                                </a>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-5 col-md-offset-1">
                    <div class="panel panel-default" style="background-color: #7F7F7F">
                        <div class="panel-heading text-center" style="background-color: black">
                             <h4 style="color: white">Disciplinas mais visualizadas</h4>
                        </div>
                        <div class="panel-body">
                            <% for (Subject disc : disciplinas) {%>
                                <p></p>
                                <a href="/RespostaCerta/ControllerServlet?control=ModulosDisciplina&id=<%=disc.getIdDominio()%>">    
                                    <div class="panel panel-default">    
                                        <div class="panel-body">
                                            <%=disc.getNomeDominio()%>
                                        </div>
                                    </div>
                                </a>
                            <%}%>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="panel panel-default" style="background-color: #7F7F7F">
                        <div class="panel-heading text-center" style="background-color: black">
                             <h4 style="color: white">Módulos mais visualizados</h4>
                        </div>
                        <div class="panel-body">
                            <% for (Module mod : modulos) {%>
                                <p></p>
                                <a href="/RespostaCerta/ControllerServlet?control=QuestoesModulo&id=<%=mod.getIdModulo()%>">    
                                    <div class="panel panel-default">    
                                        <div class="panel-body">
                                            <%=mod.getNomeModulo()%>
                                        </div>
                                    </div>
                                </a>
                            <%}%>
                        </div>
                    </div>
                </div>        
            </div>
        </div>
        <link href="css/Home.css" rel="stylesheet">
    </body>
</html>

                