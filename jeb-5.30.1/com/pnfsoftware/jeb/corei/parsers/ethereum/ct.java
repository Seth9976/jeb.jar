package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.cje;
import com.pnfsoftware.jebglobal.cjf;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ct {
   private static final ILogger q = GlobalLog.getLogger(ct.class);
   private TreeMap RF;
   private TreeMap xK;
   private TreeMap Dw;

   public ct(eM var1) {
      this.RF = var1.Uv;
      this.xK = var1.oW;
      this.Dw = new TreeMap();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var3 : this.q()) {
         if (var3.Dw != null) {
            ct.eo var4 = (ct.eo)this.Dw.get(var3.Dw);
            if (var4 == null) {
               var4 = new ct.eo(var3.Dw);
               this.Dw.put(var3.Dw, var4);
            }
         }
      }

      MultiMap var9 = new MultiMap();

      for (ct.eo var15 : this.Dw.values()) {
         this.RF(var15);
      }

      for (ct.eo var16 : this.Dw.values()) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var6 : var16.oW) {
            var9.put(var6.q, var16.q);
         }
      }

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var17 : this.q()) {
         if (var17.Dw != null) {
            ct.eo var19 = (ct.eo)this.Dw.get(var17.Dw);
            var19.RF.add(var17.q);
         }
      }

      for (ct.eo var18 : this.Dw.values()) {
         for (int var21 : var18.RF) {
            for (int var8 : var9.getSafe(var21)) {
               var18.gP.add(var8);
               ((ct.eo)this.Dw.get(var8)).nf.add(var18.q);
            }
         }
      }
   }

   public ct.eo q(int var1) {
      return (ct.eo)this.Dw.get(var1);
   }

   List q() {
      ArrayList var1 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var3 : this.xK.values()) {
         if (var3.za) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public List RF() {
      return new cje(this.Dw.values()).RF();
   }

   public List xK() {
      ArrayList var1 = new ArrayList();

      for (ct.eo var4 : new cje(this.Dw.values()).RF()) {
         List var5 = this.RF(var4.q);
         var1.addAll(var5);
      }

      return var1;
   }

   private void RF(ct.eo var1) {
      HashSet var2 = new HashSet();
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var1.q);

      while (!var3.isEmpty()) {
         int var4 = (Integer)var3.remove();
         if (var2.add(var4)) {
            com.pnfsoftware.jeb.corei.parsers.ethereum.eo var5 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.xK.get(var4);
            if (var5 != null && !var5.nf) {
               if (var5.za) {
                  if (!var5.JY) {
                     var3.add(var5.RF + 1);
                  }
               } else {
                  if (var5.Uv != null) {
                     var3.add(var5.Uv);
                  }

                  if (var5.Dw != null) {
                     if (this.Dw.containsKey(var5.Dw)) {
                        var5.za = true;
                        int var6 = var5.RF + 1;
                        if (!this.Dw.containsKey(var6) && this.q(var5, true)) {
                           var3.add(var6);
                        } else {
                           var5.JY = true;
                        }
                     } else {
                        var3.add(var5.Dw);
                     }
                  }
               }
            }
         }
      }

      var2.clear();
      var3.clear();
      var3.add(var1.q);
      var1.oW = new ArrayList();

      while (!var3.isEmpty()) {
         int var7 = (Integer)var3.remove();
         if (var2.add(var7)) {
            com.pnfsoftware.jeb.corei.parsers.ethereum.eo var10 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.xK.get(var7);
            if (var10 != null) {
               var1.oW.add(var10);
               if (!var10.nf) {
                  if (var10.za) {
                     if (!var10.JY) {
                        var3.add(var10.RF + 1);
                     }
                  } else {
                     if (var10.Uv != null) {
                        var3.add(var10.Uv);
                     }

                     if (var10.Dw != null) {
                        var3.add(var10.Dw);
                     }
                  }
               }
            }
         }
      }

      var1.gO = new HashMap();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var11 : var1.oW) {
         var1.gO.put(var11.q, new HashSet());
      }

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var12 : var1.oW) {
         if (var12.Uv != null) {
            ((Set)var1.gO.get(var12.Uv)).add(var12.q);
         }

         if (var12.za) {
            if (!var12.JY) {
               ((Set)var1.gO.get(var12.RF + 1)).add(var12.q);
            }
         } else if (var12.Dw != null) {
            ((Set)var1.gO.get(var12.Dw)).add(var12.q);
         }
      }
   }

   boolean q(com.pnfsoftware.jeb.corei.parsers.ethereum.eo var1, boolean var2) {
      vX var3 = (vX)this.RF.get(var1.RF);
      if (var3.q() == 86) {
         int var4 = var1.RF + 1;
         if (var1.Dw == null || var1.Dw != var4) {
            vX var5 = (vX)this.RF.get(var4);
            if (var5 != null && var5.q() == 91) {
               int var6 = var2 ? 0 : var1.q;

               for (vX var8 : this.RF.descendingMap().subMap(var1.RF, false, var6, true).values()) {
                  if (var8.q() == 91) {
                     break;
                  }

                  if (var8.lm != null && var8.lm.intValue() == var4) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   List RF(int var1) {
      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : ((ct.eo)this.Dw.get(var1)).oW) {
         if (var4.q(true)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   List xK(int var1) {
      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : ((ct.eo)this.Dw.get(var1)).oW) {
         if (var4.q(true)) {
            return null;
         }

         if (var4.nf) {
            var2.add(var4);
         }
      }

      return var2;
   }

   List q(ct.eo var1) {
      HashMap var2 = new HashMap();
      com.pnfsoftware.jeb.corei.parsers.ethereum.eo var3 = null;
      com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 = null;

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var6 : var1.oW) {
         var2.put(var6.q, var6);
         if (var3 == null) {
            if (var6.q(true)) {
               var3 = var6;
               break;
            }

            if (var6.nf && var4 == null) {
               var4 = var6;
            }
         }
      }

      if (var3 == null) {
         var3 = var4;
         if (var4 == null) {
            return null;
         }
      }

      ArrayList var13 = new ArrayList();
      var13.add(var3.q);
      int var14 = var1.q;
      HashSet var7 = new HashSet();
      com.pnfsoftware.jeb.corei.parsers.ethereum.eo var8 = var3;

      while (var8.q != var14) {
         Set var9 = (Set)var1.gO.get(var8.q);
         int var10 = -1;
         if (var1.gO.size() == 1) {
            var10 = (Integer)var9.iterator().next();
         } else {
            for (int var12 : var9) {
               if (!var7.contains(var12)) {
                  var10 = var12;
                  break;
               }
            }
         }

         if (var10 < 0) {
            return null;
         }

         var7.add(var10);
         var13.add(0, var10);
         var8 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.xK.get(var10);
      }

      return var13;
   }

   public class eo implements cjf {
      int q;
      List RF = new ArrayList();
      private List oW;
      private Map gO;
      private Set nf = new HashSet();
      private Set gP = new HashSet();
      Integer xK;
      Integer Dw;

      public eo(int var2) {
         this.q = var2;
      }

      @Override
      public String q() {
         return Strings.ff("sub_%X", this.q);
      }

      @Override
      public List RF() {
         ArrayList var1 = new ArrayList();

         for (int var3 : this.nf) {
            ct.eo var4 = (ct.eo)ct.this.Dw.get(var3);
            Assert.a(var4 != null);
            var1.add(var4);
         }

         return var1;
      }

      @Override
      public List xK() {
         ArrayList var1 = new ArrayList();

         for (int var3 : this.gP) {
            ct.eo var4 = (ct.eo)ct.this.Dw.get(var3);
            Assert.a(var4 != null);
            var1.add(var4);
         }

         return var1;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X", this.q);
      }
   }
}
