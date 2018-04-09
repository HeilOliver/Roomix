package at.fhv.roomix.persist.factory;

import at.fhv.roomix.persist.factory.model.TestDomain;
import at.fhv.roomix.persist.factory.model.TestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainBuilderTest {

    private TestEntity reuseableEntity;
    private TestDomain reuseableDomain;
    private AbstractDomainBuilderMock testBuilder;

    @BeforeEach
    void initTest() {
        reuseableEntity = new TestEntity();
        reuseableDomain = new TestDomain();
        testBuilder = AbstractDomainBuilderMock.getInstance();
    }

    /**
     * Test mapping from entity to domain
     */
    @Test
    void testOneWayMapping() {
        TestDomain resultDomain = testBuilder.injectEntity(reuseableEntity);
        // compare all fields of resultDomain with reuseableEntity
        assertEquals(reuseableEntity.hashCode(), resultDomain.hashCode());
    }

    /**
     * Test mapping from domain to entity
     */
    @Test
    void testReverseMapping() {
        int testInteger = 1;
        TestDomain tempDomain = testBuilder.injectEntity(reuseableEntity);
        tempDomain.setPrimitiveInteger(testInteger);
        TestEntity resultEntity = testBuilder.injectDomain(tempDomain);
        assertEquals(tempDomain.hashCode(), resultEntity.hashCode());
    }

    /**
     * Test mapping for empty variable
     */
    @Test
    void testInvalidVarMapping() {
        TestEntity resultEntity = testBuilder.injectDomain(reuseableDomain);
        assertEquals(0, resultEntity.getInvalidInteger());
    }
}
