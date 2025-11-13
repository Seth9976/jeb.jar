package com.pnfsoftware.jeb.util.encoding.json.parser;

import java.util.List;
import java.util.Map;

public interface ContainerFactory {
   Map createObjectContainer();

   List creatArrayContainer();
}
