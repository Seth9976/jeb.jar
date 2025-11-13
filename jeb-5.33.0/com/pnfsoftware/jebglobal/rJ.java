package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class rJ implements ISegment {
   long pC;
   ICodeObjectUnit A;
   String kS;

   public rJ(long var1, ICodeObjectUnit var3, String var4) {
      this.pC = var1;
      this.A = var3;
      this.kS = var4;
   }

   public Long pC() {
      return this.pC;
   }

   public Long A() {
      return this.pC + this.A.getLoaderInformation().getImageSize();
   }

   public ICodeObjectUnit kS() {
      return this.A;
   }
}
