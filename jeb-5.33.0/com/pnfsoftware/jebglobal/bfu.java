package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.ICodeType;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

@Ser
public class bfu extends com.pnfsoftware.jeb.corei.parsers.dex.Sv implements IDexMethod, bgc {
   private static final ILogger A = GlobalLog.getLogger(bfu.class);
   @SerId(2)
   private int kS;
   @SerId(3)
   private int wS;
   @SerId(4)
   private int UT;
   @SerId(5)
   private int E;
   @SerId(6)
   private volatile String sY;
   @SerId(7)
   private bgd ys;

   public bfu(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, int var3, int var4, int var5) {
      super(var1);
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = null;
   }

   @Override
   public int getIndex() {
      return this.kS;
   }

   void pC(bgd var1) {
      if (this.ys != null && this.ys != var1) {
         this.pC.logWarn(true, S.L("Method %s: MethodData already set to %s, attempting to set to %s"), this.getSignature(false), this.ys, var1);
      }

      if (var1.getMethodIndex() != this.kS) {
         throw new JebRuntimeException(
            Strings.ff("Method %s: Expected method index #%d, but MethodData references #%d", this.getSignature(false), this.kS, var1.getMethodIndex())
         );
      } else {
         this.ys = var1;
      }
   }

   public bgd A() {
      return this.ys;
   }

   @Override
   public boolean isInternal() {
      return this.A() != null;
   }

   @Override
   public boolean isArtificial() {
      return this.kS >= this.pC.fI().kS();
   }

   public boolean pC(String var1) {
      return !this.pC() ? false : this.A(var1, true, false);
   }

   public boolean pC(String var1, boolean var2, boolean var3) {
      if (!this.pC()) {
         return false;
      } else {
         int var4 = this.getIndex();
         List var5 = null;
         if (var2) {
            bhc var6 = new bhc(this.pC);

            try {
               var5 = var6.pC(this);
            } catch (RuntimeException var10) {
               this.pC.logWarn(false, "%s: %s", S.L("An error occurred while trying to rename a method"), this.getSignature(true));
               JebCoreService.notifySilentExceptionToClient(var10);
               return false;
            }

            if (!var5.contains(var4)) {
               this.pC.logWarn(false, "%s: %s", S.L("An error occurred while trying to rename a method"), this.getSignature(true));
               return false;
            }

            for (int var8 : var5) {
               bfu var9 = this.pC.sY(var8);
               if (!var9.pC(var1 != null && !var1.equals("") ? var1 : null)) {
                  this.pC.logWarn(false, S.L("Invalid name for method: %s"), var9.getSignature(true));
                  return false;
               }
            }
         }

         if (!this.A(var1, false, var3)) {
            return false;
         } else {
            if (var5 != null) {
               var1 = this.getName(true);

               for (int var13 : var5) {
                  if (var13 != var4) {
                     bfu var14 = this.pC.sY(var13);
                     if (!var14.pC(var1.equals("") ? null : var1, false, var3)) {
                        this.pC.logWarn(false, S.L("A method could not be renamed, the unit is left in an inconsistent state: %s"), var14.getSignature(true));
                     }
                  }
               }
            }

            return true;
         }
      }
   }

