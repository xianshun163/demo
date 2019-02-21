package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

@RestController
@SpringBootApplication
public class BookApplication  implements EnvironmentAware {

	private String proxyIp;
	private String proxyPort;

    @RequestMapping(value = "/available")
    public String available() {
    	String quoteString = "mesher test";
        return "I am available "+quoteString;
    }

    @RequestMapping(value = "/consumer")
    public String consumer( @RequestParam(value = "name", required = true) String name) {
//    	System.setProperty("http.proxySet", "true");
//    	System.setProperty("http.proxyHost", "127.0.0.1");
//    	System.setProperty("http.proxyPort", "30101");

    	String quoteString = "mesher";
    	RestTemplate restT = new RestTemplate();
    	String url;
    	url = "http://"+name+"/available";
    	

    	SimpleClientHttpRequestFactory reqfac = new SimpleClientHttpRequestFactory();
    	System.out.println("ip:"+proxyIp+"port:"+proxyPort);
    	reqfac.setProxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort) )) );
    	restT.setRequestFactory(reqfac);

    	System.out.println(url);
    	quoteString = restT.getForObject(url, String.class);

        return "the provider return: "+quoteString;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
	@Override
	public void setEnvironment(Environment env) {
		// TODO Auto-generated method stub
		String proxy = env.getProperty("http_proxy");
		if(proxy != null){
			proxyIp = proxy.split(":")[0];
			proxyPort = proxy.split(":")[1];

		}
	}
}

