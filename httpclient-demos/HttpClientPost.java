class HttpCLientPost{

	/** from www.ibm.com/developerWorks*/
	public static void post(String action, String json) {
		
		HttpClient httpClient = new HttpClient();
		String url = "http://localhost/test/" + action;
		PostMethod postMethod = new PostMethod(url);
		// 填入各个表单域的值
		NameValuePair[] data = { new NameValuePair("data", json), };
		System.out.println("params:" + json);
		// 将表单的值放入postMethod中
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.setRequestBody(data);
		
		try {
			// 执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			// HttpClient 对于要求接受后继服务的请求，像post和put等不能自动处理转发
			// 301 或者 302
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header locationHeader = postMethod
						.getResponseHeader("location");
				String location = null;
				if (locationHeader != null) {
					location = locationHeader.getValue();
					System.out.println("The page was redirected to: "
							+ location);
				} else {
					System.err.println("Location field value is null.");
				}
			} else {
				byte[] responseBody = postMethod.getResponseBody();
				System.out.println(postMethod.getResponseCharSet());
				System.out.println(new String(responseBody));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}