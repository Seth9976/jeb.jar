package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class cct {
   private static final ILogger pC = GlobalLog.getLogger(cct.class);
   private IDMethodContext A;

   public cct(IDMethodContext var1) {
      this.A = var1;
   }

   public int pC() {
      CFG var1 = this.A.getCfg();
      LinkedHashSet var2 = new LinkedHashSet();
      LinkedHashSet var3 = new LinkedHashSet();

      for (IDInstruction var5 : var1.getInstructions()) {
         var5.visitDepthPre(new ccu(this, var2, var3));
      }

      LinkedHashSet var20 = new LinkedHashSet();
      int var21 = 131072;
      ArrayList var6 = new ArrayList(var2);
      var6.sort(null);

      for (int var8 : var6) {
         if (var8 - var21 >= var6.size() && this.A.retrievePhysicalRegisterId(var8) < 0) {
            break;
         }

         var20.add(var8);
      }

      int var22 = 589824;
      ArrayList var23 = new ArrayList(var3);
      var23.sort(null);

      for (int var10 : var23) {
         if (var10 - var22 >= var23.size() && this.A.retrievePhysicalRegisterId(var10) < 0) {
            break;
         }

         var20.add(var10);
      }

      ArrayList var24 = new ArrayList(var6.size());

      for (int var25 = 0; var25 < var6.size(); var25++) {
         if (!var20.contains(var25 + var21)) {
            var24.add(var25 + var21);
         }
      }

      ArrayList var26 = new ArrayList(var23.size());

      for (int var11 = 0; var11 < var23.size(); var11++) {
         if (!var20.contains(var11 + var22)) {
            var26.add(var11 + var22);
         }
      }

      LinkedHashMap var27 = new LinkedHashMap();
      HashMap var12 = new HashMap();
      int var13 = 0;

      for (int var15 : var2) {
         if (!var20.contains(var15)) {
            int var16 = (Integer)var24.get(var13++);
            var27.put(var15, this.A.getVar(var15));
            var12.put(var15, var16);
         }
      }

      var13 = 0;

      for (int var31 : var3) {
         if (!var20.contains(var31)) {
            int var36 = (Integer)var26.get(var13++);
            var27.put(var31, this.A.getVar(var31));
            var12.put(var31, var36);
         }
      }

      if (var12.isEmpty()) {
         return 0;
      } else {
         bpx var30 = (bpx)this.A;

         for (int var37 : var24) {
            var30.removeVar(var37);
         }

         for (int var38 : var26) {
            var30.removeVar(var38);
         }

         for (int var39 : var12.keySet()) {
            int var17 = (Integer)var12.get(var39);
            IDVar var18 = (IDVar)var27.get(var39);
            IDVar var19 = this.A.createVar(var17, var18.getType(), false);
            Assert.a(var19 != null);
            var19.transferMetadataFrom(var18);
            var19.setPreferredName(var18.getPreferredName());
            var19.setOrigin(var18.getOrigin());
         }

         for (IDInstruction var40 : var1.getInstructions()) {
            var40.visitDepthPost(new ccv(this, var12));
         }

         if (com.pnfsoftware.jeb.corei.parsers.dexdec.Av.A) {
            Object[] var10000 = new Object[]{var1.format()};
         }

         return var12.size();
      }
   }
}
