package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.JF;
import com.pnfsoftware.jebglobal.Kd;
import com.pnfsoftware.jebglobal.ZC;
import com.pnfsoftware.jebglobal.jA;
import com.pnfsoftware.jebglobal.ta;
import com.pnfsoftware.jebglobal.tt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

public class bK {
   private static final ILogger q = GlobalLog.getLogger(bK.class);
   private Kd RF;
   private Nz xK;
   private Map Dw;
   private Map Uv;

   public bK(Kd var1) {
      this.RF = var1;
   }

   public Kd q() {
      return this.RF;
   }

   public Nz RF() {
      if (this.xK == null) {
         throw new IllegalStateException();
      } else {
         return this.xK;
      }
   }

   public Nz xK() {
      if (this.xK != null) {
         throw new IllegalStateException();
      } else {
         this.xK = new Nz(this.RF);
         this.Dw = this.xK.oW;
         this.Uv = this.xK.gO;
         this.xK.RF = this.RF.q();

         for (tt var2 : this.RF.RF()) {
            this.q(var2);
         }

         return this.xK;
      }
   }

   private void q(tt var1) {
      int var2 = var1.oW;
      this.xK.xK.add(var2);
      LinkedHashMap var3 = new LinkedHashMap();
      LinkedHashMap var4 = new LinkedHashMap();
      int var5 = 0;

      for (jA var7 : var1.JY) {
         if (var7 instanceof ZC) {
            int var8 = ((ZC)var7).gP;
            if (var3.put(var8, (ZC)var7) != null) {
               throw new RuntimeException("duplicate typeSpec: " + var8);
            }

            var4.put(var8, new ArrayList());
            var5++;
         }
      }

      for (jA var28 : var1.JY) {
         if (var28 instanceof JF) {
            int var31 = ((JF)var28).q();
            if (!var4.containsKey(var31)) {
               throw new RuntimeException("no typeSpec: " + var31);
            }

            ((List)var4.get(var31)).add((JF)var28);
         }
      }

      ArrayList var27 = new ArrayList();

      for (int var29 = 0; var29 < var1.q(); var29++) {
         var27.add(var1.q(var29));
      }

      this.xK.Dw.put(var2, var27);
      Object[] var10000 = new Object[]{var2, var5, var27.size()};
      TreeSet var30 = new TreeSet();
      this.xK.Uv.put(var2, var30);
      int var32 = 0;
      int var9 = 0;

      for (ZC var11 : var3.values()) {
         int var12 = var11.gP;
         String var13 = var1.lm.q(var12 - 1);
         LinkedHashMap var14 = new LinkedHashMap();

         for (JF var16 : (List)var4.get(var12)) {
            for (ta var18 : var16.LK) {
               if (var18 != null) {
                  Integer var19 = (Integer)var14.get(var18.za);
                  if (var19 == null) {
                     var14.put(var18.za, var18.oW);
                  } else if (var19 != var18.oW) {
                     q.warn(
                        S.L(
                           "Inconsistency: the type entry at index 0x%x was given another key (name) in a previously processed config: originally set to \"%s\" (0x%x),  now want \"%s\" (0x%x) [current config: %s]; keeping the original name and sanitizing the entry"
                        ),
                        var18.za,
                        var1.zz.q(var19),
                        var19,
                        var1.zz.q(var18.oW),
                        var18.oW,
                        var16.lm.q()
                     );
                     var18.oW = var19;
                  }
               }
            }
         }

         for (Entry var35 : var14.entrySet()) {
            int var37 = (Integer)var35.getKey();
            int var39 = 0;
            if (var37 >= 0 && var37 < var11.za.length) {
               var39 = var11.za[var37];
            } else {
               q.warn("Key OOB: %d!", var37);
            }

            Integer var41 = (Integer)var35.getValue();
            if (var41 != null) {
               int var20 = var2 << 24 | var12 << 16 | var37;
               String var21 = var1.zz.q(var41);
               boolean var22 = false;
               int var23 = var2 << 24 | var12 << 16;
               Object var24 = (Set)this.Uv.get(var23);
               if (var24 == null) {
                  var24 = new HashSet();
                  this.Uv.put(var23, var24);
               }

               if (!var24.add(var21)) {
                  var21 = Strings.ff("ResId_0x%08x", var20);
                  var22 = true;
               }

               if (Strings.isBlank(var21)) {
                  var21 = Strings.ff("ResId_0x%08x", var20);
                  var22 = true;
               }

               String var25 = Strings.ff("%s:%s/%s", var1.gO, var13, var21);
               this.q(var20, var25, var21, var39, var22);
            }
         }

         for (JF var36 : (List)var4.get(var12)) {
            String var38 = var36.lm.q(true);
            var30.add(var38);

            for (ta var42 : var36.LK) {
               if (var42 != null) {
                  int var43 = var42.za;
                  int var44 = var2 << 24 | var12 << 16 | var43;
                  ta var45 = this.q(var44, var38, var42, 2);
                  if (var45 != null) {
                     var9++;
                     byte var46 = 40;
                     if (var2 == 1) {
                        var46 = 10;
                     }

                     q.log(var46, false, S.L("Resource id 0x%x has multiple entries for configuration '%s': %s/%s"), var44, var38, var13, var45.JY);
                  }

                  var32++;
               }
            }
         }
      }

      if (var9 > 0) {
         if (var2 == 1) {
            q.debug(S.L("Collision count when assigning resource values for the Android Framework: %d/%d"), var9, var32);
         } else {
            q.warn(S.L("Collision count when assigning resource values for package 0x%X: %d/%d"), var2, var9, var32);
         }
      }
   }

   private void q(int var1, String var2, String var3, int var4, boolean var5) {
      tl var6 = new tl(var1, var2, var3, var4);
      var6.Dw = var5;
      if (this.Dw.put(var1, var6) != null) {
         throw new IllegalStateException(Strings.ff("Resource id 0x%x already exists", var1));
      }
   }

   private ta q(int var1, String var2, ta var3, int var4) {
      var3.lm = var1;
      var3.zz = var2;
      tl var5 = (tl)this.Dw.get(var1);
      var3.JY = var5.xK;
      ta var6 = (ta)var5.oW.get(var2);
      if (var6 == null) {
         var5.oW.put(var2, var3);
         return null;
      } else if (var4 == 0) {
         throw new IllegalStateException(Strings.ff("Resource id 0x%x already has an entry for configuration \"%s\"", var1, Strings.safe2(var2, "default")));
      } else {
         if (var4 == 1) {
            var5.oW.put(var2, var3);
         }

         return var6;
      }
   }
}
