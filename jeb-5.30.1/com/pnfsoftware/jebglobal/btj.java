package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class btj {
   public MultiMap q;
   public Map RF;
   public Map xK;

   public btj(MultiMap var1, Map var2, Map var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public List q() {
      ArrayList var1 = new ArrayList();

      for (int var3 : this.q.keySet()) {
         var1.addAll(this.q.get(var3));
      }

      return var1;
   }

   public List RF() {
      return new ArrayList(this.RF.values());
   }

   public List xK() {
      return new ArrayList(this.xK.values());
   }

   @Override
   public String toString() {
      return Strings.ff("LOOPS:%s,IFS:%s,SWITCHES:%s", this.q.values(), this.RF.values(), this.xK.values());
   }
}
