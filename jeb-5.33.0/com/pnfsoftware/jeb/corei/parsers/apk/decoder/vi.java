package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Up;
import com.pnfsoftware.jebglobal.WD;
import com.pnfsoftware.jebglobal.cS;
import com.pnfsoftware.jebglobal.hH;
import com.pnfsoftware.jebglobal.od;
import com.pnfsoftware.jebglobal.tH;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

public class vi {
   private static final ILogger pC = GlobalLog.getLogger(vi.class);
   private WD A;
   private GK kS;
   private Map wS;
   private Map UT;
   private int E;

   public vi(WD var1) {
      this.A = var1;
   }

   public GK pC() {
      if (this.kS == null) {
         throw new IllegalStateException();
      } else {
         return this.kS;
      }
   }

   public GK A() {
      if (this.kS != null) {
         throw new IllegalStateException();
      } else {
         this.kS = new GK(this.A);
         this.wS = this.kS.E;
         this.UT = this.kS.sY;
         this.kS.A = this.A.pC();

         for (hH var2 : this.A.A()) {
            this.pC(var2);
         }

         return this.kS;
      }
   }

   private void pC(hH var1) {
      int var2 = var1.E;
      this.kS.kS.add(var2);
      LinkedHashMap var3 = new LinkedHashMap();
      LinkedHashMap var4 = new LinkedHashMap();
      int var5 = 0;

      for (tH var7 : var1.WR) {
         if (var7 instanceof Up) {
            int var8 = ((Up)var7).E;
            if (var3.put(var8, (Up)var7) != null) {
               throw new RuntimeException("duplicate typeSpec: " + var8);
            }

            var4.put(var8, new ArrayList());
            var5++;
         }
      }

      for (tH var28 : var1.WR) {
         if (var28 instanceof od) {
            int var31 = ((od)var28).pC();
            if (!var4.containsKey(var31)) {
               throw new RuntimeException("no typeSpec: " + var31);
            }

            ((List)var4.get(var31)).add((od)var28);
         }
      }

      ArrayList var27 = new ArrayList();

      for (int var29 = 0; var29 < var1.pC(); var29++) {
         var27.add(var1.pC(var29));
      }

      this.kS.wS.put(var2, var27);
      Object[] var10000 = new Object[]{var2, var5, var27.size()};
      TreeSet var30 = new TreeSet();
      this.kS.UT.put(var2, var30);
      int var32 = 0;
      int var9 = 0;

      for (Up var11 : var3.values()) {
         int var12 = var11.E;
         String var13 = var1.oT.pC(var12 - 1);
         LinkedHashMap var14 = new LinkedHashMap();

         for (od var16 : (List)var4.get(var12)) {
            for (cS var18 : var16.fI) {
               if (var18 != null) {
                  Integer var19 = (Integer)var14.get(var18.UT);
                  if (var19 == null) {
                     var14.put(var18.UT, var18.pC);
                  } else if (var19 != var18.pC) {
                     pC.warn(
                        S.L(
                           "Inconsistency: the type entry at index 0x%x was given another key (name) in a previously processed config: originally set to \"%s\" (0x%x),  now want \"%s\" (0x%x) [current config: %s]; keeping the original name and sanitizing the entry"
                        ),
                        var18.UT,
                        var1.fI.pC(var19),
                        var19,
                        var1.fI.pC(var18.pC),
                        var18.pC,
                        var16.ys.pC()
                     );
                     var18.pC = var19;
                  }
               }
            }
         }

         for (Entry var35 : var14.entrySet()) {
            int var37 = (Integer)var35.getKey();
            int var39 = 0;
            if (var37 >= 0 && var37 < var11.sY.length) {
               var39 = var11.sY[var37];
            } else {
               pC.warn("Key OOB: %d!", var37);
            }

            Integer var41 = (Integer)var35.getValue();
            if (var41 != null) {
               int var20 = var2 << 24 | var12 << 16 | var37;
               String var21 = var1.fI.pC(var41);
               boolean var22 = false;
               int var23 = var2 << 24 | var12 << 16;
               Object var24 = (Set)this.UT.get(var23);
               if (var24 == null) {
                  var24 = new HashSet();
                  this.UT.put(var23, var24);
               }

               if (!var24.add(var21)) {
                  var21 = Strings.ff("ResId_0x%08x", var20);
                  var22 = true;
               }

               if (Strings.isBlank(var21)) {
                  var21 = Strings.ff("ResId_0x%08x", var20);
                  var22 = true;
               }

               String var25 = Strings.ff("%s:%s/%s", var1.sY, var13, var21);
               this.pC(var20, var25, var21, var39, var22);
            }
         }

         for (od var36 : (List)var4.get(var12)) {
            String var38 = var36.ys.pC(true);
            var30.add(var38);

            for (cS var42 : var36.fI) {
               if (var42 != null) {
                  int var43 = var42.UT;
                  int var44 = var2 << 24 | var12 << 16 | var43;
                  cS var45 = this.pC(var44, var38, var42, 2);
                  if (var45 != null) {
                     var9++;
                     byte var46 = 40;
                     if (var2 == 1) {
                        var46 = 10;
                     }

                     if (++this.E <= 5) {
                        if (this.E == 5) {
                           pC.log(var46, false, S.L("More resources have multiple entries for a given configuration"));
                        } else {
                           pC.log(var46, false, S.L("Resource id 0x%x has multiple entries for configuration '%s': %s/%s"), var44, var38, var13, var45.ys);
                        }
                     }
                  }

                  var32++;
               }
            }
         }
      }

      if (var9 > 0) {
         if (var2 == 1) {
            pC.debug(S.L("Collision count when assigning resource values for the Android Framework: %d/%d"), var9, var32);
         } else {
            pC.warn(S.L("Collision count when assigning resource values for package 0x%X: %d/%d"), var2, var9, var32);
         }
      }
   }

   private void pC(int var1, String var2, String var3, int var4, boolean var5) {
      p var6 = new p(var1, var2, var3, var4);
      var6.wS = var5;
      if (this.wS.put(var1, var6) != null) {
         throw new IllegalStateException(Strings.ff("Resource id 0x%x already exists", var1));
      }
   }

   private cS pC(int var1, String var2, cS var3, int var4) {
      var3.E = var1;
      var3.sY = var2;
      p var5 = (p)this.wS.get(var1);
      var3.ys = var5.kS;
      cS var6 = (cS)var5.E.get(var2);
      if (var6 == null) {
         var5.E.put(var2, var3);
         return null;
      } else if (var4 == 0) {
         throw new IllegalStateException(Strings.ff("Resource id 0x%x already has an entry for configuration \"%s\"", var1, Strings.safe2(var2, "default")));
      } else {
         if (var4 == 1) {
            var5.E.put(var2, var3);
         }

         return var6;
      }
   }
}
