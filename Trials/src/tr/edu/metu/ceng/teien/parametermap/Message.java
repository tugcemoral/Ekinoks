package tr.edu.metu.ceng.teien.parametermap;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Message {

	private int messageID;

	private String subject;

	private String content;

	private String messageDate;

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String dateStr) {
		this.messageDate = dateStr;
	}

	public HashMap<String, Object> getParameterMap() {
		// create a new hashmap...
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();

		try {
			// add all fields to it...
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				parameterMap.put(field.getName(), field.get(this));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return parameterMap;
	}

}
