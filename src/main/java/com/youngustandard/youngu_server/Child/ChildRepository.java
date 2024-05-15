package com.youngustandard.youngu_server.Child;

import java.util.List;

public interface ChildRepository {
    List<ChildDTO> find_Children(String mbr_id);
}
