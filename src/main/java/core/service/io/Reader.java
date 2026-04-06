package core.service.io;

import java.util.List;

public interface Reader {
    List<String> readFile(String fileName);
}
