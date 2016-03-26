package calltree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CallTreeElement {

	private StackTraceElement call;
	private String callPos;
	private List<CallTreeElement> childCalls;

	public CallTreeElement() {
	}

	public CallTreeElement(StackTraceElement call, String callPos) {
		this.call = call;
		this.callPos = callPos;
	}

	public void addChildCalls(List<StackTraceElement> callList, String callPos) {

		if (!callList.isEmpty()) {

			StackTraceElement element = popElement(callList);

			if (childCalls == null) {
				childCalls = new ArrayList<CallTreeElement>();
			}

			CallTreeElement newElement = new CallTreeElement(element, callPos);

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
				newElement.addChildCalls(callList, p);

			} else {

				previousCall.addChildCalls(callList, p);
			}
		}
	}

	public void printCallTree(String s, int charCount) {

		if (call != null && call.getFileName() != null) {
			for (int i = 1; i <= charCount; i++) {
				System.out.print(s);
			}
			System.out.print(
					call.getClassName().split(Pattern.quote("."))[call.getClassName().split(Pattern.quote(".")).length
							- 1] + "." + call.getMethodName() + "()");
			if (callPos != null) {
				System.out.print(" - " + callPos);
			}
			System.out.println();
			charCount++;
		}

		if (childCalls != null) {
			for (CallTreeElement e : childCalls) {
				e.printCallTree(s, charCount);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {

		CallTreeElement cte = (CallTreeElement) obj;
		return (callPos == null && cte.callPos == null) || callPos.equals(cte.callPos);
	}

	@Override
	public int hashCode() {
		return callPos.length();
	}

	private StackTraceElement popElement(List<StackTraceElement> list) {
		return list.remove(list.size() - 1);
	}
}