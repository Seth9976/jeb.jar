package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfy;

public class DH implements IDexAddress {
   public static final int pC = DexReferenceType.UNKNOWN.getId();
   public static final int A = DexReferenceType.PURE.getId();
   public static final int kS = DexReferenceType.META.getId();
   public static final int wS = DexReferenceType.GET.getId();
   public static final int UT = DexReferenceType.SET.getId();
   public static final int E = DexReferenceType.INVOKE.getId();
   public static final int sY = DexReferenceType.GETTER.getId();
   public static final int ys = DexReferenceType.SETTER.getId();
   public static final int ld = DexReferenceType.INVOKER.getId();
   public static final int gp = DexReferenceType.ALLOC.getId();
   public static final int oT = DexReferenceType.GET_REFLECTED.getId();
   public static final int fI = DexReferenceType.SET_REFLECTED.getId();
   public static final int WR = DexReferenceType.INVOKE_REFLECTED.getId();
   private String NS;
   private String vP;
   private long xC;
   private String ED;
   private int Sc;

   public DH(String var1) {
      this.NS = var1;
   }

   public DH(long var1, String var3, int var4) {
      this.xC = var1;
      this.ED = var3;
      this.Sc = var4;
   }

   public DH(bO.Av var1, String var2) {
      this(var1.pC, var2, var1.A);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.xC ^ this.xC >>> 32);
      var1 = 31 * var1 + this.Sc;
      var1 = 31 * var1 + (this.ED == null ? 0 : this.ED.hashCode());
      var1 = 31 * var1 + (this.NS == null ? 0 : this.NS.hashCode());
      return 31 * var1 + (this.vP == null ? 0 : this.vP.hashCode());
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
         DH var2 = (DH)var1;
         if (this.xC != var2.xC) {
            return false;
         } else if (this.Sc != var2.Sc) {
            return false;
         } else {
            if (this.ED == null) {
               if (var2.ED != null) {
                  return false;
               }
            } else if (!this.ED.equals(var2.ED)) {
               return false;
            }

            if (this.NS == null) {
               if (var2.NS != null) {
                  return false;
               }
            } else if (!this.NS.equals(var2.NS)) {
               return false;
            }

            if (this.vP == null) {
               if (var2.vP != null) {
                  return false;
               }
            } else if (!this.vP.equals(var2.vP)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String getInternalAddress() {
      return this.NS;
   }

   @Override
   public String getUserAddress() {
      return this.vP;
   }

   @Override
   public long getEncodedAddress() {
      return this.xC;
   }

   @Override
   public String getInfo() {
      return this.ED;
   }

   @Override
   public int getFlags() {
      return this.Sc;
   }

   @Override
   public DexReferenceType getReferenceType() {
      return DexReferenceType.fromId(this.Sc & 0xFF);
   }

   @Override
   public String toString() {
      return this.NS == null ? "?" : this.NS;
   }

   void pC(vi var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         int var2 = (int)(this.xC >>> 56);
         int var3 = (int)(this.xC >> 32 & 16777215L);
         if (var2 == 3) {
            bfy var4 = var1.wS(var3);
            this.NS = var4.getSignature(true);
            this.vP = var4.getSignature(true, false);
         } else if (var2 == 4) {
            bfs var6 = var1.ys(var3);
            this.NS = var6.getSignature(true);
            this.vP = var6.getSignature(true, false);
         } else if (var2 == 5) {
            bft var7 = var1.E(var3);
            this.NS = var7.getSignature(true);
            this.vP = var7.getSignature(true, false);
         } else if (var2 == 6) {
            bfu var8 = var1.sY(var3);
            this.NS = var8.getSignature(true);
            this.vP = var8.getSignature(true, false, false);
         } else if (var2 == 7) {
            int var9 = (int)this.xC;
            bfu var5 = var1.sY(var3);
            this.NS = Strings.ff("%s+%Xh", var5.getSignature(true), var9);
            this.vP = Strings.ff("%s+%Xh", var5.getSignature(true, false, false), var9);
         }
      }
   }
}
