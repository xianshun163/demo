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
//    	System.setProperty("http.proxySet", "true"); 
//    	System.setProperty("http.proxyHost", "127.0.0.1"); 
//    	System.setProperty("http.proxyPort", "30101");
    	String quoteString = "mesher test";
//    	RestTemplate restT = new RestTemplate();
//    	 
//    	quoteString = restT.getForObject("http://app.huawei.com/hwa/configresource/js/general/ha.js", String.class);
//		System.out.println(quoteString);
//         
//    	quoteString = restT.getForObject("http://cpro.baidustatic.com/cpro/ui/noexpire/js/4.0.1/adClosefeedbackUpgrade.min.js", String.class);
//		System.out.println(quoteString);
//		for(int i=0;i<3;i++){
//			System.out.println("--------test--2---"+i);
//		}
        return "I am available "+quoteString;
    }
    @RequestMapping(value = "/consumer")
    public String consumer( @RequestParam(value = "name", required = true) String name,@RequestParam(value = "port", required = true) String port) {
//    	System.setProperty("http.proxySet", "true"); 
//    	System.setProperty("http.proxyHost", "127.0.0.1"); 
//    	System.setProperty("http.proxyPort", "30101");
    	
    	String quoteString = "mesher";
    	RestTemplate restT = new RestTemplate();
    	String url;
    	if (port.isEmpty()){
    		url = "http://"+name+"/available";
    	}else{
    		url = "http://"+name+":"+port+"/available";
    	}
    	
    	SimpleClientHttpRequestFactory reqfac = new SimpleClientHttpRequestFactory();
    	System.out.println("ip:"+proxyIp+"port:"+proxyPort);
    	reqfac.setProxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort) )) );  
    	restT.setRequestFactory(reqfac);  
    	
    	System.out.println(url);
    	quoteString = restT.getForObject(url, String.class);

        return "the provider return: "+quoteString;
    }
    @RequestMapping(value = "/hello")
    public String hello() {
    	
    	String quoteString = "mesher test";
    	RestTemplate restT = new RestTemplate();
    	SimpleClientHttpRequestFactory reqfac = new SimpleClientHttpRequestFactory();
    	System.out.println("ip:"+proxyIp+"port:"+proxyPort);
    	reqfac.setProxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort) )) );  
    	restT.setRequestFactory(reqfac);  
    	System.out.println(proxyIp+","+proxyPort);
