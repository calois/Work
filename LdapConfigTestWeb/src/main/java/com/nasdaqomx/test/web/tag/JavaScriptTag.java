package com.nasdaqomx.test.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JavaScriptTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOGGER = LogFactory.getLog(JavaScriptTag.class);

	private String src;

	/**
	 * @param src
	 *            the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();
		if (!src.startsWith("/")) {
			src = "/" + src;
			if (-1 != src.indexOf("?")) {
				src += "&v=1";
			} else {
				src += "?v=1";
			}
		}
		try {
			writer.write("<script src=\""
					+ pageContext.getServletContext().getContextPath() + src
					+ "\" type=\"text/javascript\"></script>");
		} catch (IOException e) {
			LOGGER.error("Failed to render JavaScript tag for src: " + src, e);
		}
		return SKIP_BODY;
	}
}
