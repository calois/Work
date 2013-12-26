package com.nasdaqomx.ldapconfig.test.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UrlTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOGGER = LogFactory.getLog(UrlTag.class);

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
		if (!src.startsWith("/")) {
			src = "/" + src;
		}
		writeOut(src);

		return SKIP_BODY;
	}

	private void writeOut(String src) {
		JspWriter writer = pageContext.getOut();
		try {
			writer.write(pageContext.getServletContext().getContextPath() + src);
		} catch (IOException e) {
			LOGGER.error("Failed to render URL tag for src: " + src, e);
		}
	}
}
