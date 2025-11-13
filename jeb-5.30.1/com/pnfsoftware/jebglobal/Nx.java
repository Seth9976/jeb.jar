package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

@Ser
public abstract class Nx implements ILabelManager {
   protected static final ILogger q = GlobalLog.getLogger(Nx.class);
   @SerId(1)
   private IMemoryModel RF;
   @SerId(2)
   private Set xK = new HashSet();
   @SerId(3)
   private Map Dw = new ConcurrentHashMap();
   @SerId(4)
   private Map Uv = new ConcurrentHashMap();
   @SerId(5)
   private Set oW = new CopyOnWriteArraySet();
   @SerId(6)
   private Map gO = new ConcurrentHashMap();

   public Nx(IMemoryModel var1, Collection var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.RF = var1;
         if (var2 != null) {
            this.xK.addAll(var2);
         }
      }
   }

   @Override
   public Map getPrimaryLabels() {
      return this.Uv;
   }

   @Override
   public String getLabel(long var1, long var3, AutoLabelPolicy var5) {
      return this.q(var1, var3, var5, null);
   }

   @Override
   public String getLabel(INativeMemoryItem var1, long var2, AutoLabelPolicy var4) {
      return this.q(var1.getMemoryAddress(), var2, var4, var1);
   }

   private String q(long var1, long var3, AutoLabelPolicy var5, INativeMemoryItem var6) {
      String var7 = (String)this.Uv.get(var1);
      if (var7 != null) {
         return var7;
      } else {
         if (!(var6 instanceof axg var8)) {
            var8 = (axg)this.RF.getItemAt(var1);
         }

         if (var8 != null) {
            var7 = var8.RF(true);
         } else {
            axg var9 = (axg)this.RF.getItemOver(var1);
            if (var9 != null) {
               var7 = var9.RF(true);
               if (var7 != null) {
                  var7 = var7 + "+" + Integer.toHexString((int)(var1 - var9.getMemoryAddress())) + "h";
               }
            }
         }

         if (var7 == null && (var5 == AutoLabelPolicy.ON || var8 != null && var5 == AutoLabelPolicy.ITEM || this.oW.contains(var1))) {
            if (var6 instanceof axo) {
               var7 = ((axo)var6).RF(false);
            }

            if (var7 == null) {
               var7 = this.q(var1, var3, var6, var8);
            }
         }

         return var7;
      }
   }

   @Override
   public Collection getAlternateLabels(long var1) {
      List var3 = (List)this.gO.get(var1);
      return var3 == null ? Collections.emptyList() : var3;
   }

   @Override
   public boolean setLabel(long var1, String var3, boolean var4, boolean var5, boolean var6) {
      boolean var9;
      try (ACLock var7 = this.RF.getLock().a()) {
         boolean var8 = this.q(var1, var3, var4, var5, var6);
         if (var8) {
            this.RF.notifyListenersOfModelChange(MemoryModelEventType.LABEL_UPDATE, var1);
         }

         var9 = var8;
      }

      return var9;
   }

   @Override
   public boolean removeLabel(long var1) {
      return this.setLabel(var1, null, false, false, false);
   }

   protected boolean q(long var1, String var3, boolean var4, boolean var5, boolean var6) {
      this.RF.getLock().verifyLocked();
      if (var3 != null && var3.length() == 0) {
         var3 = null;
      }

      axg var7 = (axg)this.RF.getItemAt(var1);
      if (var3 == null) {
         String var17;
         if (var7 != null) {
            this.Uv.remove(var1);
            var17 = var7.getName(true);
            var7.RF(null);
         } else {
            var17 = (String)this.Uv.remove(var1);
         }

         this.gO.remove(var1);
         if (var17 != null) {
            Long var18 = (Long)this.Dw.get(var17);
            if (var18 != null && var18 == var1) {
               this.Dw.remove(var17);
            }
         }

         this.oW.remove(var1);
         return true;
      } else {
         boolean var8 = false;
         List var9 = (List)this.gO.get(var1);
         if (var9 != null && !var5) {
            for (String var11 : var9) {
               this.Dw.remove(var11);
            }

            this.gO.remove(var1);
            var8 = true;
         }

         StringBuilder var19 = var4 ? new StringBuilder() : null;
         if (!this.isLegalLabel(var3, var1, var19)) {
            if (var19 == null) {
               return var8;
            }

            var3 = var19.toString();
         }

         String var20 = (String)this.Uv.get(var1);
         if (Strings.equals(var20, var3)) {
            return var8;
         } else {
            if (var9 != null && var5 && var9.contains(var3) && var5) {
               if (!var6) {
                  return var8;
               }

               var9.remove(var3);
               if (var9.isEmpty()) {
                  this.gO.remove(var1);
               }

               this.Dw.remove(var3);
               var8 = true;
            }

            if (this.q(var3)) {
               return true;
            } else {
               String var12 = var3;
               if (this.Dw.containsKey(var3)) {
                  if (!var4) {
                     return var8;
                  }

                  boolean var13 = var7 instanceof axw && var12.equals(DataStringUtil.createItemNameFromString(((axw)var7).getValue(), 16));
                  int var14 = q(1, 10, var4x -> this.Dw.containsKey(this.q(var12, var4x, var13)) && !this.q(var12, var4x, var13).equals(var20));
                  var3 = this.q(var12, var14, var13);
                  if (var3.equals(var20)) {
                     return var8;
                  }

                  INativeContinuousItem var15 = this.RF.getItemAt((Long)this.Dw.get(var12));
                  if (this.q(var7, var15)) {
                     this.Dw.put(var3, var15.getMemoryAddress());
                     this.Uv.put(var15.getMemoryAddress(), var3);
                     List var16 = (List)this.gO.get(var15.getMemoryAddress());
                     if (var16 != null) {
                        var16.remove(var3);
                     }

                     ((axg)var15).RF(var3);
                     var3 = var12;
                  }
               }

               this.Dw.put(var3, var1);
               String var21 = (String)this.Uv.get(var1);
               if (var21 != null && var5) {
                  Object var22 = (List)this.gO.get(var1);
                  if (var22 == null) {
                     var22 = new CopyOnWriteArrayList();
                     this.gO.put(var1, var22);
                  }

                  if (var6) {
                     var22.add(var21);
                     this.Uv.put(var1, var3);
                     if (var7 != null) {
                        var7.RF(var12);
                     }
                  } else {
                     var22.add(var3);
                  }
               } else {
                  if (var21 != null) {
                     this.Dw.remove(var21);
                  }

                  this.Uv.put(var1, var3);
                  if (var7 != null) {
                     var7.RF(var12);
                  }
               }

               return true;
            }
         }
      }
   }

   private String q(String var1, int var2, boolean var3) {
      String var4 = var1 + var2;
      if (var3 && var4.length() > 16 && Integer.toString(var2).length() < 4) {
         var4 = var1.substring(0, 16 - Integer.toString(var2).length()) + var2;
      }

      return var4;
   }

   private boolean q(axg var1, INativeContinuousItem var2) {
      return var1 instanceof INativeStringItem ? false : var2 instanceof INativeStringItem;
   }

   public static int q(int var0, int var1, Predicate var2) {
      int var3 = var0;

      do {
         if (var3 != var1) {
            var3++;
         } else {
            var1 <<= 1;

            while (var2.test(var1)) {
               var1 <<= 1;
            }

            int var4 = var1;
            var3 = var1 >>> 1;

            while (var3 < var4) {
               int var5 = var3 + var4 >>> 1;
               if (var2.test(var5)) {
                  var3 = var5 + 1;
               } else {
                  if (var2.test(var5 - 1)) {
                     return var5;
                  }

                  var4 = var5;
               }
            }
         }
      } while (var2.test(var3));

      return var3;
   }

   @Override
   public Long resolveLabel(String var1) {
      return this.q(var1) ? this.RF(var1) : (Long)this.Dw.get(var1);
   }

   @Override
   public boolean isLegalLabel(String var1, Long var2, StringBuilder var3) {
      boolean var4 = true;
      Assert.a(var3 == null || var3.length() == 0);
      if (this.q(var1)) {
         Long var5 = this.RF(var1);
         if (var5 == null || !var5.equals(var2)) {
            var4 = false;
            if (var3 != null) {
               var3.append(this.q(var2, 0L));
            }
         }
      }

      return var4;
   }

   public boolean q(String var1) {
      for (String var3 : this.xK) {
         if (var1.startsWith(var3)) {
            return true;
         }
      }

      return false;
   }

   protected abstract Long RF(String var1);

   protected String q(long var1, long var3) {
      return this.q(var1, var3, null, null);
   }

   protected abstract String q(long var1, long var3, INativeMemoryItem var5, INativeContinuousItem var6);

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (Long var4 : this.Uv.keySet()) {
         if (var2 >= 1) {
            var1.append(",");
         }

         String var5 = (String)this.Uv.get(var4);
         List var6 = (List)this.gO.get(var4);
         var1.append(Long.toHexString(var4).toUpperCase()).append("h:").append(var5);
         if (var6 != null) {
            var1.append("(");
            var1.append(Strings.join(",", var6));
            var1.append(")");
         }

         var2++;
      }

      return var1.toString();
   }

   public int q() {
      int var1 = 0;

      for (Entry var3 : this.gO.entrySet()) {
         var1 += ((List)var3.getValue()).size();
      }

      return this.Uv.size() + var1;
   }
}
