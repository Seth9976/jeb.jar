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
public class bfy extends com.pnfsoftware.jeb.corei.parsers.dex.Sv implements IDexType, bgc {
   private static final ILogger A = GlobalLog.getLogger(bfy.class);
   @SerId(1)
   private bgj kS;
   @SerId(2)
   private volatile String wS;
   @SerId(3)
   private String UT;
   @SerId(4)
   private List E = new ArrayList();
   @SerId(5)
   private bfy sY;
   @SerId(6)
   private bfs ys;
   @SerId(7)
   private int ld;
   @SerId(8)
   private String gp;
   @SerId(9)
   private String oT;
   @SerId(10)
   private int fI;
   @SerId(11)
   private String WR;

   public bfy(bgj var1, int var2, String var3) {
      super(var1.A());
      this.kS = var1;
      this.ld = var2;
      this.gp = var3;
      int var4 = 0;

      char var5;
      for (var5 = var3.charAt(0); var5 == '['; var5 = var3.charAt(++var4)) {
         this.fI++;
      }

      this.WR = var3.substring(0, var4);
      if (var3.endsWith(";")) {
         if (var5 != 'L') {
            throw new JebRuntimeException("Illegal class signature: " + Formatter.escapeString(var3));
         }

         this.oT = var3.substring(var4);
      } else {
         this.oT = null;
      }

      if (this.oT == null) {
         this.wS = var3.substring(var4);
         if (!pC(this.wS)) {
            throw new JebRuntimeException("Illegal type letter: " + Formatter.escapeString(this.wS));
         }
      } else {
         var4 = var3.lastIndexOf(47);
         if (var4 < 0) {
            var4 = 0;
         }

         this.wS = var3.substring(var4 + 1, var3.length() - 1);
      }

      this.UT = this.wS;
   }

   @Override
   public int getIndex() {
      return this.ld;
   }

   static boolean pC(String var0) {
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
      return this.gp.equals(this.oT);
   }

   @Override
   public boolean isArray() {
      return this.fI >= 1;
   }

   @Override
   public int getDimensions() {
      return this.fI;
   }

   @Override
   public boolean isPrimitive() {
      return this.oT == null && this.fI == 0 && this.gp.length() == 1 && this.gp.charAt(0) != 'V';
   }

   @Override
   public boolean isArrayOfPrimitive() {
      return this.oT == null && this.fI >= 1;
   }

   @Override
   public String getNonArrayClass() {
      return this.oT;
   }

   @Override
   public boolean isInternal() {
      return this.ys != null;
   }

   @Override
   public boolean isArtificial() {
      return this.ld >= this.kS.kS();
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
         if (var1 && this.oT != null) {
            Object var12 = this.sY != null ? this.sY : this;
            StringBuilder var14 = new StringBuilder();

            while (true) {
               com.pnfsoftware.jeb.corei.parsers.dex.qt var15 = this.kS.A((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var12);
               if (var15 == null || var15.isRootPackage()) {
                  var9 = this.WR + "L" + var14 + this.getName(var1) + ";";
                  break;
               }

               var14.insert(0, "/").insert(0, var15.getName(var1));
               var12 = var15;
            }
         } else {
            var9 = this.gp;
         }

         return var9;
      } else {
         String var4;
         if (this.oT == null) {
            String var5 = this.wS;
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
                  var4 = this.wS;
            }
         } else {
            String var10 = "";
            if (var3) {
               StringBuilder var13 = new StringBuilder();
               Object var7 = this.sY != null ? this.sY : this;

               while (true) {
                  com.pnfsoftware.jeb.corei.parsers.dex.qt var8 = this.kS.A((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var7);
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

         for (int var11 = 0; var11 < this.fI; var11++) {
            var4 = var4 + "[]";
         }

         return var4;
      }
   }

   @Override
   public String getName(boolean var1) {
      if (!var1) {
         return this.UT;
      } else {
         return this.sY != null ? this.sY.wS : this.wS;
      }
   }

   public synchronized boolean pC(String var1, String var2) {
      if (!this.wS.endsWith(var1)) {
         JebRuntimeException var9 = new JebRuntimeException(Strings.ff("Type '%s' was supposed to end with '%s'", this.wS, var1));
         if (Licensing.isDebugBuild()) {
            throw var9;
         } else {
            JebCoreService.notifySilentExceptionToClient(var9);
            return false;
         }
      } else {
         int var3 = this.wS.length() - var1.length();
         if ((!var2.isEmpty() || !this.UT.equals(this.wS.substring(0, var3))) && !var2.equals(this.UT) && !DexUtil.isSimpleName(var2, this.pC.pC())) {
            A.warn(S.L("Illegal type name: '%s'"), Formatter.escapeString(var2));
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
               bfy var8 = this.kS.sY(var7);
               if (var8 != null && var8 != this) {
                  A.warn(S.L("A type with this name already exists: '%s'"), Formatter.escapeString(var2));
                  return false;
               }
            }

            this.pC(var3, var4, var2);
            return true;
         }
      }
   }

   private void pC(int var1, int var2, String var3) {
      String var4 = this.wS.substring(0, var1 + var2) + "$";
      String var5 = this.wS.substring(0, var1);
      String var6 = this.wS.substring(var1 + var2);
      this.wS = var5 + var3 + var6;
      com.pnfsoftware.jeb.corei.parsers.dex.HE var7 = this.kS.pC(this).A();
      Assert.a(var7.kS() instanceof com.pnfsoftware.jeb.corei.parsers.dex.qt);

      for (com.pnfsoftware.jeb.corei.parsers.dex.HE var9 : var7.getChildren()) {
         com.pnfsoftware.jeb.corei.parsers.dex.Sv var10 = var9.kS();
         if (var10 instanceof bfy && var10 != this) {
            bfy var11 = (bfy)var10;
            String var12 = var11.wS;
            if (var12.startsWith(var4)) {
               bfs var13 = var11.A();
               if (var13 == null || var13.isMemberClass()) {
                  var11.wS = var12.substring(0, var1) + var3 + var12.substring(var1 + var2);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("Type:#%d,name=%s,address=%s", this.ld, this.getName(true), this.getAddress());
   }

   @Override
   public List getDependantTypes() {
      return Collections.unmodifiableList(this.E);
   }

   public void pC(bfy var1) {
      this.E.add(var1);
   }

   @Override
   public IDexType getMasterType() {
      return this.sY;
   }

   public void A(bfy var1) {
      this.sY = var1;
   }

   boolean pC(bfs var1) {
      if (var1.getClassTypeIndex() != this.ld) {
         throw new IllegalStateException();
      } else if (this.ys != null) {
         return false;
      } else {
         this.ys = var1;
         return true;
      }
   }

   public bfs A() {
      return this.ys;
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
