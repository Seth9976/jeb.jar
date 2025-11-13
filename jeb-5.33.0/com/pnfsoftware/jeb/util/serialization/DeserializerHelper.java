package com.pnfsoftware.jeb.util.serialization;

import java.io.IOException;
import java.io.InputStream;

public class DeserializerHelper {
   IInternalDeserializer d;
   Object o;
   Class c;
   int version;
   boolean loadStandardDone;

   DeserializerHelper(IInternalDeserializer var1, Object var2, Class var3, int var4) {
      this.d = var1;
      this.o = var2;
      this.c = var3;
      this.version = var4;
   }

   public void loadStandard() throws IOException {
      if (this.loadStandardDone) {
         throw new IOException("Fields already read");
      } else {
         this.d.restoreFields(this.o, this.c);
         this.loadStandardDone = true;
      }
   }

   public Object read() throws IOException {
      return this.d.read();
   }

   public InputStream getStream() {
      return this.d.getStream();
   }

   public int getSerializedVersion() {
      return this.version;
   }
}