   private synchronized boolean A(String var1, boolean var2, boolean var3) {
      if ((var1 != null || this.sY != null) && (var1 == null || !var1.equals(this.sY))) {
         if (var1 != null && var1.isEmpty()) {
            var1 = null;
         }

         String var4 = this.getName();
         if (var1 != null && !DexUtil.isSimpleName(var1, this.pC.pC())) {
            A.warn(S.L("Illegal method name: '%s'"), Formatter.escapeString(var1));
            return false;
         } else {
            if (var1 != null && var1.equals(this.getName(false))) {
               var1 = null;
               if (this.sY == null) {
                  return true;
               }
            }

            if (var1 != null) {
               bfs var5 = this.pC.wS(this.wS).A();
               if (var5 != null && var5.sY() != null) {
                  bfp var6 = var5.sY();

                  for (bgd var8 : var6.kS()) {
                     bfu var9 = this.pC.sY(var8.getMethodIndex());
                     if (var9 != this
                        && (var9.getName(false).equals(var1) || var9.getName(true).equals(var1))
                        && var9.getParameterTypes().size() == this.getParameterTypes().size()) {
                        boolean var10 = true;

                        for (int var11 = 0; var11 < var9.getParameterTypes().size(); var11++) {
                           ICodeType var12 = (ICodeType)this.getParameterTypes().get(var11);
                           ICodeType var13 = (ICodeType)var9.getParameterTypes().get(var11);
                           if (!var12.equals(var13)) {
                              var10 = false;
                              break;
                           }
                        }

                        if (var10) {
                           return false;
                        }
                     }
                  }
               }
            }

            if (!var2) {
               this.sY = var1;
               if (var3) {
                  this.pC.pC(var3, new UnitChangeEventData(1, this, var1, var4));
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public boolean kS() {
      return this.sY != null;
   }

   @Override
   public boolean pC() {
      return !JavaUtil.isReservedMethodName(this.getName(false));
   }

   @Override
   public int getClassTypeIndex() {
      return this.wS;
   }

   public bfy wS() {
      return this.pC.wS(this.wS);
   }

   @Override
   public String getClassTypeSignature(boolean var1) {
      return this.pC.pC(this.wS, var1);
   }

   @Override
   public int getPrototypeIndex() {
      return this.UT;
   }

   public bfw UT() {
      return this.pC.UT(this.UT);
   }

   public String E() {
      return this.UT().generate(true);
   }

   @Override
   public IDexType getReturnType() {
      return this.pC.UT(this.UT).getReturnType();
   }

   public int sY() {
      return this.pC.UT(this.UT).getParameterTypeIndexes().length;
   }

   @Override
   public List getParameterTypes() {
      return Arrays.asList(this.pC.UT(this.UT).getParameterTypes());
   }

   @Override
   public int getNameIndex() {
      return this.E;
   }

   public String pC(boolean var1) {
      return this.pC(var1, true);
   }

   public String pC(boolean var1, boolean var2) {
      return this.pC.pC(this.wS, var1, var2) + (var2 ? "->" : ".") + this.getName(var1);
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return this.getSignature(var1, var2, var2);
   }

   @Override
   public String getSignature(boolean var1, boolean var2, boolean var3) {
      return this.getSignature(var1, var2, var3, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2, boolean var3, boolean var4) {
      String var5 = this.getName(false);
      if (!var2) {
         String var7;
         if (var4) {
            var7 = this.pC.pC(this.wS, var1, false);
            if (var5.equals("<init>")) {
               var7 = var7 + this.UT().pC(var1, false, false);
            } else if (var5.equals("<clinit>")) {
               var7 = var7 + " {...}";
            } else {
               var7 = var7 + "." + this.getName(var1) + this.UT().pC(var1, false, var3);
            }
         } else {
            var7 = this.pC.wS(this.wS).getName(var1);
            if (var5.equals("<init>")) {
               var7 = var7 + this.UT().pC(var1, false, false, false);
            } else if (var5.equals("<clinit>")) {
               var7 = var7 + " {...}";
            } else {
               var7 = this.getName(var1) + this.UT().pC(var1, false, var3, false);
            }
         }

         return var7;
      } else {
         return this.pC(var1, true) + this.UT().pC(var1, true, var3);
      }
   }

   public String A(boolean var1) {
      return this.getName(var1) + this.UT().generate(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("Method:#%d,name=%s,address=%s", this.kS, this.getName(true), this.getAddress());
   }

   @Override
   public String getName(boolean var1) {
      return var1 && this.sY != null ? this.sY : this.pC.A(this.E);
   }

   @Override
   public boolean setName(String var1) {
      return this.setName(var1, true);
   }

   @Override
   public boolean setName(String var1, boolean var2) {
      return this.pC(var1, true, var2);
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public int getGenericFlags() {
      if (this.ys == null) {
         return 0;
      } else {
         int var1 = DexUtil.convertDexFlagsToCodeFlags(this.ys.getAccessFlags());
         if (this.isInternal()) {
            var1 |= Integer.MIN_VALUE;
         }

         if (this.isArtificial()) {
            var1 |= 1073741824;
         }

         return var1;
      }
   }

   @Override
   public List getInstructions() {
      return this.ys != null && this.ys.pC() != null ? this.ys.pC().getInstructions() : null;
   }

   @Override
   public SortedMap getDebugVariablesMap() {
      return this.ys != null && this.ys.pC() != null && this.ys.pC().getDebugInfo() != null ? this.ys.pC().getDebugInfo().getKnownVariablesMap(this) : null;
   }

   @Override
   public String getParameterName(int var1) {
      int var2 = this.pC(var1);
      return var2 < 0 ? null : this.pC.pC(new IdentifierCoordinates(new MethodCoordinates(this.kS), var2));
   }

   @Override
   public boolean setParameterName(int var1, String var2, boolean var3, boolean var4) {
      int var5 = this.pC(var1);
      return var5 < 0 ? false : this.pC.pC(new IdentifierCoordinates(new MethodCoordinates(this.kS), var5), var2, var3, var4) >= 1;
   }

   private int pC(int var1) {
      int var2;
      if (this.ys != null && this.ys.A() != null) {
         int[] var3 = DexUtil.getMethodParameterIndices(this);
         if (!this.ys.isStatic()) {
            var1++;
         }

         if (var1 < 0 || var1 >= var3.length) {
            return -1;
         }

         var2 = var3[var1];
      } else {
         var2 = var1;
      }

      return var2;
   }
}
