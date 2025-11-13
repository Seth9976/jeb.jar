package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class bky {
   private static final ILogger q = GlobalLog.getLogger(bky.class);
   private com.pnfsoftware.jeb.corei.parsers.dex.bK RF;
   private bku xK;
   private Set Dw;
   private Set Uv;
   private Set oW;

   public bky(IDexUnit var1) {
      this.RF = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1;
   }

   private void q() {
      this.xK = this.RF.CE();
      this.Dw = new LinkedHashSet();
      this.Uv = new HashSet();
      this.oW = new HashSet();
   }

   public List q(IDexMethodData var1, boolean var2) {
      int var3 = var1.getMethodIndex();
      bjp var4 = this.RF.gO(var3);
      int var5 = var4.getClassTypeIndex();
      if (this.RF(var4)) {
         return Collections.emptyList();
      } else {
         this.q();
         bla var6 = this.xK.q(var5);
         if (var6 == null) {
            return Collections.emptyList();
         } else {
            ArrayList var7 = new ArrayList();
            ArrayDeque var8 = new ArrayDeque();
            var8.addAll(var6.oW());
            HashSet var9 = new HashSet();

            while (!var8.isEmpty()) {
               int var10 = (Integer)var8.remove();
               if (var9.add(var10)) {
                  bla var11 = this.xK.q(var10);
                  if (var11 != null) {
                     int var12 = this.q(var11, var4);
                     if (var12 >= 0 && var12 != var3 && this.RF.gO(var12).isInternal()) {
                        var7.add(var12);
                        if (var2) {
                           continue;
                        }
                     }

                     var8.addAll(var11.oW());
                  }
               }
            }

            return var7;
         }
      }
   }

   public List q(IDexMethod var1, boolean var2) {
      this.q();
      if (!this.RF(var1)) {
         this.RF((bjp)var1, var1.getClassType().getIndex(), null);
      }

      if (!var2) {
         this.Dw.remove(var1.getIndex());
      }

      return new ArrayList(this.Dw);
   }

   public List RF(IDexMethod var1, boolean var2) {
      this.q();
      if (!this.RF(var1)) {
         this.q((bjp)var1, var1.getClassType().getIndex(), null);
      }

      if (!var2) {
         this.Dw.remove(var1.getIndex());
      }

      return new ArrayList(this.Dw);
   }

   public List xK(IDexMethod var1, boolean var2) {
      this.q();
      if (!this.RF(var1)) {
         this.q((bjp)var1, var1.getClassType().getIndex(), null);
         this.RF((bjp)var1, var1.getClassType().getIndex(), null);
      }

      if (!var2) {
         this.Dw.remove(var1.getIndex());
      }

      return new ArrayList(this.Dw);
   }

   public List q(IDexMethod var1) {
      if (this.RF(var1)) {
         return Arrays.asList(var1.getIndex());
      } else {
         this.q();
         ArrayList var2 = new ArrayList();
         var2.add(var1.getClassType().getIndex());

         while (!var2.isEmpty()) {
            LinkedHashSet var3 = new LinkedHashSet();

            for (int var5 : var2) {
               this.q((bjp)var1, var5, var3);
            }

            var2.clear();
            LinkedHashSet var10 = new LinkedHashSet();

            for (int var6 : var3) {
               bla var7 = this.xK.q(var6);
               if (var7 != null) {
                  for (int var9 : var7.oW()) {
                     this.RF((bjp)var1, var9, var10);
                  }
               }
            }

            var2.addAll(var10);
         }

         return new ArrayList(this.Dw);
      }
   }

   private void q(bjp var1, int var2, Set var3) {
      if (this.oW.add(var2)) {
         bla var4 = this.xK.q(var2);
         if (var4 != null) {
            int var5 = this.q(var4, var1);
            if (var5 >= 0 && this.Dw.add(var5) && var3 != null) {
               var3.add(var2);
            }

            for (int var7 : var4.Uv()) {
               this.q(var1, var7, var3);
            }
         }
      }
   }

   private void RF(bjp var1, int var2, Set var3) {
      if (this.Uv.add(var2)) {
         bla var4 = this.xK.q(var2);
         if (var4 != null) {
            int var5 = this.q(var4, var1);
            if (var5 >= 0 && this.Dw.add(var5) && var3 != null) {
               var3.add(var2);
            }

            for (int var7 : var4.oW()) {
               this.RF(var1, var7, var3);
            }
         }
      }
   }

   private int q(bla var1, bjp var2) {
      if (var2.Dw().getIndex() == var1.q) {
         return var2.getIndex();
      } else {
         for (int var4 : var1.Uv) {
            bjp var5 = this.RF.gO(var4);
            if (var5.RF() == null || !var5.RF().isPrivate()) {
               String var6 = var2.getName(false);
               String var7 = var5.getName(false);
               if (var6.equals(var7)) {
                  int[] var8 = var2.Uv().getParameterTypeIndexes();
                  int[] var9 = var5.Uv().getParameterTypeIndexes();
                  if (Arrays.equals(var8, var9)) {
                     return var4;
                  }
               }
            }
         }

         return -1;
      }
   }

   private boolean RF(IDexMethod var1) {
      return Strings.isContainedIn(var1.getName(false), "<init>", "<clinit>");
   }
}
