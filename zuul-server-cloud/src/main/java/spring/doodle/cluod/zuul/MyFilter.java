package spring.doodle.cluod.zuul;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyFilter extends ZuulFilter {
	private static Logger log = Logger.getLogger(MyFilter.class.getName());
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).toLanguageTag();
        if (locale.equals("en-US") || locale.equals("es") || locale.equals("de")) {
        	log.info("Support pass for locale: " + locale);
        } else {
        	log.info("Locale not support: " + locale);
        	ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("Locale not support: " + locale);
            }catch (Exception e){}
        }
        
		return null;
	}

	@Override
	public String filterType() {
//		pre; before route
//		routing: during route
//		post: after route
//		error: when error happens
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
