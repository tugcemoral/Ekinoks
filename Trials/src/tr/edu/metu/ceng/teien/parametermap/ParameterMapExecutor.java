package tr.edu.metu.ceng.teien.parametermap;

public class ParameterMapExecutor {

	public static void main(String[] args) {
		Message message = new Message();

		message.setContent("The Content");
		message.setSubject("Subject");
		message.setMessageDate("Its date!");
		message.setMessageID(1);

		MessagingInfo info = new MessagingInfo(2, 1, 3, "user", message, false,
				true, true);

		System.out.println(info.getParameterMap());
	}

}
