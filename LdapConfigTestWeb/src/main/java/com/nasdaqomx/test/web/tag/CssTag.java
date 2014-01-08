package com.nasdaqomx.test.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CssTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOGGER = LogFactory.getLog(CssTag.class);

	private String src;

	private String media;

	/**
	 * @param media
	 *            the media to set
	 */
	public void setMedia(String media) {
		this.media = media;
	}

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
		if (-1 != src.indexOf("?")) {
			src += "&v=1";
		} else {
			src += "?v=1";
		}
		writeOut(src);

		return SKIP_BODY;
	}

	private void writeOut(String staticSrc) {
		JspWriter writer = pageContext.getOut();
		String contextPath = pageContext.getServletContext().getContextPath();
		try {
			if (null == media) {
				writer.write("<link href=\"" + contextPath + staticSrc
						+ "?v=1\" rel=\"stylesheet\" type=\"text/css\" />");
			} else {
				writer.write("<link href=\""
						+ contextPath
						+ staticSrc
						+ "?v=1\" rel=\"stylesheet\" type=\"text/css\" media=\""
						+ media + "\"/>");
			}
		} catch (IOException e) {
			LOGGER.error("Failed to render CSS tag for src: " + staticSrc, e);
		}
	}
}
