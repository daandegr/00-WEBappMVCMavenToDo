<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${paginaTitel}</title>
        <link href="/WEBappMVCMavenSolution/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>${paginaTitel}</h2>
        <c:if test="${foutMelding != null}">
            <!-- Mochten er foutmeldingen zijn, dan worden ze hier getoond -->
            <p>${foutMelding}</p>
        </c:if>
        <c:choose>
            <c:when test="${id == null}">
                <!-- Als er geen id is meegegeven, ga je een gebruiker toevoegen -->
                <form id="nieuweGebruiker" action="nieuw" method="post">
            </c:when>
            <c:otherwise>
                <!-- Anders ga je een gebruiker wijzigen -->
                <form id="wijzigenGebruiker" action="wijzig" method="post">
                </c:otherwise>
            </c:choose>
            <p>
                <c:if test="${id != null}">
                    <!-- Het id wordt meegestuurd, om te bepalen welke gebruiker je gaat wijzigen -->
                    <input type="hidden" name="id" id="id" value="${id}"></input>
                </c:if>
                <table border="0">
                    <tr>
                        <td>
                            <label for="naam">Naam</label>
                        </td>
                        <td>
                            <input type="textfield" id="naam" name="naam" value="${naam}"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="straatnaam">Straatnaam</label>
                        </td>
                        <td>
                            <input type="textfield" id="straatnaam" name="straatnaam" value="${adres}"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="plaats">Plaats</label>
                        </td>
                        <td>
                            <input type="textfield" id="plaats" name="plaats" value="${plaats}"></input>
                        </td>
                     </tr>
                    <tr>   
                        <td>
                            <label for="huisnummer">Huisnummer</label>
                        </td>
                        <td>
                            <input type="textfield" id="plaats" name="huisNummer" value="${huisNummer}"></input>
                        </td>
                    </tr>
                    <tr>   
                        <td>
                            <label for="rol">Rol</label>
                        </td>
                        <td>
                            <select name="rol">
                                <c:forEach var="tempRollen" items="${rollen}">
                                    <option value=${tempRollen.rolNumber}>${tempRollen.rolName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </p>
            <p>
                <input class="submit" type="submit" value="Verzenden">
            </p>
        </form>
    </body>
</html>