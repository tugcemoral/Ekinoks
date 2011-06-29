package tr.edu.metu.ceng.teien.parametermap;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MessagingInfo {

	private int messagingInfoID;

	private int senderID;

	private int receiverID;

	// @Transient
	private String receiverType;

	private Message message;

	private boolean isRead;

	private boolean inInbox;

	private boolean inOutbox;
	
	
	public MessagingInfo(int messagingInfoID, int senderID, int receiverID,
			String receiverType, Message message, boolean isRead,
			boolean inInbox, boolean inOutbox) {
		super();
		this.messagingInfoID = messagingInfoID;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.receiverType = receiverType;
		this.message = message;
		this.isRead = isRead;
		this.inInbox = inInbox;
		this.inOutbox = inOutbox;
	}

	public int getMessagingInfoID() {
		return messagingInfoID;
	}

	public void setMessagingInfoID(int messagingInfoID) {
		this.messagingInfoID = messagingInfoID;
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}

	public Message getMessage() {
		if (message == null) {
			message = new Message();
		}
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isInInbox() {
		return inInbox;
	}

	public void setInInbox(boolean inInbox) {
		this.inInbox = inInbox;
	}

	public boolean isInOutbox() {
		return inOutbox;
	}

	public void setInOutbox(boolean inOutbox) {
		this.inOutbox = inOutbox;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public MessagingInfo duplicateWOReceiverID(Integer userID) {
		MessagingInfo clone = null;
		try {
			clone = (MessagingInfo) this.clone();
			clone.setReceiverID(userID);
		} catch (CloneNotSupportedException e) {
			// this should not be occur.
			System.err.print(e.getMessage());
		}
		return clone;
	}

	public HashMap<String, Object> getParameterMap() {
		// create a new hashmap...
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();

		try {
			// add all fields to it...
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.get(this).getClass().equals(getMessage().getClass())) {
					parameterMap.put("messageID", getMessage()
							.getParameterMap().get("messageID"));
				} else {
					parameterMap.put(field.getName(), field.get(this));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return parameterMap;
	}

}
