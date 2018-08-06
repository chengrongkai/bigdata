package com.esharex.bigdata.utils;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	/**操作成功**/
	public final static  Integer RESULT_CODE_SUCCESS = 0;
	/**操作失败**/
	public final static  Integer RESULT_CODE_FAIL = 1;
	/**程序错误**/
	public final static  Integer RESULT_CODE_ERROR = 500;
	/**程序错误  提示信息**/
	public final static  String RESULT_CODE_ERROR_MSG ="服务器异常，请稍后再试！";
	public R() {
		put("code", 0);
		put("msg", "操作成功");
	}

	public static R error() {
		return error(1, "操作失败");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("code", 0);
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		r.put("code", 0);
		return r;
	}
	
	public static R ok(int code,String msg,Object result) {
		R r = new R();
		r.put("code", code);
		r.put("msg",msg);
		r.put("result", result);
		return r;
	}
	public static R ok(Object result) {
		R r = new R();
		r.put("code", 0);
		r.put("msg","请求成功");
		r.put("result", result);
		return r;
	}
	public static R ok(String msg,Object result) {
		R r = new R();
		r.put("msg",msg);
		r.put("result", result);
		r.put("code", 0);
		return r;
	}
	public static R ok() {
		R r = new R();
		r.put("code", 0);
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
