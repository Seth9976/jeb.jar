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
public class bjp extends com.pnfsoftware.jeb.corei.parsers.dex.CU implements IDexMethod, bjx {
   private static final ILogger RF = GlobalLog.getLogger(bjp.class);
   @SerId(2)
   private int xK;
   @SerId(3)
   private int Dw;
   @SerId(4)
   private int Uv;
   @SerId(5)
   private int oW;
   @SerId(6)
   private volatile String gO;
   @SerId(7)
   private bjy nf;

   public bjp(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, int var3, int var4, int var5) {
      super(var1);
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
      this.oW = var5;
      this.gO = null;
   }

   @Override
   public int getIndex() {
      return this.xK;
   }

   void q(bjy var1) {
      if (this.nf != null && this.nf != var1) {
         this.q.logWarn(true, S.L("Method %s: MethodData already set to %s, attempting to set to %s"), this.getSignature(false), this.nf, var1);
      }

      if (var1.getMethodIndex() != this.xK) {
         throw new JebRuntimeException(
            Strings.ff("Method %s: Expected method index #%d, but MethodData references #%d", this.getSignature(false), this.xK, var1.getMethodIndex())
         );
      } else {
         this.nf = var1;
      }
   }

   public bjy RF() {
      return this.nf;
   }

   @Override
   public boolean isInternal() {
      return this.RF() != null;
   }

   @Override
   public boolean isArtificial() {
      return this.xK >= this.q.PV().xK();
   }

   public boolean q(String var1) {
      return !this.q() ? false : this.RF(var1, true, false);
   }

   public boolean q(String var1, boolean var2, boolean var3) {
      if (!this.q()) {
         return false;
      } else {
         int var4 = this.getIndex();
         List var5 = null;
         if (var2) {
            bky var6 = new bky(this.q);

            try {
               var5 = var6.q(this);
            } catch (RuntimeException var10) {
               this.q.logWarn(false, "%s: %s", S.L("An error occurred while trying to rename a method"), this.getSignature(true));
               JebCoreService.notifySilentExceptionToClient(var10);
               return false;
            }

            if (!var5.contains(var4)) {
               this.q.logWarn(false, "%s: %s", S.L("An error occurred while trying to rename a method"), this.getSignature(true));
               return false;
            }

            for (int var8 : var5) {
               bjp var9 = this.q.gO(var8);
               if (!var9.q(var1 != null && !var1.equals("") ? var1 : null)) {
                  this.q.logWarn(false, S.L("Invalid name for method: %s"), var9.getSignature(true));
                  return false;
               }
            }
         }

         if (!this.RF(var1, false, var3)) {
            return false;
         } else {
            if (var5 != null) {
               var1 = this.getName(true);

               for (int var13 : var5) {
                  if (var13 != var4) {
                     bjp var14 = this.q.gO(var13);
                     if (!var14.q(var1.equals("") ? null : var1, false, var3)) {
                        this.q.logWarn(false, S.L("A method could not be renamed, the unit is left in an inconsistent state: %s"), var14.getSignature(true));
                     }
                  }
               }
            }

            return true;
         }
      }
   }

