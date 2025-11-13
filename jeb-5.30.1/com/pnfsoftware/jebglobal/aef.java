package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IErrorDescription;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class aef implements IErrorDescription {
   @SerId(1)
   private String q;
   @SerId(2)
   private String RF;
   @SerId(3)
   private String xK;

   public aef(Exception var1) {
      this.q = Throwables.getRootCause(var1).getClass().getSimpleName();
      this.RF = Throwables.getRootCause(var1).getMessage();
      this.xK = Throwables.formatStacktrace(var1);
   }

   @Override
   public String getRootClassname() {
      return this.q;
   }

   @Override
   public String getRootMessage() {
      return this.RF;
   }

   @Override
   public String getStacktrace() {
      return this.xK;
   }
}
