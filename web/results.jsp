<%-- 
    Document   : results
    Created on : 26-abr-2017, 0:20:56
    Author     : Pau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="bigbox">
            <h1>Resultats de cerca</h1>
            <%-- start web service invocation --%><hr/>
            <%
                java.lang.String result = null;
                try {
                    ws_client.SearchWS_Service service = new ws_client.SearchWS_Service();
                    ws_client.SearchWS port = service.getSearchWSPort();

                    if (request.getParameter("title") != null) {
                        result = port.searchVideoByTitle(request.getParameter("title"));
                    } else {
                        if (request.getParameter("author") != null) {
                            result = port.searchVideoByAuthor(request.getParameter("author"));
                        } else {
                            if (request.getParameter("year") != null) {
                                result = port.searchVideoByYear(request.getParameter("year"));
                            }
                        }
                    }

                    out.println(result);
                } catch (Exception ex) {
                    out.println(ex.getMessage());
                }
            %>
            <%-- end web service invocation --%><hr/>
        </div>

    </body>
</html>
