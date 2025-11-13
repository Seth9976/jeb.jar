package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class bmd {
   @SerId(1)
   private Map q = new HashMap();

   public void q(String var1, Object var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q.put(var1, var2);
      }
   }

   public void q(String var1) {
      this.q.remove(var1);
   }

   public Object RF(String var1) {
      return this.q.get(var1);
   }

   public List q() {
      ArrayList var1 = new ArrayList();

      for (String var3 : this.q.keySet()) {
         var1.add(new bmc(var3, this.q.get(var3)));
      }

      return var1;
   }

   public int RF() {
      return this.q.size();
   }

   public void xK() {
      this.q.clear();
   }
}
