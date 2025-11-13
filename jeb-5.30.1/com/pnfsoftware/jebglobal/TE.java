package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class TE implements ISegment {
   long q;
   ICodeObjectUnit RF;
   String xK;

   public TE(long var1, ICodeObjectUnit var3, String var4) {
      this.q = var1;
      this.RF = var3;
      this.xK = var4;
   }

   public Long q() {
      return this.q;
   }

   public Long RF() {
      return this.q + this.RF.getLoaderInformation().getImageSize();
   }

   public ICodeObjectUnit xK() {
      return this.RF;
   }

   public String Dw() {
      return this.xK;
   }
}
