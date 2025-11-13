package com.pnfsoftware.jeb.core.units.code.asm.type;

public class IllegalTypeNameException extends NativeTypeException {
   private static final long serialVersionUID = 1L;
   private String typename;

   public IllegalTypeNameException(String var1, String var2) {
      super(var1 + ": " + var2);
      this.typename = var2;
   }

   public String getIllegalTypename() {
      return this.typename;
   }
}
