package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.IVariable;
import com.pnfsoftware.jeb.util.collect.Bitmap;
import java.util.HashMap;
import java.util.Map;

class VarLocations {
   IVariable var;
   Map touched = new HashMap();

   public VarLocations(IVariable var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.var = var1;
      }
   }

   public IVariable getVariable() {
      return this.var;
   }

   public Map getLocations() {
      return this.touched;
   }

   public void record(Number var1, int var2) {
      Bitmap var3 = (Bitmap)this.touched.get(var1);
      if (var3 == null) {
         var3 = new Bitmap(this.var.getBitsize());
         this.touched.put(var1, var3);
      }

      var3.set(this.var.getBitFromId(var2), true);
   }
}
