package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class bjt extends com.pnfsoftware.jeb.corei.parsers.dex.CU implements IDexType, bjx {
   private static final ILogger RF = GlobalLog.getLogger(bjt.class);
   @SerId(1)
   private bke xK;
   @SerId(2)
   private volatile String Dw;
   @SerId(3)
   private String Uv;
   @SerId(4)
   private List oW = new ArrayList();
   @SerId(5)
   private bjt gO;
   @SerId(6)
   private bjn nf;
   @SerId(7)
   private int gP;
   @SerId(8)
   private String za;
   @SerId(9)
   private String lm;
   @SerId(10)
   private int zz;
   @SerId(11)
   private String JY;

   public bjt(bke var1, int var2, String var3) {
      super(var1.RF());
      this.xK = var1;
      this.gP = var2;
      this.za = var3;
      int var4 = 0;

      char var5;
      for (var5 = var3.charAt(0); var5 == '['; var5 = var3.charAt(++var4)) {
         this.zz++;
      }

      this.JY = var3.substring(0, var4);
      if (var3.endsWith(";")) {
         if (var5 != 'L') {
            throw new JebRuntimeException("Illegal class signature: " + Formatter.escapeString(var3));
         }

         this.lm = var3.substring(var4);
      } else {
         this.lm = null;
      }

      if (this.lm == null) {
         this.Dw = var3.substring(var4);
         if (!q(this.Dw)) {
            throw new JebRuntimeException("Illegal type letter: " + Formatter.escapeString(this.Dw));
         }
      } else {
         var4 = var3.lastIndexOf(47);
         if (var4 < 0) {
            var4 = 0;
         }

         this.Dw = var3.substring(var4 + 1, var3.length() - 1);
      }

      this.Uv = this.Dw;
   }

   @Override
   public int getIndex() {
      return this.gP;
   }

   static boolean q(String var0) {
      return var0.equals("B")
         || var0.equals("S")
         || var0.equals("I")
         || var0.equals("J")
         || var0.equals("F")
         || var0.equals("D")
         || var0.equals("Z")
         || var0.equals("C")
         || var0.equals("V")
         || var0.equals("L");
   }

   @Override
   public boolean isClass() {
      return this.za.equals(this.lm);
   }

   @Override
   public boolean isArray() {
      return this.zz >= 1;
   }

   @Override
   public int getDimensions() {
      return this.zz;
   }

   @Override
   public boolean isPrimitive() {
      return this.lm == null && this.zz == 0 && this.za.length() == 1 && this.za.charAt(0) != 'V';
   }

   public boolean RF() {
      return this.lm == null && this.zz == 0 && this.za.length() == 1 && this.za.charAt(0) == 'V';
   }

   @Override
   public boolean isArrayOfPrimitive() {
      return this.lm == null && this.zz >= 1;
   }

   @Override
   public String getNonArrayClass() {
      return this.lm;
   }

   @Override
   public boolean isInternal() {
      return this.nf != null;
   }

   @Override
   public boolean isArtificial() {
      return this.gP >= this.xK.xK();
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return this.getSignature(var1, var2, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2, boolean var3) {
      if (var2) {
         String var9;
         if (var1 && this.lm != null) {
            Object var12 = this.gO != null ? this.gO : this;
            StringBuilder var14 = new StringBuilder();

            while (true) {
               com.pnfsoftware.jeb.corei.parsers.dex.Vj var15 = this.xK.RF((com.pnfsoftware.jeb.corei.parsers.dex.CU)var12);
               if (var15 == null || var15.isRootPackage()) {
                  var9 = this.JY + "L" + var14 + this.getName(var1) + ";";
                  break;
               }

               var14.insert(0, "/").insert(0, var15.getName(var1));
               var12 = var15;
            }
         } else {
            var9 = this.za;
         }

         return var9;
      } else {
         String var4;
         if (this.lm == null) {
            String var5 = this.Dw;
            switch (var5) {
               case "Z":
                  var4 = "boolean";
                  break;
               case "B":
                  var4 = "byte";
                  break;
               case "C":
                  var4 = "char";
                  break;
               case "S":
                  var4 = "short";
                  break;
               case "I":
                  var4 = "int";
                  break;
               case "J":
                  var4 = "long";
                  break;
               case "F":
                  var4 = "float";
                  break;
               case "D":
                  var4 = "double";
                  break;
               case "V":
                  var4 = "void";
                  break;
               default:
                  var4 = this.Dw;
            }
         } else {
            String var10 = "";
            if (var3) {
               StringBuilder var13 = new StringBuilder();
               Object var7 = this.gO != null ? this.gO : this;

               while (true) {
                  com.pnfsoftware.jeb.corei.parsers.dex.Vj var8 = this.xK.RF((com.pnfsoftware.jeb.corei.parsers.dex.CU)var7);
                  if (var8 == null || var8.isRootPackage()) {
                     var10 = var13.toString();
                     break;
                  }

                  var13.insert(0, ".").insert(0, var8.getName(var1));
                  var7 = var8;
               }
            }

            var4 = var10 + this.getName(var1);
         }

         for (int var11 = 0; var11 < this.zz; var11++) {
            var4 = var4 + "[]";
         }

         return var4;
      }
   }

   @Override
   public String getName(boolean var1) {
      if (!var1) {
         return this.Uv;
      } else {
         return this.gO != null ? this.gO.Dw : this.Dw;
      }
   }

   public synchronized boolean q(String var1, String var2) {
      if (!this.Dw.endsWith(var1)) {
         JebRuntimeException var9 = new JebRuntimeException(Strings.ff("Type '%s' was supposed to end with '%s'", this.Dw, var1));
         if (Licensing.isDebugBuild()) {
            throw var9;
         } else {
            JebCoreService.notifySilentExceptionToClient(var9);
            return false;
         }
      } else {
         int var3 = this.Dw.length() - var1.length();
         if ((!var2.isEmpty() || !this.Uv.equals(this.Dw.substring(0, var3))) && !var2.equals(this.Uv) && !DexUtil.isSimpleName(var2, this.q.RF())) {
            RF.warn(S.L("Illegal type name: '%s'"), Formatter.escapeString(var2));
            return false;
         } else {
            int var4 = var1.length();
            String var5 = this.getSignature();
            int var6 = var5.length() - 1 - var4;
            if (var6 < 0) {
               JebCoreService.notifySilentExceptionToClient(
                  new RuntimeException("Unexpected type names encountered when trying to rename type: " + this.getSignature(false))
               );
            } else {
               String var7 = var5.substring(0, var6) + var2 + ";";
               bjt var8 = this.xK.gO(var7);
               if (var8 != null && var8 != this) {
                  RF.warn(S.L("A type with this name already exists: '%s'"), Formatter.escapeString(var2));
                  return false;
               }
            }

            this.q(var3, var4, var2);
            return true;
         }
      }
   }

   private void q(int var1, int var2, String var3) {
      String var4 = this.Dw.substring(0, var1 + var2) + "$";
      String var5 = this.Dw.substring(0, var1);
      String var6 = this.Dw.substring(var1 + var2);
      this.Dw = var5 + var3 + var6;
      com.pnfsoftware.jeb.corei.parsers.dex.oL var7 = this.xK.q(this).RF();
      Assert.a(var7.xK() instanceof com.pnfsoftware.jeb.corei.parsers.dex.Vj);

      for (com.pnfsoftware.jeb.corei.parsers.dex.oL var9 : var7.getChildren()) {
         com.pnfsoftware.jeb.corei.parsers.dex.CU var10 = var9.xK();
         if (var10 instanceof bjt && var10 != this) {
            bjt var11 = (bjt)var10;
            String var12 = var11.Dw;
            if (var12.startsWith(var4)) {
               bjn var13 = var11.xK();
               if (var13 == null || var13.isMemberClass()) {
                  var11.Dw = var12.substring(0, var1) + var3 + var12.substring(var1 + var2);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("Type:#%d,name=%s,address=%s", this.gP, this.getName(true), this.getAddress());
   }

   @Override
   public List getDependantTypes() {
      return Collections.unmodifiableList(this.oW);
   }

   public void q(bjt var1) {
      this.oW.add(var1);
   }

   @Override
   public IDexType getMasterType() {
      return this.gO;
   }

   public void RF(bjt var1) {
      this.gO = var1;
   }

   boolean q(bjn var1) {
      if (var1.getClassTypeIndex() != this.gP) {
         throw new IllegalStateException();
      } else if (this.nf != null) {
         return false;
      } else {
         this.nf = var1;
         return true;
      }
   }

   public bjn xK() {
      return this.nf;
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public int getGenericFlags() {
      int var1 = 0;
      if (this.isInternal()) {
         var1 |= Integer.MIN_VALUE;
      }

      if (this.isArtificial()) {
         var1 |= 1073741824;
      }

      return var1;
   }
}
