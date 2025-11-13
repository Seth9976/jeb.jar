package com.pnfsoftware.jeb.util.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

class Base64$1 extends ObjectInputStream {
   Base64$1(InputStream var1, ClassLoader var2) throws IOException {
      super(var1);
      this.val$loader = var2;
   }

   @Override
   public Class resolveClass(ObjectStreamClass var1) throws IOException, ClassNotFoundException {
      Class var2 = Class.forName(var1.getName(), false, this.val$loader);
      return var2 == null ? super.resolveClass(var1) : var2;
   }
}
