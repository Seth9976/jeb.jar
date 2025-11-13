package com.pnfsoftware.jeb.util.encoding;

import com.pnfsoftware.jeb.util.format.Strings;

public class BadEncodingException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   int offset;

   public BadEncodingException(String var1, int var2) {
      super(var1);
      this.offset = var2;
   }

   public BadEncodingException(String var1, int var2, Throwable var3) {
      super(var1, var3);
      this.offset = var2;
   }

   public int getOffset() {
      return this.offset;
   }

   @Override
   public String getMessage() {
      return Strings.ff("%Xh: %s", this.offset, super.getMessage());
   }
}
