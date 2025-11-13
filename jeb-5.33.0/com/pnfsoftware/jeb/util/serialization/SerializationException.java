package com.pnfsoftware.jeb.util.serialization;

import java.io.IOException;

public class SerializationException extends IOException {
   private static final long serialVersionUID = 1L;
   private String classname;

   public SerializationException(String var1) {
      super(var1);
   }

   public SerializationException(String var1, Class var2) {
      super(var1);
      this.classname = var2 == null ? null : var2.getName();
   }

   public SerializationException(Throwable var1) {
      super(var1);
   }

   public SerializationException(Throwable var1, Class var2) {
      super(var1);
      this.classname = var2 == null ? null : var2.getName();
   }

   public SerializationException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public SerializationException(String var1, Throwable var2, Class var3) {
      super(var1, var2);
      this.classname = var3 == null ? null : var3.getName();
   }

   public String getClassName() {
      return this.classname;
   }
}
