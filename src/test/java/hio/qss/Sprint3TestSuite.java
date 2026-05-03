
package hio.qss;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({Feature7_PieRuleButton.class, Feature8_UnoccupiedCellPlacementOnly.class,
    Feature9_WinningChain.class, StressTests.class})
public class Sprint3TestSuite {}
