package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import models.japidsample.Post;
import japidviews ._tags.*;
import play.mvc.Http.Context.Implicit;
import models.*;
import play.i18n.Lang;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Http.Session;
import play.mvc.Http.Flash;
import japidviews ._layouts.*;
import play.data.validation.Validation;
import java.util.*;
import static cn.bran.japid.util.WebUtils.*;
import controllers.*;
import static cn.bran.japid.util.StringUtils.*;
//
// NOTE: This file was generated from: japidviews/templates/Posts.html
// Change to this file will be lost next time the template file is compiled.
//
public class Posts extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/Posts.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
	}

// - add implicit fields with Play

	final Request request = Implicit.request(); 
	final Response response = Implicit.response(); 
	final Session session = Implicit.session();
	final Flash flash = Implicit.flash();
	final Lang lang = Implicit.lang();
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public Posts() {
		super(null);
	}
	public Posts(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"blogTitle", "allPost",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "List<Post>",  };
	public static final Object[] argDefaults= new Object[] {null,null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.Posts.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String blogTitle; // line 2
	private List<Post> allPost; // line 2
	public cn.bran.japid.template.RenderResult render(String blogTitle,List<Post> allPost) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 2
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
p("\n");// line 2
		for (Post post: allPost) { // line 4
		p("	- title: ");// line 4
		p(post.title);// line 5
		p("\n" + 
"	- date: ");// line 5
		p(post.postedAt);// line 6
		p("\n" + 
"	- author ");// line 6
		p(post.author.name);// line 7
		p(" ");// line 7
		p(post.author.gender);// line 7
		p("\n" + 
"	the real title: 你好\n");// line 7
		}// line 9
		p("\n");// line 9
		
		endDoLayout(sourceTemplate);
	}

}