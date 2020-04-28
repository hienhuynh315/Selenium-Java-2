package dashboard.common;

import com.google.inject.Binder;
import com.google.inject.Module;

import dashboard.model.AddNewPageData;
import dashboard.pages.DashBoardGeneralPage;
import dashboard.pages.LoginPage;
import dashboard.pages.MainPage;
import dashboard.pages.OverviewPage;

public class ModuleFactory implements Module {

	@Override
	public void configure(Binder binder) {

// not define 
//		 Bind Page object
		binder.bind(DashBoardGeneralPage.class);
		binder.bind(OverviewPage.class);
		binder.bind(MainPage.class);
		binder.bind(LoginPage.class);
		binder.bind(AddNewPageData.class);
	}
}
