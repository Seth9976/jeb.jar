package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class oP extends AbstractInstructionOperandGeneric {
   @SerId(1)
   boolean pC;
   @SerId(2)
   String A;
   @SerId(3)
   String kS;
   @SerId(4)
   int wS;
   @SerId(5)
   List UT = new ArrayList();
   @SerId(6)
   List E;

   public oP(int var1, int var2, long var3) {
      super(var1, var2, var3);
   }

   public static oP pC(int var0, oP var1) {
      Assert.a(var0 >= 1 && var1 != null);
      oP var2 = new oP(4098, 0, var0);
      var2.E = new ArrayList(1);
      var2.E.add(var1);
      return var2;
   }

   public static oP pC(List var0) {
      Assert.a(!var0.isEmpty());
      oP var1 = new oP(4099, 0, 0L);
      var1.E = var0;
      return var1;
   }

   public static oP pC(int var0, oP var1, List var2) {
      Assert.a(var0 >= 1 && var1 != null && !var2.isEmpty());
      oP var3 = new oP(4099, 0, var0);
      var3.E = new ArrayList(1 + var2.size());
      var3.E.add(var1);
      var3.E.addAll(var2);
      return var3;
   }

   public int pC() {
      return (int)this.value;
   }

   public oP A() {
      return (oP)this.E.get(0);
   }

   public List kS() {
      return this.pC() >= 1 ? this.E.subList(1, this.E.size()) : this.E;
   }

   public static oP pC(String var0, String var1, long var2) {
      oP var4 = new oP(4096, 0, var2);
      var4.A = var0;
      var4.kS = var1;
      return var4;
   }

   public void pC(long var1) {
      this.value = var1;
   }

   @Override
   public String getRegisterName(long var1) {
      try {
         return vi.A.getDescriptionEntryById(var1).getName();
      } catch (Exception var3) {
         return super.getRegisterName(var1);
      }
   }
}
