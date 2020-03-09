package controls.customize;

import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.ILabel;
import com.logigear.control.common.imp.Label;

public class SelectBox extends Clickable {

	public SelectBox(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public void select(String item) {
		ILabel dynamicItem = new Label(locator + "//li[text()='%s']");
		dynamicItem.setDynamicValue(item);
		dynamicItem.click();
	}
}
