package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bjt;

public class iZ implements IDexAddress {
   public static final int q = DexReferenceType.UNKNOWN.getId();
   public static final int RF = DexReferenceType.PURE.getId();
   public static final int xK = DexReferenceType.META.getId();
   public static final int Dw = DexReferenceType.GET.getId();
   public static final int Uv = DexReferenceType.SET.getId();
   public static final int oW = DexReferenceType.INVOKE.getId();
   public static final int gO = DexReferenceType.GETTER.getId();
   public static final int nf = DexReferenceType.SETTER.getId();
   public static final int gP = DexReferenceType.INVOKER.getId();
   public static final int za = DexReferenceType.ALLOC.getId();
   public static final int lm = DexReferenceType.GET_REFLECTED.getId();
   public static final int zz = DexReferenceType.SET_REFLECTED.getId();
   public static final int JY = DexReferenceType.INVOKE_REFLECTED.getId();
   private String HF;
   private String LK;
   private long io;
   private String qa;
   private int Hk;

   public iZ(String var1) {
      this.HF = var1;
   }

   public iZ(long var1, String var3, int var4) {
      this.io = var1;
      this.qa = var3;
      this.Hk = var4;
   }

   public iZ(long var1, String var3) {
      this.io = var1;
      this.qa = var3;
   }

   public iZ(long var1) {
      this(var1, null);
   }

   public iZ(oM.eo var1) {
      this(var1.q, null, var1.RF);
   }

   public iZ(oM.eo var1, String var2) {
      this(var1.q, var2, var1.RF);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.io ^ this.io >>> 32);
      var1 = 31 * var1 + this.Hk;
      var1 = 31 * var1 + (this.qa == null ? 0 : this.qa.hashCode());
      var1 = 31 * var1 + (this.HF == null ? 0 : this.HF.hashCode());
      return 31 * var1 + (this.LK == null ? 0 : this.LK.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         iZ var2 = (iZ)var1;
         if (this.io != var2.io) {
            return false;
         } else if (this.Hk != var2.Hk) {
            return false;
         } else {
            if (this.qa == null) {
               if (var2.qa != null) {
                  return false;
               }
            } else if (!this.qa.equals(var2.qa)) {
               return false;
            }

            if (this.HF == null) {
               if (var2.HF != null) {
                  return false;
               }
            } else if (!this.HF.equals(var2.HF)) {
               return false;
            }

            if (this.LK == null) {
               if (var2.LK != null) {
                  return false;
               }
            } else if (!this.LK.equals(var2.LK)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String getInternalAddress() {
      return this.HF;
   }

   @Override
   public String getUserAddress() {
      return this.LK;
   }

   @Override
   public long getEncodedAddress() {
      return this.io;
   }

   @Override
   public String getInfo() {
      return this.qa;
   }

   @Override
   public int getFlags() {
      return this.Hk;
   }

   @Override
   public DexReferenceType getReferenceType() {
      return DexReferenceType.fromId(this.Hk & 0xFF);
   }

   @Override
   public String toString() {
      return this.HF == null ? "?" : this.HF;
   }

   void q(bK var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         int var2 = (int)(this.io >>> 56);
         int var3 = (int)(this.io >> 32 & 16777215L);
         if (var2 == 3) {
            bjt var4 = var1.Dw(var3);
            this.HF = var4.getSignature(true);
            this.LK = var4.getSignature(true, false);
         } else if (var2 == 4) {
            bjn var6 = var1.nf(var3);
            this.HF = var6.getSignature(true);
            this.LK = var6.getSignature(true, false);
         } else if (var2 == 5) {
            bjo var7 = var1.oW(var3);
            this.HF = var7.getSignature(true);
            this.LK = var7.getSignature(true, false);
         } else if (var2 == 6) {
            bjp var8 = var1.gO(var3);
            this.HF = var8.getSignature(true);
            this.LK = var8.getSignature(true, false, false);
         } else if (var2 == 7) {
            int var9 = (int)this.io;
            bjp var5 = var1.gO(var3);
            this.HF = Strings.ff("%s+%Xh", var5.getSignature(true), var9);
            this.LK = Strings.ff("%s+%Xh", var5.getSignature(true, false, false), var9);
         }
      }
   }
}
