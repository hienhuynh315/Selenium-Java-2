package controls.customize;

import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.IButton;
import com.logigear.control.common.ILabel;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Label;

public class MultiSelectBox extends Clickable {

	public MultiSelectBox(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public void select(String text) {
		IButton selectButton = new Button(
				locator + "//a[@data-qtip='Add to Selected']");
		ILabel dynamicAvailableLabel = new Label(locator
				+ "//a[@data-qtip='Add to Selected']/../../../preceding-sibling::div//li[contains(@class,'x-boundlist-item')][text()='%s']");
		dynamicAvailableLabel.setDynamicValue(text);
		dynamicAvailableLabel.click();
		selectButton.click();
	}

	public void deSelect(String text) {
		IButton selectButton = new Button(
				locator + "//a[@data-qtip='Remove from Selected']");
		ILabel dynamicAvailableLabel = new Label(locator
				+ "//a[@data-qtip='Remove from Selected']/../../../following-sibling::div//li[contains(@class,'x-boundlist-item')][text()='%s']");
		dynamicAvailableLabel.setDynamicValue(text);
		dynamicAvailableLabel.click();
		selectButton.click();
	}
}
