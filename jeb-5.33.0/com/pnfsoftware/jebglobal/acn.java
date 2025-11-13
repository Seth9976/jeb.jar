package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IErrorDescription;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class acn implements IErrorDescription {
   @SerId(1)
   private String pC;
   @SerId(2)
   private String A;
   @SerId(3)
   private String kS;

   public acn(Exception var1) {
      this.pC = Throwables.getRootCause(var1).getClass().getSimpleName();
      this.A = Throwables.getRootCause(var1).getMessage();
      this.kS = Throwables.formatStacktrace(var1);
   }

   @Override
   public String getRootClassname() {
      return this.pC;
   }

   @Override
   public String getRootMessage() {
      return this.A;
   }

   @Override
   public String getStacktrace() {
      return this.kS;
   }
}