//    	quoteString = restT.getForObject("http://provider:8080/hello/xianshun", String.class);
//		System.out.println(quoteString);
         
    	return "the provider return: "+quoteString;
    }
    
    
    
    
    @RequestMapping(value = "/checked-out")
    public String checkedOut() {
        return "Spring Boot in Action";
    }
    
    @RequestMapping(value = "/token")
    public String token() {
    	
    	String token ="MIIXPQYJKoZIhvcNAQcCoIIXLjCCFyoCAQExDTALBglghkgBZQMEAgEwghWLBgkqhkiG9w0BBwGgghV8BIIVeHsidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMTktMDEtMTBUMDY6MzM6NDYuNzgxMDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiY2F0YWxvZyI6W10sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6ImUyZDc1NDI5NTQ5MDQ0ZDJiMzEzZmJkZDExNTIwZjdkIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2dwdV9wMnYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9laXBfaXB2NiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2ZhY2VfbGFiZWwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jZXNfYWd0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfRE1TX2Nvbm5lY3RvciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Jtc19ocGNfaDJsYXJnZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfdjEwMCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3JvbWFfZmRpIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYWlzX29jcl9iYW5rX2NhcmQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnNfcWFib3QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnNfcWkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kd3NfcG9jIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JzX2JvdGxhYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VycyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2lvdC10cmlhbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2xpdmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9mY3NfQmlvdGVjaCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc3F1aWNrZGVwbG95IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcnRzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcmRzX3NxbHNlcnZlcjIwMTciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92Y2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbXMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hcHBfY29uc2lzdGVuY3kiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ETVNfa2Fma2FfcHVibGljX2FjY2VzcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZjcCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RwcCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZjciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19yZWN5Y2xlYmluIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbmxwX2x1X3NhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2xvdWRpZGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9DQkhfUHJlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX2Z1bmN0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYmF0Y2giLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9maW5lX2dyYWluZWQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9tNm10IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3JldHlwZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FhZF9mcmVlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcmRzX3BnOTQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF90aWt2IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbmxwX2xnX3RzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc2ZzdHVyYm8iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3RhZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9odl92ZW5kb3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTRiIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2hpMyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3JvbWFfbGluayIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTRkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2dwdV9wNCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Jkc2kzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdGFzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JzX3Rhc2tfc2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTRmIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbWNwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHNzX2NncyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2JrbGxkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGJzc19mcmVldHJhaWwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2VfaXN0aW8iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF93cyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19oMSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Nkd2FuIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYWlzX3R0cyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2lvdDAxIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2Rpc2tpbnRlbnNpdmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLWVhc3QtMmQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9haXNfYXNyX3NlbnRlbmNlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbmxwX25scGYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92Z3ZhcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rsc19wcmVkaWN0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXBfYWNjZWxlcmF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZHNzX21vbnRoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdWZzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2NlX3Rlc3QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kZWNfbW9udGhfdXNlciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZpcF9iYW5kd2lkdGgiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9WSVMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9haXNfb2NyX2JhcmNvZGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hcGlleHBsb3JlciIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RsdiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZnaXZzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYmFuZHdpZHRoX3BhY2thZ2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kYnNzX2F1ZGl0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2xvdWRfY29ubmVjdCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NycyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2xsZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2l2c2NzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaXB2Nl9kdWFsc3RhY2siLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9MVFMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wZzExIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZ2F0ZWRfZWNzX3JlY3ljbGViaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zZHJzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGNzX2RjczIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92Z3dzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaW92LXRyaWFsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfbGVnYWN5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaGlsZW5zIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY21jIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3Bvb2xfY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX0NOLVNPVVRILTMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfc3BvdCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX01vZGVsYXJ0cyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX25scF9sdV90YyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2JzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZ3NzX2ZyZWVfdHJpYWwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kZHNfaHdJbnN0YW5jZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Fpc19vY3JfcXJfY29kZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rjc19kY3MyX3JlZGlzNSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rjc19pbWRnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZmNzX3BheSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY2VwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fYXBwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NvIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX2RldmljZV9kaXJlY3QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9yZHNfY3JlYXRlR1JJbnMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfdmdwdV9nNSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Fpc192Y20iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jbG91ZHRlc3RfcHRfaHdJbnN0YW5jZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Fpc19vY3JfcGxhdGVfbnVtYmVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2NlX3dpbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3VsYl9taWl0X3Rlc3QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9PQlNfZmlsZV9wcm90b2NvbCIsImlkIjoiMCJ9XSwicHJvamVjdCI6eyJkb21haW4iOnsibmFtZSI6Imh3c3RhZmZfbDAwMzg3NzMyIiwiaWQiOiI4YWFlMzU2MzNlYmE0MWFkOTFkYzU4YjYyYzlmMTZiZCJ9LCJuYW1lIjoiY24tbm9ydGgtNSIsImlkIjoiODg4ODZmZWUwMjNkNDA5ZWI2OWZlOWRhNjk4ZjhkZjcifSwiaXNzdWVkX2F0IjoiMjAxOS0wMS0wOVQwNjozMzo0Ni43ODEwMDBaIiwidXNlciI6eyJkb21haW4iOnsibmFtZSI6Imh3c3RhZmZfbDAwMzg3NzMyIiwiaWQiOiI4YWFlMzU2MzNlYmE0MWFkOTFkYzU4YjYyYzlmMTZiZCJ9LCJuYW1lIjoiaHdzdGFmZl9sMDAzODc3MzIiLCJpZCI6ImZlNWJkZDBjZDkzYzQxNDI5OTdjYTlhZDk2MmUzZGEzIn19fTGCAYUwggGBAgEBMFwwVzELMAkGA1UEBhMCVVMxDjAMBgNVBAgMBVVuc2V0MQ4wDAYDVQQHDAVVbnNldDEOMAwGA1UECgwFVW5zZXQxGDAWBgNVBAMMD3d3dy5leGFtcGxlLmNvbQIBATALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEAWwPlsv0c4hE51eK7EToexsSyV2ENII0WvWD+cAgazbCZSck4kbZVOQ-y84RDNni6vXLxgnTRCJSqL4xFZMfDC6bBqjsoCp-9mhYhCGoaDom9iYHzsPVUpM1FKw3zZt2UJeOy0ONKegdqGLrDN3bk4ZJMmnEHvXxK1hfHEFvn9ZG0imi9eV8IAOd4ycHmlIL+FvB3ce2243R6KJgiN1ivKoUJmjovaIeCGCjhM+ASLffrXbAcuu7CjzZm4NDbfaw0GVwD00KMzdUlQv8ZqvjZ3q0ERYSlBwhPhrA5I7BmM4r6IF8POcHTOsiwlxEOL-UwOXC5H7Pqs3fOV6E6BakHCw==";
    	
    	RestTemplate restT = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json;charset=utf8");
        requestHeaders.add("X-Auth-Token", token);
        requestHeaders.add("X-Subject-Token", token);
        
        SimpleClientHttpRequestFactory reqfac = new SimpleClientHttpRequestFactory();
    	reqfac.setProxy(new Proxy(Type.HTTP, new InetSocketAddress("127.0.0.1", 3128 )) );  
    	restT.setRequestFactory(reqfac);  
    	
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = restT.exchange("https://iam.cn-north-5.myhuaweicloud.com:443/v3/auth/tokens", HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();
        System.out.println("hello");
        return sttr;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
	@Override
	public void setEnvironment(Environment env) {
		// TODO Auto-generated method stub
		String proxy = env.getProperty("HTTP_PROXY");
		if(proxy != null){
			proxyIp = proxy.split(":")[0];
			proxyPort = proxy.split(":")[1];
			
		}
	}
}
