
package model

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by BurtonGuster on 11/7/16.
 */

class LocationTest extends GroovyTestCase {

    Location loc;

    @Before
    public void setUp() {
        super.setUp()
        loc = new Location("Marietta, GA", "Test Location", true);
    }
}
