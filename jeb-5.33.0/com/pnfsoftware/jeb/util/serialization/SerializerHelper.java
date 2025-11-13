package com.pnfsoftware.jeb.util.serialization;

import java.io.IOException;
import java.io.OutputStream;

public class SerializerHelper {
   IInternalSerializer out;
   Object object;
   Class c;
   boolean saveStandardDone;
   int flags;

   SerializerHelper(IInternalSerializer var1, Object var2, Class var3, int var4) {
      this.out = var1;
      this.object = var2;
      this.c = var3;
      this.flags = var4;
   }

   SerializerHelper(IInternalSerializer var1, Object var2, Class var3) {
      this(var1, var2, var3, 0);
   }

   public void saveStandard() throws IOException {
      if (this.saveStandardDone) {
         throw new IOException("Fields already written");
      } else {
         this.out.writeFields(this.object, this.c);
         this.saveStandardDone = true;
      }
   }

   public void write(Object var1) throws IOException {
      this.out.write(var1);
   }

   public OutputStream getStream() {
      return this.out.getStream();
   }

   public int getFlags() {
      return this.flags;
   }
}
