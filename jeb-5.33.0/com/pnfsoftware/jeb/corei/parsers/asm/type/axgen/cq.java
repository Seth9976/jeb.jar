package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;

@Ser
public class cq {
   @SerId(1)
   String pC;
   @SerId(2)
   zp A;
   @SerId(3)
   bO kS;
   @SerId(4)
   Map wS;

   cq() {
   }

   public zp pC() {
      return this.A;
   }

   public bO A() {
      return this.kS;
   }
}
