<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Rollen</title>
        <link href="/WEBappMVCMavenSolution/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>Rollen</h2>
        <c:choose>
            <c:when test="${rollen.size() != 0}">
                <!-- Wanneer er gebruikers opgeslagen zijn, worden ze hier getoond -->
                <table class="gebruikers">
                    <tr>
                        <td>
                            <strong>Rol</strong>
                        </td>
                        <td></td>
                    </tr>
                    <c:forEach var="tempRol" items="${rollen}">
                        <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                        <tr>
                            <td>
                                ${tempRol.rolName}
                            </td>
                            <td>
                                <a href="wijzig?id=${tempRol.rolNumber}">Wijzig</a> |
                                <a href="javascript:if(confirm('Weet u het zeker dat u deze rol wilt verwijderen?'))
                                   window.location='verwijder?id=${tempRol.rolNumber}';">Verwijder</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <!-- Als er geen gebruikers zijn, wordt deze melding getoond -->
                Er zijn geen rollen gevonden.
            </c:otherwise>
        </c:choose>
        <p>
            <a href="nieuw">Maak nieuwe rol aan</a>
        </p>
        <p>
            <a href="../index">Terug naar de index</a>
        </p>
    </body>
</html>
