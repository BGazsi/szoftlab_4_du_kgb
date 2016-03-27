package calltree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CallTreeElement {

	private int priority;
	private StackTraceElement call;
	private String callPos;
	private List<CallTreeElement> childCalls;

	public CallTreeElement() {
	}

	private CallTreeElement(int priority, StackTraceElement call, String callPos) {
		this.priority = priority;
		this.call = call;
		this.callPos = callPos;
	}

	public void addChildCalls(List<StackTraceElement> callList, String callPos, int priority) {

		if (!callList.isEmpty()) {

			StackTraceElement element = popElement(callList);

			if (childCalls == null) {
				childCalls = new ArrayList<CallTreeElement>();
			}

			CallTreeElement newElement = new CallTreeElement(priority, element, callPos);

			CallTreeElement previousCall = null;
			for (CallTreeElement e : childCalls) {
				if (e.equals(newElement)) {
					previousCall = e;
					break;
				}
			}

			String p = "(" + newElement.call.getFileName() + ":" + newElement.call.getLineNumber() + ")";
			if (previousCall == null) {

				childCalls.add(newElement);
				newElement.addChildCalls(callList, p, priority);

			} else {

				previousCall.priority = newElement.priority;
				previousCall.addChildCalls(callList, p, priority);
			}
		}
	}

	public void printCallTree(int priority, String s, int charCount, String arrow) {

		if (call != null && call.getFileName() != null && this.priority <= priority) {
			for (int i = 1; i <= charCount; i++) {
				System.out.print(s);
			}
			System.out
					.print(arrow
							+ call.getClassName()
									.split(Pattern.quote("."))[call.getClassName().split(Pattern.quote(".")).length - 1]
							+ "." + call.getMethodName() + "()");
			if (callPos != null) {
				System.out.print(" - " + callPos);
			}
			System.out.println();
			charCount++;
		}

		if (childCalls != null) {
			for (CallTreeElement e : childCalls) {
				e.printCallTree(priority, s, charCount, arrow);
			}
		}
	}

	public List<CallTreeElement> getChildCalls() {

		if (call == null) {
			return childCalls.get(0).childCalls;
		} else {
			return childCalls;
		}
	}

	@Override
	public boolean equals(Object obj) {

		CallTreeElement cte = (CallTreeElement) obj;
		return (callPos == null && cte.callPos == null)
				|| (callPos.equals(cte.callPos) && call.getMethodName().equals(cte.call.getMethodName()));
	}

	@Override
	public int hashCode() {
		return callPos.length();
	}

	private StackTraceElement popElement(List<StackTraceElement> list) {
		return list.remove(list.size() - 1);
	}
}