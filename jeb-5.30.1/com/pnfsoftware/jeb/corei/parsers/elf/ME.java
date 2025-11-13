package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ME {
   private static final ILogger q = GlobalLog.getLogger(ME.class);
   @SerId(1)
   private List RF = new ArrayList();

   public boolean q(long var1) {
      for (qa var4 : this.RF) {
         if (var4.Dw == var1) {
            return true;
         }
      }

      return false;
   }

   public boolean q(qa var1) {
      return this.q(var1, false);
   }

   public boolean q(qa var1, boolean var2) {
      if (this.RF.contains(var1)) {
         return false;
      } else {
         if (var1.xK) {
            for (int var3 = 0; var3 < this.RF.size(); var3++) {
               if (((qa)this.RF.get(var3)).xK) {
                  if (!var2) {
                     q.debug("Cannot register symbol table! A dynsym table is already present, and replacement is forbidden!");
                     return false;
                  }

                  this.RF.set(var3, var1);
                  return true;
               }
            }
         }

         this.RF.add(var1);
         return true;
      }
   }

   public boolean q() {
      return this.RF() != null;
   }

   public qa RF() {
      for (qa var2 : this.RF) {
         if (var2.xK) {
            return var2;
         }
      }

      return null;
   }

   public List xK() {
      return this.RF;
   }

   public qa q(int var1) {
      for (qa var3 : this.RF) {
         if (var3.RF == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("%d tables:\n%s", this.RF.size(), this.RF);
   }
}
