package software.sigma.principles.exercise4;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

@Log
public class ClassElementOrder {

    private int attempts = 0;
    private static final int MAX_RETRIES = 3;

    public ClassElementOrder(FooService fooService, FooEventService fooEventService) {
        this.fooService = fooService;
        this.fooEventService = fooEventService;
    }

    private void sendSavedEvent(String id) {
        log.info("Send save event for: " +  id );
    }

    private final FooService fooService;
    private final FooEventService fooEventService;


    public void save(Foo foo) {
        log.info("Dummy save method");
        sendSavedEvent(foo.getId());
    }

    public void delete(String id) {
        log.info("Dummy save method");
    }

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_NAME = "EXTRA_NAME";
}

class FooEventService {
}

class FooService {
}

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Foo {
    String id;
}