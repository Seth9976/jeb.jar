package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class aje {
   public Map pC;
   public MultiMap A;
   public Map kS;

   aje(Map var1, MultiMap var2, Map var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("SWITCHES:%s,LOOPS:%s,IFS:%s", this.pC.values(), this.A.values(), this.kS.values());
   }
}
