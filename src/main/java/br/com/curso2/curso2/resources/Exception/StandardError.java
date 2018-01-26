package br.com.curso2.curso2.resources.Exception;

import java.io.Serializable;

public class StandardError implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	public StandardError(int status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}
	private int status;
	private String msg;
	private Long timestamp;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
