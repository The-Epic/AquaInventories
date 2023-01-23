package sh.miles.aquainventories;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import sh.miles.aquainvs.AquaInventoriesProvider;

public class AquaInventoriesTest {

    private ServerMock server;
    private AquaInventoriesProvider plugin;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(AquaInventoriesProvider.class);
    }

    @AfterAll
    public void teardown() {
        MockBukkit.unmock();
    }

}
