package interview.tasks.changes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeServiceTest {

    private ChangeService changeService;

    @Before
    public void setUp() {
        changeService = new ChangeService();
    }

    @Test
    public void testUpdate() throws InterruptedException {
        assertEquals(0, changeService.getState());
        assertEquals("initialized", changeService.getStateComment());

        Thread thread1 = new Thread(changeService::update);
        Thread thread2 = new Thread(changeService::update);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertEquals(2, changeService.getState());
        assertEquals("updated: [2] times", changeService.getStateComment());
    }
}