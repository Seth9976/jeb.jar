package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class bpg {
   public MultiMap pC;
   public Map A;
   public Map kS;

   public bpg(MultiMap var1, Map var2, Map var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("LOOPS:%s,IFS:%s,SWITCHES:%s", this.pC.values(), this.A.values(), this.kS.values());
   }
}
