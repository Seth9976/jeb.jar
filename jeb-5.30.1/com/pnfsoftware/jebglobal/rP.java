package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class rP implements EntityResolver {
   rP(YF var1) {
      this.q = var1;
   }

   @Override
   public InputSource resolveEntity(String var1, String var2) throws SAXException, IOException {
      return var2.contains("gdb-target.dtd") ? new InputSource(new StringReader("")) : null;
   }
}
