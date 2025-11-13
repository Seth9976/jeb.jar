package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class alg {
   public Map q;
   public MultiMap RF;
   public Map xK;

   alg(Map var1, MultiMap var2, Map var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("SWITCHES:%s,LOOPS:%s,IFS:%s", this.q.values(), this.RF.values(), this.xK.values());
   }
}
