package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Ser
public abstract class eW implements gb {
   @SerId(1)
   int kS;
   @SerId(value = 2, deprecated = true)
   @Deprecated
   private boolean pC;
   @SerId(value = 3, deprecated = true)
   @Deprecated
   private Map A = new HashMap();
   @SerId(value = 4, deprecated = true)
   @Deprecated
   private Map ld = new HashMap();
   @SerId(value = 5, deprecated = true)
   @Deprecated
   private int gp;
   @SerId(6)
   eW.Sv wS;
   @SerId(7)
   private Boolean oT;
   @SerId(9)
   int UT;
   @SerId(10)
   Set E = new HashSet();
   @SerId(11)
   Set sY = new HashSet();
   @SerId(12)
   String[] ys;

   public eW(int var1) {
      this.kS = var1;
   }

   @Override
   public int A() {
      return this.kS;
   }

   public eW.Sv UT() {
      return this.wS;
   }

   public void pC(Boolean var1) {
      this.oT = var1;
   }

   public Boolean E() {
      return this.oT;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public eW.Av sY() {
      if (this.wS == null || this.oT == null) {
         return null;
      } else if (this.oT) {
         switch (this.wS) {
            case pC:
               return eW.Av.pC;
            case A:
               return eW.Av.A;
            case kS:
               return eW.Av.kS;
            default:
               throw new RuntimeException();
         }
      } else {
         return eW.Av.wS;
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.kS;
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
         eW var2 = (eW)var1;
         return this.kS == var2.kS;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X(%d)(%s)", this.kS, this.UT, this.wS);
   }

   @Ser
   public static enum Av {
      pC,
      A,
      kS,
      wS;
   }

   @Ser
   public static enum Sv {
      pC,
      A,
      kS;
   }
}
