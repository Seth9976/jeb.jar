package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.objects.SerEnumSet;
import java.util.Arrays;

public class ctj {
   private SerEnumSet q = new SerEnumSet();

   public ctj(ctj.eo... var1) {
      this.q.addAll(Arrays.asList(var1));
   }

   public void q(ctj.eo var1) {
      this.q.add((Enum)var1);
   }

   public boolean RF(ctj.eo var1) {
      return this.q.remove(var1);
   }

   public boolean xK(ctj.eo var1) {
      return this.q.contains(var1);
   }

   @Override
   public String toString() {
      return "Features:" + this.q.toString();
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm;
   }
}
