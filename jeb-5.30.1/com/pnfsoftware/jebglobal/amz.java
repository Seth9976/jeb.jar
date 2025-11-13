package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableInformationProvider;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class amz {
   @SerId(1)
   ana q;
   @SerId(2)
   ana RF;
   @SerId(3)
   int xK;
   @SerId(4)
   IVariableInformationProvider Dw;
   @SerId(5)
   private Map Uv = new HashMap();
   @SerId(6)
   private Map oW = new HashMap();
   @SerId(7)
   private Map gO = new HashMap();
   @SerId(8)
   private Map nf = new HashMap();
   @SerId(9)
   private Map gP = new HashMap();

   public amz(ana var1, ana var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public void q(IVariableInformationProvider var1) {
      this.Dw = var1;
   }

   public IVariableInformationProvider q() {
      return this.Dw;
   }

   private IEVar gP(int var1) {
      return var1 >= 0 ? this.RF.RF(var1) : this.q.RF(-var1);
   }

   public VarSrc q(IEVar var1) {
      return this.q(var1.getId());
   }

   public VarSrc q(int var1) {
      return (VarSrc)this.nf.get(var1);
   }

   public int RF() {
      return this.nf.size();
   }

   public static Integer RF(int var0) {
      if (var0 >= 0 && var0 < 131072) {
         int var1 = var0 - 0 + 65536;
         Assert.a(var1 >= 65536 && var1 < 196608);
         return var1;
      } else {
         return null;
      }
   }

   public static Integer xK(int var0) {
      if (var0 >= 0 && var0 < 131072) {
         return var0;
      } else {
         int var1 = -var0 - 65536 + 0;
         return var1 >= 131072 ? null : var1;
      }
   }

   public IEVar RF(IEVar var1) {
      VarSrc var2 = this.Dw(var1);
      if (var2 == null) {
         return null;
      } else if (var2.isDuplicate()) {
         int var3 = var2.getAsDuplicate();
         if (!amy.xK(var3)) {
            return null;
         } else {
            var1 = this.gP(var3);
            if (var3 == this.xK) {
               return null;
            } else {
               Integer var12 = RF(var3);
               if (var12 != null) {
                  IEVar var16 = this.q.RF(var12);
                  if (var16 == null) {
                     IEVar var20 = this.q.q(var12, "$" + var1.getName(), var1.getBitsize(), null, true);
                     var12 = var20.getId();
                     this.nf.put(var12, var2);
                     Maps.putMulti(this.gP, var3, var12);
                     return var20;
                  }
               }

               Object var17 = (List)this.Uv.get(var3);
               if (var17 == null) {
                  var17 = new ArrayList();
                  this.Uv.put(var3, var17);
               }

               String var7 = Strings.ff("$%s$%d", var1.getName(), 1 + var17.size());

               IEVar var19;
               try {
                  var19 = this.q.q(196608, 1245184, var7, var1.getBitsize(), null);
               } catch (RuntimeException var9) {
                  throw new RuntimeException("Cannot create copy-var: " + var7, var9);
               }

               var12 = var19.getId();
               var17.add(var12);
               this.nf.put(var12, var2);
               Maps.putMulti(this.gP, var3, var12);
               return var19;
            }
         }
      } else if (var2.isPair()) {
         Couple var11 = var2.getAsPair();
         IEVar var15 = this.gP((Integer)var11.getFirst());
         IEVar var18 = this.gP((Integer)var11.getSecond());
         return this.q(var15, var18);
      } else if (var2.isTruncated()) {
         Couple var4 = var2.getAsTruncated();
         IEVar var5 = this.gP((Integer)var4.getFirst());
         int var6 = (Integer)var4.getSecond();
         return (IEVar)this.q(var5, var6).getFirst();
      } else {
         return null;
      }
   }

   public IEVar q(IEVar var1, IEVar var2) {
      VarSrc var3 = this.Dw(var1);
      if (var3 != null && var3.isDuplicate()) {
         VarSrc var4 = this.Dw(var2);
         if (var4 != null && var4.isDuplicate()) {
            int var5 = var3.getAsDuplicate();
            int var6 = var4.getAsDuplicate();
            if (var5 != var6 && amy.xK(var5) && amy.xK(var6)) {
               var1 = this.gP(var5);
               var2 = this.gP(var6);
               int var7 = var1.getBitsize();
               if (var7 != var2.getBitsize()) {
                  return null;
               } else {
                  long var8 = var5 & 4294967295L | (long)var6 << 32;
                  Object var10 = (List)this.oW.get(var8);
                  String var11;
                  if (var10 == null) {
                     var10 = new ArrayList();
                     this.oW.put(var8, var10);
                     var11 = "";
                  } else {
                     var11 = "$" + var10.size();
                  }

                  int var12 = 2 * var7;
                  String var13 = Strings.ff("$%s_%s%s", var1.getName(), var2.getName(), var11);
                  IEVar var14 = this.q.q(1245184, 1507328, var13, var12, null);
                  var10.add(var14.getId());
                  this.nf.put(var14.getId(), VarSrc.pair(var5, var6));
                  Maps.putMulti(this.gP, var5, var14.getId());
                  Maps.putMulti(this.gP, var6, var14.getId());
                  return var14;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public Couple q(IEVar var1, int var2) {
      VarSrc var3 = this.Dw(var1);
      if (var3 != null && var3.isDuplicate()) {
         int var4 = var3.getAsDuplicate();
         if (!amy.xK(var4)) {
            return null;
         } else {
            var1 = this.gP(var4);
            int var5 = var1.getBitsize();
            if (var2 > 0 && var2 < var5) {
               String var6 = null;
               if (this.Dw != null) {
                  var6 = this.Dw.getSliceName(var1.getId(), 0, var2);
               }

               if (var6 == null) {
                  var6 = var1.getName() + "_lo" + var2;
               }

               var6 = "$" + var6;
               String var7 = null;
               if (this.Dw != null) {
                  var7 = this.Dw.getSliceName(var1.getId(), var2, var1.getBitsize());
               }

               if (var7 == null) {
                  var7 = var1.getName() + "_hi" + (var1.getBitsize() - var2);
               }

               var7 = "$" + var7;
               long var8 = var4 & 4294967295L | (long)var2 << 32;
               Object var10 = (List)this.gO.get(var8);
               String var11;
               if (var10 == null) {
                  var10 = new ArrayList();
                  this.gO.put(var8, var10);
                  var11 = "";
               } else {
                  var11 = "$" + var10.size();
               }

               int var12 = var1.getBitsize() - var2;
               IEVar var13 = this.q.q(1507328, 1769472, var6 + var11, var2, null);
               IEVar var14 = this.q.q(1507328, 1769472, var7 + var11, var12, null);
               Couple var15 = new Couple(var13.getId(), var14.getId());
               var10.add(var15);
               this.nf.put(var13.getId(), VarSrc.slice(var4, 0, var2));
               this.nf.put(var14.getId(), VarSrc.slice(var4, var2, null));
               Maps.putMulti(this.gP, var4, var13.getId());
               Maps.putMulti(this.gP, var4, var14.getId());
               return new Couple(var13, var14);
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private VarSrc Dw(IEVar var1) {
      int var2 = var1.getId();
      if (var2 >= 0 && var2 < 131072) {
         return VarSrc.dup(var2);
      } else {
         VarSrc var3 = (VarSrc)this.nf.get(var2);
         return var3 != null ? var3 : null;
      }
   }

   public Set xK(IEVar var1) {
      Set var2 = this.Dw(var1.getId());
      HashSet var3 = new HashSet(var2.size());

      for (Integer var5 : var2) {
         IEVar var6 = this.gP(var5);
         if (var6 == null) {
            throw new RuntimeException();
         }

         var3.add(var6);
      }

      return var3;
   }

   public Set Dw(int var1) {
      VarSrc var2 = this.q(var1);
      if (var2 == null) {
         return this.za(var1);
      } else if (var2.isDuplicate()) {
         return this.za(var2.getAsDuplicate());
      } else if (var2.isSlice()) {
         Couple var4 = var2.getAsSlice();
         return this.q((Integer)var4.getFirst(), (Couple)var4.getSecond());
      } else if (var2.isPair()) {
         Couple var3 = var2.getAsPair();
         return this.q((Integer)var3.getFirst(), (Integer)var3.getSecond());
      } else {
         throw new RuntimeException();
      }
   }

   public Set Uv(int var1) {
      HashSet var2 = new HashSet();
      VarSrc var3 = this.q(var1);
      if (var3 != null && !var3.isDuplicate()) {
         if (var3.isPair()) {
            Couple var14 = var3.getAsPair();

            for (Integer var18 : Arrays.asList((Integer)var14.getFirst(), (Integer)var14.getSecond())) {
               for (Integer var22 : (List)this.gP.get(var18)) {
                  VarSrc var9 = (VarSrc)this.nf.get(var22);
                  if (var9.isDuplicate() || var9.isSlice()) {
                     var2.add(var22);
                  } else if (var9.isPair() && var3.getAsPair().equals(var14)) {
                     var2.add(var22);
                  }
               }
            }
         } else {
            if (!var3.isSlice()) {
               throw new RuntimeException();
            }

            Couple var15 = var3.getAsSlice();
            Integer var17 = (Integer)var15.getFirst();
            int var19 = (Integer)((Couple)var15.getSecond()).getFirst();
            Integer var21 = (Integer)((Couple)var15.getSecond()).getSecond();

            for (Integer var24 : (List)this.gP.get(var17)) {
               VarSrc var10 = (VarSrc)this.nf.get(var24);
               if (var10.isSlice()) {
                  Couple var11 = var10.getAsSlice();
                  int var12 = (Integer)((Couple)var11.getSecond()).getFirst();
                  Integer var13 = (Integer)((Couple)var11.getSecond()).getSecond();
                  if (var12 >= var19 && (var21 == null || var13 != null && var13 <= var21)) {
                     var2.add(var24);
                  }
               }
            }
         }
      } else {
         int var4 = var3 == null ? var1 : var3.getAsDuplicate();
         List var5 = (List)this.gP.get(var4);
         if (var5 != null) {
            for (Integer var7 : (List)this.gP.get(var4)) {
               VarSrc var8 = (VarSrc)this.nf.get(var7);
               if (var8.isDuplicate() || var8.isSlice()) {
                  var2.add(var7);
               }
            }
         }
      }

      return var2;
   }

   public Set oW(int var1) {
      HashSet var2 = new HashSet();
      VarSrc var3 = this.q(var1);
      if (var3 != null && var3.isSlice()) {
         Couple var14 = var3.getAsSlice();
         Integer var15 = (Integer)var14.getFirst();
         int var16 = (Integer)((Couple)var14.getSecond()).getFirst();
         Integer var17 = (Integer)((Couple)var14.getSecond()).getSecond();
         if (var17 == null) {
            var17 = Integer.MAX_VALUE;
         }

         for (Integer var9 : (List)this.gP.get(var15)) {
            VarSrc var10 = (VarSrc)this.nf.get(var9);
            if (!var10.isSlice()) {
               var2.add(var9);
            } else {
               Couple var11 = var10.getAsSlice();
               int var12 = (Integer)((Couple)var11.getSecond()).getFirst();
               Integer var13 = (Integer)((Couple)var11.getSecond()).getSecond();
               if (var13 == null) {
                  var13 = Integer.MAX_VALUE;
               }

               if (var12 < var17 && var13 > var16) {
                  var2.add(var9);
               }
            }
         }

         return var2;
      } else {
         HashSet var4 = new HashSet();
         if (var3 == null) {
            var4.add(var1);
         } else {
            var3.collectSourceIds(var4);
         }

         for (Integer var6 : var4) {
            List var7 = (List)this.gP.get(var6);
            if (var7 != null) {
               var2.addAll(var7);
            }
         }

         return var2;
      }
   }

   public Set gO(int var1) {
      HashSet var2 = new HashSet();
      VarSrc var3 = this.q(var1);
      HashSet var4 = new HashSet();
      if (var3 == null) {
         var4.add(var1);
      } else {
         var3.collectSourceIds(var4);
      }

      for (Integer var6 : var4) {
         List var7 = (List)this.gP.get(var6);
         if (var7 != null) {
            var2.addAll(var7);
         }
      }

      return var2;
   }

   private Set za(int var1) {
      return this.q(var1, null);
   }

   private Set q(int var1, Couple var2) {
      HashSet var3 = new HashSet();
      if (var2 == null) {
         Integer var4 = RF(var1);
         if (var4 != null && this.q.RF(var4) != null) {
            var3.add(-var4);
         }

         List var5 = (List)this.Uv.get(var1);
         if (var5 != null) {
            var3.addAll(var5);
         }
      }

      for (Long var11 : this.gO.keySet()) {
         int var6 = var11.intValue();
         if (var6 == var1) {
            if (var2 == null) {
               for (Couple var14 : (List)this.gO.get(var11)) {
                  var3.add((Integer)var14.getFirst());
                  var3.add((Integer)var14.getSecond());
               }
            } else {
               int var7 = (int)(var11 >> 32);
               if (var2.getSecond() != null && (Integer)var2.getFirst() == 0 && var7 <= (Integer)var2.getSecond()) {
                  for (Couple var15 : (List)this.gO.get(var11)) {
                     var3.add((Integer)var15.getFirst());
                  }
               } else if (var2.getSecond() == null && var7 >= (Integer)var2.getFirst()) {
                  for (Couple var9 : (List)this.gO.get(var11)) {
                     var3.add((Integer)var9.getSecond());
                  }
               }
            }
         }
      }

      return var3;
   }

   private Set q(int var1, int var2) {
      Set var3 = this.za(var1);
      Set var4 = this.za(var2);
      var3.addAll(var4);
      long var5 = var1 & 4294967295L | (long)var2 << 32;
      List var7 = (List)this.oW.get(var5);
      if (var7 != null) {
         var3.addAll(var7);
      }

      return var3;
   }

   public List nf(int var1) {
      Integer var2 = RF(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();
         IEVar var4 = this.q.RF(var2);
         if (var4 == null) {
            return var3;
         } else {
            var3.add(var4);
            List var5 = (List)this.Uv.get(var1);
            if (var5 == null) {
               return var3;
            } else {
               for (int var7 : var5) {
                  var4 = this.q.RF(-var7);
                  Assert.a(var4 != null);
                  var3.add(var4);
               }

               return var3;
            }
         }
      }
   }

   public boolean RF(IEVar var1, IEVar var2) {
      if (var1 == var2) {
         return true;
      } else if (var1.getBitsize() != var2.getBitsize()) {
         return false;
      } else {
         VarSrc var3 = this.q(var1.getId());
         VarSrc var4 = this.q(var2.getId());
         if (var3 == null && var4 == null) {
            return var1.getId() == var2.getId();
         } else if (var3 == null && var4 != null) {
            return var4.isDuplicate() && var4.getAsDuplicate() == var1.getId();
         } else {
            return var3 != null && var4 == null
               ? var3.isDuplicate() && var3.getAsDuplicate() == var2.getId()
               : var3.isDuplicate() && var4.isDuplicate() && var3.getAsDuplicate() == var4.getAsDuplicate();
         }
      }
   }

   public boolean q(IEVar var1, IEVar var2, int var3, int var4) {
      int var5 = var2.getId();
      VarSrc var6 = this.q(var5);
      if (var6 != null) {
         if (!var6.isDuplicate()) {
            return false;
         }

         var5 = var6.getAsDuplicate();
      }

      VarSrc var7 = this.q(var1.getId());
      if (var7 != null && var7.isSlice()) {
         Couple var8 = var7.getAsSlice();
         if (var5 != (Integer)var8.getFirst()) {
            return false;
         } else {
            Couple var9 = (Couple)var8.getSecond();
            int var10 = (Integer)var9.getFirst();
            int var11 = var9.getSecond() == null ? var2.getBitsize() : (Integer)var9.getSecond();
            return var10 == var3 && var11 == var4;
         }
      } else {
         return false;
      }
   }
}
