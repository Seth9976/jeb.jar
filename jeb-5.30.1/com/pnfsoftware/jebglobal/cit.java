package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

class cit {
   private static final ILogger xK = GlobalLog.getLogger(cit.class);
   static final int q = 0;
   static final int RF = 1;
   private Map Dw = new TreeMap();

   public cit() {
   }

   public cit.eo q(long var1) {
      return (cit.eo)this.Dw.get(var1);
   }

   public static cit q(cjc var0) throws MemoryException {
      cit var1 = new cit();

      while (var0.zz() > 0) {
         long var2 = var0.lm();
         long var4 = var0.gP();
         if (var4 == 0L) {
            break;
         }

         int var6 = (int)var0.gP();
         Dwarf.DwarfTag var7 = Dwarf.DwarfTag.getByValue(var6);
         int var8 = var0.q();
         cit.eo var9 = new cit.eo(var2, var6, var7, var8 == 1);

         while (true) {
            long var10 = var0.gP();
            long var12 = var0.gP();
            if (var10 == 0L && var12 == 0L) {
               var1.Dw.put(var4, var9);
               break;
            }

            if (var12 == Dwarf.DwarfForm.DW_FORM_implicit_const.getValue()) {
               long var14 = var0.nf();
               var9.Dw.add(new cit.CU(var10, var12, var14));
            } else {
               var9.Dw.add(new cit.CU(var10, var12));
            }
         }
      }

      return var1;
   }

   public void q() {
      for (Entry var2 : this.Dw.entrySet()) {
         this.q((Long)var2.getKey(), (cit.eo)var2.getValue());
      }
   }

   private void q(long var1, cit.eo var3) {
      StringBuilder var4 = new StringBuilder();
      var4.append("<").append(Long.toHexString(var3.q)).append("h, ");
      var4.append(Long.toHexString(var1)).append("h>  ");
      int var5 = var4.length();
      var4.append(var3.xK == null ? Long.toHexString(var3.RF) + "h" : var3.xK.name());
      if (var3.Uv) {
         var4.append(" hasChildren");
      }

      Object[] var10000 = new Object[0];

      for (cit.CU var7 : var3.Dw) {
         var10000 = new Object[]{
            Strings.pad(' ', var5),
            Dwarf.DwarfAttribute.getByValue((int)var7.q()),
            var7.q(),
            Dwarf.DwarfForm.getByValue((int)var7.RF()),
            var7.RF(),
            var7.xK() == null ? "" : " - Value: " + var7.xK()
         };
      }
   }

   public static class CU {
      private long q;
      private long RF;
      private Long xK;

      public CU(long var1, long var3) {
         this(var1, var3, null);
      }

      public CU(long var1, long var3, Long var5) {
         this.q = var1;
         this.RF = var3;
         this.xK = var5;
      }

      public long q() {
         return this.q;
      }

      public long RF() {
         return this.RF;
      }

      public Long xK() {
         return this.xK;
      }
   }

   public static class eo {
      private long q;
      private int RF;
      private Dwarf.DwarfTag xK;
      private List Dw = new ArrayList();
      private boolean Uv;

      public eo(long var1, int var3, Dwarf.DwarfTag var4, boolean var5) {
         this.q = var1;
         this.RF = var3;
         this.xK = var4;
         this.Uv = var5;
      }

      public boolean q() {
         return this.Uv;
      }

      public int RF() {
         return this.RF;
      }

      public Dwarf.DwarfTag xK() {
         return this.xK;
      }

      public List Dw() {
         return this.Dw;
      }

      @Override
      public String toString() {
         return "AbbrevEntry [tag="
            + (this.xK != null ? this.xK.name() : Long.toHexString(this.RF) + "h")
            + ", children="
            + this.Uv
            + ", attributeSize="
            + this.Dw.size()
            + "]";
      }
   }
}
