package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;

@Ser
public class iZ {
   @SerId(1)
   String q;
   @SerId(2)
   qV RF;
   @SerId(3)
   Nt xK;
   @SerId(4)
   Map Dw;

   iZ() {
   }

   public String q() {
      return this.q;
   }

   public qV RF() {
      return this.RF;
   }

   public Nt xK() {
      return this.xK;
   }
}
