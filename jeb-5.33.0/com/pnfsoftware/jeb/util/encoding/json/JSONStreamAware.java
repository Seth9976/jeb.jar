package com.pnfsoftware.jeb.util.encoding.json;

import java.io.IOException;
import java.io.Writer;

public interface JSONStreamAware {
   void writeJSONString(Writer var1) throws IOException;
}
