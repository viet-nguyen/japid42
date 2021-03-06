package cn.bran.play;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.Map;

import play.mvc.Result;
import play.api.mvc.SimpleResult;

import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Results.Status;
import cn.bran.japid.template.RenderResult;

/**
 * class for use to indicate that the result has been flushed to the response
 * result
 * 
 * The content extraction from the RenderResult is postponed until the apply()
 * if eval() is not called before apply. The eval() will make the JapidResult
 * render the content eagerly and once, therefore any nested cache will effect
 * once. stage so that JapidResult can be cached and still retain dynamic
 * feature of a RenderResultPartial
 
 * @author bran
 * 
 */
public class JapidResult implements Result,  Externalizable {
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CACHE_CONTROL = "Cache-Control";

	private RenderResult renderResult;
	private Map<String, String> headers = new HashMap<String, String>();
	private boolean eager = false;

	String resultContent = "";

	// public JapidResult(String contentType) {
	// super();
	// this.contentType = contentType;
	// }
	//
	// public JapidResult(String contentType2, String string) {
	// this.contentType = contentType2;
	// this.content = string;
	// }

	public JapidResult(RenderResult r) {
		this.renderResult = r;
		this.setHeaders(r.getHeaders());
       
	}

	public JapidResult() {
	}


	/**
	 * extract content now and once. Eager evaluation of RenderResult
	 */
	public JapidResult eval() {
		this.eager = true;
		this.resultContent = extractContent();
		return this;
	}

	/**
	 * @param r
	 */
	public String extractContent() {
		String content = "";
		StringBuilder sb = renderResult.getContent();
		if (sb != null)
			content = sb.toString();
		return content;
	}

//	public void apply(Request request, Response response) {
//		String content = resultContent;
//
//		if (!eager)
//			// late evaluation
//			content = extractContent();
//
//		if (content != null)
//			try {
//				Response.current().out.write(content.getBytes("UTF-8"));
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//
//		Map<String, Header> resHeaders = response.headers;
//
//		if (headers != null) {
//			for (String h : headers.keySet()) {
//				String value = headers.get(h);
//				if (CONTENT_TYPE.equals(h)) {
//					setContentTypeIfNotSet(response, value);
//				} else {
//					if (resHeaders.containsKey(h)) {
//						// shall I override it?
//						// override it. Consider the value in templates are
//						// meant to override
//						response.setHeader(h, value);
//					} else {
//						response.setHeader(h, value);
//					}
//				}
//			}
//		}
//	}

	public RenderResult getRenderResult() {
		return renderResult;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(renderResult);
		out.writeObject(getHeaders());
		out.writeBoolean(eager);
		out.writeUTF(resultContent);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		renderResult = (RenderResult) in.readObject();
		setHeaders((Map<String, String>) in.readObject());
		eager = in.readBoolean();
		resultContent = in.readUTF();
	}

    final private play.api.mvc.Result wrappedResult = null;


    public play.api.mvc.Result getWrappedResult() {
		String content = resultContent;

		if (!eager)
			// late evaluation
			content = extractContent();
    	Status re = new play.mvc.Results.Status(play.core.j.JavaResults.Status(200), content,  play.api.mvc.Codec.javaSupported("utf-8"));
        return re.getWrappedResult();
    }

	/**
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	private void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

}
