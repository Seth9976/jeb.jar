package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kW {
   pF q;
   Endianness RF;
   Map xK = new HashMap();

   kW(pF var1, Endianness var2, Map var3) {
      if (var1 == null) {
         throw new NullPointerException("Null registers layout");
      } else {
         this.q = var1;
         if (var2 == null) {
            throw new NullPointerException("Null endianness");
         } else {
            this.RF = var2;
            if (var3 != null) {
               this.xK.putAll(var3);
            }
         }
      }
   }

   kW(pF var1, Endianness var2) {
      this(var1, var2, null);
   }

   public pF q() {
      return this.q;
   }

   public Endianness RF() {
      return this.RF;
   }

   public Collection xK() {
      return this.q.xK();
   }

   public int Dw() {
      return this.xK.size();
   }

   public void q(Map var1) {
      this.xK.putAll(var1);
   }

   public boolean q(byte[] var1, int[] var2) {
      int var3 = 0;
      int var4 = 0;

      for (RegisterDescriptionEntry var6 : this.q.getDescriptionEntries()) {
         int var7 = var6.getOffset();
         int var8 = var7 + (var6.getBitsize() + 7) / 8;
         if (var8 <= var1.length) {
            if (this.q(var6.getNumber(), Arrays.copyOfRange(var1, var7, var8))) {
               var3++;
            } else {
               var4++;
            }
         }
      }

      if (var2 != null) {
         if (var2.length != 2) {
            throw new RuntimeException();
         }

         var2[0] = var3;
         var2[1] = var4;
      }

      return var4 == 0;
   }

   public boolean q(int var1, byte[] var2) {
      if (var1 >= 0 && var2 != null) {
         this.xK.put(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   public List Uv() {
      ArrayList var1 = new ArrayList(this.xK.size());

      for (int var3 : this.xK.keySet()) {
         var1.add(new Couple(this.q.getDescriptionEntry(var3), (byte[])this.xK.get(var3)));
      }

      return var1;
   }

   public byte[] q(int var1) {
      return (byte[])this.xK.get(var1);
   }

   private Long q(RegisterDescriptionEntry var1) {
      if (var1 == null) {
         return null;
      } else {
         byte[] var2 = (byte[])this.xK.get(var1.getNumber());
         if (var2 != null && var2.length * 8 >= var1.getBitsize()) {
            switch (var1.getBitsize()) {
               case 8:
                  return var2[0] & 255L;
               case 16:
                  return EndianUtil.bytesToShort(var2, 0, this.RF.toByteOrder()) & 65535L;
               case 32:
                  return EndianUtil.bytesToInt(var2, 0, this.RF.toByteOrder()) & 4294967295L;
               case 64:
                  return EndianUtil.bytesToLong(var2, 0, this.RF.toByteOrder());
               default:
                  return null;
            }
         } else {
            return null;
         }
      }
   }

   public Long RF(int var1) {
      RegisterDescriptionEntry var2 = this.q.getDescriptionEntry(var1);
      return this.q(var2);
   }

   public long xK(int var1) {
      Long var2 = this.RF(var1);
      return var2 == null ? 0L : var2;
   }

   public Long q(String var1) {
      RegisterDescriptionEntry var2 = this.q.getDescriptionEntryByName(var1);
      return this.q(var2);
   }

   public long RF(String var1) {
      Long var2 = this.q(var1);
      return var2 == null ? 0L : var2;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (int var4 : this.xK()) {
         if (var2 > 0 && var2 % 4 == 0) {
            var1.append("\n");
         }

         byte[] var5 = this.q(var4);
         RegisterDescriptionEntry var6 = this.q().getDescriptionEntry(var4);
         if (var5 == null) {
            Strings.ff(var1, "%s:%s ", var6.getName(), Strings.generate('?', (var6.getBitsize() + 3) / 4));
         } else {
            Strings.ff(var1, "%s:%s ", var6.getName(), RegisterUtil.byteArrayToHex(this.RF, var5));
         }

         var2++;
      }

      return var1.toString();
   }
}
