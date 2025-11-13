package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class AddressConversionLists {
   Map nativeToInter;
   Map interToNative;

   public AddressConversionLists(Map var1, Map var2) {
      if (var1 == null) {
         var1 = new HashMap();
      }

      if (var2 == null) {
         var2 = new HashMap();
      }

      this.nativeToInter = (Map)var1;
      this.interToNative = (Map)var2;
   }

   public void record(long var1, int var3) {
      if (this.nativeToInter.get(var1) == null) {
         this.nativeToInter.put(var1, var3);
      }

      if (this.interToNative.get(var3) == null) {
         this.interToNative.put(var3, var1);
      }
   }

   public Map getNativeToInter() {
      return this.nativeToInter;
   }

   public Integer convertNativeAddress(long var1) {
      return (Integer)this.nativeToInter.get(var1);
   }

   public Map getInterToNative() {
      return this.interToNative;
   }

   public Long convertIntermediateOffset(int var1) {
      return (Long)this.interToNative.get(var1);
   }

   public List getIntermediateOffsetsMappingToNativeAddress(long var1) {
      ArrayList var3 = new ArrayList();

      for (Entry var5 : this.interToNative.entrySet()) {
         if ((Long)var5.getValue() == var1) {
            var3.add((Integer)var5.getKey());
         }
      }

      return var3;
   }

   public List getNativeAddressesMappingToIntermediateOffset(int var1) {
      ArrayList var2 = new ArrayList();

      for (Entry var4 : this.nativeToInter.entrySet()) {
         if ((Integer)var4.getValue() == var1) {
            var2.add((Long)var4.getKey());
         }
      }

      return var2;
   }

   @Override
   public String toString() {
      return formatConversionLists(this.interToNative, this.nativeToInter);
   }

   public static String formatConversionLists(Map var0, Map var1) {
      StringBuilder var2 = new StringBuilder();
      if (var0 != null) {
         var2.append("IR to Native:\n");

         for (Entry var4 : new TreeMap(var0).entrySet()) {
            Strings.ff(var2, "%X->%X\n", var4.getKey(), var4.getValue());
         }
      }

      if (var1 != null) {
         var2.append("Native to IR:\n");

         for (Entry var6 : new TreeMap(var1).entrySet()) {
            Strings.ff(var2, "%X->%X\n", var6.getKey(), var6.getValue());
         }
      }

      return var2.toString();
   }

   public static AddressConversionLists generateFromCFG(CFG var0) {
      if (var0.getFirstAddress() == 0L && var0.getEntryAddress() == 0L) {
         return generate(var0.addressableInstructions());
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static AddressConversionLists generateFromList(List var0) {
      ArrayList var1 = new ArrayList(var0.size());
      int var2 = 0;

      for (IEStatement var4 : var0) {
         var1.add(new AddressableInstruction(var2, var4));
         var2 += var4.getSize();
      }

      return generate(var1);
   }

   private static AddressConversionLists generate(Iterable var0) {
      TreeMap var1 = new TreeMap();
      TreeMap var2 = new TreeMap();

      for (AddressableInstruction var4 : var0) {
         int var5 = (int)var4.getOffset();
         IEStatement var6 = (IEStatement)var4.getInstruction();
         Collection var7 = var6.getLowerLevelAddresses();
         var7.isEmpty();
         int var8 = 0;

         for (Long var10 : var7) {
            if (var8 == 0 && var2.put(var5, var10) != null) {
               throw new RuntimeException();
            }

            Integer var11 = (Integer)var1.put(var10, var5);
            if (var11 != null && var11 < var5) {
               var1.put(var10, var11);
            }

            var8++;
         }

         var6.getSize();
      }

      return new AddressConversionLists(var1, var2);
   }
}
