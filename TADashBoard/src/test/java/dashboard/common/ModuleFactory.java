package dashboard.common;

import com.google.inject.Binder;
import com.google.inject.Module;

import dashboard.pages.DashBoardGeneralPage;
import dashboard.pages.OverviewPage;

public class ModuleFactory implements Module {

	@Override
	public void configure(Binder binder) {


//		 Bind Page object
		binder.bind(DashBoardGeneralPage.class);
		binder.bind(OverviewPage.class);
	}
}
