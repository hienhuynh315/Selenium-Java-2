package utils.config;

import com.google.inject.Binder;
import com.google.inject.Module;

import data.dashboard.example.ExampleData;
import pages.dashboard.example.DashBoardGeneralPage;
import pages.dashboard.example.OverviewPage;

public class ModuleFactory implements Module {

	@Override
	public void configure(Binder binder) {

		// Bind BC test data
		binder.bind(ExampleData.class);

		// Bind Page object
		binder.bind(DashBoardGeneralPage.class);
		binder.bind(OverviewPage.class);
	}
}
