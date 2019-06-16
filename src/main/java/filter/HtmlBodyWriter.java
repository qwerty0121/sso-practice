package filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import model.ViewAndModel;

@Provider
@Produces(MediaType.TEXT_HTML)
public class HtmlBodyWriter implements MessageBodyWriter<ViewAndModel>{

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@Context
	private ServletContext servletContext;

	@Inject
	private TemplateEngine templateEngine;

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return ViewAndModel.class.equals(type);
	}

	@Override
	public void writeTo(ViewAndModel t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		response.setCharacterEncoding("UTF-8");

		Map<String, Object> param = new HashMap<>();
		param.put("model", t.getModel());

		WebContext wc = new WebContext(request, response, servletContext, request.getLocale(), param);
		Writer writer = new OutputStreamWriter(entityStream);
		templateEngine.process(t.getView(), wc, writer);
	}

}