   private synchronized boolean RF(String var1, boolean var2, boolean var3) {
      if ((var1 != null || this.gO != null) && (var1 == null || !var1.equals(this.gO))) {
         if (var1 != null && var1.isEmpty()) {
            var1 = null;
         }

         String var4 = this.getName();
         if (var1 != null && !DexUtil.isSimpleName(var1, this.q.RF())) {
            RF.warn(S.L("Illegal method name: '%s'"), Formatter.escapeString(var1));
            return false;
         } else {
            if (var1 != null && var1.equals(this.getName(false))) {
               var1 = null;
               if (this.gO == null) {
                  return true;
               }
            }

            if (var1 != null) {
               bjn var5 = this.q.Dw(this.Dw).xK();
               if (var5 != null && var5.gP() != null) {
                  bjk var6 = var5.gP();

                  for (bjy var8 : var6.xK()) {
                     bjp var9 = this.q.gO(var8.getMethodIndex());
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
               this.gO = var1;
               if (var3) {
                  this.q.q(var3, new UnitChangeEventData(1, this, var1, var4));
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public boolean xK() {
      return this.gO != null;
   }

   @Override
   public boolean q() {
      return !JavaUtil.isReservedMethodName(this.getName(false));
   }

   @Override
   public int getClassTypeIndex() {
      return this.Dw;
   }

   public bjt Dw() {
      return this.q.Dw(this.Dw);
   }

   @Override
   public String getClassTypeSignature(boolean var1) {
      return this.q.q(this.Dw, var1);
   }

   @Override
   public int getPrototypeIndex() {
      return this.Uv;
   }

   public bjr Uv() {
      return this.q.Uv(this.Uv);
   }

   public String oW() {
      return this.Uv().generate(true);
   }

   @Override
   public IDexType getReturnType() {
      return this.q.Uv(this.Uv).getReturnType();
   }

   public int gO() {
      return this.q.Uv(this.Uv).getParameterTypeIndexes().length;
   }

   @Override
   public List getParameterTypes() {
      return Arrays.asList(this.q.Uv(this.Uv).getParameterTypes());
   }

   @Override
   public int getNameIndex() {
      return this.oW;
   }

   public String nf() {
      return this.gO;
   }

   public String q(boolean var1) {
      return this.q(var1, true);
   }

   public String q(boolean var1, boolean var2) {
      return this.q.q(this.Dw, var1, var2) + (var2 ? "->" : ".") + this.getName(var1);
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
            var7 = this.q.q(this.Dw, var1, false);
            if (var5.equals("<init>")) {
               var7 = var7 + this.Uv().q(var1, false, false);
            } else if (var5.equals("<clinit>")) {
               var7 = var7 + " {...}";
            } else {
               var7 = var7 + "." + this.getName(var1) + this.Uv().q(var1, false, var3);
            }
         } else {
            var7 = this.q.Dw(this.Dw).getName(var1);
            if (var5.equals("<init>")) {
               var7 = var7 + this.Uv().q(var1, false, false, false);
            } else if (var5.equals("<clinit>")) {
               var7 = var7 + " {...}";
            } else {
               var7 = this.getName(var1) + this.Uv().q(var1, false, var3, false);
            }
         }

         return var7;
      } else {
         return this.q(var1, true) + this.Uv().q(var1, true, var3);
      }
   }

   public String RF(boolean var1) {
      return this.getName(var1) + this.Uv().generate(var1);
   }

   public String xK(boolean var1) {
      return this.getName(var1) + this.Uv().q(var1, true, false);
   }

   @Override
   public String toString() {
      return Strings.ff("Method:#%d,name=%s,address=%s", this.xK, this.getName(true), this.getAddress());
   }

   @Override
   public String getName(boolean var1) {
      return var1 && this.gO != null ? this.gO : this.q.RF(this.oW);
   }

   @Override
   public boolean setName(String var1) {
      return this.q(var1, true);
   }

   public boolean q(String var1, boolean var2) {
      return this.q(var1, true, var2);
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public int getGenericFlags() {
      if (this.nf == null) {
         return 0;
      } else {
         int var1 = DexUtil.convertDexFlagsToCodeFlags(this.nf.getAccessFlags());
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
      return this.nf != null && this.nf.q() != null ? this.nf.q().getInstructions() : null;
   }

   @Override
   public SortedMap getDebugVariablesMap() {
      return this.nf != null && this.nf.q() != null && this.nf.q().getDebugInfo() != null ? this.nf.q().getDebugInfo().getKnownVariablesMap(this) : null;
   }

   @Override
   public String getParameterName(int var1) {
      int var2 = this.q(var1);
      return var2 < 0 ? null : this.q.q(new IdentifierCoordinates(new MethodCoordinates(this.xK), var2));
   }

   @Override
   public boolean setParameterName(int var1, String var2, boolean var3, boolean var4) {
      int var5 = this.q(var1);
      return var5 < 0 ? false : this.q.q(new IdentifierCoordinates(new MethodCoordinates(this.xK), var5), var2, var3, var4) >= 1;
   }

   private int q(int var1) {
      int var2;
      if (this.nf != null && this.nf.RF() != null) {
         int[] var3 = DexUtil.getMethodParameterIndices(this);
         if (!this.nf.isStatic()) {
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
