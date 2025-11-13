package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Ser
public class Xg {
   @SerId(1)
   long q;
   @SerId(2)
   List RF = new ArrayList();
   @SerId(3)
   CFG xK;
   @SerId(4)
   Set Dw;
   @SerId(5)
   boolean Uv;

   public Xg(long var1) {
      this.q = var1;
   }

   public CFG q() {
      return this.xK;
   }

   public CFG RF() {
      ArrayList var1 = new ArrayList();
      Object var2 = new ArrayList();

      for (kR var4 : this.RF) {
         if (var4.q == this.q) {
            var2 = var4.xK;
            var1.add(new BasicBlock(var4.q, var4.RF, (List)(var2.size() == 1 ? var2 : new ArrayList()), new ArrayList(), false));
         }
      }

      if (var2.size() == 1) {
         for (kR var6 : this.RF) {
            if (var6.q == (Long)var2.get(0)) {
               var1.add(new BasicBlock(var6.q, var6.RF, new ArrayList(), new ArrayList(), false));
            }
         }
      }

      return new CFG(this.q, var1);
   }

   public int xK() {
      int var1 = 0;

      for (kR var3 : this.RF) {
         var1 += (int)(var3.getEndAddress() - var3.getFirstAddress());
      }

      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("%Xh(%d)", this.q, this.RF.size());
   }
}
