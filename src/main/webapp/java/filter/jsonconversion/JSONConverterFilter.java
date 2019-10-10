package filter.jsonconversion;

import com.fasterxml.jackson.databind.JsonNode;
import service.JSONService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSONConverterFilter implements Filter {
    private JSONService jsonService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jsonService = new JSONService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("#############\nIN JSON CONVERTER FILTER\n#############");
        String JSONString = jsonService.InputStreamToJSON(request.getInputStream());
        if (JSONString != null) {
            System.out.println(JSONString);
            JsonNode jsonNode = jsonService.JSONStringToNode(JSONString);
            if (jsonNode != null) {
                request.setAttribute("jsonNode", jsonNode);
                System.out.println(jsonNode.toString());
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
