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
public class bjo extends com.pnfsoftware.jeb.corei.parsers.dex.CU implements IDexField, bjx {
   private static final ILogger RF = GlobalLog.getLogger(bjo.class);
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
   private bju nf;

   public bjo(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, int var3, int var4, int var5) {
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

   void q(bju var1) {
      if (this.nf != null && this.nf != var1) {
         this.q.logWarn(true, S.L("Field %s: FieldData already set to %s, attempting to set to %s"), this.getSignature(false), this.nf, var1);
      }

      if (var1.getFieldIndex() != this.xK) {
         throw new JebRuntimeException(
            Strings.ff("Field %s: Expected field index #%d, but FieldData references #%d", this.getSignature(false), this.xK, var1.getFieldIndex())
         );
      } else {
         this.nf = var1;
      }
   }

   public bju RF() {
      return this.nf;
   }

   @Override
   public boolean isInternal() {
      return this.RF() != null;
   }

   @Override
   public boolean isArtificial() {
      return this.xK >= this.q.Me().xK();
   }

   public boolean q(String var1) {
      return this.RF(var1, true, false);
   }

   public boolean q(String var1, boolean var2, boolean var3) {
      int var4 = this.getIndex();
      List var5 = null;
      if (var2) {
         bkx var6 = new bkx(this.q);

         try {
            var5 = var6.RF(this);
         } catch (RuntimeException var10) {
            this.q.logWarn(false, S.L("An error occurred while trying to rename a field: %s"), this.getSignature(true));
            JebCoreService.notifySilentExceptionToClient(var10);
            return false;
         }

         if (!var5.contains(var4)) {
            this.q.logWarn(false, S.L("An error occurred while trying to rename a field: %s"), this.getSignature(true));
            return false;
         }

         for (int var8 : var5) {
            bjo var9 = this.q.oW(var8);
            if (!var9.q(var1 != null && !var1.equals("") ? var1 : null)) {
               this.q.logWarn(false, S.L("Invalid name for field: %s"), var9.getSignature(true));
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
                  bjo var14 = this.q.oW(var13);
                  if (!var14.q(var1.equals("") ? null : var1, false, var3)) {
                     this.q.logWarn(false, S.L("A field could not be renamed, the unit is left in an inconsistent state: %s"), var14.getSignature(true));
                  }
               }
            }
         }

         return true;
      }
   }

   private synchronized boolean RF(String var1, boolean var2, boolean var3) {
      if ((var1 != null || this.gO != null) && (var1 == null || !var1.equals(this.gO))) {
         if (var1 != null && var1.isEmpty()) {
            var1 = null;
         }

         String var4 = this.getName();
         if (var1 != null && !DexUtil.isSimpleName(var1, this.q.RF())) {
            RF.warn(S.L("Illegal field name: '%s'"), Formatter.escapeString(var1));
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

                  for (bju var8 : var6.q()) {
                     bjo var9 = this.q.oW(var8.getFieldIndex());
                     if (var9 != this && (var9.getName(false).equals(var1) || var9.getName(true).equals(var1))) {
                        return false;
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
         return true;
      }
   }

   public boolean xK() {
      return this.gO != null;
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
   public int getFieldTypeIndex() {
      return this.Uv;
   }

   public bjt Uv() {
      return this.q.Dw(this.Uv);
   }

   @Override
   public String getFieldTypeSignature(boolean var1) {
      return this.q.q(this.Uv, var1);
   }

   @Override
   public int getNameIndex() {
      return this.oW;
   }

   public String oW() {
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
      return this.q(var1) + ":" + this.q.q(this.Uv, var1);
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
            ? this.q(var1, false) + (!var3 ? "" : " : " + this.q.q(this.Uv, var1, false))
            : this.getName(var1) + (!var3 ? "" : " : " + this.q.q(this.Uv, var1, false, false));
      } else {
         return this.q(var1, true) + (!var3 ? "" : ":" + this.q.q(this.Uv, var1, true));
      }
   }

   public String RF(boolean var1) {
      return this.getName(var1) + ":" + this.q.q(this.Uv, var1);
   }

   @Override
   public String toString() {
      return Strings.ff("Field:#%d,name=%s,address=%s", this.xK, this.getName(true), this.getAddress());
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
   public IDexValue getStaticInitializer() {
      if (this.nf == null) {
         return null;
      } else if (this.Dw() == null) {
         return null;
      } else {
         bjn var1 = this.Dw().xK();
         return var1 == null ? null : DexUtil.getStaticFieldInitializer(var1, this.nf);
      }
   }
}
