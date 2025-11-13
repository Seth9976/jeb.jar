package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bmg implements bsy {
   int q;
   int RF;
   List xK = new ArrayList();
   boolean Dw;

   public bmg(int var1, int var2) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else if (var2 < var1) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   @Override
   public int xK() {
      return -1;
   }

   @Override
   public void q(Map var1) {
      throw new RuntimeException();
   }

   public List Dw() {
      return this.xK;
   }

   public bll Uv() {
      return this.xK.isEmpty() ? null : (bll)this.xK.get(this.xK.size() - 1);
   }

   public int oW() {
      if (this.xK.isEmpty()) {
         return this.RF;
      } else {
         bll var1 = (bll)this.xK.get(this.xK.size() - 1);
         return var1.RF == -1 ? var1.q : var1.RF;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "try=(%d..%d) catches=[", this.q, this.RF);
      int var2 = 0;

      for (bll var4 : this.xK) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.append(var4.toString());
      }

      var1.append("]");
      return var1.toString();
   }
}
