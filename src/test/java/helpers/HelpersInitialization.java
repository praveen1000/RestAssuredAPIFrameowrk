package helpers;

import stepDefinitions.TemporaryStore;

public interface HelpersInitialization {

    Utils util = new Utils();
    TestDataBuild data = new TestDataBuild();
    TemporaryStore store = TemporaryStore.getInstance();
}
