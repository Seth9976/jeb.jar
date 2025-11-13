package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class vX {
   @SerId(1)
   private List q = new ArrayList();

   public boolean q(long var1) {
      for (CI var4 : this.q) {
         if (var4.Uv == var1) {
            return true;
         }
      }

      return false;
   }

   public void q(CI var1) {
      if (!this.q.contains(var1)) {
         this.q.add(var1);
      }
   }

   public List q() {
      return this.q;
   }

   public CI q(int var1) {
      for (CI var3 : this.q) {
         if (var3.RF == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("%d tables:\n%s", this.q.size(), this.q);
   }
}
