package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Ser
public abstract class qx implements ME {
   @SerId(1)
   int oW;
   @SerId(value = 2, deprecated = true)
   @Deprecated
   private boolean Dw;
   @SerId(value = 3, deprecated = true)
   @Deprecated
   private Map Uv = new HashMap();
   @SerId(value = 4, deprecated = true)
   @Deprecated
   private Map zz = new HashMap();
   @SerId(value = 5, deprecated = true)
   @Deprecated
   private int JY;
   @SerId(6)
   qx.CU gO;
   @SerId(7)
   private Boolean HF;
   @SerId(9)
   int nf;
   @SerId(10)
   Set gP = new HashSet();
   @SerId(11)
   Set za = new HashSet();
   @SerId(12)
   String[] lm;

   public qx(int var1) {
      this.oW = var1;
   }

   @Override
   public int Dw() {
      return this.oW;
   }

   public qx.CU gO() {
      return this.gO;
   }

   public void q(Boolean var1) {
      this.HF = var1;
   }

   public Boolean nf() {
      return this.HF;
   }

   public qx.eo gP() {
      if (this.gO == null || this.HF == null) {
         return null;
      } else if (this.HF) {
         switch (this.gO) {
            case q:
               return qx.eo.q;
            case RF:
               return qx.eo.RF;
            case xK:
               return qx.eo.xK;
            default:
               throw new RuntimeException();
         }
      } else {
         return qx.eo.Dw;
      }
   }

   public boolean za() {
      return this.nf == 2;
   }

   public String lm() {
      return this.lm != null && this.lm.length != 0 ? this.lm[0] : null;
   }

   public String[] zz() {
      return this.lm;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.oW;
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
         qx var2 = (qx)var1;
         return this.oW == var2.oW;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X(%d)(%s)", this.oW, this.nf, this.gO);
   }

   @Ser
   public static enum CU {
      q,
      RF,
      xK;
   }

   @Ser
   public static enum eo {
      q,
      RF,
      xK,
      Dw;
   }
}
