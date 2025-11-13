package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bft extends com.pnfsoftware.jeb.corei.parsers.dex.Sv implements IDexField, bgc {
   private static final ILogger A = GlobalLog.getLogger(bft.class);
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
   private bfz ys;

   public bft(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, int var3, int var4, int var5) {
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

   void pC(bfz var1) {
      if (this.ys != null && this.ys != var1) {
         this.pC.logWarn(true, S.L("Field %s: FieldData already set to %s, attempting to set to %s"), this.getSignature(false), this.ys, var1);
      }

      if (var1.getFieldIndex() != this.kS) {
         throw new JebRuntimeException(
            Strings.ff("Field %s: Expected field index #%d, but FieldData references #%d", this.getSignature(false), this.kS, var1.getFieldIndex())
         );
      } else {
         this.ys = var1;
      }
   }

   public bfz A() {
      return this.ys;
   }

   @Override
   public boolean isInternal() {
      return this.A() != null;
   }

   @Override
   public boolean isArtificial() {
      return this.kS >= this.pC.oT().kS();
   }

   public boolean pC(String var1) {
      return this.A(var1, true, false);
   }

   public boolean pC(String var1, boolean var2, boolean var3) {
      int var4 = this.getIndex();
      List var5 = null;
      if (var2) {
         bhb var6 = new bhb(this.pC);

         try {
            var5 = var6.A(this);
         } catch (RuntimeException var10) {
            this.pC.logWarn(false, S.L("An error occurred while trying to rename a field: %s"), this.getSignature(true));
            JebCoreService.notifySilentExceptionToClient(var10);
            return false;
         }

         if (!var5.contains(var4)) {
            this.pC.logWarn(false, S.L("An error occurred while trying to rename a field: %s"), this.getSignature(true));
            return false;
         }

         for (int var8 : var5) {
            bft var9 = this.pC.E(var8);
            if (!var9.pC(var1 != null && !var1.equals("") ? var1 : null)) {
               this.pC.logWarn(false, S.L("Invalid name for field: %s"), var9.getSignature(true));
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
                  bft var14 = this.pC.E(var13);
                  if (!var14.pC(var1.equals("") ? null : var1, false, var3)) {
                     this.pC.logWarn(false, S.L("A field could not be renamed, the unit is left in an inconsistent state: %s"), var14.getSignature(true));
                  }
               }
            }
         }

         return true;
      }
   }

   private synchronized boolean A(String var1, boolean var2, boolean var3) {
      if ((var1 != null || this.sY != null) && (var1 == null || !var1.equals(this.sY))) {
         if (var1 != null && var1.isEmpty()) {
            var1 = null;
         }

         String var4 = this.getName();
         if (var1 != null && !DexUtil.isSimpleName(var1, this.pC.pC())) {
            A.warn(S.L("Illegal field name: '%s'"), Formatter.escapeString(var1));
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

                  for (bfz var8 : var6.pC()) {
                     bft var9 = this.pC.E(var8.getFieldIndex());
                     if (var9 != this && (var9.getName(false).equals(var1) || var9.getName(true).equals(var1))) {
                        return false;
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
         return true;
      }
   }

   public boolean kS() {
      return this.sY != null;
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
   public int getFieldTypeIndex() {
      return this.UT;
   }

   public bfy UT() {
      return this.pC.wS(this.UT);
   }

   @Override
   public String getFieldTypeSignature(boolean var1) {
      return this.pC.pC(this.UT, var1);
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
      return this.pC(var1) + ":" + this.pC.pC(this.UT, var1);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return this.getSignature(var1, var2, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2, boolean var3) {
      return this.getSignature(var1, var2, var3, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2, boolean var3, boolean var4) {
      if (!var2) {
         return var4
            ? this.pC(var1, false) + (!var3 ? "" : " : " + this.pC.pC(this.UT, var1, false))
            : this.getName(var1) + (!var3 ? "" : " : " + this.pC.pC(this.UT, var1, false, false));
      } else {
         return this.pC(var1, true) + (!var3 ? "" : ":" + this.pC.pC(this.UT, var1, true));
      }
   }

   public String A(boolean var1) {
      return this.getName(var1) + ":" + this.pC.pC(this.UT, var1);
   }

   @Override
   public String toString() {
      return Strings.ff("Field:#%d,name=%s,address=%s", this.kS, this.getName(true), this.getAddress());
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
   public IDexValue getStaticInitializer() {
      if (this.ys == null) {
         return null;
      } else if (this.wS() == null) {
         return null;
      } else {
         bfs var1 = this.wS().A();
         return var1 == null ? null : DexUtil.getStaticFieldInitializer(var1, this.ys);
      }
   }
}
